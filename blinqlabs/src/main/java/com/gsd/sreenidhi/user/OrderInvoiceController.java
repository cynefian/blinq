package com.gsd.sreenidhi.user;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogCategoryForm;
import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.invoice.UserAttachmentForm;
import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.services.ServiceTypeForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.user.UserForm;

@Controller
@RestController
public class OrderInvoiceController {

	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public OrderInvoiceController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}
	
	@RequestMapping(value="/user/orderInvoice", method = RequestMethod.GET)
	public ModelAndView getServiceSettings(HttpSession session,  @RequestParam(value="page", required=false) String page) {
		
		int countOnPage = 10;
		int totalCount =  getInvoiceCount(session);
 		double rounder = (double)totalCount/(double) countOnPage;
 		int pages = (int) Math.ceil(rounder);
 		int p = 1;
 		if(page==null || page.equals("0")) {
 			p = 1;
 		}else {
 			p = Integer.parseInt(page);
 		}
		
		String GET_USER_ATTACHMENTS =  " DECLARE @PageNumber AS INT, @RowspPage AS INT"
				+ " SET @PageNumber = " + p
				+ " SET @RowspPage = " + countOnPage
				+ " SELECT * FROM ( "
				+ "			SELECT									 ROW_NUMBER() OVER(ORDER BY ATTACHMENTS.ID DESC) AS NUMBER, "
				+ "					    	  ATTACHMENTS.ID AS ID_ATTACH, "
				+ "       ATTACHMENTS.TX_NAME AS TX_ATTACH_NAME, "
				+ "       ATTACHMENTS.TX_ATTACHMENT AS TX_ATTACH_DATA, "
				+ "       ATTACHMENTS.TX_TYPE AS TX_ATTACH_TYPE, "
				+ "       ATTACHMENTS.TX_SIZE AS TX_ATTACH_SIZE, "
				+ "       ATTACHMENTS.TX_DATE AS TX_ATTACH_DATE, "
				+ "       ACCOUNT_ATTACHMENTS.TX_DESCRIPTION AS TX_ATTACH_DESCRIPTION, "
				+ "       USERS.ID AS ID_USER, "
				+ "       USERS.ID_ACCOUNT AS ID_ACCOUNT, "
				+ "       USERS.TX_FIRSTNAME AS TX_FIRSTNAME, "
				+ "       USERS.TX_LASTNAME AS TX_LASTNAME, "
				+ "       ACCOUNTS.TX_EMAIL AS TX_EMAIL "
				+ "FROM   ATTACHMENTS "
				+ "       JOIN ACCOUNT_ATTACHMENTS "
				+ "         ON ACCOUNT_ATTACHMENTS.ID_ATTACHMENT = ATTACHMENTS.ID "
				+ "       JOIN USERS "
				+ "         ON USERS.ID = ACCOUNT_ATTACHMENTS.ID_USER "
				+ "       JOIN ACCOUNTS "
				+ "         ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "    WHERE USERS.ID = " + session.getAttribute("userId")
				+ "       ) AS TBL "
				+ "WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
		
		List<UserAttachmentForm> userAttachFormList = this.jdbcTemplate.query(GET_USER_ATTACHMENTS, new RowMapper<UserAttachmentForm>() {
			public UserAttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserAttachmentForm ua = new UserAttachmentForm();
				ua.setID_ATTACH(resulSet.getInt("ID_ATTACH"));
				ua.setTX_ATTACH_NAME(resulSet.getString("TX_ATTACH_NAME"));
				ua.setTX_ATTACH_DATA(resulSet.getString("TX_ATTACH_DATA"));
				ua.setTX_ATTACH_TYPE(resulSet.getString("TX_ATTACH_TYPE"));
				ua.setTX_ATTACH_SIZE(resulSet.getString("TX_ATTACH_SIZE"));
				ua.setTX_DATE(resulSet.getString("TX_ATTACH_DATE"));
				ua.setTX_ATTACH_DESCRIPTION(resulSet.getString("TX_ATTACH_DESCRIPTION"));
				ua.setID_USER(resulSet.getInt("ID_USER"));
				ua.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
				ua.setTX_FIRSTNAME(resulSet.getString("TX_FIRSTNAME"));
				ua.setTX_LASTNAME(resulSet.getString("TX_LASTNAME"));
				ua.setTX_EMAIL(resulSet.getString("TX_EMAIL"));
				return ua;
			}
		});
		
		ModelAndView mv = new ModelAndView("user/invoice/invoice");
		mv.addObject("invoiceList", userAttachFormList);
		mv.addObject("currentPage",p);
		mv.addObject("totalPages",pages);
		return mv;
	}
	
	
	public int getInvoiceCount(HttpSession session) {
		String GET_INVOICE_COUNT = "SELECT COUNT(*) AS INVOICE_COUNT "
				+ "FROM   ATTACHMENTS "
				+ "       JOIN ACCOUNT_ATTACHMENTS "
				+ "         ON ACCOUNT_ATTACHMENTS.ID_ATTACHMENT = ATTACHMENTS.ID "
				+ "       JOIN USERS "
				+ "         ON USERS.ID = ACCOUNT_ATTACHMENTS.ID_USER "
				+ "       JOIN ACCOUNTS "
				+ "         ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "    WHERE USERS.ID = " + session.getAttribute("userId");
		
		List<UserAttachmentForm> userAttachFormList = this.jdbcTemplate.query(GET_INVOICE_COUNT, new RowMapper<UserAttachmentForm>() {
			public UserAttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserAttachmentForm ua = new UserAttachmentForm();
				ua.setAttachCount(resulSet.getInt("INVOICE_COUNT"));
				return ua;
			}
		});
		
		return userAttachFormList.get(0).getAttachCount();		
	}
	
}