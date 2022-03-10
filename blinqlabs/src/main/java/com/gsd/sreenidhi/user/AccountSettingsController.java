package com.gsd.sreenidhi.user;

import java.security.GeneralSecurityException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.security.twofa.TimeBasedOneTimePasswordUtil;
import com.gsd.sreenidhi.utils.AESEncryption;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;

@Controller
@SessionAttributes("AccountSettingsBean")
public class AccountSettingsController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountSettingsController.class);
	public static final String ACCOUNT_SID = "AC7f61b460390bf53b93713fa74cc00d69";
    public static final String AUTH_TOKEN = "d8ab56932ba6ba7b143773f33e1ab939";

	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public AccountSettingsController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("AccountSettingsBean")
		 public AccountSettingForm createAccountSettingsBean() {
		 	return new AccountSettingForm();
		 }

		 @RequestMapping(method=RequestMethod.GET, value="/user/useraccountsettings")  
			public ModelAndView userAccountSettingsGet(HttpSession session) {
			 
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
				
				logger.info("Retrieving user account information");
				
				AccountActions aa= new AccountActions();
				List<AccountSettingForm> accSettingList  = aa.getAccount(usrId, jdbcTemplate);
				List<AccountVerificationForm> accVerifyList  = aa.getAccountVerification(usrId, jdbcTemplate);
						
				Collection<SimpleGrantedAuthority> userAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();	
				ModelAndView mv = new ModelAndView("user/account-settings", "successmessage", "Update Successful!");
				mv.addObject("accSettingList", accSettingList);
				mv.addObject("accVeriyList", accVerifyList);
				mv.addObject("userid",usrId);
				mv.addObject("authorities",userAuthorities);
				return mv;
			}
		 
		 @RequestMapping(method=RequestMethod.POST, value="/user/useraccountsettings")  
			public ModelAndView userAccountSettingsPost(@ModelAttribute("AccountSettingsBean") @Valid AccountSettingForm accountSettingsBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 logger.info("Updating user account information");
			 
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
			 
				final String QUERY_SQL = "UPDATE USERS " + 
						"SET TX_FIRSTNAME = ? " + 
						", TX_LASTNAME=? " + 
						", TX_WEBSITE=? " + 
						", TX_CONTACT_NUM=? " + 
						", TX_BIO=?" +
						", TX_IMAGE=?" + 
						" where ID_ACCOUNT = ?" ;
				
				final String ufname = accountSettingsBean.getTx_firstname();
				final String ulname = accountSettingsBean.getTx_lastname();
				final String uweb = accountSettingsBean.getTx_website();
				final String ucontnum =  accountSettingsBean.getTx_contact_num();
				final String ubio = accountSettingsBean.getTx_bio();
				final String uimg = accountSettingsBean.getTX_IMAGE();
				
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL);
						preparedStatement.setString(1, ufname);
						preparedStatement.setString(2, ulname);
						preparedStatement.setString(3, uweb);
						preparedStatement.setString(4, ucontnum);
						preparedStatement.setString(5, ubio);
						preparedStatement.setString(6, uimg);
						preparedStatement.setInt(7, usrId);
						return preparedStatement;
					}
				});	
			 
			 ModelAndView mv = null;
				mv = userAccountSettingsGet(session);
				return mv;
		 }
		 
		 
	
		 @RequestMapping(method=RequestMethod.GET, value="/user/verifyEmail")  
			public ModelAndView verifyUseEmailGet(@ModelAttribute("AccountVerificationBean") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 String secret = Generator.randomString(8);
			 String encrypted = AESEncryption.encrypt(secret, Integer.toString(accountVerificationBean.getID_USER()));
			 
			 String INSERT_VERIFY_EMAIL = "IF NOT EXISTS (SELECT * FROM ACCOUNT_VERIFICATION WHERE ID_USER = "+accountVerificationBean.getID_USER()+")"
			 		+ " INSERT INTO ACCOUNT_VERIFICATION (FL_EMAIL_VERIFIED, TX_EMAIL_VERIFIED, TS_EMAIL_VERIFIED, FL_MOB_VERIFIED, TX_MOB_VERIFIED, TS_MOB_VERIFIED, ID_USER) "
			 		+ " VALUES(0,null,null, 0, null, null, "+accountVerificationBean.getID_USER()+")";
			 
			 jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VERIFY_EMAIL);
						return preparedStatement;
					}
				});	
			 
			 String UPDATE_VERIFY_EMAIL = "UPDATE ACCOUNT_VERIFICATION SET TX_EMAIL_VERIFIED = ? WHERE ID_USER = ?";
				 						 
				 jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFY_EMAIL);
							preparedStatement.setString(1, encrypted);
							preparedStatement.setInt(2, accountVerificationBean.getID_USER());
							return preparedStatement;
						}
					});	
				 
				 AccountActions aa = new AccountActions();
				 List<AccountSettingForm> acc = aa.getAccount(accountVerificationBean.getID_USER(), jdbcTemplate);
				 
				 EmailForm ef = new EmailForm();
				 AccountVerificationForm avf = new AccountVerificationForm();
				 
				 String tempalte = ef.getEmailTemplate();
				String emailMessage = avf.getTX_EMAIL_VERIFY_CONTENT();
				emailMessage = emailMessage.replace("${secret}", secret);
					
						SendMail sm = new SendMail();
						  
						  final String emailMsg = tempalte.replace("${username}", acc.get(0).getTx_firstname()).replace("${title}", "Multi-Factor Authentication").replace("${message}", emailMessage).replace("${image}", avf.getImage()).replace("${dateTime}", CalendarUtils.getCurrentTimeStamp()).replace("${disclaimer}", ef.getDisclaimer());
							
						    ExecutorService service = Executors.newFixedThreadPool(4);
						    service.submit(new Runnable() {
						        public void run() {
						        	sm.sendMessage(emailMsg, acc.get(0).getTx_email(), null, "Blinqlabs login verification", false);
						        }
						    });
				
			ModelAndView mv = new ModelAndView("/user/emailVerification");
			mv.addObject("userid", accountVerificationBean.getID_USER());
			return mv;
			 
		 }
		 
		 @RequestMapping(method=RequestMethod.POST, value="/user/verifyEmail")  
			public ModelAndView verifyUseEmailPost(@ModelAttribute("AccountVerificationBean") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
				String GET_SECRET_VERIFICATION = "SELECT TX_EMAIL_VERIFIED FROM ACCOUNT_VERIFICATION WHERE ID_USER = " + accountVerificationBean.getID_USER();
				
				List<AccountVerificationForm> accVerList = jdbcTemplate.query(GET_SECRET_VERIFICATION, new RowMapper<AccountVerificationForm>() {
					public AccountVerificationForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountVerificationForm acc = new AccountVerificationForm();
						acc.setTX_RETURN_CODE(resulSet.getString("TX_EMAIL_VERIFIED"));
						return acc;
					}
				});
				
				String encrypted = AESEncryption.encrypt(accountVerificationBean.getTX_RETURN_CODE(), Integer.toString(accountVerificationBean.getID_USER()));
				
				String redirection;
				ModelAndView mv = null;
				
				if(accVerList!=null && accVerList.get(0).getTX_RETURN_CODE().equals(encrypted)) {

					final String UPDATE_VERIFICATION_SQL = "UPDATE ACCOUNT_VERIFICATION " + 
							"SET FL_EMAIL_VERIFIED = ? " + 
							", TS_EMAIL_VERIFIED = ? " + 
							" where ID_USER = ?" ;
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFICATION_SQL);
							preparedStatement.setInt(1, 1);
							preparedStatement.setString(2, CalendarUtils.dateToStringDateTimeReadable(new Date()));
							preparedStatement.setInt(3, accountVerificationBean.getID_USER());
							return preparedStatement;
						}
					});	
				 
					
					redirection = "redirect:/user/useraccountsettings";
				}else {
					redirection = "/user/emailVerification";
				}
				mv = new ModelAndView(redirection);
				if(!redirection.contains("redirect")) {
					mv.addObject("failuremessage","Error verifying secret. Please try again");
				}
			return mv;
			 
		 }
		 
		 @RequestMapping(method=RequestMethod.GET, value="/user/verifyPhone")  
			public ModelAndView verifyUserPhoneGet(@ModelAttribute("AccountVerificationBean") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 String secret = Generator.generateOTP(6);
			 String encrypted = AESEncryption.encrypt(secret, Integer.toString(accountVerificationBean.getID_USER()));
			 
			 String INSERT_VERIFY_EMAIL = "IF NOT EXISTS (SELECT * FROM ACCOUNT_VERIFICATION WHERE ID_USER = "+accountVerificationBean.getID_USER()+")"
			 		+ " INSERT INTO ACCOUNT_VERIFICATION (FL_EMAIL_VERIFIED, TX_EMAIL_VERIFIED, TS_EMAIL_VERIFIED, FL_MOB_VERIFIED, TX_MOB_VERIFIED, TS_MOB_VERIFIED, ID_USER) "
			 		+ " VALUES(0,null,null, 0, null, null, "+accountVerificationBean.getID_USER()+")";
			 
			 jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VERIFY_EMAIL);
						return preparedStatement;
					}
				});	
			 
			 String UPDATE_VERIFY_EMAIL = "UPDATE ACCOUNT_VERIFICATION SET TX_MOB_VERIFIED = ? WHERE ID_USER = ?";
				 						 
				 jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFY_EMAIL);
							preparedStatement.setString(1, encrypted);
							preparedStatement.setInt(2, accountVerificationBean.getID_USER());
							return preparedStatement;
						}
					});	
				 
				
				 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			      
			       
			        
				AccountActions aa = new AccountActions();
				List<AccountSettingForm> acc = aa.getAccount(accountVerificationBean.getID_USER(), jdbcTemplate);
				 
				 Message message = Message
			                .creator(new PhoneNumber(acc.get(0).getTx_contact_num()), // to
			                        new PhoneNumber("+12105854392"), // from
			                        "You code for verifying Blinqlabs account phone number is: "+ secret)
			                .create();

				 logger.info("New Text Message Sent with SID: " + message.getSid());
				    
				ModelAndView mv = new ModelAndView("/user/mobVerification");
				mv.addObject("userid", accountVerificationBean.getID_USER());
				 
			 return mv;
			 
		 }
			 
		 @RequestMapping(method=RequestMethod.POST, value="/user/verifyPhone")  
			public ModelAndView verifyUserPhonePost(@ModelAttribute("AccountVerificationBean") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 String GET_SECRET_VERIFICATION = "SELECT TX_MOB_VERIFIED FROM ACCOUNT_VERIFICATION WHERE ID_USER = " + accountVerificationBean.getID_USER();
				
				List<AccountVerificationForm> accVerList = jdbcTemplate.query(GET_SECRET_VERIFICATION, new RowMapper<AccountVerificationForm>() {
					public AccountVerificationForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountVerificationForm acc = new AccountVerificationForm();
						acc.setTX_RETURN_CODE(resulSet.getString("TX_MOB_VERIFIED"));
						return acc;
					}
				});
				
				String encrypted = AESEncryption.encrypt(accountVerificationBean.getTX_RETURN_CODE(), Integer.toString(accountVerificationBean.getID_USER()));
				
				String redirection;
				ModelAndView mv = null;
				
				if(accVerList!=null && accVerList.get(0).getTX_RETURN_CODE().equals(encrypted)) {

					final String UPDATE_VERIFICATION_SQL = "UPDATE ACCOUNT_VERIFICATION " + 
							"SET FL_MOB_VERIFIED = ? " + 
							", TS_MOB_VERIFIED = ? " + 
							" where ID_USER = ?" ;
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFICATION_SQL);
							preparedStatement.setInt(1, 1);
							preparedStatement.setString(2, CalendarUtils.dateToStringDateTimeReadable(new Date()));
							preparedStatement.setInt(3, accountVerificationBean.getID_USER());
							return preparedStatement;
						}
					});	
				 
					
					redirection = "redirect:/user/useraccountsettings";
				}else {
					redirection = "/user/mobVerification";
				}
				mv = new ModelAndView(redirection);
				if(!redirection.contains("redirect")) {
					mv.addObject("failuremessage","Error verifying secret. Please try again");
				}
			return mv;
			 
		 }
		 
		 
		 @RequestMapping(method=RequestMethod.GET, value="/user/enable2FA")  
			public ModelAndView enable2FA(HttpSession session) {
			 
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 
			 AccountActions aa= new AccountActions();
			 List<AccountSettingForm> accSettingList  = aa.getAccount(uid, jdbcTemplate);
			 
			 String qrUrl = ""; 
			 
			 if(!accSettingList.get(0).isFL_2FA()) {
				 String secret = TimeBasedOneTimePasswordUtil.generateBase32Secret();
				 
				 String START_2FA_SQL = "UPDATE ACCOUNTS "
				 		+ " SET TX_2FA = ? "
				 		+ " WHERE ID = ?  ";
				 
				 jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(START_2FA_SQL);
							preparedStatement.setString(1, AESEncryption.encrypt(secret, userId));
							preparedStatement.setInt(2, uid);
							return preparedStatement;
						}
					});	
				 
				 qrUrl = TimeBasedOneTimePasswordUtil.qrImageUrl("Blinqlabs: " + accSettingList.get(0).getTx_email(), secret);
			 }
			 
			 
			 ModelAndView mv = new ModelAndView("/user/security/enableTwoFA");
			 mv.addObject("qrUrl",qrUrl);
			 mv.addObject("accSettingList", accSettingList);
			 return mv;
			 
		 }
		 
		 
		 
		 @RequestMapping(method=RequestMethod.POST, value="/user/enable2FA")  
			public ModelAndView enable2FAProcess(@ModelAttribute("AccountVerificationForm") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 
			 String START_2FA_SQL = "SELECT TX_2FA FROM ACCOUNTS "
				 		+ " WHERE ID = " + uid;
				 
				List<AccountSettingForm> accSettingList = jdbcTemplate.query(START_2FA_SQL, new RowMapper<AccountSettingForm>() {
					public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountSettingForm acc = new AccountSettingForm();
						acc.setTX_2FA(resulSet.getString("TX_2FA"));
						return acc;
					}
				});
			 
			int state=0;
			String successmessage = null;
			String failuremessage = null;
			 try {
				String currentSecretCode = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(AESEncryption.decrypt(accSettingList.get(0).getTX_2FA(), userId));
				if(accountVerificationBean.getTX_RETURN_CODE().equals(currentSecretCode)) {
						state = 1;
						successmessage = "Success";
				}else {
						state = 0;
						failuremessage = "Failed to Enable 2FA. Please try again.";
				}

				String UPDATE_2FA_SQL = "UPDATE ACCOUNTS "
	 		+ " SET FL_2FA = ? "
	 		+ " WHERE ID = ?  ";
	 
			final int stateVal = state;
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_2FA_SQL);
					preparedStatement.setInt(1, stateVal);
					preparedStatement.setInt(2, uid);
					return preparedStatement;
				}
			});	
		 
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
			 
			 ModelAndView mv = new ModelAndView("redirect:/user/enable2FA");
			 mv.addObject("successmessage",successmessage);
			 mv.addObject("failuremessage",failuremessage);
			 return mv;
			 
		 }
		 
		 
		 @RequestMapping(method=RequestMethod.GET, value="/user/reset2FA")  
			public ModelAndView reset2FA(@ModelAttribute("AccountVerificationForm") @Valid AccountVerificationForm accountVerificationBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
	
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 
			 
			 String START_2FA_SQL = "SELECT TX_2FA FROM ACCOUNTS "
				 		+ " WHERE ID = " + uid;
				 
				List<AccountSettingForm> accSettingList = jdbcTemplate.query(START_2FA_SQL, new RowMapper<AccountSettingForm>() {
					public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountSettingForm acc = new AccountSettingForm();
						acc.setTX_2FA(resulSet.getString("TX_2FA"));
						return acc;
					}
				});
				
				String successmessage = null;
				String failuremessage = null;
				int state = 0;
				String secret;
				
				try {
					String currentSecretCode = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(AESEncryption.decrypt(accSettingList.get(0).getTX_2FA(), userId));
					if(accountVerificationBean.getTX_RETURN_CODE().equals(currentSecretCode)) {
							state = 0;
							successmessage = "Success";
							secret = null;
					}else {
							state = 1;
							failuremessage = "Failed to disable 2FA. Code mismatch. Please try again.";
							secret = accSettingList.get(0).getTX_2FA();
					}

					final int statusVal = state;
					String UPDATE_2FA_SQL = "UPDATE ACCOUNTS "
					 		+ " SET FL_2FA = ?, TX_2FA = ? "
					 		+ " WHERE ID = ?  ";
					 
							jdbcTemplate.update(new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_2FA_SQL);
									preparedStatement.setInt(1, statusVal);
									preparedStatement.setString(2, secret);
									preparedStatement.setInt(3, uid);
									return preparedStatement;
								}
							});	
			 
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
				 
			 
				
				ModelAndView mv = new ModelAndView("redirect:/user/enable2FA");
				 mv.addObject("successmessage",successmessage);
				 mv.addObject("failuremessage",failuremessage);
				 return mv;
		 }
		
			 
		 
}
