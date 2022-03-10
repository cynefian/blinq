package com.gsd.sreenidhi.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.gsd.sreenidhi.admin.workbench.LabActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;

@Configuration
@PropertySource("classpath:db.properties")
public class JobService {

	private static final Logger logger = LoggerFactory.getLogger(JobService.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	private DataSource dataSource;

	public JobService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.jdbcTemplate = (new JdbcTemplate(getDataSource()));
	}

	@Autowired
	public JobService(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	public void processQueue() {
		System.out.println("ScheduleTask cronSchedule Every 60 seconds is called: " + new Date());
		JobServiceActions jsa = new JobServiceActions();
		List<JobQueueList> jobList = jsa.readQueuedJobs(jdbcTemplate);

		if (jobList != null && jobList.size() > 0) {
			jsa.updateQueueToRead(jdbcTemplate, jobList);

			Map<String, List<JobQueueList>> groupByKeyMap = jobList.stream()
					.collect(Collectors.groupingBy(JobQueueList::getTX_JOB_KEY));

			Iterator<Entry<String, List<JobQueueList>>> it = groupByKeyMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, List<JobQueueList>> pair = (Map.Entry<String, List<JobQueueList>>) it.next();
				QueueExecution qExec = new QueueExecution(pair.getValue());
				qExec.start(this.jdbcTemplate);
			}
		}
		
		List<JobServiceStatusForm> jssfList = jsa.getServiceStatus(jdbcTemplate, null, null, " ASC ");
		
		if (jssfList != null && jssfList.size() > 0) {
			Map<String, List<JobServiceStatusForm>> serviceGrouping = jssfList.stream()
					.collect(Collectors.groupingBy(JobServiceStatusForm::getTX_SRVC_KEY));

			Iterator<Entry<String, List<JobServiceStatusForm>>> itr = serviceGrouping.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<String, List<JobServiceStatusForm>> pair = (Map.Entry<String, List<JobServiceStatusForm>>) itr.next();
				ServiceConfigurator srvConfig = new ServiceConfigurator(pair.getValue());
				srvConfig.start(this.jdbcTemplate);
			}
		}
		
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

}









