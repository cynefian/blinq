package com.gsd.sreenidhi.admin.invoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
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
import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.services.ServiceTypeForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.user.messages.MessageActions;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
@RestController
public class InvoiceController {

	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public InvoiceController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value="/admin/invoice", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView getServiceSettings(HttpSession session,  @RequestParam(value="page", required=false) String page) {
		
		int countOnPage = 10;
		int totalCount =  getInvoiceCount();
 		double rounder = (double)totalCount/(double) countOnPage;
 		int pages = (int) Math.ceil(rounder);
 		int p = 1;
 		if(page==null || page.equals("0")) {
 			p = 1;
 		}else {
 			p = Integer.parseInt(page);
 		}
 		
 		String GET_USER_ATTACHMENTS = 
				 " DECLARE @PageNumber AS INT, @RowspPage AS INT"
						+ " SET @PageNumber = " + p
						+ " SET @RowspPage = " + countOnPage
						+ " SELECT * FROM ( "
						+ "			SELECT									 ROW_NUMBER() OVER(ORDER BY ATTACHMENTS.ID DESC) AS NUMBER, "
						+ "					    	 ATTACHMENTS.ID AS ID_ATTACH, "
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
				+ "         ON ACCOUNTS.ID = USERS.ID_ACCOUNT"
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
		
		ModelAndView mv = new ModelAndView("admin/invoice/invoice");
		mv.addObject("invoiceList", userAttachFormList);
		mv.addObject("currentPage",p);
		mv.addObject("totalPages",pages);
		return mv;
	}
	
	
	
	public int getInvoiceCount() {
		String GET_INVOICE_COUNT = 
				"  SELECT  	COUNT(*) AS INVOICE_COUNT "
				+ "FROM   ATTACHMENTS "
				+ "       JOIN ACCOUNT_ATTACHMENTS "
				+ "         ON ACCOUNT_ATTACHMENTS.ID_ATTACHMENT = ATTACHMENTS.ID "
				+ "       JOIN USERS "
				+ "         ON USERS.ID = ACCOUNT_ATTACHMENTS.ID_USER "
				+ "       JOIN ACCOUNTS "
				+ "         ON ACCOUNTS.ID = USERS.ID_ACCOUNT"
			;
		
		List<UserAttachmentForm> userAttachFormList = this.jdbcTemplate.query(GET_INVOICE_COUNT, new RowMapper<UserAttachmentForm>() {
			public UserAttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserAttachmentForm ua = new UserAttachmentForm();
				ua.setAttachCount(resulSet.getInt("INVOICE_COUNT"));
				return ua;
			}
		});
		
		return userAttachFormList.get(0).getAttachCount();		
	}
	
	
	
	@RequestMapping(value="/admin/addInvoice", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView addInvoice(@ModelAttribute("invoiceBean") @Valid InvoiceForm invoiceBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, final HttpSession session) {
		
		String GET_USER_ID_SQL = "SELECT users.id AS ID "
				+ " FROM   users "
				+ "       JOIN accounts "
				+ "         ON accounts.id = users.id_account "
				+ "WHERE  accounts.tx_email = '"+invoiceBean.getTX_ACCOUNT_EMAIL().trim()+"'";
		List<UserForm> UserFormList = this.jdbcTemplate.query(GET_USER_ID_SQL, new RowMapper<UserForm>() {
			public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserForm acc = new UserForm();
				acc.setId(resulSet.getInt("ID"));
				return acc;
			}
		});
		
		final KeyHolder attachKey = new GeneratedKeyHolder();
		final String INSERT_ATTACHMENT_SQL = "INSERT INTO ATTACHMENTS(TX_ATTACHMENT, TX_NAME, TX_TYPE, TX_SIZE, TX_DATE) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTACHMENT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, invoiceBean.getTX_DATA());
				preparedStatement.setNString(2, invoiceBean.getTX_NAME());
				preparedStatement.setNString(3, invoiceBean.getTX_TYPE());
				preparedStatement.setNString(4, invoiceBean.getTX_SIZE());
				preparedStatement.setString(5, CalendarUtils.getCurrentTimeStamp());
				return preparedStatement;
			}
		}, attachKey);
		
		final int attachId = attachKey.getKey().intValue();
		
		final String INSERT_ACCOUNT_ATTACHMENT_SQL = "INSERT INTO ACCOUNT_ATTACHMENTS(ID_USER, ID_ATTACHMENT, TX_DESCRIPTION) VALUES (?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_ATTACHMENT_SQL);
				preparedStatement.setInt(1, UserFormList.get(0).getId());
				preparedStatement.setInt(2, attachId);
				preparedStatement.setString(3, invoiceBean.getTX_ATTACH_DESCRIPTION());
				return preparedStatement;
			}
		});
		
		String hrefRedirect = fetchURLProperties() + "/user/orderInvoice";
		MessageActions ma = new MessageActions();
		InvoiceControllerForm icf = new InvoiceControllerForm();
		ma.insertMessage(jdbcTemplate, "New Invoice Attachment", icf.getSYSTEM_MESSAGE().replace("{ahref}", hrefRedirect), UserFormList.get(0).getId(), 2, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
		
		EmailForm ef = new EmailForm();
		String tempalte = ef.getEmailTemplate();
		
		String emailMessage = icf.getEMAIL_MESSAGE(); 
		
		String msg = tempalte.replace("${username}", invoiceBean.getTX_ACCOUNT_EMAIL().trim());
		msg = msg.replace("${title}", "There's an update for you.");
		msg = msg.replace("${message}", emailMessage);
		msg = msg.replace("${image}", icf.getImage());
		msg = msg.replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
		msg = msg.replace("${disclaimer}", ef.getDisclaimer());
		if(!"".equals(invoiceBean.getTX_ACCOUNT_EMAIL().trim())) {
			SendMail sm = new SendMail();
			
			final String emailMsg = msg;
			
		    ExecutorService service = Executors.newFixedThreadPool(4);
		    service.submit(new Runnable() {
		        public void run() {
		        	sm.sendMessage(emailMsg,invoiceBean.getTX_ACCOUNT_EMAIL().trim(), null, "Blinqlabs Update - You have a new Invoice.", false);
		        }
		    });
		    
		}
		ModelAndView mv = new ModelAndView("redirect:/admin/invoice");
		return mv;
	}
	
	@RequestMapping(value="/admin/deleteInvoice", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView deleteService(@ModelAttribute("invoiceBean") @Valid InvoiceForm invoiceBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, final HttpSession session, @RequestParam(value="id", required=true) String id) {
		
		final String DELETE_ACCOUNT_ATTACHMENT_SQL = "DELETE FROM ACCOUNT_ATTACHMENTS WHERE ID_ATTACHMENT = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_ATTACHMENT_SQL);
				preparedStatement.setInt(1, Integer.parseInt(id));
				return preparedStatement;
			}
		});
		
		final String DELETE_ATTACHMENT_SQL = "DELETE FROM ATTACHMENTS WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTACHMENT_SQL);
				preparedStatement.setInt(1, Integer.parseInt(id));
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/invoice");
		return mv;
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