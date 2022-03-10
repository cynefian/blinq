package com.gsd.sreenidhi.security.twofa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.settings.UserActions;
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.user.AccountSettingForm;
import com.gsd.sreenidhi.user.AccountVerificationForm;
import com.gsd.sreenidhi.user.UserController;
import com.gsd.sreenidhi.utils.AESEncryption;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;

@Controller
public class MFAController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public MFAController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value = "/validateAuth", method = RequestMethod.GET)
	public ModelAndView validateProfileShow(HttpSession session) throws Exception {
		
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		String VALIDATE_USER_SQL = "SELECT FL_2FA, TX_2FA, ID FROM ACCOUNTS " + " WHERE TX_EMAIL = '"
				+ userDetails.getUsername() + "'";

		List<AccountSettingForm> accSettingList = jdbcTemplate.query(VALIDATE_USER_SQL,
				new RowMapper<AccountSettingForm>() {
					public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountSettingForm acc = new AccountSettingForm();
						acc.setFL_2FA(resulSet.getInt("FL_2FA")==1?true:false);
						acc.setTX_2FA(resulSet.getString("TX_2FA"));
						acc.setId(resulSet.getInt("ID"));
						return acc;
					}
				});

		String redirection;
		if (accSettingList.get(0).isFL_2FA()) {
			redirection = "/user/security/validateLogin2FA";
		
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_PRE_AUTH_USER");
			List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
			updatedAuthorities.add(authority);
			
		    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

		    SecurityContextHolder.getContext().setAuthentication(newAuth);
		}else {
			redirection = "redirect:/user/userprofile";
			
		}
		ModelAndView mv = new ModelAndView(redirection);
		mv.addObject("userId", accSettingList.get(0).getId());
		return mv;
	}
			
			
	@RequestMapping(value = "/validateAuth", method = RequestMethod.POST)
	public ModelAndView validateProfileCheck(
			@ModelAttribute("AccountVerificationForm") @Valid AccountVerificationForm accountVerificationBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		String VALIDATE_USER_SQL = "SELECT TX_2FA, ID FROM ACCOUNTS " + " WHERE TX_EMAIL = '"
				+ userDetails.getUsername() + "'";

		List<AccountSettingForm> accSettingList = jdbcTemplate.query(VALIDATE_USER_SQL,
				new RowMapper<AccountSettingForm>() {
					public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountSettingForm acc = new AccountSettingForm();
						acc.setTX_2FA(resulSet.getString("TX_2FA"));
						acc.setId(resulSet.getInt("ID"));
						return acc;
					}
				});

		int state = 0;
		String successmessage = null;
		String failuremessage = null;
		String redirection="";
		try {
			String currentSecretCode = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(AESEncryption
					.decrypt(accSettingList.get(0).getTX_2FA(), Integer.toString(accSettingList.get(0).getId())));

			if (accountVerificationBean.getTX_RETURN_CODE().equals(currentSecretCode)) {
				state = 1;
				successmessage = "Success";
			} else {
				state = 0;
				failuremessage = "Failed to Enable 2FA. Please try again.";
			}

			if (state == 1) {
				redirection = "redirect:/user/userprofile";
				
				UserActions ua = new UserActions();
				List<SimpleGrantedAuthority> updatedAuthorities = ua.getAuthorities(jdbcTemplate, auth.getName());
			    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			    SecurityContextHolder.getContext().setAuthentication(newAuth);

							 
			} else {
				redirection = "redirect:/validateAuth";
				
				session.invalidate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(redirection);
		return mv;
	}
	
	@RequestMapping(value = "/temp2FA", method = RequestMethod.GET)
	public ModelAndView validateTemp2FA(HttpSession session) throws Exception {
		
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		String secret = Generator.randomString(8);
		String encrypted = AESEncryption.encrypt(secret, userDetails.getUsername().trim());
		 
		 String UPDATE_VERIFY_EMAIL = "UPDATE ACCOUNTS SET TX_TEMP_AUTH = ? WHERE TX_EMAIL = ?";
			 
		 final String email = userDetails.getUsername();
		 jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFY_EMAIL);
					preparedStatement.setString(1, encrypted);
					preparedStatement.setString(2, email);
					return preparedStatement;
				}
			});	
		 
		EmailForm ef = new EmailForm();
		MultiFAForm wmf = new MultiFAForm();
		
		String tempalte = ef.getEmailTemplate();
		String emailMessage = wmf.getTX_MFA_MESSAGE(); 
		emailMessage.replace("{secret}", secret);
		
		SendMail sm = new SendMail();
		  
		  
		  final String emailMsg = tempalte.replace("${username", userDetails.getUsername()).replace("${title}", "Multi-Factor Authentication").replace("${message", emailMessage).replace("${image}", wmf.getImage()).replace("${dateTime}", CalendarUtils.getCurrentTimeStamp()).replace("${disclaimer}", ef.getDisclaimer());
		  final String uName = 	userDetails.getUsername().trim();
		    ExecutorService service = Executors.newFixedThreadPool(4);
		    service.submit(new Runnable() {
		        public void run() {
		        	sm.sendMessage(emailMsg, uName, null, "Blinqlabs login verification", false);
		        }
		    });
		    
		ModelAndView mv = new ModelAndView("/user/security/emailMFAAuth");
		return mv;
	}

	@RequestMapping(value = "/temp2FA", method = RequestMethod.POST)
	public ModelAndView validateTemp2FACode(
			@ModelAttribute("AccountVerificationForm") @Valid AccountVerificationForm accountVerificationBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) throws Exception {
		
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		
		String VALIDATE_USER_SQL = "SELECT TX_TEMP_AUTH FROM ACCOUNTS " + " WHERE TX_EMAIL = '"	+ userDetails.getUsername().trim() + "'";

		List<AccountSettingForm> accSettingList = jdbcTemplate.query(VALIDATE_USER_SQL,
				new RowMapper<AccountSettingForm>() {
					public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AccountSettingForm acc = new AccountSettingForm();
						acc.setTX_2FA(resulSet.getString("TX_TEMP_AUTH"));
						return acc;
					}
				});
		
		
		int state = 0;
		String successmessage = null;
		String failuremessage = null;
		String redirection="";
		try {
			String currentSecretCode = AESEncryption.decrypt(accSettingList.get(0).getTX_2FA(), userDetails.getUsername().trim());

			if (accountVerificationBean.getTX_RETURN_CODE().equals(currentSecretCode)) {
				state = 1;
				successmessage = "Success";
			} else {
				state = 0;
				failuremessage = "Code mismatch. Please try again.";
			}

			if (state == 1) {
				redirection = "redirect:/user/userprofile";
				
				UserActions ua = new UserActions();
				List<SimpleGrantedAuthority> updatedAuthorities = ua.getAuthorities(jdbcTemplate, auth.getName());
			    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			    SecurityContextHolder.getContext().setAuthentication(newAuth);
			    
			    String UPDATE_VERIFY_EMAIL = "UPDATE ACCOUNTS SET TX_TEMP_AUTH = ? WHERE TX_EMAIL = ?";
				 
				 final String email = userDetails.getUsername().trim();
				 jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VERIFY_EMAIL);
							preparedStatement.setString(1, null);
							preparedStatement.setString(2, email);
							return preparedStatement;
						}
					});	
							 
			} else {
				redirection = "redirect:/validateAuth";
				session.invalidate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView(redirection);
		return mv;
	}
}
