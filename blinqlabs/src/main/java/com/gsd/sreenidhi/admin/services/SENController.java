package com.gsd.sreenidhi.admin.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogCategoryForm;
import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.CloudAuthForm;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsActions;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsToolsForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.admin.tickets.WorkLogForm;
import com.gsd.sreenidhi.admin.workbench.AWSActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;
import com.gsd.sreenidhi.admin.workbench.WorkbenchForm;
import com.gsd.sreenidhi.billing.BillingCycleForm;
import com.gsd.sreenidhi.billing.BillingForm;
import com.gsd.sreenidhi.commons.AttachmentForm;
import com.gsd.sreenidhi.commons.serviceEntitlement.TierRolloverForm;
import com.gsd.sreenidhi.job.JobServiceActions;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;
import com.gsd.sreenidhi.utils.JSONUtils;

@Controller
@RestController
public class SENController {
	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public SENController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	
	@RequestMapping(value="/admin/updateSENStatus", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView updateSENStatus(HttpSession session, 
			@RequestParam(value="id", required=true) String id, 
			@RequestParam(value="val", required=true) String val) {
		
		final String UPDATE_SERVICE_ENTITLEMENT_SQL = "UPDATE SERVICE_ENTITLEMENT SET FL_ACTIVE = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_ENTITLEMENT_SQL);
				preparedStatement.setString(1, val);
				preparedStatement.setString(2, id);
				return preparedStatement;

			}
		});
		
		String redirection = "redirect:/admin/adminServiceEntitlements";
		return new ModelAndView(redirection);
	}
	
	@RequestMapping(value="/admin/adminServiceEntitlements", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView adminServiceEntitlementsGet(HttpSession session, 
			@RequestParam(value="page", required=false) String page, 
			@RequestParam(value="filter", required=false) String filter) {
		
		return adminServiceEntitlementsPost(session, page, filter);
	}
	
	
	@RequestMapping(value="/admin/SENdetails", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView viewAdminSENDetailsPost(HttpSession session, 
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="source", required=false) String source,
			@RequestParam(value="page", required=false) String page,
			@RequestParam(value="startDate", required=false) String startDate,
			@RequestParam(value="endDate", required=false) String endDate) {
		
		return viewAdminSENDetailsGet(session, id, source, page, startDate, endDate);
	}
	
	@RequestMapping(value="/admin/SENdetails", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView viewAdminSENDetailsGet(HttpSession session, 
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="source", required=false) String source,
			@RequestParam(value="page", required=false) String page,
			@RequestParam(value="startDate", required=false) String startDate,
			@RequestParam(value="endDate", required=false) String endDate) {
		
		EntitlementActions ea = new EntitlementActions();
		List<SENForm> senList = ea.getSENdetails(session, jdbcTemplate, id);
		List<SENRatesForm> senRatesList = ea.getSENRates(session, jdbcTemplate, id);
		List<AttachmentForm> SENAttachFormList = ea.getSENAttachments(session, jdbcTemplate, id, page);
		List<WorkLogForm> senActivityList = ea.getEntitlementActivities(session, jdbcTemplate, id, startDate, endDate);
		List<BillingCycleForm> senBillingList = ea.getEntitlementBillingCycles(session, jdbcTemplate, id);
		
		
		//Retrieving Rollover hours
		List<TierRolloverForm> senRollover = ea.getEntitlementRollOvers(jdbcTemplate, id);
		int availRolloverHours = 0;
		int totalRolloverHours = 0;
		if(senRollover!=null && senRollover.size()>0) {
			for(int i=0;i<senRollover.size();i++) {
				availRolloverHours = availRolloverHours + senRollover.get(i).getNUM_ROLLOVER_BALANCE();
				totalRolloverHours = totalRolloverHours + senRollover.get(i).getNUM_ROLLOVER_ORIG();
			}
		}
		
		
		//Retrieve Current Billing Cycle
		String currentCycleStart = "";
		String currentCycleEnd = "";
		try {
			if(senBillingList!=null && senBillingList.size()>0) {
				for(int i=0;i<senBillingList.size();i++) {
					if((CalendarUtils.compareDates(senBillingList.get(i).getTS_START_DATE(), CalendarUtils.getCurrentDateOnly())<=0)
							&& (CalendarUtils.compareDates(CalendarUtils.getCurrentDateOnly(), senBillingList.get(i).getTS_END_DATE())<=0)) {
						currentCycleStart = senBillingList.get(i).getTS_START_DATE();
						currentCycleEnd = senBillingList.get(i).getTS_END_DATE();
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		//Calculate Hours recorded
		Calendar c = Calendar.getInstance();
 		c.set(0, 0, 0, 0, 0, 0);
 		int hours=0;
 		int mins=0;
 		
 		for(int i=0;i<senActivityList.size();i++) {
 			String log = senActivityList.get(i).getTX_WORKLOG();
 			String[] logarr = log.split(" ");
 			for(int j=0;j<logarr.length;j++) {
 				if(logarr[j].contains("w")) {
 					String unit =logarr[j].replace("w", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*40;
 				}else if(logarr[j].contains("W")) {
 					String unit =logarr[j].replace("W", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*40;
 				}else if(logarr[j].contains("d")) {
 					String unit =logarr[j].replace("d", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*8;
 				}else if(logarr[j].contains("D")) {
 					String unit =logarr[j].replace("D", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*8;
 				}else if(logarr[j].contains("h")) {
 					String unit =logarr[j].replace("h", ""); 
 					hours = hours + Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("H")) {
 					String unit =logarr[j].replace("H", ""); 
 					hours = hours + Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("m")) {
 					String unit =logarr[j].replace("m", "");
 					mins = mins+Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("M")) {
 					String unit =logarr[j].replace("M", ""); 
 					mins = mins+Integer.parseInt(unit.trim());
 				}
 			}
 		}
 		
 		int hourappend = 0;
 		int minremain = 0;
 		if(mins>=60) {
 			hourappend = mins/60;
 			minremain = mins-(hourappend*60);
 		}else {
 			minremain = mins;
 		}
 		
 		hours = hours + hourappend;
 		
 		String totalTime = hours +" hours, "+ minremain + " minutes";
		
 		
 		//Billing Cycle Calculations
 		String currentTier = "";
 		String currentTierCost = ""; 	
 		int bill = 0;
 		int billingHours = 0;
 		String cycleCaltotalTime = "";
 		String billableHour = "";
 		
 		if(!"".equalsIgnoreCase(currentCycleStart)
				&& !"".equalsIgnoreCase(currentCycleEnd)) {
			List<WorkLogForm> cycleActivityList = ea.getEntitlementActivities(session, jdbcTemplate, id, currentCycleStart, currentCycleEnd);
			
			//Calculate Hours recorded
			Calendar cycleCal = Calendar.getInstance();
			cycleCal.set(0, 0, 0, 0, 0, 0);
	 		int cycleCalhours=0;
	 		int cycleCalmins=0;
	 		
	 		for(int i=0;i<cycleActivityList.size();i++) {
	 			String cycleCallog = cycleActivityList.get(i).getTX_WORKLOG();
	 			String[] cycleCallogarr = cycleCallog.split(" ");
	 			for(int j=0;j<cycleCallogarr.length;j++) {
	 				if(cycleCallogarr[j].contains("w")) {
	 					String unit =cycleCallogarr[j].replace("w", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*40;
	 				}else if(cycleCallogarr[j].contains("W")) {
	 					String unit =cycleCallogarr[j].replace("W", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*40;
	 				}else if(cycleCallogarr[j].contains("d")) {
	 					String unit =cycleCallogarr[j].replace("d", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*8;
	 				}else if(cycleCallogarr[j].contains("D")) {
	 					String unit =cycleCallogarr[j].replace("D", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*8;
	 				}else if(cycleCallogarr[j].contains("h")) {
	 					String unit =cycleCallogarr[j].replace("h", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim());
	 				}else if(cycleCallogarr[j].contains("H")) {
	 					String unit =cycleCallogarr[j].replace("H", ""); 
	 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim());
	 				}else if(cycleCallogarr[j].contains("m")) {
	 					String unit =cycleCallogarr[j].replace("m", "");
	 					cycleCalmins = cycleCalmins+Integer.parseInt(unit.trim());
	 				}else if(cycleCallogarr[j].contains("M")) {
	 					String unit =cycleCallogarr[j].replace("M", ""); 
	 					cycleCalmins = cycleCalmins+Integer.parseInt(unit.trim());
	 				}
	 			}
	 		}
	 		
	 		int cycleCalhourappend = 0;
	 		int cycleCalminremain = 0;
	 		if(cycleCalmins>=60) {
	 			cycleCalhourappend = cycleCalmins/60;
	 			cycleCalminremain = cycleCalmins-(cycleCalhourappend*60);
	 		}else {
	 			cycleCalminremain = cycleCalmins;
	 		}
	 		
	 		cycleCalhours = cycleCalhours + cycleCalhourappend;
	 		cycleCaltotalTime = cycleCalhours +" hours, "+ cycleCalminremain + " minutes";
	 		
	 		//billing hours = logged hours - rollover hours
	 		billingHours = cycleCalhours - totalRolloverHours ;
	 		if(billingHours<0) {
	 			bill = 0;
	 		}else{
	 			bill = billingHours;
	 		}
	 		billableHour =  bill +" hours, "+ cycleCalminremain + " minutes";
	 		
	 		if(cycleCalminremain>0) {
	 			bill = bill +1;
	 		}
	 		//Identify current tier
	 		
	 		if(senRatesList!=null && senRatesList.size()>0) {
	 			for(int i=senRatesList.size()-1;i>=0;i--) {
	 				if(bill<=Integer.parseInt(senRatesList.get(i).getTX_LIMIT())) {
	 					currentTier = senRatesList.get(i).getTX_TIER();
	 					currentTierCost = senRatesList.get(i).getTX_COST();
	 				}
	 			}
	 		}
	 		
		}
 		
 		
 		
 		
 		
		int countOnPage = 10;
		int pg;
		if("".equals(page)|| page==null) {
			pg = 1;
		}else {
			pg = Integer.parseInt(page);
		}
		int totalCount =  ea.getSENAttachmentsCount(session, jdbcTemplate, id);
		double rounder = (double)totalCount/(double) countOnPage;
	 	int pages = (int) Math.ceil(rounder);
	 	
		ModelAndView mv = new ModelAndView("admin/service/serviceEntitlementDetails");
		mv.addObject("SENFormList",senList);
		mv.addObject("senRatesList",senRatesList);
		mv.addObject("senAttachList",SENAttachFormList);
		mv.addObject("pageTab",source);
		mv.addObject("senActivityList", senActivityList);
		mv.addObject("senUsageTime", totalTime);
		
		mv.addObject("totalCount", totalCount);
		mv.addObject("currentPage", pg);
		mv.addObject("totalPages",pages);
		mv.addObject("startDate", startDate);
		mv.addObject("endDate", endDate);
		
		mv.addObject("currentCycleStart", currentCycleStart);
		mv.addObject("currentCycleEnd", currentCycleEnd);
		mv.addObject("availRolloverHours",availRolloverHours);
		mv.addObject("totalRolloverHours",totalRolloverHours);
		mv.addObject("cycleCaltotalTime",cycleCaltotalTime);
		mv.addObject("currentTier",currentTier);
		mv.addObject("currentTierCost",currentTierCost);
		mv.addObject("billableHour",billableHour);
		return mv;
	}
	
	
	
	@RequestMapping(value="/admin/adminServiceEntitlements", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView adminServiceEntitlementsPost(HttpSession session, 
			@RequestParam(value="page", required=false) String page, 
			@RequestParam(value="filter", required=false) String filter) {
		
		EntitlementActions ea = new EntitlementActions();
		
		 int countOnPage = 10;
		
		 
		 int pg;
		if(page==null) {
				pg=1;
			}else {
				pg = Integer.parseInt(page);
			}
		 
		int totalCount =  ea.getEntitlementListCount(session, jdbcTemplate, false, page, filter);
		double rounder = (double)totalCount/(double) countOnPage;
	 	int pages = (int) Math.ceil(rounder);
		
		List<SENForm> SENFormList = ea.getEntitlementList(session, jdbcTemplate, false, true, page, filter);
		
		List<ServiceTypeForm> typeList = ea.getEntitlementTypes(jdbcTemplate);
		
		ModelAndView mv = new ModelAndView("admin/service/serviceEntitlements");
		mv.addObject("SENFormList",SENFormList);
		mv.addObject("entitlementTypes", typeList);
		mv.addObject("totalCount", totalCount);
		mv.addObject("currentPage", pg);
		mv.addObject("currentFilter",filter);
		mv.addObject("totalPages",pages);
		return mv;
		
	
	}

	@RequestMapping(value = "/admin/service/SEN/getUserAccount", method = RequestMethod.GET)
	public @ResponseBody List<String> checkExistingWorkbench(@RequestParam("key") String key, HttpSession session) {
		String USER_ACCOUNTS_SQL = "SELECT TX_EMAIL FROM ACCOUNTS WHERE TX_EMAIL LIKE '%" + key + "%'";

		List<UserForm> UserFormList = this.jdbcTemplate.query(USER_ACCOUNTS_SQL, new RowMapper<UserForm>() {
			public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserForm acc = new UserForm();
				acc.setEmail(resulSet.getString("TX_EMAIL"));
				return acc;
			}
		});

		List<String> accounts = new ArrayList();
		String buffer = "";
		if (UserFormList != null) {
			Iterator<UserForm> itrUser = UserFormList.iterator();
			while (itrUser.hasNext()) {
				accounts.add(itrUser.next().getEmail());
			}
		}
		return accounts;
	}

	@RequestMapping(value = "/admin/service/SEN/getEntitlementRollable", method = RequestMethod.GET)
	public @ResponseBody String getEntitlementRollableStatus(@RequestParam("key") String key, HttpSession session) {
		String USER_ACCOUNTS_SQL = "SELECT FL_ROLLOVER FROM SERVICE_ENTITLEMENT_TYPE WHERE ID = " + key;

		List<ServiceTypeForm> UserFormList = this.jdbcTemplate.query(USER_ACCOUNTS_SQL,
				new RowMapper<ServiceTypeForm>() {
					public ServiceTypeForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						ServiceTypeForm acc = new ServiceTypeForm();
						acc.setFL_ALLOW_ROLLOVER(resulSet.getInt("FL_ROLLOVER") == 1 ? true : false);
						return acc;
					}
				});

		return UserFormList.get(0).isFL_ALLOW_ROLLOVER() == true ? "TRUE" : "FALSE";
	}
	
	
	
	
	
	public boolean getEntitlementMonthlyBillingStatus(int key, HttpSession session) {
		String USER_ACCOUNTS_SQL = "SELECT FL_MONTHLY_BILLING FROM SERVICE_ENTITLEMENT_TYPE WHERE ID = " + key;

		List<ServiceTypeForm> UserFormList = this.jdbcTemplate.query(USER_ACCOUNTS_SQL,
				new RowMapper<ServiceTypeForm>() {
					public ServiceTypeForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						ServiceTypeForm acc = new ServiceTypeForm();
						acc.setFL_MONTHLY_BILLING(resulSet.getInt("FL_MONTHLY_BILLING") == 1 ? true: false);
						return acc;
					}
				});

		return UserFormList.get(0).isFL_MONTHLY_BILLING();
	}
	
	
	
	

	@RequestMapping(value = "/admin/addServiceEntitlement", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView newServiceTypeSettings(@ModelAttribute("senBean") @Valid SENForm senBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, final HttpSession session) {

		
		String dt_end = "";
		if ("DEFINED".equalsIgnoreCase(senBean.getTX_DURATION())) {
			dt_end = senBean.getTS_END();
		} else if (senBean.getTX_DURATION().contains("YEAR")) {
			try {
				dt_end = CalendarUtils.addYears(senBean.getTS_START(),
						Integer.parseInt(senBean.getTS_END().substring(0, 1).trim()), "yyyy-MM-dd");
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		} else if (senBean.getTX_DURATION().contains("MONTH")) {
			try {
				dt_end = CalendarUtils.addMonths(senBean.getTS_START(),
						Integer.parseInt(senBean.getTS_END().substring(0, 1).trim()), "yyyy-MM-dd");
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		} else if (senBean.getTX_DURATION().contains("WEEK")) {
			try {
				dt_end = CalendarUtils.addWeeks(senBean.getTS_START(),
						Integer.parseInt(senBean.getTS_END().substring(0, 1).trim()), "yyyy-MM-dd");
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			dt_end = "UNKNOWN";
		}
		
		
		final String endingDt = dt_end;
		final KeyHolder senKey = new GeneratedKeyHolder();
		final String INSERT_SERVICE_ENTITLEMENT_SQL = "INSERT INTO SERVICE_ENTITLEMENT(ID_ACCOUNT,  TX_ENTITLEMENT, TS_START, TX_END_CRITERIA, TS_END, ID_ENTITLEMENT_TYPE, FL_ROLLED, FL_ACTIVE, TX_ENTITLEMENT_DESCRIPTION) VALUES ((SELECT ID FROM ACCOUNTS WHERE TX_EMAIL = ?),?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_ENTITLEMENT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, senBean.getTX_ACCOUNT_EMAIL());
				preparedStatement.setString(2, "SEN-" + Generator.randomString(12));
				preparedStatement.setString(3, senBean.getTS_START());
				preparedStatement.setString(4, senBean.getTX_DURATION());
				preparedStatement.setString(5, endingDt);
				preparedStatement.setInt(6, senBean.getID_ENTITLEMENT_TYPE());
				preparedStatement.setInt(7, senBean.getROLLABLE_FLAG());
				preparedStatement.setInt(8, 1);
				preparedStatement.setString(9, senBean.getTX_ENTITLEMENT_DESCRIPTION());
				return preparedStatement;

			}
		}, senKey);
		final int senKeyId = senKey.getKey().intValue();
		
		boolean monthlyBilling = getEntitlementMonthlyBillingStatus(senBean.getID_ENTITLEMENT_TYPE(), session);
		
		if(monthlyBilling) {
			String startDate = senBean.getTS_START();
			String endDate = endingDt;
		
			List<String> startDates  = new ArrayList<String>();
			List<String> endDates  = new ArrayList<String>();
			
			String ender = endDate;
			
			String INSERT_SEN_BILLING_CYCLE = "INSERT INTO SERVICE_BILLING_CYCLE(ID_ENTITLEMENT, TS_START_DATE, TS_END_DATE, FL_COMPLETE) VALUES(?,?,?,?)";
			
			try {
				while(CalendarUtils.compareDates(startDate, endDate)<=0) {
					startDates.add(startDate);
					final String startDtStr = startDate;
					ender = CalendarUtils.addMonths(startDate,1, "yyyy-MM-dd");
					if(CalendarUtils.compareDates(ender, endDate)>0) {
						ender = endDate;
					}
					endDates.add(ender);
					final String endDtStr = ender;
					startDate = CalendarUtils.addDays(ender, 1, "yyyy-MM-dd");
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SEN_BILLING_CYCLE);
							preparedStatement.setInt(1, senKeyId);
							preparedStatement.setString(2, startDtStr);
							preparedStatement.setString(3, endDtStr);
							preparedStatement.setInt(4, 0);
							return preparedStatement;

						}
					});
					
					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}

		

		String json = senBean.getTX_TIER_JSON();

		JSONUtils ju = new JSONUtils();
		JSONArray arr = ju.getJsonArray(json, "rows");
		for (int i = 0; i < arr.length(); i++) {

			final int indx = i;

			final KeyHolder rateKey = new GeneratedKeyHolder();
			final String INSERT_SERVICE_RATE_SQL = "INSERT INTO SERVICE_ENTITLEMENT_RATES(ID_ENTITLEMENT,  TX_TIER, TX_COST, TX_LIMIT) VALUES (?,?,?,?)";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_RATE_SQL,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, senKeyId);
					preparedStatement.setString(2, arr.getJSONObject(indx).getString("key"));
					preparedStatement.setString(3, arr.getJSONObject(indx).getString("value"));
					preparedStatement.setString(4, arr.getJSONObject(indx).getString("limit"));

					return preparedStatement;
				}
			}, rateKey);
		}

		String redirection = "redirect:/admin/adminServiceEntitlements";
		return new ModelAndView(redirection);

	}
	
	
	@RequestMapping(value = "/admin/uploadSenAttachment", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView newServiceTypeSettings(@ModelAttribute("attachmentForm") @Valid AttachmentForm attachmentForm, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, final HttpSession session) {
		
		final KeyHolder attachKey = new GeneratedKeyHolder();
		final String INSERT_ATTACHMENT_SQL = "INSERT INTO ATTACHMENTS(TX_ATTACHMENT, TX_NAME, TX_TYPE, TX_SIZE) VALUES (?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTACHMENT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, attachmentForm.getTX_DATA());
				preparedStatement.setNString(2, attachmentForm.getTX_NAME());
				preparedStatement.setNString(3, attachmentForm.getTX_TYPE());
				preparedStatement.setNString(4, attachmentForm.getTX_SIZE());
				return preparedStatement;
			}
		}, attachKey);
		
		final int attachId = attachKey.getKey().intValue();
		
		
		final String INSERT_SEN_ATTACHMENT_SQL = "INSERT INTO SERVICE_ENTITLEMENT_ATTACHMENTS(ID_ENTITLEMENT, ID_ATTACHMENT) VALUES (?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SEN_ATTACHMENT_SQL);
				preparedStatement.setInt(1, attachmentForm.getID_LINK());
				preparedStatement.setInt(2, attachId);
				return preparedStatement;
			}
		});
		
		
		
		String redirection = "redirect:/admin/SENdetails?id="+attachmentForm.getID_LINK();
		ModelAndView mv = new ModelAndView(redirection);
		mv.addObject("pageTab","ATTACH");
		return mv;
	}
	
	
	
	@RequestMapping(value="/admin/deleteSenAttachment", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_LICENSE')")
	public ModelAndView adminDeleteSenAttachment(HttpSession session, 
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="sen", required=true) String sen
			) {
		
		
		final String DELETE_SEN_ATTACHMENT_SQL = "DELETE FROM SERVICE_ENTITLEMENT_ATTACHMENTS WHERE ID_ATTACHMENT = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SEN_ATTACHMENT_SQL);
				preparedStatement.setInt(1, Integer.parseInt(id));
				return preparedStatement;
			}
		});
		
		
		final String DELETE_ATTACHMENT_SQL = "DELETE FROM ATTACHMENTS WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTACHMENT_SQL);
				preparedStatement.setInt(1, Integer.parseInt(id));
				return preparedStatement;
			}
		});
		
		String redirection = "redirect:/admin/SENdetails?id="+sen;
		ModelAndView mv = new ModelAndView(redirection);
		mv.addObject("pageTab","ATTACH");
		return mv;
	}
	
	
	
}
