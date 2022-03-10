package com.gsd.sreenidhi.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.gsd.sreenidhi.admin.settings.cloud.CloudComponentForm;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;

class ServiceConfigurator implements Runnable {

	Thread jobExecutor;
	private List<JobServiceStatusForm> jobObject;

	private JdbcTemplate jdbcTemplate;
	
	ServiceConfigurator(List<JobServiceStatusForm> list) {
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
		System.out.println("Running thread for Service Implemetation Executor:" + jobObject.get(0).getTX_SRVC_KEY());
		
		String UPDATE_SERVICE_STATUS_INIT_SQL = "UPDATE USER_SERVICE_STATUS SET FL_INIT = ? WHERE ID = ?";
		String UPDATE_SERVICE_STATUS_COMPLETE_SQL = "UPDATE USER_SERVICE_STATUS SET FL_COMPLETE = ? WHERE ID = ?";
		
		UserServicesForm usf = new UserServicesForm();
		
		for(int i=0;i<jobObject.size();i++) {
			
			if(jobObject.get(i).isFL_INIT()) {
				if(jobObject.get(i).isFL_COMPLETE()) {
					//completed this stage
				}else {
					//Still in progress
					// or 
					//something went wrong at this stage
				}
			}else {
				
				boolean forward = false;
				if(jobObject.get(i).getID_ORDER()>1) {
					String CHECK_PREV_STATE_SQL = "SELECT FL_COMPLETE FROM USER_SERVICE_STATUS WHERE TX_SRVC_KEY = '"+jobObject.get(i).getTX_SRVC_KEY()+"' AND ID_ORDER = " + (jobObject.get(i).getID_ORDER()-1);
					
					List<JobServiceStatusForm> cmpList = jdbcTemplate.query(CHECK_PREV_STATE_SQL, new RowMapper<JobServiceStatusForm>() {
			            public JobServiceStatusForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
			            	JobServiceStatusForm form = new JobServiceStatusForm();
			            	form.setFL_COMPLETE(resulSet.getInt("FL_COMPLETE")==1?true:false);
			                return form;
			            }
			        });
					if(cmpList.get(0).isFL_COMPLETE()) {
						forward = true;
					}else {
						forward = false;
					}
					
				}else {
					forward  = true;
				}
				
				
				if(forward) {
					
				
					final int itrId =  jobObject.get(i).getID_SRVC();
					//start implementing this stage
					//UPDATE STATUS DB TABLE - INIT
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_STATUS_INIT_SQL);
							preparedStatement.setInt(1, 1);
							preparedStatement.setInt(2, itrId);	
							return preparedStatement;
						}
					});
					
					ServiceImpl si = new ServiceImpl();
					boolean implStatus;
					usf = si.implementTask(jdbcTemplate, jobObject.get(i), usf);
					if(usf.isImplStat()) {
						//UPDATE STATUS DB TABLE - COMPLETE
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_STATUS_COMPLETE_SQL);
								preparedStatement.setInt(1,1);
								preparedStatement.setInt(2, itrId);	
								return preparedStatement;
							}
						});
					}else {
						/*
						 * jdbcTemplate.update(new PreparedStatementCreator() { public PreparedStatement
						 * createPreparedStatement(Connection connection) throws SQLException {
						 * PreparedStatement preparedStatement =
						 * connection.prepareStatement(UPDATE_SERVICE_STATUS_INIT_SQL);
						 * preparedStatement.setInt(1, 0); preparedStatement.setInt(2, itrId); return
						 * preparedStatement; } });
						 */
						
						String UPDATE_SERVICE_STATUS_ERRORT_SQL = "UPDATE USER_SERVICE_STATUS SET FL_ERROR = ? WHERE ID = ?";
						
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_STATUS_ERRORT_SQL);
								preparedStatement.setInt(1, 1);
								preparedStatement.setInt(2, itrId);	
								return preparedStatement;
							}
						});
						
						break;
					}
				}
			}
		}
	}

}