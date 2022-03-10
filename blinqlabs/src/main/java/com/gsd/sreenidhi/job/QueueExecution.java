package com.gsd.sreenidhi.job;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gsd.sreenidhi.admin.workbench.LabActions;

class QueueExecution implements Runnable {

	Thread jobExecutor;
	private List<JobQueueList> jobObject;

	private JdbcTemplate jdbcTemplate;
	
	QueueExecution(List<JobQueueList> list) {
		jobObject = list;
	}

	public void start(JdbcTemplate template) {
		this.jdbcTemplate = template;
		if (jobExecutor == null) {
			jobExecutor = new Thread(this);
			jobExecutor.start();
		}
	}

	@Override
	public void run() {
		System.out.println("Running thread for JobQueue:" + jobObject.get(0).getTX_JOB_KEY());
		
		 LabActions la = new LabActions();
		for(int i=0;i<jobObject.size();i++) {
			if("AWS".equalsIgnoreCase(jobObject.get(i).getTX_JOB_TYPE())){
				System.out.println("Procssing AWS Infrastructure");
				try {
					 la.initializeServiceSequencing(this.jdbcTemplate,"AWS", jobObject.get(i));
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
