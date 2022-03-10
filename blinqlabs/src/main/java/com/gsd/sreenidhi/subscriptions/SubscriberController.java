package com.gsd.sreenidhi.subscriptions;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.utils.CalendarUtils;


@Controller
@SessionAttributes("subscriptionBean")
public class SubscriberController {
private static final Logger logger = LoggerFactory.getLogger(SubscriberController.class);
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public SubscriberController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("subscriptionBean")
		 public SubscriptionForm createSubscriptionFormBean() {
		 	return new SubscriptionForm();
		 }
		 
		 @RequestMapping(value ="/subscribe", method=RequestMethod.POST)  
		 public ModelAndView manageSubscriptions(@ModelAttribute("SubscriptionBean") @Valid SubscriptionForm subscriptionBean,
					BindingResult result, Model model, RedirectAttributes redirectAttrs,
					final HttpSession session) {
			 
			 
			 final SubscriptionForm sb = subscriptionBean;
			 final KeyHolder subsKey = new GeneratedKeyHolder();
			
			 Date dt = new Date();
				String date = CalendarUtils.dateToStringDateTimeReadable(dt);
				
			 final String INSERT_SUBSCRIBER_SQL = "if not exists (select * from SUBSCRIBE WHERE TX_EMAIL = ?) "
			 		+ " INSERT INTO SUBSCRIBE(TX_EMAIL, FL_ACTIVE, TS_TIMESTAMP) VALUES(?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBSCRIBER_SQL);
						preparedStatement.setString(1, sb.getTX_EMAIL());
						preparedStatement.setString(2, sb.getTX_EMAIL());
						preparedStatement.setInt(3, 1);
						preparedStatement.setString(4, date);
						return preparedStatement;
					}
				});
							 
				WelcomMailLForm wmf = new WelcomMailLForm();
				EmailForm ef = new EmailForm();
				
				String tempalte = ef.getEmailTemplate();
				String emailMessage = wmf.getTX_SUBSCRIPTION_MESSAGE();
				
				SendMail sm = new SendMail();
				  
				  
				  final String emailMsg = tempalte.replace("${username", sb.getTX_EMAIL()).replace("${title}", "You are subscribed.").replace("${message", emailMessage).replace("${image}", wmf.getImage()).replace("${dateTime}", CalendarUtils.getCurrentTimeStamp()).replace("${disclaimer}", ef.getDisclaimer());
					
				    ExecutorService service = Executors.newFixedThreadPool(4);
				    service.submit(new Runnable() {
				        public void run() {
				        	sm.sendMessage(emailMsg, sb.getTX_EMAIL().trim(), null, "Blinqlabs Subscription", false);
				        }
				    });
			
			 ModelAndView mv = new ModelAndView("common/subscribed");
			 mv.addObject("email",sb.getTX_EMAIL());
			return mv;
			 
		 }
}

