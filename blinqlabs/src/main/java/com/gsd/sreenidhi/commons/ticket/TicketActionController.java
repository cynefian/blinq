package com.gsd.sreenidhi.commons.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gsd.sreenidhi.admin.tickets.AdminTicketController;

@Controller
public class TicketActionController {

	 private static final Logger logger = LoggerFactory.getLogger(TicketActionController.class);
		
		
		private JdbcTemplate jdbcTemplate = null;
			
			@Autowired
			public TicketActionController(DataSource dataSource) {
				this.jdbcTemplate = (new JdbcTemplate(dataSource));
			}

			@RequestMapping(value="/updateStatus", method=RequestMethod.POST)  
			public @ResponseBody ModelAndView postTicketDetails(HttpSession session,@RequestParam("id") String id, 
					@RequestParam("action") String action) {
				TicketDetailController tdc = new TicketDetailController(this.jdbcTemplate.getDataSource());
				final int ticketId = Integer.parseInt(id);
				final int statusId = Integer.parseInt(action);
					
				updateStatus(ticketId,statusId);
				
				String redirection="redirect:/ticketDetails?id="+id;
				return new ModelAndView(redirection);
			}
			
			@RequestMapping(value="/updateStatus", method=RequestMethod.GET)  
			public @ResponseBody ModelAndView getTicketDetails(HttpSession session,@RequestParam("id") String id, 
					@RequestParam("action") String action) {
			
				final int ticketId = Integer.parseInt(id);
				final int statusId = Integer.parseInt(action);
					
				updateStatus(ticketId,statusId);
				
				String redirection="redirect:/ticketDetails?id="+id;
				return new ModelAndView(redirection);
			}
			
				
			
			public void updateStatus(int ticketId, int statusId){
				final String UPDATE_TICKET_STATUS_SQL = "UPDATE TICKETS SET ID_STATUS = ? WHERE ID = ?";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET_STATUS_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setInt(1, statusId);
						preparedStatement.setInt(2, ticketId);
						return preparedStatement;
					}
				});	
			}
}
