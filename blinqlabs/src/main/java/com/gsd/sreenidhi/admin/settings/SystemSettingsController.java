package com.gsd.sreenidhi.admin.settings;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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

import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.faq.FaqForm;
import com.gsd.sreenidhi.admin.settings.coordination.CoordinationActions;
import com.gsd.sreenidhi.admin.settings.coordination.TechCoordinationForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.commons.user.RegisterModule;
import com.gsd.sreenidhi.register.Register;
import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.tables.Accounts;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;
import com.gsd.sreenidhi.utils.CalendarUtils;

	@Controller

	@SessionAttributes("settingsBean")
	public class SystemSettingsController {

		private static final long serialVersionUID = 1195564381169111658L;

		private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

		private JdbcTemplate jdbcTemplate = null;

		@Autowired
		public SystemSettingsController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		@ModelAttribute("settingsBeanBean")
		public SettingsForm createUserTicketsBean() {
			return new SettingsForm();
		}

		@RequestMapping(value="/admin/system-settings", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettings(HttpSession session) {
			
			String mainTab = "Application";
			String subTab = "Dashboard";
			ModelAndView mv = new ModelAndView("admin/settings/application/dash");
			mv.addObject("mainTab",mainTab);
			mv.addObject("subTab", subTab);
			return mv;
		}
		
		
		@RequestMapping(value="/admin/settings/dashboard", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsDashboard(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/application/dash");
			mv.addObject("mainTab","Application");
			mv.addObject("subTab","Dashboard");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/about", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsAbout(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/application/about");
			mv.addObject("mainTab","Application");
			mv.addObject("subTab","About");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/integrations", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsIntegrations(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/application/integrations");
			mv.addObject("mainTab","Application");
			mv.addObject("subTab","Integrations");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/systemInfo", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsSystemInfo(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/system/info");
			mv.addObject("mainTab","System");
			mv.addObject("subTab","SystemInfo");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/generalConfiguration", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsGenConfig(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/system/general");
			mv.addObject("mainTab","System");
			mv.addObject("subTab","GeneralConfiguration");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/announcementConfiguration", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsSysAnnouncements(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/system/announcements");
			mv.addObject("mainTab","System");
			mv.addObject("subTab","AnnouncementConfiguration");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/blogs/blogSettings", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSettingsBlogSettings(HttpSession session) {
			
			ModelAndView mv = new ModelAndView("admin/settings/blog/blog-settings");
			mv.addObject("mainTab","System");
			mv.addObject("subTab","BlogSettings");
			return mv;
		}
		
		
		@RequestMapping(value="/admin/settings/permissions", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_SETTINGS_VIEW')")
		public ModelAndView adminSystemPermissionSettings(HttpSession session) {
			
			PermissionsActions pa = new PermissionsActions();
			List<PermissionsForm> ps = pa.getPermissionSections(jdbcTemplate);
			List<PermissionsForm> pf = pa.getPermissions(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/permissions/viewPermissions");
			mv.addObject("permissionSection",ps);
			mv.addObject("permissionList",pf);
			mv.addObject("mainTab","System");
			mv.addObject("subTab","Permissions");
			return mv;
		}
		
		
		@RequestMapping(value="/admin/settings/permission/viewGroupforPermission", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_VIEW')")
		public ModelAndView adminSystemGroupsforPermissionSettings(HttpSession session,  @RequestParam("id") int id) {
			
			PermissionsActions pa = new PermissionsActions();
			
			List<PermissionGroups> pg = pa.getPermissionGroups(jdbcTemplate, id);
			List<PermissionForm>  permInfo = pa.getPermissionInfo(jdbcTemplate, id);
			
			
			ModelAndView mv = new ModelAndView("admin/settings/permissions/viewPermissionGroups");
			mv.addObject("permissionGroups",pg);
			mv.addObject("permissionInfo",permInfo);
			mv.addObject("mainTab","System");
			mv.addObject("subTab","Permissions >> Groups");
			return mv;
		}
		
		
		@RequestMapping(value="/admin/settings/permission/viewUsersforPermission", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView adminSystemUsersForPermissionSettings(HttpSession session,  @RequestParam("id") int id) {
			
			PermissionsActions pa = new PermissionsActions();
			
			List<PermissionUsers> pu = pa.getPermissionUsers(jdbcTemplate, id);
			List<PermissionForm>  permInfo = pa.getPermissionInfo(jdbcTemplate, id);
			
			ModelAndView mv = new ModelAndView("admin/settings/permissions/viewPermissionUsers");
			mv.addObject("permissionUsers",pu);
			mv.addObject("permissionInfo",permInfo);
			mv.addObject("mainTab","System");
			mv.addObject("subTab","Permissions >> Users");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/deleteGroup", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView disableProfile(HttpSession session,  @RequestParam("id") int id) {
			
			final String DELETE_GROUP_PERMISSION_LINK_SQL = "DELETE FROM GROUP_PERMISSION_LINK WHERE ID_GROUP = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP_PERMISSION_LINK_SQL);
					preparedStatement.setInt(1, id);
					return preparedStatement;
				}
			});	
			
			
			final String DELETE_GROUP_SQL = "DELETE FROM GROUPS WHERE ID = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP_SQL);
					preparedStatement.setInt(1, id);
					return preparedStatement;
				}
			});	
		 return new ModelAndView("redirect:/admin/settings/users/viewAllGroups");
		}
		
		@RequestMapping(value="/admin/settings/users/viewGroup", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_VIEW')")
		public ModelAndView adminViewGroups(HttpSession session,  @RequestParam("id") int id) {
			
			GroupActions ga = new GroupActions();
			PermissionsActions pa = new PermissionsActions();
			
			List<PermissionsForm> ps = pa.getPermissionSections(jdbcTemplate);
			List<GroupPermissionsForm> pf = ga.getGroupPermissions(jdbcTemplate, id);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewGroup");
			mv.addObject("permissionSection",ps);
			mv.addObject("groupPerms",pf);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Groups");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/viewGroupByName", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_VIEW')")
		public ModelAndView adminViewGroupByName(HttpSession session,  @RequestParam("name") String name) {
			
			GroupActions ga = new GroupActions();
			PermissionsActions pa = new PermissionsActions();
			
			List<PermissionsForm> ps = pa.getPermissionSections(jdbcTemplate);
			List<GroupPermissionsForm> pf = ga.getGroupPermissionsByName(jdbcTemplate, name);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewGroup");
			mv.addObject("permissionSection",ps);
			mv.addObject("groupPerms",pf);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Groups");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/updateGroupPermission", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView adminViewGroups(@ModelAttribute("GroupPermissionsBean") @Valid GroupPermissionsForm GroupPermissionsBean,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			
			GroupActions ga = new GroupActions();
			
			int groupID = GroupPermissionsBean.getID_GROUP();
			int permissionID = GroupPermissionsBean.getID_PERMISSION();
			int value = GroupPermissionsBean.isFL_STATUS()==true?1:0;
			
			ga.updateGroupPermissions(jdbcTemplate,groupID,permissionID, value);
			 return new ModelAndView("redirect:/admin/settings/users/viewGroup?id="+groupID);
		}
		
		@RequestMapping(value="/admin/settings/users/viewGroupUsers", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView adminViewUsers(final HttpSession session,  @RequestParam(value="groupid", required=false) String groupid ,
				@RequestParam(value="page", required=false) String page) {
			
			
			UserActions ua = new UserActions();
			GroupActions ga = new GroupActions();
			
			int p;
			if(page==null) {
				p=1;
			}else {
				p=Integer.parseInt(page);
			}
			
			List<UsersForm> ul = ua.getUSers(jdbcTemplate,groupid,p, null);
			List<GroupPermissionsForm> gl = ga.getGroups(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewUsers");
			mv.addObject("groupUsers",ul);
			mv.addObject("groupId",groupid);
			mv.addObject("groupList",gl);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Groups");
			return mv;
		}
		
		
		
		@RequestMapping(value="/admin/settings/users/search", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView adminUserSearch(@ModelAttribute("UserSearchBean") @Valid UserSearchForm UserSearchBean,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			
			UserActions ua = new UserActions();
			GroupActions ga = new GroupActions();
			
			int p=1;
			String groupid = UserSearchBean.getID_GROUP();
			
			List<UsersForm> ul = ua.getUSers(jdbcTemplate,groupid,p, UserSearchBean.getTX_USER());
			List<GroupPermissionsForm> gl = ga.getGroups(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewUsers");
			mv.addObject("groupUsers",ul);
			mv.addObject("groupId",groupid==null?"-":groupid);
			mv.addObject("groupList",gl);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Groups");
			mv.addObject("searchQuery",UserSearchBean.getTX_USER());
			return mv;
			
		}
		
		
		
		@RequestMapping(value="/admin/settings/users/viewAllUsers", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView viewAllUsers(HttpSession session) {
			
			UserActions ua = new UserActions();
			GroupActions ga = new GroupActions();
			
			int p=1;
			String groupid = null;
			
			List<UsersForm> ul = ua.getUSers(jdbcTemplate,groupid,p, null);
			List<GroupPermissionsForm> gl = ga.getGroups(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewUsers");
			mv.addObject("groupUsers",ul);
			mv.addObject("groupId",groupid);
			mv.addObject("groupList",gl);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","View Users");
			return mv;
			
		}
		
		
		@RequestMapping(value="/admin/settings/users/viewAllGroups", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_VIEW')")
		public ModelAndView viewAllGroups(HttpSession session) {
			
			GroupActions ga = new GroupActions();
			PermissionsActions pa = new PermissionsActions();
			
			List<GroupPermissionsForm> gf = ga.getGroups(jdbcTemplate);

			ModelAndView mv = new ModelAndView("admin/settings/users/viewGroups");
			mv.addObject("groupList",gf);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Groups");
			return mv;
			
		}
		
		@RequestMapping(value="/action/settings/users/deleteUserFromGroup", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView deleteUserFromGroup(final HttpSession session,  
				@RequestParam(value="groupid", required=false) String groupid ,
				@RequestParam(value="userid", required=false) String userid,
				@RequestParam(value="page", required=false) String page) {
			
			GroupActions ga = new GroupActions();
			
			int p;
			if(page==null) {
				p=1;
			}else {
				p=Integer.parseInt(page);
			}
			
			ga.removeUserFromGroup(jdbcTemplate, groupid, userid);
			
			ModelAndView mv = null;
			if(page==null||page.equals("")) {
				mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			}else {
				mv = new ModelAndView("redirect:/admin/settings/users/viewGroupUsers?groupid="+groupid+"&page=1");
			}
			mv.addObject("successmessage", "User successfully deleted from group.");
			return mv;
		}
		
		@RequestMapping(value="/action/settings/users/deleteUserFromGroup", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView deleteUserFromGroupGET(final HttpSession session,  
				@RequestParam(value="groupid", required=false) String groupid ,
				@RequestParam(value="userid", required=false) String userid,
				@RequestParam(value="page", required=false) String page) {
			
			GroupActions ga = new GroupActions();
			
			int p;
			if(page==null) {
				p=1;
			}else {
				p=Integer.parseInt(page);
			}
			
			ga.removeUserFromGroup(jdbcTemplate, groupid, userid);
			
			ModelAndView mv = null;
			if(page==null||page.equals("")) {
				mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			}else {
				mv = new ModelAndView("redirect:/admin/settings/users/viewGroupUsers?groupid="+groupid+"&page=1");
			}
			mv.addObject("successmessage", "User successfully deleted from group.");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/addUserToGroup", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView addUserToGroup(final HttpSession session,  
				@RequestParam(value="groupid", required=true) String groupid ,
				@RequestParam(value="user", required=true) String userid,
				@RequestParam(value="source", required=false) String source
				) {
			
			GroupActions ga = new GroupActions();
			
			ga.addUserToGroup(jdbcTemplate, groupid, userid);
			
			ModelAndView mv = null;
			if(source!=null && source.equalsIgnoreCase("USER_PROFILE")) {
				mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			}else {
				mv = new ModelAndView("redirect:/admin/settings/users/viewGroupUsers?groupid="+groupid+"&page=1");	
			}
			
			mv.addObject("successmessage", "User added to group successfully.");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/addUserToGroupById", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView addUserToGroupById(final HttpSession session,  
				@RequestParam(value="groupid", required=true) String groupid ,
				@RequestParam(value="user", required=true) String userid,
				@RequestParam(value="source", required=false) String source
				) {
			
			GroupActions ga = new GroupActions();
			
			ga.addUserToGroupById(jdbcTemplate, groupid, userid);
			
			ModelAndView mv = null;
			if(source!=null && source.equalsIgnoreCase("USER_PROFILE")) {
				mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			}else {
				mv = new ModelAndView("redirect:/admin/settings/users/viewGroupUsers?groupid="+groupid+"&page=1");	
			}
			
			mv.addObject("successmessage", "User added to group successfully.");
			return mv;
		}

		
		@RequestMapping(value="/admin/settings/users/viewUserProfile", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView getUserProfile(final HttpSession session,   @RequestParam(value="userid", required=false) String userid 
				) {
			
			UserActions ua = new UserActions();
			GroupActions ga = new GroupActions();
			PermissionsActions pa = new PermissionsActions();
			
			List<UsersForm> user  = ua.getUserProfile(jdbcTemplate, userid);
			List<GroupPermissionsForm> userLvlPermission = ua.getUserLvlPermissions(jdbcTemplate, userid);
			List<UserPermissionGroupLvlForm> groupLvlPermission = ua.getGroupLvlPermissions(jdbcTemplate, userid);
			List<GroupPermissionsForm> groups = ga.getGroups(jdbcTemplate);
			List<PermissionsForm> permissions = pa.getPermissions(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/viewUserProfile");
			mv.addObject("userInfo", user);
			mv.addObject("userLvlPermission", userLvlPermission);
			mv.addObject("groupLvlPermission", groupLvlPermission);
			mv.addObject("groupsList", groups);
			mv.addObject("permissionsList", permissions);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","User Profile");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/addUserLvlPermission", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView addUserLvlPermission(final HttpSession session,   @RequestParam(value="userid", required=false) String userid, @RequestParam(value="permid", required=false) String permid 
				) {
			
			UserActions ua = new UserActions();
			PermissionsActions pa = new PermissionsActions();
			
			ua.addUserLvlPermission(jdbcTemplate, userid, permid);
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","User Profile");
			return mv;
		}
		
		@RequestMapping(value="/admin/settings/users/deleteUserLvlPermission", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView deleteUserLvlPermission(final HttpSession session,   @RequestParam(value="userid", required=false) String userid, @RequestParam(value="permid", required=false) String permid 
				) {
			
			UserActions ua = new UserActions();
			PermissionsActions pa = new PermissionsActions();
			
			ua.deleteUserLvlPermission(jdbcTemplate, userid, permid);
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+userid);
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","User Profile");
			return mv;
		}

		@RequestMapping(value="/admin/settings/groups/newGroup", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_GROUPS_CREATE_MODITY')")
		public ModelAndView addNewGroup(@ModelAttribute("groupPermissionsBean") @Valid GroupPermissionsForm groupPermissionsBean,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			
			GroupActions ga = new GroupActions();
			
			int p=1;
			
			ga.createGroup(jdbcTemplate, groupPermissionsBean.getTX_GROUP_NAME());
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewAllGroups");
			mv.addObject("successmessage", "Successfully created new group");
			return mv;
			
		}
		
		@RequestMapping(value="/admin/settings/users/newUser", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView createUserFromAdminSettings(@ModelAttribute("usersBean") @Valid UsersForm usersBean,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			Register registerForm = new Register();
			registerForm.setEmail(usersBean.getTX_EMAIL());
			registerForm.setFirstname(usersBean.getTX_FIRSTNAME());
			registerForm.setLastname(usersBean.getTX_LASTNAME());
			registerForm.setPassword(usersBean.getPassword());
			
			final String QUERY_SQL = "SELECT * FROM ACCOUNTS WHERE TX_EMAIL = '" + registerForm.getEmail() +"'";
			
			List<Accounts> accountList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<Accounts>() {
	            public Accounts mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	Accounts acc = new Accounts();
	            	acc.setId(resulSet.getInt("ID"));
	            	acc.setEmail(resulSet.getString("TX_EMAIL"));
	            	acc.setPassword(resulSet.getString("TX_PASSWORD"));
	            	acc.setEnabled(resulSet.getInt("FL_ENABLED"));
	            	acc.setTimestamp(resulSet.getString("TS_CREATE"));
	            	
	                return acc;
	            }
	        });
			
			if(accountList!=null && accountList.size()>0) {
				logger.warn("Account already exists");
				ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewAllUsers");
				mv.addObject("failuremessage", "Email already registered");
				return mv;
				
			}
			
			RegisterModule rm = new RegisterModule(this.jdbcTemplate.getDataSource());
			rm.registerAccount(registerForm);
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewAllUsers");
			mv.addObject("successmessage", "Successfully created new user");
			return mv;
			
		}
		
		
		@RequestMapping(value="/admin/settings/users/updateCredentials", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY') AND hasAuthority('PERM_USER_CREDENTIALS')")
		public ModelAndView updateCredentials(@ModelAttribute("usersBean") @Valid UsersForm usersBean, @RequestParam(value="user", required=false) String user 
				, @RequestParam(value="id", required=false) String id ,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			Bcrypt bcrypte = new Bcrypt();
			final String encryptedPassword = "{bcrypt}"+bcrypte.encode(usersBean.getPassword());
			
			final String UPDATE_ACCOUNTS_SQL = "UPDATE ACCOUNTS SET TX_PASSWORD = ? WHERE TX_EMAIL = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNTS_SQL);
					preparedStatement.setString(1, encryptedPassword);
					preparedStatement.setString(2, user);
					return preparedStatement;
				}
			});	
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+id);
			return mv;
			
		}
		
		@RequestMapping(value="/admin/settings/users/enableProfile", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView enableProfile(@ModelAttribute("usersBean") @Valid UsersForm usersBean, @RequestParam(value="user", required=false) String user 
				, @RequestParam(value="id", required=false) String id ,@RequestParam(value="status", required=false) String status ,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			final String UPDATE_ACCOUNTS_SQL = "UPDATE ACCOUNTS SET FL_ENABLED = ? WHERE TX_EMAIL = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNTS_SQL);
					preparedStatement.setInt(1, Integer.parseInt(status));
					preparedStatement.setString(2, user);
					return preparedStatement;
				}
			});	
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+id);
			return mv;
			
		}
		
		@RequestMapping(value="/admin/settings/users/disableProfile", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView disableProfile(@ModelAttribute("usersBean") @Valid UsersForm usersBean, @RequestParam(value="user", required=false) String user 
				, @RequestParam(value="id", required=false) String id , @RequestParam(value="status", required=false) String status ,
				BindingResult result, Model model, RedirectAttributes redirectAttrs,
				final HttpSession session) {
			
			final String UPDATE_ACCOUNTS_SQL = "UPDATE ACCOUNTS SET FL_ENABLED = ? WHERE TX_EMAIL = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNTS_SQL);
					preparedStatement.setInt(1, Integer.parseInt(status));
					preparedStatement.setString(2, user);
					return preparedStatement;
				}
			});	
			
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewUserProfile?userid="+id);
			return mv;
			
		}
		
		@RequestMapping(value="/admin/settings/users/deleteUser", method = RequestMethod.POST)
		@PreAuthorize("hasAuthority('PERM_USERS_CREATE_MODIFY')")
		public ModelAndView deleteUser(@RequestParam(value="user", required=false) String user 
				, @RequestParam(value="id", required=false) String id ,
				final HttpSession session) {
			
			final String DELETE_AUTHORITY_SQL = "DELETE FROM AUTHORITY WHERE ID_ACCOUNT = (SELECT ID FROM ACCOUNTS WHERE TX_EMAIL = ?)";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHORITY_SQL);
					preparedStatement.setString(1, user);
					return preparedStatement;
				}
			});	
			
			
			final String DELETE_USER_SQL = "DELETE FROM USERS WHERE ID_ACCOUNT = (SELECT ID FROM ACCOUNTS WHERE TX_EMAIL = ?)";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
					preparedStatement.setString(1, user);
					return preparedStatement;
				}
			});	
    		
    		final String INSERT_ACCOUNTS_SQL = "DELETE FROM ACCOUNTS WHERE TX_EMAIL = ?";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_SQL);
					preparedStatement.setString(1, user);
					return preparedStatement;
				}
			});	
    		
			ModelAndView mv = new ModelAndView("redirect:/admin/settings/users/viewAllUsers");
			return mv;
			
		}
		
		
		
		
		@RequestMapping(value="/admin/settings/techcos", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('PERM_USERS_VIEW')")
		public ModelAndView viewTechnicalCoordinators(HttpSession session) {
			
			CoordinationActions ca = new CoordinationActions();
	
			List<TechCoordinationForm> techcoList = ca.getTCLinks(jdbcTemplate);
			
			ModelAndView mv = new ModelAndView("admin/settings/users/techcos");
			mv.addObject("techcoList",techcoList);
		
			mv.addObject("mainTab","Users");
			mv.addObject("subTab","Technical Coordinators");
			return mv;
			
		}
		
		
}
