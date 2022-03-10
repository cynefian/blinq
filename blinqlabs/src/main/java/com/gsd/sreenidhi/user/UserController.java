package com.gsd.sreenidhi.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.settings.UserActions;
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.register.Register;
import com.gsd.sreenidhi.register.RegisterFormController;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.security.twofa.MultiFAForm;
import com.gsd.sreenidhi.security.twofa.TimeBasedOneTimePasswordUtil;
import com.gsd.sreenidhi.tables.Accounts;
import com.gsd.sreenidhi.user.messages.MessageActions;
import com.gsd.sreenidhi.user.messages.UserMessageForm;
import com.gsd.sreenidhi.user.tickets.TicketActions;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;
import com.gsd.sreenidhi.utils.AESEncryption;
import com.gsd.sreenidhi.utils.Generator;

/**
 * @author Sreenidhi Gundlupet sreenidhi.gsd@gmail.com
 */

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public UserController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	/*
	 * ************************************* User Section
	 * *************************************
	 */

	@RequestMapping("/user/user_account")
	public ModelAndView user_account() {
		ModelAndView mv = new ModelAndView("user/account");
		return mv;
	}

	@RequestMapping(value = "/user/userprofile", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpSession session) {

		logger.info("Retrieving user information for session scope");

		ModelAndView mv = new ModelAndView("user/profile");
		UserDetails userDetails = null;
		// (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		UserActions ua = new UserActions();
		ua.loadUserPermissions(jdbcTemplate);
		
		if (userDetails.getUsername() != null) {

			final String QUERY_SQL = "SELECT accounts.id AS ID, "
					+ " users.tx_firstname AS FIRSTNAME , users.tx_lastname AS LASTNAME, accounts.tx_password AS PASSWD, "
					+ "	accounts.tx_email AS EMAIL, users.TX_IMAGE AS USER_IMAGE, accounts.TS_CREATE AS TS_CREATE, "
					+ " accounts.FL_2FA AS FL_2FA " + " FROM ACCOUNTS, users " + "WHERE accounts.id = users.id_account "
					+ "and TX_EMAIL = '" + userDetails.getUsername() + "'";

			List<UserForm> userList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<UserForm>() {
				public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					UserForm user = new UserForm();
					user.setId(resulSet.getInt("ID"));
					user.setEmail(resulSet.getString("EMAIL"));
					user.setPassword(resulSet.getString("PASSWD"));
					user.setFirstname(resulSet.getString("FIRSTNAME"));
					user.setLastname(resulSet.getString("LASTNAME"));
					user.setUser_image(resulSet.getString("USER_IMAGE"));
					user.setTS_UPDATE(resulSet.getString("TS_CREATE"));
					user.setFL_2FA(resulSet.getInt("FL_2FA") == 1 ? true : false);
					return user;
				}
			});

			/*
			 * HttpSession session = null;
			 * 
			 * ServletRequestAttributes attr = (ServletRequestAttributes)
			 * RequestContextHolder.currentRequestAttributes(); session =
			 * attr.getRequest().getSession(true); // true == allow create
			 */
			if (userList != null && userList.size() > 0) {
				logger.info("Set Session Attribute: firstname = " + userList.get(0).getFirstname());
				logger.info("Set Session Attribute: lastname = " + userList.get(0).getLastname());

				MessageActions ma = new MessageActions();
				List<UserMessageForm> umf_unread = ma.getMessageCount(jdbcTemplate,
						Integer.toString(userList.get(0).getId()), "U");
				List<UserMessageForm> umf_star = ma.getMessageCount(jdbcTemplate,
						Integer.toString(userList.get(0).getId()), "S");
				List<UserMessageForm> umf_all = ma.getMessageCount(jdbcTemplate,
						Integer.toString(userList.get(0).getId()), null);

				mv.addObject("inboxCount", umf_unread.get(0).getQueryMessageCount());
				mv.addObject("starCount", umf_star.get(0).getQueryMessageCount());
				mv.addObject("allMessageCount", umf_all.get(0).getQueryMessageCount());

				session.setAttribute("firstname", userList.get(0).getFirstname());
				session.setAttribute("lastname", userList.get(0).getLastname());
				session.setAttribute("userEmail", userList.get(0).getEmail());
				session.setAttribute("userId", userList.get(0).getId());
				session.setAttribute("userImage", userList.get(0).getUser_image());
				mv.addObject("passwordChanged", userList.get(0).getTS_UPDATE());
				mv.addObject("doublesecure", userList.get(0).isFL_2FA());

				/*
				 * mv.addObject("fname", userList.get(0).getFirstname()); mv.addObject("lname",
				 * userList.get(0).getLastname());
				 */

				String LICENSE_COUNT_SQL = "			SELECT	L.TX_LICENSE AS TX_LICENSE " + " FROM   LICENSES L "
						+ "       JOIN PRODUCTS P " + "         ON L.TX_PRODUCT = P.ID "
						+ " JOIN ACCOUNTS ON ACCOUNTS.ID = L.ID_ACCOUNT " + "  WHERE  P.FL_LICENSE = 1 "
						+ "   AND ACCOUNTS.TX_EMAIL =  '" + userDetails.getUsername() + "'";

				List<License> licenseList = this.jdbcTemplate.query(LICENSE_COUNT_SQL, new RowMapper<License>() {
					public License mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						License lic = new License();
						lic.setTx_license(resulSet.getString("TX_LICENSE"));
						return lic;

					}
				});

				mv.addObject("licenseCount", licenseList.size());
				
				
				
				EntitlementActions ea = new EntitlementActions();
				List<SENForm> SENFormList = ea.getEntitlementList(session, jdbcTemplate, true, true, "1", "ACTIVE");
				mv.addObject("entitlementCount", SENFormList.size());
				
				
				
				
				

				AccountActions aa = new AccountActions();
				List<AccountVerificationForm> accVerifyList = aa.getAccountVerification(userList.get(0).getId(),
						jdbcTemplate);

				mv.addObject("accVeriyList", accVerifyList);

				TicketActions ta = new TicketActions();
				List<UserTicketsForm> tktTotalList = ta.getTotalCount(jdbcTemplate, 1, "all",
						Integer.toString(userList.get(0).getId()));
				List<UserTicketsForm> tktResolvedList = ta.getResolvedCount(jdbcTemplate, 1, "all",
						Integer.toString(userList.get(0).getId()));
				List<UserTicketsForm> tktRespList = ta.getResponseCount(jdbcTemplate, 1, "all",
						Integer.toString(userList.get(0).getId()));
				List<UserTicketsForm> tktPendingList = ta.getPendingCount(jdbcTemplate, 1, "all",
						Integer.toString(userList.get(0).getId()));

				mv.addObject("totalTickets", (tktTotalList.get(0).getTotalTickets()));
				mv.addObject("pendingTickets", (tktPendingList.get(0).getTotalTickets()));
				mv.addObject("respondedTickets", (tktRespList.get(0).getTotalTickets()));
				mv.addObject("resolvedTickets", (tktResolvedList.get(0).getTotalTickets()));
			
			}
		} else {
			logger.error("Failed to retieve user details");
		}

		return mv;
	}

	@RequestMapping("/user/ticketList")
	public ModelAndView ticketList() {
		ModelAndView mv = new ModelAndView("user/ticketList");
		return mv;
	}

	@RequestMapping("/authenticate")
	public ModelAndView authenticate() {
		ModelAndView mv = new ModelAndView("security/authenticate");
		return mv;
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("security/register");
		return mv;
	}

	@RequestMapping("/invalidSession")
	public ModelAndView invalidSession(HttpSession session) {

		session.invalidate();
		ModelAndView mv = new ModelAndView("security/authenticate", "failuremessage",
				"Session Timeout. Please login again.");
		return mv;
	}

	
	
}
