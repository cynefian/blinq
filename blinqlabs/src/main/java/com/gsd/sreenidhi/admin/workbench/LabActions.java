package com.gsd.sreenidhi.admin.workbench;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.admin.settings.devops.ToolConfigForm;
import com.gsd.sreenidhi.job.JobQueueList;
import com.gsd.sreenidhi.job.ServiceImpl;
import com.gsd.sreenidhi.utils.Generator;

public class LabActions {

	public void getLabTools(JdbcTemplate jdbcTemplate) {}

	public List<UserServicesForm> getUserServices(JdbcTemplate jdbcTemplate, String userId, String filter) {
		String GET_USER_SERVICES_SQL = "SELECT DISTINCT "
				+ "				 LS.ID AS ID_SERVICE, "
				+ "				 LS.TX_LAB_SRVC_KEY AS TX_LAB_SRVC_KEY, "
				+ "				 LS.TX_INFRA_TYPE AS TX_INFRASTRUCTURE_TYPE, "
				+ "				 LS.ID_INFRASTRUCTURE AS ID_INFRASTRUCTURE, "
				+ "				 LS.ID_INFRA_SRVC_TYPE AS ID_INFRA_SERVICE_TYPE, "
				+ "				 INFRA_SERVICE_TYPE.TX_INFRA_SRVC_TYPE AS TX_INFRA_SRVC_TYPE, "
				+ "				 LS.ID_SERVICE_TOOL AS ID_SRVC_TOOL, "
				+ "				 DEVOPS_TOOLS.TX_TOOL_NAME AS TX_SRVC_TOOL_NAME, "
				+ "				 DEVOPS_TOOLS.TX_TOOL_IMAGE AS TX_SRVC_TOOL_IMG, "
				+ "				 DEVOPS_CATEGORIES.TX_CATEGORY AS TX_SRVC_TOOL_CATEGORY, "
				+ "				 DEVOPS_CATEGORIES.TX_CAT_CODE AS TX_SRVC_TOOL_CAT_CODE, "
				+ "				 LS.TX_PORT AS TX_PORT, "
				+ "				 LS.TX_ID_CONTAINER AS ID_CONTAINER, "
				+ "				 LS.FL_SERVICE_STATUS AS FL_SERVICE_STATUS, "
				+ "				 LS.TX_SERVICE_URL AS TX_SERVICE_URL, "
				+ "				 LS.ID_USER_WORKBENCH AS ID_WORKBENCH, "
				+ "				 LS.FL_INITIALIZED AS FL_SRVC_INITIALIZED, "
				+ "				 LS.TS_TIMESTAMP_CREATE AS TS_SRVC_TIMESTAMP_CREATE, "
				+ "				 USER_WORKBENCH.TX_WORKBENCH_NAME AS TX_WORKBENCH_NAME, "
				+ "				 USER_WORKBENCH.TX_WORKBENCH_KEY AS TX_WORKBENCH_KEY, "
				+ "				 LS.ID_USER AS ID_USER , "
				+ "				 CLOUD_COMPUTE_EC2.TX_IP AS TX_IP, "
				+ "				 CLOUD_VPC.ID_VPC_KEY AS TX_VPC_KEY, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY AS TX_CLOUD_COMPUTE_KEY, "
				+ "				 LAB_SERVICE_INFRASTRUCTURE.FL_STATUS AS SRVC_INFRA_STATUS "
				+ "				 FROM LAB_SERVICE LS "
				+ "				 JOIN USERS ON USERS.ID = LS.ID_uSER "
				+ "				 JOIN INFRA_TYPE  ON LS.TX_INFRA_TYPE = INFRA_TYPE.TX_INFRA_TYPE "
				+ "				 JOIN INFRA_SERVICE_TYPE ON INFRA_SERVICE_TYPE.ID = LS.ID_INFRA_SRVC_TYPE "
				+ "				 JOIN USER_WORKBENCH ON USER_WORKBENCH.ID = LS.ID_USER_WORKBENCH "
				+ "				 JOIN DEVOPS_TOOLS ON DEVOPS_TOOLS.ID = LS.ID_SERVICE_TOOL "
				+ "				 JOIN DEVOPS_CATEGORY_TOOL_LINK ON DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_TOOL = DEVOPS_TOOLS.ID "
				+ "				 JOIN DEVOPS_CATEGORIES ON DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_CATEGORY = DEVOPS_CATEGORIES.ID "
				+ "				 LEFT JOIN LAB_SERVICE_INFRASTRUCTURE ON LS.ID = LAB_SERVICE_INFRASTRUCTURE.ID_LAB_SERVICE "
				+ "				 LEFT JOIN CLOUD_COMPUTE_EC2 ON CLOUD_COMPUTE_EC2.ID = LAB_SERVICE_INFRASTRUCTURE.ID_CLOUD_COMPUTE  AND INFRA_TYPE.ID = CLOUD_COMPUTE_EC2.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_VPC ON CLOUD_VPC.ID = CLOUD_COMPUTE_EC2.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_VPC.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_NACL ON CLOUD_VPC.ID = CLOUD_NACL.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_NACL.ID_INFRA_TYPE "
				+ "				 LEFT JOIN ROUTE_TABLE ON CLOUD_VPC.ID = ROUTE_TABLE.ID_CLOUD_VPC  AND INFRA_TYPE.ID = ROUTE_TABLE.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_SECURITY_GROUP ON CLOUD_VPC.ID = CLOUD_SECURITY_GROUP.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_SECURITY_GROUP.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_SUBNET ON CLOUD_VPC.ID = CLOUD_SUBNET.ID_CLOUD_VPC AND INFRA_TYPE.ID = CLOUD_SUBNET.ID_INFRA_TYPE "
				+ "				 WHERE LS.ID_USER = " + userId;
		if(filter!=null) {
			GET_USER_SERVICES_SQL += " " + filter;
		}
				
		
		List<UserServicesForm> userServicesList = jdbcTemplate.query(GET_USER_SERVICES_SQL, new RowMapper<UserServicesForm>() {
			public UserServicesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserServicesForm uService = new UserServicesForm();
				uService.setID_SERVICE(resulSet.getInt("ID_SERVICE"));
				uService.setTX_LAB_SRVC_KEY(resulSet.getString("TX_LAB_SRVC_KEY"));
				uService.setTX_INFRASTRUCTURE_TYPE(resulSet.getString("TX_INFRASTRUCTURE_TYPE"));
				uService.setID_INFRASTRUCTURE(resulSet.getInt("ID_INFRASTRUCTURE"));
				uService.setID_INFRA_SERVICE_TYPE(resulSet.getInt("ID_INFRA_SERVICE_TYPE"));
				uService.setTX_INFRA_SRVC_TYPE(resulSet.getString("TX_INFRA_SRVC_TYPE"));
				uService.setID_SRVC_TOOL(resulSet.getInt("ID_SRVC_TOOL"));
				uService.setTX_SRVC_TOOL_NAME(resulSet.getString("TX_SRVC_TOOL_NAME"));
				uService.setTX_SRVC_TOOL_IMG(resulSet.getString("TX_SRVC_TOOL_IMG"));
				uService.setTX_SRVC_TOOL_CATEGORY(resulSet.getString("TX_SRVC_TOOL_CATEGORY"));
				uService.setTX_SRVC_TOOL_CAT_CODE(resulSet.getString("TX_SRVC_TOOL_CAT_CODE"));
				uService.setTX_PORT(resulSet.getString("TX_PORT"));
				uService.setID_CONTAINER(resulSet.getString("ID_CONTAINER"));
				uService.setFL_SERVICE_STATUS(resulSet.getInt("FL_SERVICE_STATUS")==1?true:false);
				uService.setTX_SERVICE_URL(resulSet.getString("TX_SERVICE_URL"));
				uService.setID_WORKBENCH(resulSet.getInt("ID_WORKBENCH"));
				uService.setTX_WORKBENCH_NAME(resulSet.getString("TX_WORKBENCH_NAME"));
				uService.setTX_WORKBENCH_KEY(resulSet.getString("TX_WORKBENCH_KEY"));
				uService.setID_USER(resulSet.getInt("ID_USER"));
				uService.setFL_SRVC_INITIALIZED(resulSet.getInt("FL_SRVC_INITIALIZED")==1?true:false);
				uService.setTS_SRVC_TIMESTAMP_CREATE(resulSet.getString("TS_SRVC_TIMESTAMP_CREATE"));
				
				uService.setTX_IP(resulSet.getString("TX_IP"));
				uService.setTX_ID_VPC(resulSet.getString("TX_VPC_KEY"));
				uService.setTX_ID_EC2(resulSet.getString("TX_CLOUD_COMPUTE_KEY"));
				uService.setTX_ID_AMI("");
				uService.setFL_INFRASTRUCTURE_STATUS(resulSet.getInt("SRVC_INFRA_STATUS")==1?true:false);
				uService.setTX_SERVICES_COUNT(0);
					
				
				
				return uService;
			}
		});
		
		return userServicesList;
	}

	
	public void initializeServiceSequencing(JdbcTemplate jdbcTemplate, String infrastructure, JobQueueList jobQueueList) throws IOException, InterruptedException {
		String JOB_SRVC_STATUS_SQL = "IF NOT EXISTS (SELECT * FROM USER_SERVICE_STATUS WHERE TX_SRVC_KEY = ? AND TX_STATUS = ?) "
				+ " INSERT INTO USER_SERVICE_STATUS (TX_SRVC_KEY, TX_STATUS, ID_ORDER, FL_INIT, FL_COMPLETE) VALUES (?,?,?,?,?)";
		
		ServiceImpl si = new ServiceImpl();
		
		HashMap<Integer, String> sequencer = si.getJOB_SEQUENCE();
		
		Iterator<Integer> itr = sequencer.keySet().iterator();
		while (itr.hasNext()) {
			
			int keyItem = itr.next();
			String status =  sequencer.get(keyItem);
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(JOB_SRVC_STATUS_SQL);
					preparedStatement.setString(1, jobQueueList.getTX_JOB_KEY());
					preparedStatement.setString(2, status);
					preparedStatement.setString(3, jobQueueList.getTX_JOB_KEY());
					preparedStatement.setString(4, status);
					preparedStatement.setInt(5, keyItem);
					preparedStatement.setInt(6, 0);
					preparedStatement.setInt(7, 0);
					return preparedStatement;
				}
			});
		}
	}
	
	
	
	public List<UserServicesForm> getUserServicesByKey(JdbcTemplate jdbcTemplate, String filter) {
		String GET_USER_SERVICES_SQL = "SELECT "
				+ "				 LS.ID AS ID_SERVICE, "
				+ "				 LS.TX_LAB_SRVC_KEY AS TX_LAB_SRVC_KEY, "
				+ "				 LS.TX_INFRA_TYPE AS TX_INFRASTRUCTURE_TYPE, "
				+ "				 LS.ID_INFRASTRUCTURE AS ID_INFRASTRUCTURE, "
				+ "				 LS.ID_INFRA_SRVC_TYPE AS ID_INFRA_SERVICE_TYPE, "
				+ "				 INFRA_SERVICE_TYPE.TX_INFRA_SRVC_TYPE AS TX_INFRA_SRVC_TYPE, "
				+ "				 LS.ID_SERVICE_TOOL AS ID_SRVC_TOOL, "
				+ "				 DEVOPS_TOOLS.TX_TOOL_NAME AS TX_SRVC_TOOL_NAME, "
				+ "				 DEVOPS_TOOLS.TX_TOOL_IMAGE AS TX_SRVC_TOOL_IMG, "
				+ "				 DEVOPS_CATEGORIES.TX_CATEGORY AS TX_SRVC_TOOL_CATEGORY, "
				+ "				 DEVOPS_CATEGORIES.TX_CAT_CODE AS TX_SRVC_TOOL_CAT_CODE, "
				+ "				 LS.TX_PORT AS TX_PORT, "
				+ "				 LS.TX_ID_CONTAINER AS ID_CONTAINER, "
				+ "				 LS.FL_SERVICE_STATUS AS FL_SERVICE_STATUS, "
				+ "				 LS.TX_SERVICE_URL AS TX_SERVICE_URL, "
				+ "				 LS.ID_USER_WORKBENCH AS ID_WORKBENCH, "
				+ "				 LS.FL_INITIALIZED AS FL_SRVC_INITIALIZED, "
				+ "				 LS.TS_TIMESTAMP_CREATE AS TS_SRVC_TIMESTAMP_CREATE, "
				+ "				 USER_WORKBENCH.TX_WORKBENCH_NAME AS TX_WORKBENCH_NAME, "
				+ "				 USER_WORKBENCH.TX_WORKBENCH_KEY AS TX_WORKBENCH_KEY, "
				+ "				 LS.ID_USER AS ID_USER , "
				+ "				 CLOUD_COMPUTE_EC2.TX_IP AS TX_IP, "
				+ "				 CLOUD_VPC.ID_VPC_KEY AS TX_VPC_KEY, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY AS TX_CLOUD_COMPUTE_KEY, "
				+ "				 LAB_SERVICE_INFRASTRUCTURE.FL_STATUS AS SRVC_INFRA_STATUS "
				+ "				 FROM LAB_SERVICE LS "
				+ "				 JOIN USERS ON USERS.ID = LS.ID_uSER "
				+ "				 JOIN INFRA_TYPE  ON LS.TX_INFRA_TYPE = INFRA_TYPE.TX_INFRA_TYPE "
				+ "				 JOIN INFRA_SERVICE_TYPE ON INFRA_SERVICE_TYPE.ID = LS.ID_INFRA_SRVC_TYPE "
				+ "				 JOIN USER_WORKBENCH ON USER_WORKBENCH.ID = LS.ID_USER_WORKBENCH "
				+ "				 JOIN DEVOPS_TOOLS ON DEVOPS_TOOLS.ID = LS.ID_SERVICE_TOOL "
				+ "				 JOIN DEVOPS_CATEGORY_TOOL_LINK ON DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_TOOL = DEVOPS_TOOLS.ID "
				+ "				 JOIN DEVOPS_CATEGORIES ON DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_CATEGORY = DEVOPS_CATEGORIES.ID "
				+ "				 LEFT JOIN LAB_SERVICE_INFRASTRUCTURE ON LS.ID = LAB_SERVICE_INFRASTRUCTURE.ID_LAB_SERVICE "
				+ "				 LEFT JOIN CLOUD_COMPUTE_EC2 ON CLOUD_COMPUTE_EC2.ID = LAB_SERVICE_INFRASTRUCTURE.ID_CLOUD_COMPUTE  AND INFRA_TYPE.ID = CLOUD_COMPUTE_EC2.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_VPC ON CLOUD_VPC.ID = CLOUD_COMPUTE_EC2.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_VPC.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_NACL ON CLOUD_VPC.ID = CLOUD_NACL.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_NACL.ID_INFRA_TYPE "
				+ "				 LEFT JOIN ROUTE_TABLE ON CLOUD_VPC.ID = ROUTE_TABLE.ID_CLOUD_VPC  AND INFRA_TYPE.ID = ROUTE_TABLE.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_SECURITY_GROUP ON CLOUD_VPC.ID = CLOUD_SECURITY_GROUP.ID_CLOUD_VPC  AND INFRA_TYPE.ID = CLOUD_SECURITY_GROUP.ID_INFRA_TYPE "
				+ "				 LEFT JOIN CLOUD_SUBNET ON CLOUD_VPC.ID = CLOUD_SUBNET.ID_CLOUD_VPC AND INFRA_TYPE.ID = CLOUD_SUBNET.ID_INFRA_TYPE "
				+ "				 WHERE LS.TX_LAB_SRVC_KEY = '" + filter + "'";
		
		List<UserServicesForm> userServicesList = jdbcTemplate.query(GET_USER_SERVICES_SQL, new RowMapper<UserServicesForm>() {
			public UserServicesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserServicesForm uService = new UserServicesForm();
				uService.setID_SERVICE(resulSet.getInt("ID_SERVICE"));
				uService.setTX_LAB_SRVC_KEY(resulSet.getString("TX_LAB_SRVC_KEY"));
				uService.setTX_INFRASTRUCTURE_TYPE(resulSet.getString("TX_INFRASTRUCTURE_TYPE"));
				uService.setID_INFRASTRUCTURE(resulSet.getInt("ID_INFRASTRUCTURE"));
				uService.setID_INFRA_SERVICE_TYPE(resulSet.getInt("ID_INFRA_SERVICE_TYPE"));
				uService.setTX_INFRA_SRVC_TYPE(resulSet.getString("TX_INFRA_SRVC_TYPE"));
				uService.setID_SRVC_TOOL(resulSet.getInt("ID_SRVC_TOOL"));
				uService.setTX_SRVC_TOOL_NAME(resulSet.getString("TX_SRVC_TOOL_NAME"));
				uService.setTX_SRVC_TOOL_IMG(resulSet.getString("TX_SRVC_TOOL_IMG"));
				uService.setTX_SRVC_TOOL_CATEGORY(resulSet.getString("TX_SRVC_TOOL_CATEGORY"));
				uService.setTX_SRVC_TOOL_CAT_CODE(resulSet.getString("TX_SRVC_TOOL_CAT_CODE"));
				uService.setTX_PORT(resulSet.getString("TX_PORT"));
				uService.setID_CONTAINER(resulSet.getString("ID_CONTAINER"));
				uService.setFL_SERVICE_STATUS(resulSet.getInt("FL_SERVICE_STATUS")==1?true:false);
				uService.setTX_SERVICE_URL(resulSet.getString("TX_SERVICE_URL"));
				uService.setID_WORKBENCH(resulSet.getInt("ID_WORKBENCH"));
				uService.setTX_WORKBENCH_NAME(resulSet.getString("TX_WORKBENCH_NAME"));
				uService.setTX_WORKBENCH_KEY(resulSet.getString("TX_WORKBENCH_KEY"));
				uService.setID_USER(resulSet.getInt("ID_USER"));
				uService.setFL_SRVC_INITIALIZED(resulSet.getInt("FL_SRVC_INITIALIZED")==1?true:false);
				uService.setTS_SRVC_TIMESTAMP_CREATE(resulSet.getString("TS_SRVC_TIMESTAMP_CREATE"));
				
				uService.setTX_IP(resulSet.getString("TX_IP"));
				uService.setTX_ID_VPC(resulSet.getString("TX_VPC_KEY"));
				uService.setTX_ID_EC2(resulSet.getString("TX_CLOUD_COMPUTE_KEY"));
				uService.setTX_ID_AMI("");
				uService.setFL_INFRASTRUCTURE_STATUS(resulSet.getInt("SRVC_INFRA_STATUS")==1?true:false);
				uService.setTX_SERVICES_COUNT(0);
					
				
				
				return uService;
			}
		});
		
		return userServicesList;
	}

	public List<ToolConfigForm> getServiceConfig(JdbcTemplate jdbcTemplate, List<UserServicesForm> userServicesList) {
		
		List<ToolConfigForm> configList = new ArrayList<ToolConfigForm>() ;
		if(userServicesList!=null && userServicesList.size()>0) {
			for ( int i=0;i<userServicesList.size();i++) {
				
				String GET_CONFIG_FOR_SRVC = "SELECT "
						+ "LAB_SERVICE.ID AS ID_LAB_SERVICE, "
						+ "LAB_SERVICE.TX_LAB_SRVC_KEY AS TX_LAB_SRVC_KEY, "
						+ "LAB_SERVICE_TOOL_CONFIG.ID_CONFIG AS ID_CONFIG, "
						+ "DEVOPS_TOOL_CONFIG.TX_CONFIG_NAME AS TX_CONFIG_NAME, "
						+ "LAB_SERVICE_TOOL_CONFIG.TX_RESULT AS TX_RESULT "
						+ "FROM LAB_SERVICE_TOOL_CONFIG "
						+ "JOIN LAB_SERVICE ON LAB_SERVICE.ID = LAB_SERVICE_TOOL_CONFIG.ID_LAB_SRVC "
						+ "JOIN DEVOPS_TOOL_CONFIG on DEVOPS_TOOL_CONFIG.ID = ID_CONFIG "
						+ "WHERE LAB_SERVICE.ID = " + userServicesList.get(i).getID_SERVICE();
				
				List<ToolConfigForm> srvcConfList = jdbcTemplate.query(GET_CONFIG_FOR_SRVC, new RowMapper<ToolConfigForm>() {
					public ToolConfigForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						ToolConfigForm srvcConf = new ToolConfigForm();
						srvcConf.setID_LAB_SRVC(resulSet.getInt("ID_LAB_SERVICE"));
						srvcConf.setTX_LAB_SRVC_KEY(resulSet.getString("TX_LAB_SRVC_KEY"));
						srvcConf.setID(resulSet.getInt("ID_CONFIG"));
						srvcConf.setTX_CONFIG_NAME(resulSet.getString("TX_CONFIG_NAME"));
						srvcConf.setTX_RESULT(resulSet.getString("TX_RESULT"));
					
						return srvcConf;
					}
				});
				
				if(srvcConfList!=null && srvcConfList.size()>0) {
					for(int j=0;j<srvcConfList.size();j++) {
						configList.add(srvcConfList.get(j));
					}
				}
				
			}
		}
		
		return configList;
	}

	
}
