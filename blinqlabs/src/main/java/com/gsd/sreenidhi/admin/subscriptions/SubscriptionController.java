package com.gsd.sreenidhi.admin.subscriptions;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gsd.sreenidhi.subscriptions.SubscriptionForm;


@Controller
@SessionAttributes("subscriptionBean")
public class SubscriptionController {
private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public SubscriptionController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("subscriptionBean")
		 public SubscriptionForm createSubscriptionFormBean() {
		 	return new SubscriptionForm();
		 }
		 
		 @RequestMapping(value ="/admin/subscriptionManagement", method=RequestMethod.GET)  
		 @PreAuthorize("hasAuthority('PERM_SUBSCRIPTION')")
		 public ModelAndView manageSubscriptions(HttpSession session) {
			 
			 ModelAndView mv = new ModelAndView("admin/subscriptions/manageSubscriptions"); 
			return null;
			 
		 }
}
