package com.gsd.sreenidhi.admin.faq;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;

@Controller

@SessionAttributes("faqBean")
public class FaqController {

	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public FaqController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@ModelAttribute("adminTicketsBean")
	public UserTicketsForm createUserTicketsBean() {
		return new UserTicketsForm();
	}

	@RequestMapping(value="/admin/faqs", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_FAQ')")
	public ModelAndView adminFaqsGet(HttpSession session, @RequestParam("type") String type) {
		logger.info("Retrieving FAQs");

		List<FaqForm> fList = getFAQs(type);

		ModelAndView mv = new ModelAndView("admin/faq/faq");
		mv.addObject("faqlist", fList);

		return mv;
	}

	@RequestMapping(value="/admin/faqs", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FAQ')")
	public ModelAndView adminFaqsPost(@ModelAttribute("adminfaqBean") @Valid FaqForm faqBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		final FaqForm fF = faqBean;
		final KeyHolder faqKey = new GeneratedKeyHolder();
		
		logger.info("Insert FAQs");

		final String GET_FAQ_SQL = "INSERT INTO FAQ( TX_QUESTION, TX_ANSWER, FL_ACTIVE) VALUES(?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(GET_FAQ_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, fF.getTX_QUESTION());
				preparedStatement.setString(2, fF.getTX_ANSWER());
				preparedStatement.setInt(3, 1);
				
				return preparedStatement;
			}
		}, faqKey);
		faqBean.setID(faqKey.getKey().intValue());
		
		ModelAndView mv = adminFaqsGet(session,"a");
		mv.addObject("successmessage","FAQ has been successfully created!");
		return mv;

	}
	
	@RequestMapping(value="/admin/deleteFaq", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_FAQ')")
	public ModelAndView adminFaqsDelete( @RequestParam("id") String id, final HttpSession session) {
		
		final String DELETE_FAQ_SQL = "DELETE FROM FAQ WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_FAQ_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		/*
		 * ModelAndView mv = adminFaqsGet(session,"a");
		 * mv.addObject("successmessage","FAQ has been successfully deleted!");
		 */
		return new ModelAndView("redirect:/admin/faqs?type=a");
	}
	
	@RequestMapping(value="/admin/editFaq", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FAQ')")
	public ModelAndView adminFaqsDelete( @RequestParam("id") String id, @ModelAttribute("adminfaqBean") @Valid FaqForm faqBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		final FaqForm fF = faqBean;
		
		final String UPDATE_FAQ_SQL = "UPDATE FAQ SET TX_QUESTION = ?, TX_ANSWER= ? WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_FAQ_SQL);
				preparedStatement.setString(1,fF.getTX_QUESTION());
				preparedStatement.setString(2,fF.getTX_ANSWER());
				preparedStatement.setString(3,id);
				return preparedStatement;
			}
		});
		
		/*
		 * ModelAndView mv = adminFaqsGet(session,"a");
		 * mv.addObject("successmessage","FAQ has been successfully deleted!");
		 */
		return new ModelAndView("redirect:/admin/faqs?type=a");
	}
	
	
	@RequestMapping("/faq")
	public ModelAndView faq(HttpSession session, @RequestParam("type") String type) {
		
		List<FaqForm> fList = getFAQs(type);

		
		ModelAndView mv = new ModelAndView("/resources/faq");
		mv.addObject("faqlist", fList);
		return mv;
	}


	private List<FaqForm> getFAQs(String type) {
		/*
		 * Filter Types: a = admin g = global
		 */

		String queryStringPlaceHolder = "";

		if (type != null) {
			if ("a".equalsIgnoreCase(type)) {
				queryStringPlaceHolder = "";
			} else {
				queryStringPlaceHolder = " WHERE FL_ACTIVE = 1";
			}
		} else {
			queryStringPlaceHolder = " WHERE FL_ACTIVE = 1";
		}

		final String GET_FAQ_SQL = "SELECT ID, TX_QUESTION, TX_ANSWER, FL_ACTIVE "
				+ "						 from FAQ    " + queryStringPlaceHolder;

		List<FaqForm> fList = this.jdbcTemplate.query(GET_FAQ_SQL, new RowMapper<FaqForm>() {
			public FaqForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FaqForm faq = new FaqForm();
				faq.setID(resulSet.getInt("ID"));
				faq.setTX_QUESTION(resulSet.getString("TX_QUESTION"));
				faq.setTX_ANSWER(resulSet.getString("TX_ANSWER"));
				faq.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE") == 0 ? false : true);
				return faq;
			}
		});
		return fList;
	}


}
