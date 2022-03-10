package com.gsd.sreenidhi.admin.workbench;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogCategoryForm;
import com.gsd.sreenidhi.admin.blogs.BlogController;
import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.settings.cloud.RulesForm;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsActions;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsToolsForm;
import com.gsd.sreenidhi.admin.settings.devops.ToolConfigForm;
import com.gsd.sreenidhi.job.JobService;
import com.gsd.sreenidhi.job.JobServiceActions;
import com.gsd.sreenidhi.job.JobServiceStatusForm;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;

@Controller
public class LabController {

	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public LabController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value = "/user/userLab", method = RequestMethod.GET)
	public ModelAndView getWorkbench(HttpSession session) throws IOException {
		String GET_WORKBENCH_SQL = "SELECT ID, TX_WORKBENCH_KEY, TX_WORKBENCH_NAME, ID_USER FROM USER_WORKBENCH WHERE ID_USER = " + session.getAttribute("userId");
		
		List<WorkbenchForm> workbenchList = this.jdbcTemplate.query(GET_WORKBENCH_SQL, new RowMapper<WorkbenchForm>() {
			public WorkbenchForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				WorkbenchForm workbench = new WorkbenchForm();
				workbench.setID(resulSet.getInt("ID"));
				workbench.setTX_WORKBENCH_KEY(resulSet.getString("TX_WORKBENCH_KEY"));
				workbench.setTX_WORKBENCH_NAME(resulSet.getString("TX_WORKBENCH_NAME"));
				workbench.setID_USER(resulSet.getString("ID_USER"));
				return workbench;
			}
		});
		
		DevOpsActions da = new DevOpsActions();
		AWSActions aa = new AWSActions();
		List<RulesForm> defaultRuleList = aa.getDefaultRule(jdbcTemplate);
		List<DevOpsToolsForm> dToolList ;
		if(defaultRuleList!=null && defaultRuleList.size()>0) {
			dToolList = da.getDOCatTools(jdbcTemplate,"active");
		}else {
			dToolList = null;
		}
		
		LabActions la = new LabActions();
		List<UserServicesForm> userServicesList = la.getUserServices(jdbcTemplate, session.getAttribute("userId").toString(), null);
		List<ToolConfigForm> servicesConfigList = la.getServiceConfig(jdbcTemplate, userServicesList);
		
		
		ModelAndView mv = new ModelAndView("/user/userLab");
		mv.addObject("wbList",workbenchList);
		mv.addObject("srvcTools",dToolList);
		mv.addObject("userServicesList", userServicesList);
		mv.addObject("srvcConfList",servicesConfigList);
		return mv;
	}
	
	@RequestMapping(value = "/user/addWorkbench", method = RequestMethod.POST)
	public ModelAndView addWorkbench(@ModelAttribute("workbenchBean") @Valid WorkbenchForm workbenchBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String ADD_WORKBENCH_SQL = "INSERT INTO USER_WORKBENCH (TX_WORKBENCH_KEY, TX_WORKBENCH_NAME, ID_USER) VALUES (?,?,?)";
		String workbenchKey = Generator.randomString(32);
		
		final KeyHolder benchKey = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_WORKBENCH_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, workbenchKey);
				preparedStatement.setString(2, workbenchBean.getTX_WORKBENCH_NAME());
				preparedStatement.setString(3, session.getAttribute("userId").toString());
				
				return preparedStatement;
			}
		}, benchKey);
		
		int workBenchID = benchKey.getKey().intValue();
		
		ModelAndView mv = new ModelAndView("redirect:/user/userLab");
		return mv;
	}
	
	@RequestMapping(value = "/user/deleteWorkbench", method = RequestMethod.POST)
	public ModelAndView addWorkbench(@RequestParam("id") int id, final HttpSession session) {
		
		String DEL_WORKBENCH_SQL = "DELETE FROM USER_WORKBENCH WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DEL_WORKBENCH_SQL);
				preparedStatement.setInt(1, id);
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/user/userLab");
		return mv;
	}
	
	@RequestMapping(value = "/userLab/createService", method = RequestMethod.POST)
	public ModelAndView createService(@ModelAttribute("userServicesBean") @Valid UserServicesForm userServicesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) throws IOException, InterruptedException {
		
		String CREATE_LAB_SERVICE_SQL = "INSERT INTO LAB_SERVICE(TX_LAB_SRVC_KEY, TX_INFRA_TYPE, ID_INFRASTRUCTURE, ID_INFRA_SRVC_TYPE, "
				+ "ID_SERVICE_TOOL, TX_PORT, TX_ID_CONTAINER, FL_SERVICE_STATUS, TX_SERVICE_URL, ID_USER_WORKBENCH, ID_USER, TS_TIMESTAMP_CREATE, FL_INITIALIZED) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		userServicesBean.setTX_LAB_SRVC_KEY(Generator.randomString(16));
		final KeyHolder svcKey = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LAB_SERVICE_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, userServicesBean.getTX_LAB_SRVC_KEY());
				preparedStatement.setString(2, "AWS"); //AWS, GCP, AZURE
				preparedStatement.setInt(3, 0); //ID of the COMPUTE_ENGINE_EC2
				preparedStatement.setInt(4, 2); //OS, Docker, Kubernetes
				preparedStatement.setInt(5, userServicesBean.getID_SRVC_TOOL());
				preparedStatement.setString(6, null); //Port
				preparedStatement.setString(7, null); //Container
				preparedStatement.setInt(8, 0); // available status flag
				preparedStatement.setString(9,null); //url
				preparedStatement.setInt(10,userServicesBean.getID_WORKBENCH());
				preparedStatement.setInt(11,Integer.parseInt(session.getAttribute("userId").toString()));
				preparedStatement.setString(12,CalendarUtils.dateToStringDateTimeReadable(new Date()));
				preparedStatement.setInt(13,0); //initialized status
				return preparedStatement;
			}
		}, svcKey);
		userServicesBean.setID_SERVICE(svcKey.getKey().intValue());
		
		JobServiceActions jobSrvcAct = new JobServiceActions();
		//Job for Infrastructure
		jobSrvcAct.addToQueue(jdbcTemplate,"AWS",0,"AWS_INFASTRUCTURE", userServicesBean.getTX_LAB_SRVC_KEY(), "CHECK", "QUEUE", CalendarUtils.dateToStringDateTimeReadable(new Date()), session.getAttribute("userId").toString());
		//Job for service
		jobSrvcAct.addToQueue(jdbcTemplate,null,userServicesBean.getID_SERVICE(),"LAB_SERVICE", userServicesBean.getTX_LAB_SRVC_KEY(), "CHECK", "QUEUE", CalendarUtils.dateToStringDateTimeReadable(new Date()), session.getAttribute("userId").toString());
		
		//jobSrvc.addToQueue(jdbcTemplate,null,userServicesBean.getID_SRVC_TOOL(),"DEVOPS_TOOLS", null, "CHECK", "QUEUE", CalendarUtils.dateToStringDateTimeReadable(new Date()), session.getAttribute("userId").toString());
		
		String messageType = "message";
		String messageContent = "";
		/*
		 * if(ip==null) { messageType="failuremessage";
		 * messageContent="Failed to configure your infrastructure. Please contact Admininstrator."
		 * ; }
		 */
		ModelAndView mv = new ModelAndView("redirect:/user/userLab",messageType,messageContent);
		return mv;
	}
	
	@RequestMapping("/userLab/validateService")
	public @ResponseBody String validateService(@RequestParam("key") String key,  HttpSession session) {
		logger.info("ajax called validateService: " + key);
		JobServiceActions jsa = new JobServiceActions();
		String status = jsa.checkServiceStatus(session, jdbcTemplate,key);
		return status;
	}
	
	@RequestMapping("/userLab/getServiceInitializationStatus")
	public @ResponseBody List<JobServiceStatusForm> getServiceStatus(@RequestParam("key") String key,  HttpSession session) {
		logger.info("ajax called ServiceInitialization: " + key);
		JobServiceActions jsa = new JobServiceActions();
		List<JobServiceStatusForm> status = jsa.getServiceStatus(jdbcTemplate,key, null, "ASC");
		return status;
	}
	
	@RequestMapping("/userLab/checkExistingWorkbench")
	public @ResponseBody String checkExistingWorkbench(@RequestParam("key") String key,  HttpSession session) {
		logger.info("ajax called checkExistingWorkbench: " + key);
		JobServiceActions jsa = new JobServiceActions();
		String status = jsa.checkExistingWorkbench(session, jdbcTemplate,key);
		return status;
	}
	
}
