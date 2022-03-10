package com.gsd.sreenidhi.user.tickets;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
@SessionAttributes("userTicketsBean")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				maxFileSize=1024*1024*10,      // 10MB
				maxRequestSize=1024*1024*50)   // 50MB
public class UserTicketsController  extends HttpServlet{

/**
	 * 
	 */
	private static final long serialVersionUID = 1195564381169111658L;
	
	/**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";


private static final Logger logger = LoggerFactory.getLogger(UserTicketsController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public UserTicketsController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("userTicketsBean")
		 public UserTicketsForm createUserTicketsBean() {
		 	return new UserTicketsForm();
		 }
		
		 @RequestMapping(value = "/user/UserTickets", method = RequestMethod.GET)
		 public ModelAndView userTicketsGet(HttpSession session, @RequestParam("page") int page, @RequestParam("filter") String filter) {
			 
			 int countOnPage = 10;
			 
			 logger.info("Retrieving User Tickets");
			 
			 final String userId = session.getAttribute("userId").toString().trim();
			 final int uid = Integer.parseInt(userId);
			 logger.info("uid:"+uid);
			 
			 
			 String queryStringPlaceHolder = "";
			 if(filter!=null) {
				 
				 
				 // 1 - OPEN
				 // 2 - IN PROGRESS
				 // 3 - INVESTIGATION
				 // 4 - WAITING FOR CUSTOMER
				 // 5 - CLOSED
				 // 6 - ESCALATED
				 
				 
				 if("all".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=""; 
				 }else if("pending".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=" AND STATUSTAB.ID in (1,2,3,6)";
				 } 
				 else if("escalated".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=" AND STATUSTAB.ID in (6)";
				 } 
				 else if("responded".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=" AND STATUSTAB.ID in (4)";
				 } 
				 else if("resolved".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
				 } 
				 else if("closed".equalsIgnoreCase(filter)) {
					 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
				 } 
				 else  {
					 queryStringPlaceHolder=""; 
				 } 
			 }
			 
			 
			 TicketActions ta = new TicketActions();
			 List<UserTicketsForm> tktcountList = ta.getTicketCounts(jdbcTemplate, page, filter, userId);
			 
			
			 	
		 		int totalCount =  tktcountList.get(0).getQueryTicketCount();
		 		double rounder = (double)totalCount/(double) countOnPage;
			 	int pages = (int) Math.ceil(rounder);
			 	
			 	logger.info("Total count: " + totalCount);
			 	logger.info("Total rounder: " + rounder);
			 	logger.info("Total Pages: " + pages);
			 	
			 	
			 	List<UserTicketsForm> tktList  = ta.getTickets(jdbcTemplate, page, filter, userId);
			 	List<UserTicketsForm> tktTotalList = ta.getTotalCount(jdbcTemplate, page, filter, userId);
			 	List<UserTicketsForm> tktResolvedList = ta.getResolvedCount(jdbcTemplate, page, filter, userId);
			 	List<UserTicketsForm> tktRespList = ta.getResponseCount(jdbcTemplate, page, filter, userId);
			 	List<UserTicketsForm> tktPendingList = ta.getPendingCount(jdbcTemplate, page, filter, userId);
							
					
					
					
					
			 ModelAndView mv = new ModelAndView("user/ticketList");
			 mv.addObject("ticketlist", tktList);
			 mv.addObject("totalTickets", (tktTotalList.get(0).getTotalTickets()));
			 mv.addObject("pendingTickets", (tktPendingList.get(0).getTotalTickets()));
			 mv.addObject("respondedTickets", (tktRespList.get(0).getTotalTickets()));
			 mv.addObject("resolvedTickets", (tktResolvedList.get(0).getTotalTickets()));
			 
			 ProductController pc = new ProductController(this.jdbcTemplate.getDataSource());
			 List<ProductForm> productList =pc.getProductList(2,1);
			 
			 EntitlementActions ea = new EntitlementActions();
			 List<SENForm> SENFormList = ea.getEntitlementList(session, jdbcTemplate, true, false, "", "ACTIVE");
			 
			 mv.addObject("productList", productList);
			 mv.addObject("userSenList",SENFormList);
			 mv.addObject("currentFilter",filter);
			 mv.addObject("currentPage",page);
			 mv.addObject("totalPages",pages);
			 return mv;
		 }
		 
		 
		 @RequestMapping(value="/user/UserTickets", method=RequestMethod.POST)
		 	public ModelAndView userTicketsPost(@ModelAttribute("userTicketsBean") @Valid UserTicketsForm userTicketsBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session,
					   HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
			 final KeyHolder ticketKey = new GeneratedKeyHolder();
			 
			 final UserTicketsForm uTF = userTicketsBean;
			 
			 Date d = new Date();
			 String dt = CalendarUtils.dateToString(d);
			 uTF.setTX_TIME(dt);
			 uTF.setID_ACCOUNT((Integer) session.getAttribute("userId"));
			 
			 final String INSERT_TICKET_SQL = "INSERT INTO TICKETS (TX_SUBJECT, ID_ACCOUNT, TX_TIME, ID_PRIORITY, ID_SERVICE_ENTITLEMENT, ID_STATUS) VALUES (?,?,?,?,?,?)";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, uTF.getTX_SUBJECT());
						preparedStatement.setInt(2, uTF.getID_ACCOUNT());
						preparedStatement.setString(3, uTF.getTX_TIME());
						preparedStatement.setInt(4, 1); //TODO - capture in jsp
						preparedStatement.setString(5, uTF.getTX_SEN()); //capture from jsp
						preparedStatement.setInt(6, 1);
						return preparedStatement;
						
					}
				},ticketKey);	
				
				final KeyHolder threadKey = new GeneratedKeyHolder();
				final String INSERT_TICKET_THREAD_SQL = "INSERT INTO TICKET_THREADS (TICKET_ID, TX_BODY, TX_READ, TX_TIME, ID_PARENT, ID_USER) VALUES (?,?,?,?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_THREAD_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setInt(1, ticketKey.getKey().intValue());
						preparedStatement.setString(2, uTF.getTX_BODY());
						preparedStatement.setInt(3, 0);
						preparedStatement.setString(4, uTF.getTX_TIME());
						preparedStatement.setString(5, null);
						preparedStatement.setString(6, session.getAttribute("userId").toString());
						return preparedStatement;
					}
				},threadKey);
				
				KeyHolder attachKey = null; 
				if(uTF.getTX_DATA()!=null && !"".equals(uTF.getTX_DATA())) {
					
					attachKey = new GeneratedKeyHolder();
					final String INSERT_ATTACHMENT_SQL = "INSERT INTO ATTACHMENTS (TX_ATTACHMENT, TX_NAME, TX_SIZE, TX_TYPE) VALUES (?,?,?,?)";
					
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTACHMENT_SQL, Statement.RETURN_GENERATED_KEYS);
							preparedStatement.setString(1, uTF.getTX_DATA());
							preparedStatement.setString(2, uTF.getTX_NAME());
							preparedStatement.setString(3, uTF.getTX_SIZE());
							preparedStatement.setString(4, uTF.getTX_TYPE());
							return preparedStatement;
						}
					},attachKey);	
					
					int attKey = 0;
					attKey = attachKey.getKey().intValue();
					
					if(attKey>0) {
						final KeyHolder attachmentKey = attachKey;
						final String INSERT_TICKET__THREAD_ATTACHMENT_SQL = "INSERT INTO TICKET_THREAD_ATTACHMENT (ID_TICKET_THREAD, ID_ATTACHMENT) VALUES (?,?)";
						 
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET__THREAD_ATTACHMENT_SQL, Statement.RETURN_GENERATED_KEYS);
								preparedStatement.setInt(1, threadKey.getKey().intValue());
								preparedStatement.setInt(2, attachmentKey.getKey().intValue());
								return preparedStatement;
							}
						},threadKey);
					}
				
				}
				String redirection = "redirect:/user/UserTickets?page=1&filter=all";
			 	return new  ModelAndView(redirection);
		 }
		 
		 
		 /**
		     * Extracts file name from HTTP header content-disposition
		     */
		    private String extractFileName(Part part) {
		        String contentDisp = part.getHeader("content-disposition");
		        String[] items = contentDisp.split(";");
		        for (String s : items) {
		            if (s.trim().startsWith("filename")) {
		                return s.substring(s.indexOf("=") + 2, s.length()-1);
		            }
		        }
		        return "";
		    }

			 
}
