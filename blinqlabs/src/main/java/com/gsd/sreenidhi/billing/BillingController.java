package com.gsd.sreenidhi.billing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.workbench.LabActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Configuration
@PropertySource("classpath:db.properties")
public class BillingController {

	private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	private DataSource dataSource;

	public BillingController() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.jdbcTemplate = (new JdbcTemplate(getDataSource()));
	}

	@Autowired
	public BillingController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	public DataSource getDataSource() {
		Properties dbProperties = fetchDBProperties();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbProperties.getProperty("db.class"));
		dataSource.setUrl(dbProperties.getProperty("db.url"));
		dataSource.setUsername(dbProperties.getProperty("db.user"));
		dataSource.setPassword(dbProperties.getProperty("db.password"));

		return dataSource;
	}

	public Properties fetchDBProperties() {
		Properties properties = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:db.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	
	public void processQueue() {
		logger.info("Scheduled Billing Analysis Task: " + new Date());
		String today = "";
		String ydayDate = "";
		String ydate = "";
		String expiry = "";
		
		try {
			 today = CalendarUtils.getCurrentTimeStamp();
			 ydayDate = CalendarUtils.addDays(today, -1, "yyyy-MM-dd");
			 expiry = CalendarUtils.addMonths(today, -3, "yyyy-MM-dd");
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
			BillingActions BA = new BillingActions();
			logger.info("Retrieving Billing cycles for invoice generation");
			List<ServiceBillingForm> senActivityList = BA.getEligibleBillingCycles(this.jdbcTemplate, today, ydayDate);
		
			for(int i=0;i<senActivityList.size();i++) {
				BA.generateInvoice(this.jdbcTemplate,senActivityList.get(i),today);
			}
			logger.info("Invoice Generation Complete");
			
			//Clear expired hours
			List<ServiceBillingForm> srvcExpiredList = BA.getEligibleExpiredCycles(this.jdbcTemplate, expiry);
			logger.info("Attempting to set expiry for completed billing cycles - Count:"+srvcExpiredList.size());
			for(int i=0;i<srvcExpiredList.size();i++) {
				BA.setServiceExpiry(this.jdbcTemplate,srvcExpiredList.get(i));
			}
			
			
			//Deactivate expired SEN
			List<SENForm> expiredSenList = BA.getExpiredSEN(this.jdbcTemplate, today);
			logger.info("Attempting to deactivate expired Service Entitlements - count:" + expiredSenList.size());
			for(int i=0;i<expiredSenList.size();i++) {
				BA.setSENExpiry(jdbcTemplate, expiredSenList.get(i).getID());
			}
 			
	}
}









