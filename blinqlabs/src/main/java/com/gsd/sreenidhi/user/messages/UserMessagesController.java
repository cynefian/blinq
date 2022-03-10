package com.gsd.sreenidhi.user.messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.security.passwordManagement.PasswordManagementController;
import com.gsd.sreenidhi.security.passwordManagement.PasswordManagementForm;

@Controller
@SessionAttributes("UserMessageBean")
public class UserMessagesController {

private static final Logger logger = LoggerFactory.getLogger(UserMessagesController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public UserMessagesController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("UserMessageBean")
		 public UserMessageForm createUserMessageBean() {
		 	return new UserMessageForm();
		 }
		 
		 @RequestMapping(value="/user/UserMessages", method=RequestMethod.GET)  
			public ModelAndView userMessageGet(HttpSession session,
					@RequestParam(value="page", required = false) String p , 
					@RequestParam(value="filter", required=false) String filter, 
					@RequestParam(value="search", required=false) String search) {
				
			 int countOnPage = 10;
			 int page = 1;
			 if (p==null) {
				 page = 1;
			 }else {
				 page = Integer.parseInt(p);
			 }
			 
			 String filterQuery = null;
				String subQ = "";
				
				if(filter!=null && filter.equalsIgnoreCase("U")) {
					filterQuery = " TX_READ = 0";
				}else if(filter!=null && filter.equalsIgnoreCase("S")) {
					filterQuery = " TX_STAR = 1";
				}else if(filter!=null && filter.equalsIgnoreCase("R")) {
					filterQuery = " TX_READ = 1";
				}else if(filter!=null && filter.equalsIgnoreCase("F")) {
					filterQuery = " TX_FLAG = 1";
				}else {
					filterQuery = null;
				}
				
				if(filterQuery!=null) {
					subQ = subQ + " AND " + filterQuery;
				}
				
				if(search!=null && !search.trim().equalsIgnoreCase("")) {
					subQ = subQ + " AND  (MESSAGES.TX_SUBJECT LIKE '%"+search+"%' OR MESSAGES.TX_BODY LIKE '%\"+search+\"%' )";
				}
				
				
			 logger.info("Retrieving User Messages");
			 
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 
			 MessageActions ma = new MessageActions();
			 List<UserMessageForm> msgcountList = ma.getMessageCount(this.jdbcTemplate, userId, filter);
			 
		 		int totalCount =  msgcountList.get(0).getQueryMessageCount();
		 		double rounder = (double)totalCount/(double) countOnPage;
			 	int pages = (int) Math.ceil(rounder);
			 	
			 
				final String GET_MESSAGES_QUERY_SQL = " DECLARE @PageNumber AS INT, @RowspPage AS INT"
						+ " SET @PageNumber = " + page
						+ " SET @RowspPage = " + countOnPage
						+ " SELECT * FROM ( "
						+ "			SELECT									 ROW_NUMBER() OVER(ORDER BY MESSAGES.ID DESC) AS NUMBER, "
						+ "					    					MESSAGES.ID AS ID, "
						+ "						 MESSAGES.TX_SUBJECT AS SUBJECT, "
						+ "						 MESSAGES.TX_BODY AS BODY, "
						+ "						 MESSAGES.ID_TO AS ID_TO, "
						+ "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_TO) AS TO_USER_NAME, "
						+ "						 MESSAGES.ID_FROM AS ID_FROM, "
						+ "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_FROM) AS FROM_USER_NAME, "
						+ "						 (SELECT TX_IMAGE FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_FROM) AS FROM_USER_IMAGE, "
						+ "						 MESSAGES.TX_READ AS TX_READ, "
						+ "						 MESSAGES.TX_STAR AS TX_STAR, "
						+ "						 MESSAGES.TX_TIME AS TX_TIME, "
						+ "						 MESSAGES.TX_ATTACHMENT AS TX_ATTACHMENT, "
						+ "						 MESSAGES.TX_FLAG AS TX_FLAG, "
						+ "						 MESSAGES.ID_PRIORITY AS ID_PRIORITY, "
						+ "						 MESSAGES.ID_PARENT AS ID_PARENT "
						+ "						 from MESSAGES "
						+ "						 WHERE ID_TO =  " + userId + subQ
						+ "												 ) AS TBL "
						+ " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
			 
				logger.info("uid:"+uid);
				
				 
					
					List<UserMessageForm> msgList = this.jdbcTemplate.query(GET_MESSAGES_QUERY_SQL, new RowMapper<UserMessageForm>() {
			            public UserMessageForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserMessageForm msg = new UserMessageForm();
			            	
			            	msg.setId(resulSet.getInt("ID"));
			            	msg.setTX_SUBJECT(resulSet.getString("SUBJECT"));
			            	msg.setTX_BODY(resulSet.getString("BODY"));
			            	msg.setID_TO(resulSet.getInt("ID_TO"));
			            	msg.setTX_FROM_NAME((resulSet.getString("FROM_USER_NAME")==null)?"System Admin":resulSet.getString("FROM_USER_NAME"));
			            	msg.setTX_FROM_USER_IMAGE(resulSet.getString("FROM_USER_IMAGE"));
			            	msg.setTX_READ(resulSet.getInt("TX_READ")==1?true:false);
			            	msg.setTX_STAR(resulSet.getInt("TX_STAR")==1?true:false);
			            	msg.setTX_FLAG(resulSet.getString("TX_FLAG"));
			            	msg.setTX_TIME(resulSet.getString("TX_TIME"));
			            	msg.setTX_ATTACHMENT(resulSet.getString("TX_ATTACHMENT"));
			                msg.setID_PRIORITY(resulSet.getInt("ID_PRIORITY"));
			                msg.setID_PARENT(resulSet.getInt("ID_PARENT"));
			            	
			            	return msg;
			            }
			        });
			 
			 
			 ModelAndView mv = new ModelAndView("user/inboxList");
			 mv.addObject("messagelist", msgList);
			 mv.addObject("currentPage",page);
			 mv.addObject("totalPages",pages);
			 mv.addObject("filter",filter);
			 mv.addObject("search",search);
			 return mv;
		 }
		 
		 
		 @RequestMapping(value="/user/UserMessages", method=RequestMethod.POST)  
			public ModelAndView userMessagePost(@ModelAttribute("UserMessageBean") @Valid UserMessageForm userMessageForm, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session,
					   @RequestParam(value="page", required = false) String p , 
						@RequestParam(value="filter", required=false) String filter, 
						@RequestParam(value="search", required=false) String search) {
			 	
			 	String redirection = "redirect:/user/UserMessages?page="+p;
			 	if(filter!=null) {
			 		redirection = redirection + "&filter="+filter;
			 	}
			 	if(search!=null) {
			 		redirection = redirection + "&search="+search;
			 	}
			 	ModelAndView mv = new ModelAndView(redirection);
				return mv;
		 }
			 
		 
		 @RequestMapping(value="/user/message/updateMessageStar", method=RequestMethod.GET)  
			public ModelAndView updateMessageStar(HttpSession session, @RequestParam("star") int star , @RequestParam(value="source", required=true) String source, 
					@RequestParam(value="id", required=true) int id) {
			 
			 final String UPDATE_MESSAGE_STAR = "UPDATE MESSAGES SET TX_STAR = ? WHERE ID = ?";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE_STAR);
						preparedStatement.setInt(1, star);
						preparedStatement.setInt(2,  id);
						return preparedStatement;
					}
				});	
			 
			 
			 ModelAndView mv = new ModelAndView("redirect:/user/UserMessages?page=1");
			 return mv;
		 }
		 
		 
		 @RequestMapping(value="/user/message/readMessage", method=RequestMethod.GET)  
			public ModelAndView readMessage(HttpSession session,  @RequestParam(value="id", required=true) int id) {
			 
			 final String READ_MESSAGE_STAR =  "			SELECT			"
						+ "					    					MESSAGES.ID AS ID, "
						+ "						 MESSAGES.TX_SUBJECT AS SUBJECT, "
						+ "						 MESSAGES.TX_BODY AS BODY, "
						+ "						 MESSAGES.ID_TO AS ID_TO, "
						+ "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_TO) AS TO_USER_NAME, "
						+ "						 MESSAGES.ID_FROM AS ID_FROM, "
						+ "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_FROM) AS FROM_USER_NAME, "
						+ "						 (SELECT TX_IMAGE FROM USERS WHERE ID_ACCOUNT = MESSAGES.ID_FROM) AS FROM_USER_IMAGE, "
						+ "						 MESSAGES.TX_READ AS TX_READ, "
						+ "						 MESSAGES.TX_STAR AS TX_STAR, "
						+ "						 MESSAGES.TX_TIME AS TX_TIME, "
						+ "						 MESSAGES.TX_ATTACHMENT AS TX_ATTACHMENT, "
						+ "						 MESSAGES.TX_FLAG AS TX_FLAG, "
						+ "						 MESSAGES.ID_PRIORITY AS ID_PRIORITY, "
						+ "						 MESSAGES.ID_PARENT AS ID_PARENT "
						+ "						 from MESSAGES "
						+ "						 WHERE ID =  " + id    	;
			 
			 List<UserMessageForm> msgList = this.jdbcTemplate.query(READ_MESSAGE_STAR, new RowMapper<UserMessageForm>() {
		            public UserMessageForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
		            	UserMessageForm msg = new UserMessageForm();
		            	
		            	msg.setId(resulSet.getInt("ID"));
		            	msg.setTX_SUBJECT(resulSet.getString("SUBJECT"));
		            	msg.setTX_BODY(resulSet.getString("BODY"));
		            	msg.setID_TO(resulSet.getInt("ID_TO"));
		            	msg.setTX_FROM_NAME((resulSet.getString("FROM_USER_NAME")==null)?"System Admin":resulSet.getString("FROM_USER_NAME"));
		            	msg.setTX_FROM_USER_IMAGE(resulSet.getString("FROM_USER_IMAGE"));
		            	msg.setTX_READ(resulSet.getInt("TX_READ")==1?true:false);
		            	msg.setTX_STAR(resulSet.getInt("TX_STAR")==1?true:false);
		            	msg.setTX_FLAG(resulSet.getString("TX_FLAG"));
		            	msg.setTX_TIME(resulSet.getString("TX_TIME"));
		            	msg.setTX_ATTACHMENT(resulSet.getString("TX_ATTACHMENT"));
		                msg.setID_PRIORITY(resulSet.getInt("ID_PRIORITY"));
		                msg.setID_PARENT(resulSet.getInt("ID_PARENT"));
		            	
		            	return msg;
		            }
		        });
			 
			 final String UPDATE_MESSAGE_STAR = "UPDATE MESSAGES SET TX_READ = ? WHERE ID = ?";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE_STAR);
						preparedStatement.setInt(1, 1);
						preparedStatement.setInt(2,  id);
						return preparedStatement;
					}
				});	
			 
			 
			 ModelAndView mv = new ModelAndView("messages/viewMessage");
			 mv.addObject("messagelist", msgList);
			 return mv;
		 }
}
