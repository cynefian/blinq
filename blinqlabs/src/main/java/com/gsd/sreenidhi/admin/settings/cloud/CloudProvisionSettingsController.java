package com.gsd.sreenidhi.admin.settings.cloud;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.CloudAuthForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;

@Controller
public class CloudProvisionSettingsController {


	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public CloudProvisionSettingsController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}


	@RequestMapping(value="/admin/settings/cloud/ansible", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView ansibleProvisioningSettings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Ansible";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/ansible");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/chef", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView chefProvisioningSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Chef";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/chef");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/puppet", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView puppetProvisioningSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Puppet";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/puppet");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	
	@RequestMapping(value="/admin/settings/cloud/rundeck", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView rundeckProvisioningSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Rundeck";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/rundeck");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/terraform", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView terraformProvisioningSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Terraform";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/terraform");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	
}
