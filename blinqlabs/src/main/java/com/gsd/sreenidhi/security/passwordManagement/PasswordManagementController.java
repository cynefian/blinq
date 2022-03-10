package com.gsd.sreenidhi.security.passwordManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.user.AccountActions;
import com.gsd.sreenidhi.user.AccountSettingForm;
import com.gsd.sreenidhi.utils.CalendarUtils;


@Controller
@RequestMapping("/user/passwordManagement")
@SessionAttributes("passwordManagementBean")
public class PasswordManagementController {
	
private static final Logger logger = LoggerFactory.getLogger(PasswordManagementController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public PasswordManagementController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("passwordManagementBean")
		 public PasswordManagementForm createPasswordManagementBean() {
		 	return new PasswordManagementForm();
		 }
		 
		 @RequestMapping(method=RequestMethod.GET)  
			public ModelAndView userPasswordManagementGet(HttpSession session) {
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
			 AccountActions aa= new AccountActions();
			 List<AccountSettingForm> accSettingList  = aa.getAccount(usrId, jdbcTemplate);
				
			 ModelAndView mv = new ModelAndView("user/passwordManagement");
			 mv.addObject("accSettingList", accSettingList);
				return mv;
		 }
		 
		 
		 @RequestMapping(method=RequestMethod.POST)  
			public ModelAndView userPasswordManagementPost(@ModelAttribute("passwordManagementBean") @Valid PasswordManagementForm passwordManagementForm, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 logger.info("Attempting User Password Update");
			 
			 if(!passwordManagementForm.getNewpassword1().equals(passwordManagementForm.getNewpassword2())) {
				 ModelAndView mv = new ModelAndView("user/passwordManagement", "failuremessage","New Passwords don't match.");
					return mv;
			 }
				 
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 
				final String QUERY_SQL = "SELECT TX_PASSWORD FROM ACCOUNTS where ID = ? "; 
			 
				logger.info("uid:"+uid);
				
				 final String GET_CURR_PW__SQL = "SELECT TX_PASSWORD FROM ACCOUNTS where ID = " + userId;
					
					List<PasswordManagementForm> pwList = this.jdbcTemplate.query(GET_CURR_PW__SQL, new RowMapper<PasswordManagementForm>() {
			            public PasswordManagementForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	PasswordManagementForm pw = new PasswordManagementForm();
			            	pw.setPwCurrHash(resulSet.getString("TX_PASSWORD"));
			                return pw;
			            }
			        });
				
				
			/*	 String retrievedHash = (String) jdbcTemplate.queryForObject(
						 QUERY_SQL, new Object[] { session.getAttribute("uid")}, String.class);
*/
					
					
				String retrievedHash = pwList.get(0).getPwCurrHash();
				retrievedHash = retrievedHash.substring(retrievedHash.indexOf('{')+8);
				Bcrypt bcrypt = new Bcrypt();
				
				if(!bcrypt.matches(passwordManagementForm.getCurrentpassword(), retrievedHash)) {
					logger.info("Password does not match");
					 ModelAndView mv = new ModelAndView("user/passwordManagement", "failuremessage","Password Check failed. Please enter correct password.");
						return mv;
				}
				   
					 
				 final String newHash = "{bcrypt}"+bcrypt.encode(passwordManagementForm.getNewpassword1());
				 final String usernameEmail = (String) session.getAttribute("userEmail");
				 
				 final String UPDATE_QUERY_SQL = "UPDATE ACCOUNTS " + 
							"SET TX_PASSWORD = ?,"
							+ " TS_CREATE = ? " + 
							" where TX_EMAIL = ?" ;
				 
				 logger.info("Updating new password");
				 
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY_SQL);
							
							preparedStatement.setString(1, newHash);
							preparedStatement.setString(2, CalendarUtils.dateToStringDateTimeReadable(new Date()));
							preparedStatement.setString(3, usernameEmail);
							
							return preparedStatement;
						}
					});	
				 
			 ModelAndView mv = new ModelAndView("user/passwordManagement", "successmessage", "Password updated successfully");
				return mv;
		 }

}
