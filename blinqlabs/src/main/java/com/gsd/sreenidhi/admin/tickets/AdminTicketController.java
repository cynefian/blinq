package com.gsd.sreenidhi.admin.tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.gsd.sreenidhi.admin.products.ProductController;
import com.gsd.sreenidhi.admin.products.ProductForm;
import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.commons.serviceEntitlement.TierRolloverForm;
import com.gsd.sreenidhi.user.tickets.UserTicketsController;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;
import com.gsd.sreenidhi.utils.CalendarUtils;


@Controller
@SessionAttributes("adminTicketsBean")
public class AdminTicketController {

	private static final long serialVersionUID = 1195564381169111658L;
	
	/**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";


    private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public AdminTicketController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("adminTicketsBean")
		 public UserTicketsForm createUserTicketsBean() {
		 	return new UserTicketsForm();
		 }
		 
		 @RequestMapping(value="/admin/adminTickets", method=RequestMethod.GET) 
		 @PreAuthorize("hasAuthority('PERM_TICKET_VIEW')")
			public ModelAndView adminTicketsGet(HttpSession session, @RequestParam("filter") String filter,
					 @RequestParam("page") int page) {
			 
			 int countOnPage = 10;
			 logger.info("Retrieving Tickets");
			 
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 logger.info("uid:"+uid);
			 
			 String queryStringPlaceHolder = "";
			 if(filter!=null) {
				 
				 if("all".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=""; 
				 }else if("pending".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder="WHERE STATUSTAB.ID in (1,2,3,6)";
				 } 
				 else if("escalated".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder="WHERE STATUSTAB.ID in (6)";
				 } 
				 else if("responded".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder="WHERE STATUSTAB.ID in (4)";
				 } 
				 else if("resolved".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder="WHERE STATUSTAB.ID in (5)";
				 } 
				 else if("closed".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder="WHERE STATUSTAB.ID in (5)";
				 } 
				 else  {
					 queryStringPlaceHolder=""; 
				 } 
			 }
			 
			 	final String GET_TICKET_COUNT_SQL = "SELECT COUNT(*) AS COUNT " + 
					   "						 from TICKETS    " + 
						"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + queryStringPlaceHolder +
						"" ;
			 	
			 	
			 	List<UserTicketsForm> tktcountList = this.jdbcTemplate.query(GET_TICKET_COUNT_SQL, new RowMapper<UserTicketsForm>() {
		            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
		            	UserTicketsForm tkt = new UserTicketsForm();
		            	
		            	tkt.setQueryTicketCount(resulSet.getInt("COUNT"));
		            	return tkt;
		            }
		        });
			 	
		 		int totalCount =  tktcountList.get(0).getQueryTicketCount();
		 		double rounder = (double)totalCount/(double) countOnPage;
			 	
			 	int pages = (int) Math.ceil(rounder);
			 	
			 	logger.info("Total count: " + totalCount);
			 	logger.info("Total rounder: " + rounder);
			 	logger.info("Total Pages: " + pages);
			 	
				final String GET_TICKETS_QUERY_SQL = ""
						+ " DECLARE @PageNumber AS INT, @RowspPage AS INT"
						+ " SET @PageNumber = " + page
						+ " SET @RowspPage = " + countOnPage
						+ " SELECT * FROM ( "
						+ "			SELECT									 ROW_NUMBER() OVER(ORDER BY TICKETS.ID DESC) AS NUMBER, "
						+ "					    						 TICKETS.ID AS ID, "
						+ "												 TICKETS.TX_SUBJECT AS SUBJECT, "
						+ "												 TICKETS.ID_ACCOUNT AS ID_ACCOUNT, "
						+ "												 (SELECT CONCAT(U1.TX_FIRSTNAME,' ',U1.TX_LASTNAME) FROM USERS U1 WHERE  U1.ID_ACCOUNT = TICKETS.ID_ACCOUNT) AS TX_ACCOUNT_NAME, "
						+ "  											 (SELECT TX_EMAIL FROM ACCOUNTS WHERE ID=TICKETS.ID_ACCOUNT) AS TX_ACCOUNT_EMAIL,	"
						+ "												 TICKETS.TX_TIME AS TX_TIME, "
						+ "												 TICKETS.ID_PRIORITY AS ID_PRIORITY, "
						+ "												 STATUSTAB.TX_STATUS AS TX_STATUS , "
						+ "												 (SELECT CONCAT(U2.TX_FIRSTNAME,' ',U2.TX_LASTNAME) FROM USERS U2 WHERE  U2.ID_ACCOUNT = TICKETS.ID_AGENT) AS TX_AGENT_NAME, "
						+ "												 TICKETS.ID_SERVICE_ENTITLEMENT AS ID_SERVICE_ENTITLEMENT, "
						+ "												 (SELECT TX_ENTITLEMENT FROM SERVICE_ENTITLEMENT WHERE ID = TICKETS.ID_SERVICE_ENTITLEMENT) AS TX_ENTITLEMENT "
						+ "												 from TICKETS "
						+ "												 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID " + queryStringPlaceHolder
						+ "												 ) AS TBL "
						+ "WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
				
				List<UserTicketsForm> tktList = this.jdbcTemplate.query(GET_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
			            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserTicketsForm tkt = new UserTicketsForm();
			            	
			            	tkt.setID(resulSet.getInt("ID"));
			            	tkt.setTX_SUBJECT(resulSet.getString("SUBJECT"));
			            	tkt.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
			            	tkt.setTX_ACCOUNT_NAME(resulSet.getString("TX_ACCOUNT_NAME"));
			            	tkt.setTX_ACCOUNT_EMAIL(resulSet.getString("TX_ACCOUNT_EMAIL"));
			            	tkt.setTX_TIME(resulSet.getString("TX_TIME"));
			            	tkt.setID_PRIORITY(resulSet.getInt("ID_PRIORITY"));
			            	tkt.setTX_STATUS(resulSet.getString("TX_STATUS"));
			            	tkt.setTX_AGENT_NAME(resulSet.getString("TX_AGENT_NAME"));
			            	tkt.setID_PRODUCT(resulSet.getInt("ID_SERVICE_ENTITLEMENT"));
			            	tkt.setTX_PRODUCT(resulSet.getString("TX_ENTITLEMENT"));
			            	
			            	return tkt;
			            }
			        });
			 
					final String GET_TOTAL_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
							"						 from TICKETS    " + 
							"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " ; 
						
					
					List<UserTicketsForm> tktTotalList = this.jdbcTemplate.query(GET_TOTAL_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
			            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserTicketsForm tkt = new UserTicketsForm();
			            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
			            	return tkt;
			            }
			        });
					
							
					final String GET_RESOLVED_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
							"						 from TICKETS    " + 
							"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
							"						 WHERE STATUSTAB.ID = 6" ;
					
					List<UserTicketsForm> tktEscalatedList = this.jdbcTemplate.query(GET_RESOLVED_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
			            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserTicketsForm tkt = new UserTicketsForm();
			            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
			            	return tkt;
			            }
			        });
					
					final String GET_RESPONDED_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
							"						 from TICKETS    " + 
							"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
							"						 WHERE STATUSTAB.ID = 4" ;
					
					List<UserTicketsForm> tktRespList = this.jdbcTemplate.query(GET_RESPONDED_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
			            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserTicketsForm tkt = new UserTicketsForm();
			            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
			            	return tkt;
			            }
			        });
					
					final String GET_PENDING_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
							"						 from TICKETS    " + 
							"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
							"						 WHERE STATUSTAB.ID IN (1,2,3,6)" ;
			 
					List<UserTicketsForm> tktPendingList = this.jdbcTemplate.query(GET_PENDING_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
			            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	UserTicketsForm tkt = new UserTicketsForm();
			            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
			            	return tkt;
			            }
			        });
			 ModelAndView mv = new ModelAndView("admin/tickets/ticketList");
			 mv.addObject("ticketlist", tktList);
			 mv.addObject("totalTickets", (tktTotalList.get(0).getTotalTickets()));
			 mv.addObject("pendingTickets", (tktPendingList.get(0).getTotalTickets()));
			 mv.addObject("respondedTickets", (tktRespList.get(0).getTotalTickets()));
			 mv.addObject("escalatedTickets", (tktEscalatedList.get(0).getTotalTickets()));
			 
			 ProductController pc = new ProductController(this.jdbcTemplate.getDataSource());
			 List<ProductForm> productList =pc.getProductList(2,1);
			 mv.addObject("productList",productList);
			 mv.addObject("currentFilter",filter);
			 mv.addObject("currentPage",page);
			 mv.addObject("totalPages",pages);
			 
			 //session.setAttribute("productList", productList);
			 
			 return mv;
		 }
		 
		 @RequestMapping(value="/admin/service/sen/addWorklog", method=RequestMethod.POST) 
		 public ModelAndView logWork(@ModelAttribute("worklogForm") @Valid WorkLogForm worklogForm, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session,
					   HttpServletRequest request, HttpServletResponse response) {
			 
			 String INSERT_WORK_LOG = "INSERT INTO TICKET_WORK_LOG(ID_TICKET, ID_USER, TX_WORKLOG, TX_DATETIME, TX_DESCRIPTION) VALUES(?,?,?,?,?)";
			 jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORK_LOG);
						preparedStatement.setInt(1, worklogForm.getID_TICKET());
						preparedStatement.setInt(2, Integer.parseInt(session.getAttribute("userId").toString().trim()));
						preparedStatement.setString(3, worklogForm.getTX_WORKLOG());
						preparedStatement.setString(4, CalendarUtils.getCurrentTimeStamp());
						preparedStatement.setString(5, worklogForm.getTX_DESCRIPTION()); 
						return preparedStatement;
						
					}
				});	
			 
			 EntitlementActions ea = new EntitlementActions();
			 List<TierRolloverForm> ticketSenRollovers = ea.getTicketEntitlementRollOvers(session, jdbcTemplate, Integer.toString(worklogForm.getID_TICKET()));
			 
			 
				//Calculate Hours recorded
				Calendar c = Calendar.getInstance();
		 		c.set(0, 0, 0, 0, 0, 0);
		 		int hours=0;
		 		int mins=0;
		 		
		 
		 			String log = worklogForm.getTX_WORKLOG();
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
		 		
		 		if(minremain>0) {
		 			hours = hours + 1;
		 		}
		 		
			 for (int i=0;i<ticketSenRollovers.size();i++) {
				 int ticketSenRolloverCycleHours = ticketSenRollovers.get(i).getNUM_ROLLOVER_BALANCE();
				 while(ticketSenRolloverCycleHours>0
						 && hours>0) {
					 hours = hours - 1;
					 ticketSenRolloverCycleHours = ticketSenRolloverCycleHours -1;
				 }
				 ea.updateEntitlementTypeRollableBalance(jdbcTemplate, ticketSenRollovers.get(i).getID_SERVICE_TIER_ROLLOVER(), ticketSenRolloverCycleHours);
				 if(ticketSenRolloverCycleHours==0) {
					 ea.updateEntitlementTypeRollableFlag(jdbcTemplate, ticketSenRollovers.get(i).getID_SERVICE_TIER_ROLLOVER(), 0);
				 }
			 }
			 
			 String redirection = "redirect:/ticketDetails?id="+worklogForm.getID_TICKET();
				return new ModelAndView(redirection);	
			 
		 }
}
