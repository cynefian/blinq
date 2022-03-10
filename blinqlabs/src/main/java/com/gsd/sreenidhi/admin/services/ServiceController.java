package com.gsd.sreenidhi.admin.services;

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
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;

@Controller
@RestController
public class ServiceController {

	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public ServiceController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value="/admin/ServiceAdministration", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView getServiceSettings(HttpSession session) {
		
		EntitlementActions ea = new EntitlementActions();
		List<ServiceTypeForm> typeList = ea.getEntitlementTypes(jdbcTemplate);
		
		ModelAndView mv = new ModelAndView("admin/service/serviceAdministration");
		mv.addObject("entitlementTypes", typeList);
		return mv;
	}
	
	@RequestMapping(value="/admin/addServiceType", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView newServiceTypeSettings(@ModelAttribute("ServiceTypeBean") @Valid ServiceTypeForm ServiceTypeBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
	

		EntitlementActions ea = new EntitlementActions();
		ea.insertEntitlementType(jdbcTemplate, ServiceTypeBean);
		
		String redirection = "redirect:/admin/ServiceAdministration";
		return new ModelAndView(redirection);
		
	}
	
	
	@RequestMapping(value="/admin/service/updateEntitlementRollable", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView updateEntitlementRollable(@RequestParam("id") String id,@RequestParam("value") String value,
			final HttpSession session) {
	
		EntitlementActions ea = new EntitlementActions();
		ea.updateEntitlementTypeRollable(jdbcTemplate, id, value);
		
		String redirection = "redirect:/admin/ServiceAdministration";
		return new ModelAndView(redirection);
	}
	
	@RequestMapping(value="/admin/service/updateEntitlementMonthlyBilling", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_ADMIN_SERVICES')")
	public ModelAndView updateEntitlementTypeMonthly(@RequestParam("id") String id,@RequestParam("value") String value,
			final HttpSession session) {
	
		EntitlementActions ea = new EntitlementActions();
		ea.updateEntitlementTypeMonthly(jdbcTemplate, id, value);
		
		String redirection = "redirect:/admin/ServiceAdministration";
		return new ModelAndView(redirection);
	}
}
