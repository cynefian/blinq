package com.gsd.sreenidhi.admin.settings.devops;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.CloudAuthForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.JSONUtils;

@Controller
public class DevOpsSettingsController {

	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public DevOpsSettingsController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value = "/admin/settings/devops/categories", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView getDevOpsCategories(HttpSession session) {

		DevOpsActions da = new DevOpsActions();
		List<DevOpsCategoriesForm> dCatList = da.getDOCategories(jdbcTemplate);

		String mainTab = "Application";
		String subTab = "Categories";
		ModelAndView mv = new ModelAndView("/admin/settings/devops/categories");
		mv.addObject("mainTab", mainTab);
		mv.addObject("subTab", subTab);
		mv.addObject("catList", dCatList);
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/categories/updateCategory", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateDevOpsCategory(HttpSession session, @RequestParam("id") final String id,
			@RequestParam("value") final int value) {

		String UPDATE_DEVOPS_CATEGORIES_SQL = " UPDATE DEVOPS_CATEGORIES SET FL_STATUS = ? WHERE ID = ?";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVOPS_CATEGORIES_SQL);
				preparedStatement.setInt(1, value == 1 ? 0 : 1);
				preparedStatement.setString(2, id);
				return preparedStatement;
			}
		});

		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/categories");
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/categories/addCategory", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView addDevOpsCategory(
			@ModelAttribute("devOpsCategoriesBean") @Valid DevOpsCategoriesForm devOpsCategoriesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, final HttpSession session) {

		String ADD_DEVOPS_CATEGORIES_SQL = "IF NOT EXISTS(SELECT * FROM DEVOPS_CATEGORIES WHERE TX_CAT_CODE = ? ) "
				+ " INSERT INTO DEVOPS_CATEGORIES (TX_CATEGORY, TX_CAT_CODE, TX_DESCRIPTION, FL_STATUS, TS_TIMESTAMP) VALUES (?,?,?,?,?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEVOPS_CATEGORIES_SQL);
				preparedStatement.setString(1, devOpsCategoriesBean.getTX_CAT_CODE());
				preparedStatement.setString(2, devOpsCategoriesBean.getTX_CATEGORY());
				preparedStatement.setString(3, devOpsCategoriesBean.getTX_CAT_CODE());
				preparedStatement.setString(4, devOpsCategoriesBean.getTX_DESCRIPTION());
				preparedStatement.setInt(5, 1);
				preparedStatement.setString(6, CalendarUtils.dateToStringDateTimeReadable(new Date()));
				return preparedStatement;
			}
		});

		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/categories");
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/tools", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView chefProvisioningSetings(HttpSession session) {

		DevOpsActions da = new DevOpsActions();
		List<DevOpsCategoriesForm> dCatList = da.getDOCategories(jdbcTemplate);
		List<DevOpsToolsForm> dToolList = da.getDOCatTools(jdbcTemplate, null);

		String mainTab = "Application";
		String subTab = "DevOps";
		ModelAndView mv = new ModelAndView("/admin/settings/devops/tools");
		mv.addObject("mainTab", mainTab);
		mv.addObject("subTab", subTab);
		mv.addObject("catList", dCatList);
		mv.addObject("toolList", dToolList);
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/tools/updateTool", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateDevOpsTool(HttpSession session, @RequestParam("id") final String id,
			@RequestParam("value") final int value) {

		String UPDATE_DEVOPS_CATEGORIES_SQL = " UPDATE DEVOPS_TOOLS SET FL_STATUS = ? WHERE ID = ?";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVOPS_CATEGORIES_SQL);
				preparedStatement.setInt(1, value == 1 ? 0 : 1);
				preparedStatement.setString(2, id);
				return preparedStatement;
			}
		});

		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/tools");
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/tools/addTool", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView addDevOpsTool(@ModelAttribute("devOpsToolsBean") @Valid DevOpsToolsForm devOpsToolsBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, final HttpSession session) {

		DevOpsActions da = new DevOpsActions();
		int toolKey;

		toolKey = da.getToolByName(jdbcTemplate, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());

		if (toolKey == 0) {

			String ADD_DEVOPS_TOOL_SQL = "IF NOT EXISTS(SELECT * FROM DEVOPS_TOOLS WHERE TX_TOOL_NAME = ? ) "
					+ " INSERT INTO DEVOPS_TOOLS (TX_TOOL_NAME, TX_TOOL_IMAGE, TX_DOWNLOAD_LOC, FL_STATUS, TS_TIMESTAMP, TX_PORT) VALUES (?,?,?,?,?,?)";

			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEVOPS_TOOL_SQL);
					preparedStatement.setString(1, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());
					preparedStatement.setString(2, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());
					preparedStatement.setString(3,
							devOpsToolsBean.getTX_DEVOPS_TOOL_IMAGE().trim().equalsIgnoreCase("") ? null
									: devOpsToolsBean.getTX_DEVOPS_TOOL_IMAGE().trim());
					preparedStatement.setString(4,
							devOpsToolsBean.getTX_DEVOPS_TOOL_DOWNLOAD_LOC().trim().equalsIgnoreCase("") ? null
									: devOpsToolsBean.getTX_DEVOPS_TOOL_DOWNLOAD_LOC().trim());
					preparedStatement.setInt(5, 1);
					preparedStatement.setString(6, CalendarUtils.dateToStringDateTimeReadable(new Date()));
					preparedStatement.setInt(7, devOpsToolsBean.getTX_PORT());
					return preparedStatement;
				}
			});

			toolKey = da.getToolByName(jdbcTemplate, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());

			String ADD_DEVOPS_CATEGORY_TOOL_LINK_SQL = "IF NOT EXISTS(SELECT * FROM DEVOPS_CATEGORY_TOOL_LINK WHERE ID_DEVOPS_TOOL = (SELECT ID FROM DEVOPS_TOOLS WHERE TX_TOOL_NAME = ? ) ) "
					+ " INSERT INTO DEVOPS_CATEGORY_TOOL_LINK (ID_DEVOPS_CATEGORY, ID_DEVOPS_TOOL) VALUES (?,(SELECT ID FROM DEVOPS_TOOLS WHERE TX_TOOL_NAME = ? ))";

			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(ADD_DEVOPS_CATEGORY_TOOL_LINK_SQL);
					preparedStatement.setString(1, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());
					preparedStatement.setInt(2, devOpsToolsBean.getID_CATEGORY());
					preparedStatement.setString(3, devOpsToolsBean.getTX_DEVOPS_TOOL_NAME());
					return preparedStatement;
				}
			});

			JSONUtils ju = new JSONUtils();
			if (devOpsToolsBean.getTX_CONFIG() != null) {
				ObjectMapper mapper = new ObjectMapper();
				ToolConfigForm[] config;
				try {
					config = mapper.readValue(devOpsToolsBean.getTX_CONFIG(), ToolConfigForm[].class);
					for (int i = 0; i < config.length; i++) {
						
						da.insertToolConfig(jdbcTemplate,config[i], i, toolKey);
					}
					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			}
		}

		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/tools");
		return mv;
	}

	@RequestMapping(value = "/admin/settings/devops/tools/updateToolConfig", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateToolConfig(@ModelAttribute("devOpsToolsBean") @Valid DevOpsToolsForm devOpsToolsBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, final HttpSession session) {

		DevOpsActions da = new DevOpsActions();
		
		if (devOpsToolsBean.getTX_CONFIG() != null) {
			ObjectMapper mapper = new ObjectMapper();
			ToolConfigForm[] config;
			try {
				config = mapper.readValue(devOpsToolsBean.getTX_CONFIG(), ToolConfigForm[].class);
				for (int i = 0; i < config.length; i++) {
					final int itr = i;
					if("DELETE".equalsIgnoreCase(config[i].getACTION())) {
						String DELETE_CONFIG = "DELETE FROM DEVOPS_TOOL_CONFIG WHERE ID = ? " ;
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONFIG);
								preparedStatement.setInt(1,  config[itr].getID());
								return preparedStatement;
							}
						});
						
						String DELETE_CONFIG_LINK = "DELETE FROM DEVOPS_TOOL_CONFIG_LINK WHERE ID_DEVOPS_TOOL_CONFIG =?  " ;
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONFIG_LINK);
								preparedStatement.setInt(1,  config[itr].getID());
								return preparedStatement;
							}
						});
					}if("INSERT".equalsIgnoreCase(config[i].getACTION())) {
						da.insertToolConfig(jdbcTemplate,config[i], i, config[i].getID_DEVOPS_TOOL());
					}else {
						String UPDATE_CONFIG = "UPDATE DEVOPS_TOOL_CONFIG SET TX_COMMAND = ?, TX_FILE = ? WHERE ID = ?";
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONFIG);
								preparedStatement.setString(1,  config[itr].getTX_COMMAND()==null?"":config[itr].getTX_COMMAND());
								preparedStatement.setString(2,  config[itr].getTX_FILE()==null?"":config[itr].getTX_FILE());
								preparedStatement.setInt(3,  config[itr].getID());
								return preparedStatement;
							}
						});
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/tools");
		return mv;
	}
	@RequestMapping(value = "/admin/settings/devops/tools/updateToolInfo", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateDevOpsToolInfo(@ModelAttribute("devOpsToolsBean") @Valid DevOpsToolsForm devOpsToolsBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, final HttpSession session) {

		String UPDATE_DEVOPS_TOOL = "UPDATE DEVOPS_TOOLS SET TX_TOOL_IMAGE = ?, TX_DOWNLOAD_LOC = ?, TX_PORT = ? WHERE ID = ? ";

		String img = (devOpsToolsBean.getTX_DEVOPS_TOOL_IMAGE() == null
				|| "".equalsIgnoreCase(devOpsToolsBean.getTX_DEVOPS_TOOL_IMAGE().trim())) ? ""
						: devOpsToolsBean.getTX_DEVOPS_TOOL_IMAGE();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVOPS_TOOL);
				preparedStatement.setString(1, img);
				preparedStatement.setString(2, devOpsToolsBean.getTX_DEVOPS_TOOL_DOWNLOAD_LOC());
				preparedStatement.setInt(3, devOpsToolsBean.getTX_PORT());
				preparedStatement.setInt(4, devOpsToolsBean.getID_DEVOPS_TOOL());
				return preparedStatement;
			}
		});

		String UPDATE_DEVOPS_TOOL_CATEGORY = "UPDATE DEVOPS_CATEGORY_TOOL_LINK SET ID_DEVOPS_CATEGORY = ? WHERE ID_DEVOPS_TOOL = ?";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVOPS_TOOL_CATEGORY);
				preparedStatement.setInt(1, devOpsToolsBean.getID_CATEGORY());
				preparedStatement.setInt(2, devOpsToolsBean.getID_DEVOPS_TOOL());
				return preparedStatement;
			}
		});

		ModelAndView mv = new ModelAndView("redirect:/admin/settings/devops/tools");
		return mv;
	}
}
