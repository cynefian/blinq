package com.gsd.sreenidhi.register;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.admin.features.FeatureActions;
import com.gsd.sreenidhi.commons.user.RegisterModule;
import com.gsd.sreenidhi.register.RegisterBeanValidator;
import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.tables.Accounts;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
@RequestMapping("/registerForm")
@SessionAttributes("registerBean")
public class RegisterFormController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterFormController.class);
	
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	public RegisterFormController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	 @ModelAttribute("registerBean")
	 public Register createRegisterFormBean() {
	 	return new Register();
	 }

	 @RequestMapping(method=RequestMethod.GET)  
	 public ModelAndView registerForm() {  
		 return new ModelAndView("security/register"); 
	 }  
	 
	 @RequestMapping(method=RequestMethod.POST)
	 public ModelAndView processSubmit(@ModelAttribute("registerBean") @Valid Register registerBean, 
			 						   BindingResult result, Model model, RedirectAttributes redirectAttrs) {
			
			logger.info("RegisterFormController post called");
			logger.info("Validation result: " + result);
			
			FeatureActions fa = new FeatureActions();
			
			if(fa.validateFeatureFunctionality(jdbcTemplate, "Registration")) {
				
				if (result.hasErrors()) {
					logger.error("Validation failed");
					return new ModelAndView("security/register", "failuremessage", "Registration Failed"); 
				}
				
				final Register registerForm = registerBean;
				
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
					return new ModelAndView("security/register", "failuremessage", "Email already registered");
				}
				
				RegisterModule rm = new RegisterModule(this.jdbcTemplate.getDataSource());
				rm.registerAccount(registerForm);
				logger.info("Registered");
			}else {
				return new ModelAndView("security/register", "failuremessage", "The Registration functionality has currently been disabled.");
			}
			
			return new ModelAndView("security/authenticate","successmessage","Registration successful. Please login.");  
		}
	  
	 @RequestMapping(value="/passwordValidator", method=RequestMethod.POST)
	 public ModelAndView passwordValidator(@ModelAttribute("registerBean") Register registerBean, BindingResult result)
	    throws Exception
	  {
		 
		RegisterBeanValidator registerBeanValidator = new RegisterBeanValidator();
		registerBeanValidator.validate(registerBean, result);
		
		logger.info("registerBeanValidator hasErrors(): "+ result.hasErrors());
		
		if (result.hasErrors()) {
			return new ModelAndView("security/register","failuremessage", "Registration Failed"); 
		}
			return new ModelAndView("security/register","message","Registered"); 
	    
	  }


}
