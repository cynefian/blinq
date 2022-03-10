package com.gsd.sreenidhi.user.services;

import java.text.ParseException;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gsd.sreenidhi.admin.products.ProductController;
import com.gsd.sreenidhi.admin.products.ProductForm;
import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.services.SENRatesForm;
import com.gsd.sreenidhi.admin.services.ServiceTypeForm;
import com.gsd.sreenidhi.admin.tickets.WorkLogForm;
import com.gsd.sreenidhi.billing.BillingCycleForm;
import com.gsd.sreenidhi.commons.AttachmentForm;
import com.gsd.sreenidhi.commons.serviceEntitlement.TierRolloverForm;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.security.licensing.ProductLicenseContoller;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
@SessionAttributes("servicesBean")
public class ServicesController {

	private static final Logger logger = LoggerFactory.getLogger(ServicesController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public ServicesController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("licenseBean")
		 public ServicesForm createServicesFormBean() {
		 	return new ServicesForm();
		 }
		 
		 @RequestMapping(value="/user/userServices", method=RequestMethod.GET)  
		 public ModelAndView retrieveUserLicense(HttpSession session, 
					@RequestParam(value="page", required=false) String page, 
					@RequestParam(value="filter", required=false) String filter) {
			 
			 
				EntitlementActions ea = new EntitlementActions();
				
				 int countOnPage = 10;
				
				 
				 int pg;
				if(page==null) {
						pg=1;
					}else {
						pg = Integer.parseInt(page);
					}
				 
				int totalCount =  ea.getEntitlementListCount(session, jdbcTemplate, true, page, filter);
				double rounder = (double)totalCount/(double) countOnPage;
			 	int pages = (int) Math.ceil(rounder);
				
				List<SENForm> SENFormList = ea.getEntitlementList(session, jdbcTemplate, true, true, page, filter);
				
				List<ServiceTypeForm> typeList = ea.getEntitlementTypes(jdbcTemplate);
				
			 ModelAndView mv =  new ModelAndView("user/userServices");
				mv.addObject("SENFormList",SENFormList);
				mv.addObject("entitlementTypes", typeList);
				mv.addObject("totalCount", totalCount);
				mv.addObject("currentPage", pg);
				mv.addObject("currentFilter",filter);
				mv.addObject("totalPages",pages);
			 
			
			 return mv;
		}
				
		 @RequestMapping(value="/user/serviceEntitlements", method=RequestMethod.GET)  
		 public ModelAndView retrieveUserEntitlements(HttpSession session, 
					@RequestParam(value="page", required=false) String page, 
					@RequestParam(value="filter", required=false) String filter) {
			 
			
			 return new ModelAndView("redirect:/user/userServices?page="+page+"&filter="+filter);
		 }
		 
		 
		 
			@RequestMapping(value="/user/SENdetails", method = RequestMethod.POST)
			@PreAuthorize("hasAuthority('PERM_FEATURE_TECH_SUPPORT')")
			public ModelAndView viewUserSENDetailsPost(HttpSession session, 
					@RequestParam(value="id", required=true) String id,
					@RequestParam(value="source", required=false) String source,
					@RequestParam(value="page", required=false) String page,
					@RequestParam(value="startDate", required=false) String startDate,
					@RequestParam(value="endDate", required=false) String endDate) {
				
				return viewUserSENDetailsGet(session, id, source, page, startDate, endDate);
			}
			
		 
			@RequestMapping(value="/user/SENdetails", method = RequestMethod.GET)
			@PreAuthorize("hasAuthority('PERM_FEATURE_TECH_SUPPORT')")
			public ModelAndView viewUserSENDetailsGet(HttpSession session, 
					@RequestParam(value="id", required=true) String id,
					@RequestParam(value="source", required=false) String source,
					@RequestParam(value="page", required=false) String page,
					@RequestParam(value="startDate", required=false) String startDate,
					@RequestParam(value="endDate", required=false) String endDate) {

				EntitlementActions ea = new EntitlementActions();
				List<SENForm> senList = ea.getSENdetails(session, jdbcTemplate, id);
				List<SENRatesForm> senRatesList = ea.getSENRates(session, jdbcTemplate, id);
				List<AttachmentForm> SENAttachFormList = ea.getSENAttachments(session, jdbcTemplate, id, page);
				List<WorkLogForm> senActivityList = ea.getEntitlementActivities(session, jdbcTemplate, id, startDate, endDate);
				
				List<BillingCycleForm> senBillingList = ea.getEntitlementBillingCycles(session, jdbcTemplate, id);
				
				
				//Retrieving Rollover hours
				List<TierRolloverForm> senRollover = ea.getEntitlementRollOvers(jdbcTemplate, id);
				int availRolloverHours = 0;
				int totalRolloverHours = 0;
				if(senRollover!=null && senRollover.size()>0) {
					for(int i=0;i<senRollover.size();i++) {
						availRolloverHours = availRolloverHours + senRollover.get(i).getNUM_ROLLOVER_BALANCE();
						totalRolloverHours = totalRolloverHours + senRollover.get(i).getNUM_ROLLOVER_ORIG();
					}
				}
				
				
				//Retrieve Current Billing Cycle
				String currentCycleStart = "";
				String currentCycleEnd = "";
				try {
					if(senBillingList!=null && senBillingList.size()>0) {
						for(int i=0;i<senBillingList.size();i++) {
							if((CalendarUtils.compareDates(senBillingList.get(0).getTS_START_DATE(), CalendarUtils.getCurrentDateOnly())<0)
									&& (CalendarUtils.compareDates(CalendarUtils.getCurrentDateOnly(), senBillingList.get(0).getTS_END_DATE())<=0)) {
								currentCycleStart = senBillingList.get(0).getTS_START_DATE();
								currentCycleEnd = senBillingList.get(0).getTS_END_DATE();
							}
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}		
				
				//Calculate Hours recorded
				Calendar c = Calendar.getInstance();
		 		c.set(0, 0, 0, 0, 0, 0);
		 		int hours=0;
		 		int mins=0;
		 		
		 		for(int i=0;i<senActivityList.size();i++) {
		 			String log = senActivityList.get(i).getTX_WORKLOG();
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
		 		
		 		//Billing Cycle Calculations
		 		String currentTier = "";
		 		String currentTierCost = ""; 	
		 		int bill = 0;
		 		int billingHours = 0;
		 		String cycleCaltotalTime = "";
		 		String billableHour = "";
		 		
		 		if(!"".equalsIgnoreCase(currentCycleStart)
						&& !"".equalsIgnoreCase(currentCycleEnd)) {
					List<WorkLogForm> cycleActivityList = ea.getEntitlementActivities(session, jdbcTemplate, id, currentCycleStart, currentCycleEnd);
					
					//Calculate Hours recorded
					Calendar cycleCal = Calendar.getInstance();
					cycleCal.set(0, 0, 0, 0, 0, 0);
			 		int cycleCalhours=0;
			 		int cycleCalmins=0;
			 		
			 		for(int i=0;i<cycleActivityList.size();i++) {
			 			String cycleCallog = cycleActivityList.get(i).getTX_WORKLOG();
			 			String[] cycleCallogarr = cycleCallog.split(" ");
			 			for(int j=0;j<cycleCallogarr.length;j++) {
			 				if(cycleCallogarr[j].contains("w")) {
			 					String unit =cycleCallogarr[j].replace("w", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*40;
			 				}else if(cycleCallogarr[j].contains("W")) {
			 					String unit =cycleCallogarr[j].replace("W", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*40;
			 				}else if(cycleCallogarr[j].contains("d")) {
			 					String unit =cycleCallogarr[j].replace("d", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*8;
			 				}else if(cycleCallogarr[j].contains("D")) {
			 					String unit =cycleCallogarr[j].replace("D", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim())*8;
			 				}else if(cycleCallogarr[j].contains("h")) {
			 					String unit =cycleCallogarr[j].replace("h", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim());
			 				}else if(cycleCallogarr[j].contains("H")) {
			 					String unit =cycleCallogarr[j].replace("H", ""); 
			 					cycleCalhours = cycleCalhours + Integer.parseInt(unit.trim());
			 				}else if(cycleCallogarr[j].contains("m")) {
			 					String unit =cycleCallogarr[j].replace("m", "");
			 					cycleCalmins = cycleCalmins+Integer.parseInt(unit.trim());
			 				}else if(cycleCallogarr[j].contains("M")) {
			 					String unit =cycleCallogarr[j].replace("M", ""); 
			 					cycleCalmins = cycleCalmins+Integer.parseInt(unit.trim());
			 				}
			 			}
			 		}
			 		
			 		int cycleCalhourappend = 0;
			 		int cycleCalminremain = 0;
			 		if(cycleCalmins>=60) {
			 			cycleCalhourappend = cycleCalmins/60;
			 			cycleCalminremain = cycleCalmins-(cycleCalhourappend*60);
			 		}else {
			 			cycleCalminremain = cycleCalmins;
			 		}
			 		
			 		cycleCalhours = cycleCalhours + cycleCalhourappend;
			 		cycleCaltotalTime = cycleCalhours +" hours, "+ cycleCalminremain + " minutes";
			 		
			 		//billing hours = logged hours - rollover hours
			 		billingHours = cycleCalhours - totalRolloverHours ;
			 		if(billingHours<0) {
			 			bill = 0;
			 		}else{
			 			bill = billingHours;
			 		}
			 		billableHour =  bill +" hours, "+ cycleCalminremain + " minutes";
			 		
			 		if(cycleCalminremain>0) {
			 			bill = bill +1;
			 		}
			 		//Identify current tier
			 		
			 		if(senRatesList!=null && senRatesList.size()>0) {
			 			for(int i=senRatesList.size()-1;i>=0;i--) {
			 				if(bill<=Integer.parseInt(senRatesList.get(i).getTX_LIMIT())) {
			 					currentTier = senRatesList.get(i).getTX_TIER();
			 					currentTierCost = senRatesList.get(i).getTX_COST();
			 				}
			 			}
			 		}
			 		
				}
		 		
				
				int countOnPage = 10;
				int pg;
				if("".equals(page)|| page==null) {
					pg = 1;
				}else {
					pg = Integer.parseInt(page);
				}
				int totalCount =  ea.getSENAttachmentsCount(session, jdbcTemplate, id);
				double rounder = (double)totalCount/(double) countOnPage;
			 	int pages = (int) Math.ceil(rounder);
			 	
				ModelAndView mv = new ModelAndView("user/service/serviceEntitlementDetails");
				mv.addObject("SENFormList",senList);
				mv.addObject("senRatesList",senRatesList);
				mv.addObject("senAttachList",SENAttachFormList);
				mv.addObject("pageTab",source);
				mv.addObject("senActivityList", senActivityList);
				mv.addObject("senUsageTime", totalTime);
				
				mv.addObject("totalCount", totalCount);
				mv.addObject("currentPage", pg);
				mv.addObject("totalPages",pages);
				mv.addObject("startDate", startDate);
				mv.addObject("endDate", endDate);
				
				mv.addObject("currentCycleStart", currentCycleStart);
				mv.addObject("currentCycleEnd", currentCycleEnd);
				mv.addObject("availRolloverHours",availRolloverHours);
				mv.addObject("totalRolloverHours",totalRolloverHours);
				mv.addObject("cycleCaltotalTime",cycleCaltotalTime);
				mv.addObject("currentTier",currentTier);
				mv.addObject("currentTierCost",currentTierCost);
				mv.addObject("billableHour",billableHour);
				
				return mv;
			
			}
}
