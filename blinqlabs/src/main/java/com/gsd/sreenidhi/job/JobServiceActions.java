package com.gsd.sreenidhi.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.admin.settings.devops.DevOpsActions;
import com.gsd.sreenidhi.admin.workbench.LabActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;
import com.gsd.sreenidhi.admin.workbench.WorkbenchForm;

public class JobServiceActions {
	

	public void addToQueue(JdbcTemplate jdbcTemplate, String tx_Job_type, int id_Job_Type, String tx_table, String tx_key, String tx_operation,
			String job_status, String date, String userId) throws IOException, InterruptedException {
		
		if("LAB_SERVICE".equalsIgnoreCase(tx_table)) {
			DevOpsActions da = new DevOpsActions();
			tx_Job_type = da.getDevOpsToolNameByLabJobId(jdbcTemplate, id_Job_Type);
		}

		final String tx_type = tx_Job_type;
		String INSERT_JOB_QUEUE_SQL = "INSERT INTO JOB_QUEUE(TX_JOB_TYPE, ID_JOB_TYPE, TX_JOB_TABLE, TX_JOB_KEY, TX_JOB_OPERATION, TX_JOB_STATUS, TS_TIMESTAMP_CREATE, ID_USER) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		
		final KeyHolder svcKey = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_JOB_QUEUE_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, tx_type);
				preparedStatement.setInt(2, id_Job_Type);
				preparedStatement.setString(3, tx_table);
				preparedStatement.setString(4, tx_key);
				preparedStatement.setString(5, tx_operation);
				preparedStatement.setString(6, job_status);
				preparedStatement.setString(7, date);
				preparedStatement.setInt(8, Integer.parseInt(userId));
				return preparedStatement;
			}
		}, svcKey);
		
	}

	public List<JobQueueList> readQueuedJobs(JdbcTemplate jdbcTemplate) {
		String GET_JOBS_SQL = "SELECT ID, TX_JOB_TYPE, ID_JOB_TYPE, TX_JOB_TABLE, TX_JOB_KEY, TX_JOB_OPERATION, TX_JOB_STATUS, TS_TIMESTAMP_CREATE, ID_USER FROM JOB_QUEUE WHERE TX_JOB_STATUS = 'QUEUE'";
		
		List<JobQueueList> jobList = jdbcTemplate.query(GET_JOBS_SQL, new RowMapper<JobQueueList>() {
            public JobQueueList mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	JobQueueList job = new JobQueueList();
            		job.setID(resulSet.getInt("ID"));
            		job.setTX_JOB_TYPE(resulSet.getString("TX_JOB_TYPE"));
            		job.setID_JOB_TYPE(resulSet.getInt("ID_JOB_TYPE"));
            		job.setTX_JOB_TABLE(resulSet.getString("TX_JOB_TABLE"));
            		job.setTX_JOB_KEY(resulSet.getString("TX_JOB_KEY"));
            		job.setTX_JOB_OPERATION(resulSet.getString("TX_JOB_OPERATION"));
            		job.setTX_JOB_STATUS(resulSet.getString("TX_JOB_STATUS"));
            		job.setTS_JOB_CREATE(resulSet.getString("TS_TIMESTAMP_CREATE"));
            		job.setID_USER(resulSet.getInt("ID_USER"));
        	    return job;
            }
        });
		System.out.println("JobList Count:" + jobList.size());
		return jobList;
	}

	public void deleteReadJobs(JdbcTemplate jdbcTemplate, List<JobQueueList> jobList) {
		if(jobList!=null && jobList.size()>0) {
			String DELETE_JOB_FROM_QUEUE_SQL="DELETE FROM JOB_QUEUE WHERE ID = ? ";
			for(int i=0;i<jobList.size();i++) {
				final int loop = i;
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DELETE_JOB_FROM_QUEUE_SQL);
						preparedStatement.setInt(1, jobList.get(loop).getID());
						return preparedStatement;
						
					}
				});
			}
		}
	}

	public void updateQueueToRead(JdbcTemplate jdbcTemplate, List<JobQueueList> jobList) {
		if(jobList!=null && jobList.size()>0) {
			String UPDATE_JOB_STATUS_QUEUE_SQL="UPDATE JOB_QUEUE SET TX_JOB_STATUS = 'READ' WHERE ID = ? ";
			for(int i=0;i<jobList.size();i++) {
				final int loop = i;
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_JOB_STATUS_QUEUE_SQL);
						preparedStatement.setInt(1, jobList.get(loop).getID());
						return preparedStatement;
						
					}
				});
			}
		}
		
	}

	public String checkServiceStatus(HttpSession session, JdbcTemplate jdbcTemplate, String key) {
		String status="UNKNOWN";
		
		String filter = " USER_SERVICE_STATUS.FL_COMPLETE = 1 OR (USER_SERVICE_STATUS.FL_COMPLETE = 0 AND USER_SERVICE_STATUS.FL_INIT = 1) ";
		String ordering = "DESC";
		List<JobServiceStatusForm> jssfList = getServiceStatus(jdbcTemplate, key, filter, ordering);
		
		if(jssfList!=null && jssfList.size()>0) {
			if(jssfList.get(0).isFL_COMPLETE()) {
				status = "Completed - " + jssfList.get(0).getTX_STATUS();
			}else {
				status = "Started -" + jssfList.get(0).getTX_STATUS();
			}
			
		}else {
			status = "Queued";
		}
		
		return status;
	}

	public List<JobServiceStatusForm> getServiceStatus(JdbcTemplate jdbcTemplate, String key, String filter, String ordering) {
		
		boolean hasWhere = false;
		boolean hasAnd = false;
		String fullFilter ="";
		if(key!=null && filter!=null) {
			hasWhere = true;
			hasAnd = true;
			fullFilter = " WHERE " + "USER_SERVICE_STATUS.TX_SRVC_KEY = '"+ key + "' AND " + filter;
		}else if(key!=null || filter !=null){
			if(key!=null) {
				fullFilter = " WHERE " + "USER_SERVICE_STATUS.TX_SRVC_KEY = '"+ key + "' ";
			}else {
				fullFilter = " WHERE " + filter;
			}
		}
		
		if(ordering==null) {
			ordering = " ASC ";
		}
		
		
		String GET_SERVICE_STATUS_SQL = "SELECT "
				+ "LAB_SERVICE.ID_SERVICE_TOOL AS ID_TOOL, "
				+ "DEVOPS_TOOLS.TX_TOOL_NAME AS TX_TOOL, "
				+ "USER_SERVICE_STATUS.ID AS ID_SERVICE, "
				+ "USER_SERVICE_STATUS.TX_SRVC_KEY AS TX_SRVC_KEY, "
				+ "USER_SERVICE_STATUS.TX_STATUS AS TX_STATUS, "
				+ "USER_SERVICE_STATUS.ID_ORDER AS ID_ORDER, "
				+ "USER_SERVICE_STATUS.FL_INIT AS FL_INIT, "
				+ "USER_SERVICE_STATUS.FL_COMPLETE AS FL_COMPLETE, "
				+ "USER_SERVICE_STATUS.TS_COMPLETE AS TS_COMPLETE "
				+ "FROM USER_SERVICE_STATUS "
				+ "JOIN LAB_SERVICE ON LAB_SERVICE.TX_LAB_SRVC_KEY = USER_SERVICE_STATUS.TX_SRVC_KEY "
				+ "JOIN DEVOPS_TOOLS on DEVOPS_TOOLS.ID = LAB_SERVICE.ID_SERVICE_TOOL "
				+ fullFilter +" ORDER BY  USER_SERVICE_STATUS.TX_SRVC_KEY, USER_SERVICE_STATUS.ID_ORDER " + ordering;
		
		List<JobServiceStatusForm> jssfList = jdbcTemplate.query(GET_SERVICE_STATUS_SQL, new RowMapper<JobServiceStatusForm>() {
			public JobServiceStatusForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				JobServiceStatusForm jssf = new JobServiceStatusForm();
				jssf.setID_TOOL(resulSet.getInt("ID_TOOL"));
				jssf.setTX_TOOL(resulSet.getString("TX_TOOL"));
				jssf.setID_SRVC(resulSet.getInt("ID_SERVICE"));
				jssf.setTX_SRVC_KEY(resulSet.getString("TX_SRVC_KEY"));
				jssf.setTX_STATUS(resulSet.getString("TX_STATUS"));
				jssf.setID_ORDER(resulSet.getInt("ID_ORDER"));
				jssf.setFL_INIT(resulSet.getInt("FL_INIT")==1?true:false);
				jssf.setFL_COMPLETE(resulSet.getInt("FL_COMPLETE")==1?true:false);
				jssf.setTS_COMPELTE(resulSet.getString("TS_COMPLETE"));
				return jssf;
			}
		});
		return jssfList;
	}

	public String checkExistingWorkbench(HttpSession session, JdbcTemplate jdbcTemplate, String key) {
		String GET_WB_SQL = "SELECT TX_WORKBENCH_NAME FROM USER_WORKBENCH WHERE TX_WORKBENCH_NAME = '"+key+"'";
		
		List<UserServicesForm> wbList = jdbcTemplate.query(GET_WB_SQL, new RowMapper<UserServicesForm>() {
            public UserServicesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	UserServicesForm job = new UserServicesForm();
            		job.setTX_WORKBENCH_NAME(resulSet.getString("TX_WORKBENCH_NAME"));
            	return job;
            }
        });
		if(wbList!=null && wbList.size()>0) {
			return "false";
		}else {
			return "true";
		}
	}
	

}
