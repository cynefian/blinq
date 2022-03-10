package com.gsd.sreenidhi.commons.ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.services.ServiceTypeForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.admin.tickets.WorkLogForm;
import com.gsd.sreenidhi.commons.AttachmentForm;
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.user.messages.MessageActions;
import com.gsd.sreenidhi.utils.CalendarUtils;


@Controller
@PropertySource("classpath:application.properties")
@SessionAttributes("ticketDetailsBean")
public class TicketDetailController{
	
	 private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);
		
		
		private JdbcTemplate jdbcTemplate = null;
			
			@Autowired
			public TicketDetailController(DataSource dataSource) {
				this.jdbcTemplate = (new JdbcTemplate(dataSource));
			}

			 @ModelAttribute("ticketDetailsBean")
			 public TicketDetailsForm createTicketsBean() {
			 	return new TicketDetailsForm();
			 }
			 
			@RequestMapping(value="/ticketDetails", method=RequestMethod.GET)  
			public @ResponseBody ModelAndView getTicketDetails(HttpSession session,@RequestParam("id") String id,
					@RequestParam(value="source", required=false) String source,
					@RequestParam(value="page", required=false) String page) {
				 
				
				List<TicketDetailsForm> tktList = getTicketList(jdbcTemplate, id);
				
				 
				 int pg;
					if("".equals(page)|| page==null) {
						pg = 1;
					}else {
						pg = Integer.parseInt(page);
					}
					
					int countOnPage = 10;
				 final String GET_TICKET_ATTACHMENTS = 
							" DECLARE @PageNumber AS INT, @RowspPage AS INT"
									 + " SET @PageNumber = " + pg
									 + " SET @RowspPage =  " + countOnPage
									 + " SELECT * FROM ( "
							+ " SELECT  "
							+ "  ROW_NUMBER() OVER(ORDER BY ATTACHMENTS.ID ASC) AS NUMBER, "	
							+ " ATTACHMENTS.ID AS ID, "
							+ " ATTACHMENTS.TX_NAME AS TX_NAME, "
				 		+ " ATTACHMENTS.TX_ATTACHMENT AS TX_ATTACHMENT, "
				 		+ " ATTACHMENTS.TX_TYPE AS TX_TYPE, "
				 		+ " ATTACHMENTS.TX_SIZE AS TX_SIZE, "
				 		+ " TICKET_THREADS.ID AS ID_TICKET_THREAD, "
				 		+ " TICKET_THREADS.TICKET_ID AS ID_TICKET "
				 		+ " FROM ATTACHMENTS "
				 		+ " JOIN TICKET_THREAD_ATTACHMENT ON ATTACHMENTS.ID = TICKET_THREAD_ATTACHMENT.ID_ATTACHMENT "
				 		+ " JOIN TICKET_THREADS ON TICKET_THREADS.ID = TICKET_THREAD_ATTACHMENT.ID_TICKET_THREAD "
				 		+ " JOIN TICKETS ON TICKETS.ID = TICKET_THREADS.TICKET_ID "
				 		+ " WHERE TICKETS.ID = " + id
				 		+ "												 ) AS TBL "
						+ " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
				 		; 
				 
		 		List<AttachmentForm> ticketAttachFormList = jdbcTemplate.query(GET_TICKET_ATTACHMENTS, new RowMapper<AttachmentForm>() {
					public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AttachmentForm att = new AttachmentForm();
						att.setID(resulSet.getInt("ID"));
						att.setTX_DATA(resulSet.getString("TX_ATTACHMENT"));
						att.setTX_NAME(resulSet.getString("TX_NAME"));
						att.setTX_TYPE(resulSet.getString("TX_TYPE"));
						att.setTX_SIZE(resulSet.getString("TX_SIZE"));
						att.setID_LINK(resulSet.getInt("ID_TICKET_THREAD"));
						return att;
					}
				});
		 		
		 		final String GET_TICKET_WORKLOG= 
						 " SELECT  "
						 + " TICKET_WORK_LOG.ID, "
						 + " TICKET_WORK_LOG.ID_TICKET, "
						 + " TICKET_WORK_LOG.ID_USER, "
						 + " TICKET_WORK_LOG.TX_WORKLOG, "
						 + " TICKET_WORK_LOG.TX_DATETIME,"
						 + " TICKET_WORK_LOG.TX_DESCRIPTION,"
						 + " USERS.TX_FIRSTNAME, "
						 + " USERS.TX_LASTNAME "
					+ " FROM TICKET_WORK_LOG "
					+ " JOIN USERS ON TICKET_WORK_LOG.ID_USER = USERS.ID "
			 		+ " WHERE ID_TICKET = " + id ; 
			 		
			 		List<WorkLogForm> ticketWorkLogList = jdbcTemplate.query(GET_TICKET_WORKLOG, new RowMapper<WorkLogForm>() {
						public WorkLogForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
							WorkLogForm wrk = new WorkLogForm();
							wrk.setID(resulSet.getInt("ID"));
							wrk.setID_TICKET(resulSet.getInt("ID_TICKET"));
							wrk.setID_USER(resulSet.getInt("ID_USER"));
							wrk.setTX_WORKLOG(resulSet.getString("TX_WORKLOG"));
							wrk.setTX_USER(resulSet.getString("TX_FIRSTNAME") + " " + resulSet.getString("TX_LASTNAME"));
							wrk.setTX_DATE(resulSet.getString("TX_DATETIME"));
							wrk.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
							return wrk;
						}
					});
			 		
			 		Calendar c = Calendar.getInstance();
			 		c.set(0, 0, 0, 0, 0, 0);
			 		int hours=0;
			 		int mins=0;
			 		
			 		for(int i=0;i<ticketWorkLogList.size();i++) {
			 			String log = ticketWorkLogList.get(i).getTX_WORKLOG();
			 			String[] logarr = log.split(" ");
			 			for(int j=0;j<logarr.length;j++) {
			 				if(logarr[j].contains("w")) {
			 					String unit =logarr[j].replace("w", ""); 
			 					hours = hours + Integer.parseInt(unit.trim())*40;
			 				}else if(logarr[j].contains("W")) {
			 					String unit =logarr[j].replace("W", ""); 
			 					hours = hours + Integer.parseInt(unit.trim())*40;
			 				}else if(logarr[j].contains("d")) {
			 					String unit =logarr[j].replace("d", ""); 
			 					hours = hours + Integer.parseInt(unit.trim())*8;
			 				}else if(logarr[j].contains("D")) {
			 					String unit =logarr[j].replace("D", ""); 
			 					hours = hours + Integer.parseInt(unit.trim())*8;
			 				}else if(logarr[j].contains("h")) {
			 					String unit =logarr[j].replace("h", ""); 
			 					hours = hours + Integer.parseInt(unit.trim());
			 				}else if(logarr[j].contains("H")) {
			 					String unit =logarr[j].replace("H", ""); 
			 					hours = hours + Integer.parseInt(unit.trim());
			 				}else if(logarr[j].contains("m")) {
			 					String unit =logarr[j].replace("m", "");
			 					mins = mins+Integer.parseInt(unit.trim());
			 				}else if(logarr[j].contains("M")) {
			 					String unit =logarr[j].replace("M", ""); 
			 					mins = mins+Integer.parseInt(unit.trim());
			 				}
			 			}
			 		}
			 		
			 		int hourappend = 0;
			 		int minremain = 0;
			 		if(mins>=60) {
			 			hourappend = mins/60;
			 			minremain = mins-(hourappend*60);
			 		}else {
			 			minremain = mins;
			 		}
			 		
			 		hours = hours + hourappend;
			 		
			 		String totalTime = hours +" hours, "+ minremain + " minutes";
			 		
		 		final String GET_TICKET_ATTACHMENTS_COUNT = 
						 " SELECT  "
						 + " COUNT(*) AS ATTACH_COUNT "
					+ " FROM ATTACHMENTS "
			 		+ " JOIN TICKET_THREAD_ATTACHMENT ON ATTACHMENTS.ID = TICKET_THREAD_ATTACHMENT.ID_ATTACHMENT "
			 		+ " JOIN TICKET_THREADS ON TICKET_THREADS.ID = TICKET_THREAD_ATTACHMENT.ID_TICKET_THREAD "
			 		+ " JOIN TICKETS ON TICKETS.ID = TICKET_THREADS.TICKET_ID "
			 		+ " WHERE TICKETS.ID = " + id ; 
			 		
			 		List<AttachmentForm> ticketAttachFormCountList = jdbcTemplate.query(GET_TICKET_ATTACHMENTS_COUNT, new RowMapper<AttachmentForm>() {
						public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
							AttachmentForm att = new AttachmentForm();
							att.setAttachCount(resulSet.getInt("ATTACH_COUNT"));
							return att;
						}
					});
			 		
			 	int totalCount =  ticketAttachFormCountList.get(0).getAttachCount();
				double rounder = (double)totalCount/(double) countOnPage;
			 	int pages = (int) Math.ceil(rounder);
			 	
			 	
			 	final String GET_TICKET_SENT = 
						 " SELECT  "
						 + " SERVICE_ENTITLEMENT.ID AS ID_SERVICE_ENTITLEMENT,"
						 + " SERVICE_ENTITLEMENT.TX_ENTITLEMENT AS TX_SERVICE_ENTITLEMENT, "
						 + " SERVICE_ENTITLEMENT.TS_START AS TS_START, "
						 + " SERVICE_ENTITLEMENT.TS_END AS TS_END, "
						 + " SERVICE_ENTITLEMENT.FL_ACTIVE AS FL_ACTIVE, "
						 + " SERVICE_ENTITLEMENT.TX_ENTITLEMENT_DESCRIPTION AS TX_DESCRIPTION "
					+ " FROM SERVICE_ENTITLEMENT "
			 		+ " JOIN TICKETS ON TICKETS.ID_SERVICE_ENTITLEMENT = SERVICE_ENTITLEMENT.ID "
			 		+ " WHERE TICKETS.ID = " + id ; 
			 		
			 		List<SENForm> senList = jdbcTemplate.query(GET_TICKET_SENT, new RowMapper<SENForm>() {
						public SENForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
							SENForm sen = new SENForm();
							sen.setID(resulSet.getInt("ID_SERVICE_ENTITLEMENT"));
							sen.setTX_ENTITLEMENT(resulSet.getString("TX_SERVICE_ENTITLEMENT"));
							sen.setTS_START(resulSet.getString("TS_START"));
							sen.setTS_END(resulSet.getString("TS_END"));
							sen.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE"));
							sen.setTX_ENTITLEMENT_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
							return sen;
						}
					});
			 		
			 	
				 ModelAndView mv = new ModelAndView("common/ticketDetails");
				 mv.addObject("ticketList", tktList);
				 mv.addObject("ticketAttachments", ticketAttachFormList);
				 mv.addObject("tktTab",source);
				 mv.addObject("totalCount", totalCount);
				 mv.addObject("currentPage", pg);
				 mv.addObject("totalPages",pages);
				 mv.addObject("worklog",ticketWorkLogList);
				 mv.addObject("totalLoggedWork", totalTime);
				 mv.addObject("senList",senList);
				 
				 return mv;
			 }
			
			
			private List<TicketDetailsForm> getTicketList(JdbcTemplate jdbcTemplate2, String id) {
				 final String GET_TICKET_QUERY_SQL = "" 
						 + " SELECT "
						 + "				 		 T.ID AS TICKET_ID, "
						 + "				 	 T.TX_SUBJECT AS TICKET_SUBJECT, "
						 + "				 		 T.TX_TIME AS CREATE_TIME, "
						 + "				 		 T.ID_PRIORITY AS PRIORITY, "
						 + "				 		 T.ID_SERVICE_ENTITLEMENT AS ID_SERVICE_ENTITLEMENT, "
						 + "						 T.ID_ACCOUNT AS ID_ACCOUNT, "
						 + "  						 (SELECT TX_STATUS FROM STATUSTAB WHERE ID = T.ID_STATUS) AS TX_STATUS,  "	
						 + " 						 T.ID_STATUS AS ID_STATUS, "
						 + "						 (SELECT TX_EMAIL FROM ACCOUNTS WHERE ID = T.ID_ACCOUNT) AS ACCOUNT_EMAIL, "
						 + "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = T.ID_ACCOUNT) AS ACCOUNT_NAME, "
						 + "						 T.ID_AGENT AS   ID_AGENT, "
						 + "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = T.ID_AGENT) AS AGENT_NAME, "
						 + "						 (SELECT TX_EMAIL FROM ACCOUNTS WHERE ID = T.ID_AGENT) AS AGENT_EMAIL, "
						 + "				 		 H.ID AS THREAD_ID, "
						 + "				 		 H.TX_BODY AS THREAD_BODY, "
						 + "				 		 H.TX_READ AS FL_READ, "
						 + "				 		 H.TX_TIME AS THREAD_TIME, "
						 + "				 		 H.TX_ATTACHMENT AS ID_ATTACHMENT, "
						 + "				 		 H.ID_PARENT AS ID_THREAD_PARENT,"
						 + "						 H.ID_USER AS ID_THREAD_USER, "
						 + "						 H.FL_EDIT AS FL_EDIT, " 
						 + "						 (SELECT CONCAT(TX_FIRSTNAME,' ',TX_LASTNAME) FROM USERS WHERE ID_ACCOUNT = H.ID_USER) AS THREAD_USER_NAME, "  
						 + "						 (SELECT TX_EMAIL FROM ACCOUNTS WHERE ID = H.ID_USER) AS THREAD_USER_EMAIL, "
						 + "						 (SELECT " + 
						 "													CASE " + 
						 "														WHEN COUNT(*)>0 THEN 1 " + 
						 "														ELSE 0 " + 
						 "													END AS FL_ATTACHMENTS " + 
						 "													FROM TICKET_THREAD_ATTACHMENT " + 
						 "													WHERE ID_TICKET_THREAD = H.ID) AS FL_ATTACHMENT"
						 + "				 		 FROM TICKETS T, "
						 + "				 		 TICKET_THREADS H "
						 + "				 		 WHERE T.ID =  " + id 
						 + "				 		 AND T.ID = H.TICKET_ID "
						 + "				 		 ORDER BY H.ID ASC";
				 
				 List<TicketDetailsForm> tktList = this.jdbcTemplate.query(GET_TICKET_QUERY_SQL, new RowMapper<TicketDetailsForm>() {
			            public TicketDetailsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	TicketDetailsForm tkt = new TicketDetailsForm();
			            	
			            	tkt.setTICKET_ID(resulSet.getInt("TICKET_ID"));
			            	tkt.setTICKET_SUBJECT(resulSet.getString("TICKET_SUBJECT"));
			            	tkt.setCREATE_TIME(resulSet.getString("CREATE_TIME"));
			            	tkt.setPRIORITY(resulSet.getInt("PRIORITY"));
			            	tkt.setTICKET_PRODUCT(resulSet.getString("ID_SERVICE_ENTITLEMENT"));
			            	tkt.setTHREAD_ID(resulSet.getInt("THREAD_ID"));
			            	tkt.setTHREAD_BODY(resulSet.getString("THREAD_BODY"));
			            	tkt.setFL_READ(resulSet.getBoolean("FL_READ"));
			            	tkt.setTHREAD_TIME(resulSet.getString("THREAD_TIME"));
			            	tkt.setID_ATTACHMENT(resulSet.getShort("ID_ATTACHMENT"));
			            	tkt.setID_THREAD_PARENT(resulSet.getInt("ID_THREAD_PARENT"));
			            	tkt.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
			            	tkt.setACCOUNT_EMAIL(resulSet.getString("ACCOUNT_EMAIL"));
			            	tkt.setACCOUNT_NAME(resulSet.getString("ACCOUNT_NAME"));
			            	tkt.setID_AGENT(resulSet.getInt("ID_AGENT"));
			            	tkt.setAGENT_NAME(resulSet.getString("AGENT_NAME"));
			            	tkt.setAGENT_EMAIL(resulSet.getString("AGENT_EMAIL"));
			            	tkt.setID_STATUS(resulSet.getInt("ID_STATUS"));
			            	tkt.setTX_STATUS(resulSet.getString("TX_STATUS"));
			            	tkt.setID_THREAD_USER(resulSet.getInt("ID_THREAD_USER"));
			            	tkt.setTHREAD_USER_NAME(resulSet.getString("THREAD_USER_NAME"));
			            	tkt.setTHREAD_USER_EMAIL(resulSet.getString("THREAD_USER_EMAIL"));
			            	tkt.setFL_ATTACHMENT(resulSet.getInt("FL_ATTACHMENT"));
			            	tkt.setFL_EDIT(resulSet.getInt("FL_EDIT"));
			            	return tkt;
			            }
			        });
				 return tktList;
			}

			@RequestMapping(value="postDetails", method=RequestMethod.POST)  
			public ModelAndView postTicketDetails(@ModelAttribute("ticketDetailsBean") @Valid TicketDetailsForm ticketDetailsBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session, @RequestParam("id") String id) {
				
				final int ticketId = Integer.parseInt(id);
				final TicketDetailsForm tdf = ticketDetailsBean;
				final int userId = (Integer) session.getAttribute("userId");
				final String time = CalendarUtils.dateToStringDateTimeReadable(new Date());
				
				final KeyHolder threadKey = new GeneratedKeyHolder();
				final String INSERT_TICKET_THREAD_SQL = "INSERT INTO TICKET_THREADS(TICKET_ID, TX_BODY, TX_READ, ID_USER, TX_ATTACHMENT, TX_TIME) VALUES (?,?,?,?,?,?)";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_THREAD_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setInt(1, ticketId);
						preparedStatement.setString(2, tdf.getTHREAD_BODY() );
						preparedStatement.setInt(3, 0);
						preparedStatement.setInt(4, userId);
						preparedStatement.setString(5, null);
						preparedStatement.setString(6, time);
						return preparedStatement;
					}
				},threadKey);
				
				
				if(tdf.getTX_DATA()!=null && !"".equals(tdf.getTX_DATA())) {
					final KeyHolder attachKey = new GeneratedKeyHolder();
					final String INSERT_ATTACHMENT_SQL = "INSERT INTO ATTACHMENTS(TX_ATTACHMENT, TX_NAME, TX_SIZE, TX_TYPE) VALUES (?,?,?,?)";    	
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTACHMENT_SQL, Statement.RETURN_GENERATED_KEYS);
							preparedStatement.setString(1, tdf.getTX_DATA());
							preparedStatement.setString(2, tdf.getTX_NAME());
							preparedStatement.setString(3, tdf.getTX_SIZE());
							preparedStatement.setString(4, tdf.getTX_TYPE());
							return preparedStatement;
						}
					},attachKey);
					
					
					
					final String INSERT_TICKET_THREAD_ATTACHMENT_SQL = "INSERT INTO TICKET_THREAD_ATTACHMENT(ID_TICKET_THREAD, ID_ATTACHMENT) VALUES (?,?)";    	
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_THREAD_ATTACHMENT_SQL, Statement.RETURN_GENERATED_KEYS);
							preparedStatement.setInt(1, threadKey.getKey().intValue());
							preparedStatement.setInt(2, attachKey.getKey().intValue());
							return preparedStatement;
						}
					});
				}
				
				
				
				TicketActionController tac = new TicketActionController(this.jdbcTemplate.getDataSource());
				
				ModelAndView mav = getTicketDetails(session, id, "", "");
				List<TicketDetailsForm> tktList = (List<TicketDetailsForm>) mav.getModel().get("ticketList");
				if(!tktList.isEmpty()) {
					int listSize = tktList.size();
					TicketDetailsForm tDF = tktList.get(listSize-1);
					if(tDF.getID_ACCOUNT()==userId) {
						tac.updateStatus(ticketId,2);
					}else {
						tac.updateStatus(ticketId,4);
					}
			}
				
				List<TicketDetailsForm> ticketList = getTicketList(jdbcTemplate, id);
				if(ticketList!=null && ticketList.size()>0) {
					TicketDetailsForm tkt = ticketList.get(0);
					MessageActions ma = new MessageActions();
					TicketMessageForm tmf = new TicketMessageForm();
					
					int tktId = tkt.getTICKET_ID();
					int creatorId = tkt.getID_ACCOUNT();
					int agentId= tkt.getID_AGENT();
					if(agentId==0) {
						agentId=2;
					}
					
					String toEmail = "";
					String recptUser = "";
					int toId = 0;
					int fromId = 0;
					
					String hrefRedirect = fetchURLProperties() + "/ticketDetails?id="+tktId;
					
					if(Integer.parseInt(session.getAttribute("userId").toString().trim())!=creatorId){
						ma.insertMessage(jdbcTemplate, "Ticket Updated- ["+id+"]: "+tkt.getTICKET_SUBJECT(), tmf.getSYSTEM_MESSAGE().replace("{a-href}", hrefRedirect), creatorId, agentId, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
						toEmail = tkt.getACCOUNT_EMAIL();
						toId = creatorId;
						recptUser = tkt.getACCOUNT_NAME();
						fromId = agentId;
						
					}else {
						ma.insertMessage(jdbcTemplate, "Ticket Updated- ["+id+"]: "+tkt.getTICKET_SUBJECT(), tmf.getSYSTEM_MESSAGE().replace("{a-href}", hrefRedirect), agentId, creatorId, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
						toEmail = tkt.getAGENT_EMAIL();
						recptUser = tkt.getAGENT_NAME();
						toId = agentId;
						fromId = creatorId;
						if("".equals(toEmail) || toEmail == null) {
							toEmail = "support@blinqlabs.com";
							recptUser = "Blinqlabs Support";
							toId = 2;
						}
					}
					
					EmailForm ef = new EmailForm();
					String tempalte = ef.getEmailTemplate();
					String emailMessage = tmf.getEMAIL_MESSAGE(); 
					
					String msg = tempalte.replace("${username}", recptUser);
					msg = msg.replace("${title}", "There's an update for you.");
					msg = msg.replace("${message}", emailMessage);
					msg = msg.replace("${image}", tmf.getImage());
					msg = msg.replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
					msg = msg.replace("${disclaimer}", ef.getDisclaimer());
					if(!"".equals(toEmail)) {
						SendMail sm = new SendMail();
						  
						  final String emailMsg = msg;
						  final String rcpt = toEmail;
							
						    ExecutorService service = Executors.newFixedThreadPool(4);
						    service.submit(new Runnable() {
						        public void run() {
						        	sm.sendMessage(emailMsg,rcpt, null, "Blinqlabs Update - You have a new message.", false);
						        }
						    });
					}
					
				}
				
				String redirection = "redirect:/ticketDetails?id="+id;
				return new ModelAndView(redirection);				
			}


			@RequestMapping(value = "/viewThreadAttachment", method = RequestMethod.GET)
			public @ResponseBody List<AttachmentForm> getTicketThreadAttachments(@RequestParam("key") String key, HttpSession session) {
				String GET_TICKET_THREAD_ATTACHMENTS_SQL = "SELECT "
						+ "	ATTACHMENTS.ID AS ID, "
						+ "	ATTACHMENTS.TX_ATTACHMENT AS TX_ATTACHMENT, "
						+ "	ATTACHMENTS.TX_NAME AS TX_NAME, "
						+ "	ATTACHMENTS.TX_TYPE AS TX_TYPE, "
						+ "	ATTACHMENTS.TX_SIZE AS TX_SIZE "
						+ "FROM TICKET_THREAD_ATTACHMENT "
						+ "JOIN ATTACHMENTS "
						+ "ON TICKET_THREAD_ATTACHMENT.ID_ATTACHMENT = ATTACHMENTS.ID "
						+ "WHERE TICKET_THREAD_ATTACHMENT.ID_TICKET_THREAD="+key;

				List<AttachmentForm> tktThreadAttachFormList = jdbcTemplate.query(GET_TICKET_THREAD_ATTACHMENTS_SQL, new RowMapper<AttachmentForm>() {
					public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AttachmentForm att = new AttachmentForm();
						att.setID(resulSet.getInt("ID"));
						att.setTX_DATA(resulSet.getString("TX_ATTACHMENT"));
						att.setTX_NAME(resulSet.getString("TX_NAME"));
						att.setTX_TYPE(resulSet.getString("TX_TYPE"));
						att.setTX_SIZE(resulSet.getString("TX_SIZE"));
						return att;
					}
				});
				return tktThreadAttachFormList;
			}
			
			@RequestMapping(value="/deleteTicket", method=RequestMethod.GET)  
			public ModelAndView deleteTicket(HttpSession session,@RequestParam("id") String id, @RequestParam("source") String source) {
				String GETATTACHMENTS = "SELECT ID_ATTACHMENT "
						+ " FROM TICKET_THREAD_ATTACHMENT "
						+ " JOIN TICKET_THREADS ON TICKET_THREADS.ID = TICKET_THREAD_ATTACHMENT.ID_TICKET_THREAD"
						+ " WHERE TICKET_THREADS.TICKET_ID = " + id;
				
				List<AttachmentForm> tktThreadAttachFormList = jdbcTemplate.query(GETATTACHMENTS, new RowMapper<AttachmentForm>() {
					public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						AttachmentForm att = new AttachmentForm();
						att.setID(resulSet.getInt("ID_ATTACHMENT"));
						return att;
					}
				});
				
				String DEL_TICKET_THREAD_ATTACH = "DELETE FROM TICKET_THREAD_ATTACHMENT WHERE ID_TICKET_THREAD IN (SELECT ID FROM TICKET_THREADS WHERE TICKET_ID = ? )";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DEL_TICKET_THREAD_ATTACH);
						preparedStatement.setInt(1, Integer.parseInt(id));
						return preparedStatement;
					}
				});
				
				String DEL_TICKET_THREAD = "DELETE FROM TICKET_THREADS WHERE TICKET_ID = ?";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DEL_TICKET_THREAD);
						preparedStatement.setInt(1, Integer.parseInt(id));
						return preparedStatement;
					}
				});
				
				String DEL_TICKET= "DELETE FROM TICKETS WHERE ID = ?";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DEL_TICKET);
						preparedStatement.setInt(1, Integer.parseInt(id));
						return preparedStatement;
					}
				});
				
				for(int i=0;i<tktThreadAttachFormList.size();i++) {
					final int att_id = tktThreadAttachFormList.get(i).getID();
					String DEL_ATTACHMENT = "DELETE FROM ATTACHMENTS WHERE ID = ?" ;
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(DEL_TICKET);
							preparedStatement.setInt(1, att_id);
							return preparedStatement;
						}
					});
				}
				
				String redirection = "";
				if("USER".equalsIgnoreCase(source)) {
					redirection = "redirect:/user/UserTickets?page=1&filter=all";
				}else {
					redirection = "redirect:/admin/adminTickets?page=1&filter=all";
				}
				
				
				return new ModelAndView(redirection);
			}
			
			
			@RequestMapping(value="/updateComment", method=RequestMethod.POST)  
			public ModelAndView updateComment(@ModelAttribute("ticketDetailsBean") @Valid TicketDetailsForm ticketDetailsBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session, @RequestParam("id") String id) {
				
				final KeyHolder threadKey = new GeneratedKeyHolder();
				final String UPDATE_TICKET_THREAD_SQL = "UPDATE TICKET_THREADS SET TX_BODY = ?, FL_EDIT = ? WHERE ID = ?";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET_THREAD_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, ticketDetailsBean.getTHREAD_BODY());
						preparedStatement.setInt(2, 1);
						preparedStatement.setInt(3, Integer.parseInt(id));
						return preparedStatement;
					}
				},threadKey);
				
				String redirection = "redirect:/ticketDetails?id="+ticketDetailsBean.getTICKET_ID();
				return new ModelAndView(redirection);	
				
			}
			
			@RequestMapping(value="/redirectTicketId", method=RequestMethod.POST)  
			public ModelAndView redirectTicket(HttpSession session, @RequestParam("id") String id) {
				String redirection = "redirect:/ticketDetails?id="+id;
				return new ModelAndView(redirection);	
			}
			
			public String fetchURLProperties() {
				
				String baseUrl = "";
				Properties properties = new Properties();
				try {
					File file = ResourceUtils.getFile("classpath:application.properties");
					InputStream in = new FileInputStream(file);
					properties.load(in);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(properties!=null) {
					baseUrl =  properties.getProperty("application.url");
				}
				return baseUrl;
			}
			
		
				
			
			
}
