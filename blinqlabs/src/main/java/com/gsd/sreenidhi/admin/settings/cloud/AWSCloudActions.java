package com.gsd.sreenidhi.admin.settings.cloud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.gsd.sreenidhi.job.JobService;
import com.gsd.sreenidhi.tables.Accounts;
import com.gsd.sreenidhi.utils.CalendarUtils;

public class AWSCloudActions {

	public List<CloudAuthForm> getAuthentication(JdbcTemplate jdbcTemplate) {
		String GET_AWS_AUTH_SQL = "SELECT ID, TX_IDENTIFIER, TX_ACCESS_KEY, TX_ACCESS_SECRET, TS_TIMESTAMP FROM CLOUD_AUTH WHERE TX_PROVIDER = 'AWS'";
		
		List<CloudAuthForm> authList = jdbcTemplate.query(GET_AWS_AUTH_SQL, new RowMapper<CloudAuthForm>() {
            public CloudAuthForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudAuthForm auth = new CloudAuthForm();
            	auth.setID_CLOUD_AUTH(resulSet.getInt("ID"));
            	auth.setTS_TIMESTAMP(resulSet.getString("TS_TIMESTAMP"));
            	auth.setTX_ACCESS_KEY(resulSet.getString("TX_ACCESS_KEY"));
            	auth.setTX_ACCESS_SECRET(resulSet.getString("TX_ACCESS_SECRET"));
            	auth.setTX_IDENTIFIER(resulSet.getString("TX_IDENTIFIER"));
                return auth;
            }
        });
		
		return authList;
	}

	public void updateAuthentication(JdbcTemplate jdbcTemplate, @Valid CloudAuthForm cloudAuthForm) {
		
		final CloudAuthForm caf= cloudAuthForm;
		final String dt = CalendarUtils.dateToStringDateTimeReadable(new Date());
		
		if(cloudAuthForm.getID_CLOUD_AUTH()>0) {
			String INSERT_AWS_CRED_SQL = "UPDATE CLOUD_AUTH SET"
					+ " TX_IDENTIFIER = ? ,"
					+ " TX_ACCESS_KEY = ? ,"
					+ " TX_ACCESS_SECRET = ? ,"
					+ " TS_TIMESTAMP = ? "
					+ " WHERE ID = ?";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AWS_CRED_SQL);
					preparedStatement.setString(1, caf.getTX_IDENTIFIER());
					preparedStatement.setString(2, caf.getTX_ACCESS_KEY());
					preparedStatement.setString(3, caf.getTX_ACCESS_SECRET());
					preparedStatement.setString(4, dt);
					preparedStatement.setInt(5, caf.getID_CLOUD_AUTH());
					
					return preparedStatement;
				}
			});
		}else {
			String INSERT_AWS_CRED_SQL = "IF NOT EXISTS(SELECT * FROM CLOUD_AUTH WHERE TX_PROVIDER='AWS') "
					+ " INSERT INTO CLOUD_AUTH(TX_PROVIDER, TX_IDENTIFIER, TX_ACCESS_KEY, TX_ACCESS_SECRET, TS_TIMESTAMP) VALUES('AWS', ?,?,?,?)";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AWS_CRED_SQL);
					preparedStatement.setString(1, caf.getTX_IDENTIFIER());
					preparedStatement.setString(2, caf.getTX_ACCESS_KEY());
					preparedStatement.setString(3, caf.getTX_ACCESS_SECRET());
					preparedStatement.setString(4, dt);
					
					return preparedStatement;
				}
			});
		}
		
		
	}

	public List<CloudComponentForm> getCloudVPC(JdbcTemplate jdbcTemplate) {
	
		String GET_AWS_VPC_SQL = "SELECT ID, ID_INFRA_TYPE, ID_VPC_KEY, TX_VPC_NAME, FL_DEFAULT FROM CLOUD_VPC";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_VPC_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_INFRA_TYPE(resulSet.getInt("ID_INFRA_TYPE"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("ID_VPC_KEY"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_VPC_NAME"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
                return form;
            }
        });
		return cmpList;
	}
	
	public List<CloudComponentForm> getCloudVPCByKey(JdbcTemplate jdbcTemplate, String key) {
		
		String GET_AWS_VPC_SQL = "SELECT ID, ID_INFRA_TYPE, ID_VPC_KEY, TX_VPC_NAME, FL_DEFAULT FROM CLOUD_VPC WHERE ID_VPC_KEY='"+key+"'";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_VPC_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_INFRA_TYPE(resulSet.getInt("ID_INFRA_TYPE"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("ID_VPC_KEY"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_VPC_NAME"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
                return form;
            }
        });
		return cmpList;
	}

	public List<CloudComponentForm> getCloudNACL(JdbcTemplate jdbcTemplate) {

		String GET_AWS_SQL = "select ID, ID_CLOUD_VPC, TX_NACL_NAME, TX_NACL_KEY, FL_DEFAULT FROM CLOUD_NACL WHERE ID_INFRA_TYPE = 1";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_NACL_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_NACL_KEY"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
                return form;
            }
        });
		
		return cmpList;
	}

	public List<CloudComponentForm> getCloudRouteTables(JdbcTemplate jdbcTemplate) {

		String GET_AWS_SQL = "select ID, ID_CLOUD_VPC, TX_ROUTE_TABLE_NAME, TX_ROUTE_TABLE_KEY, FL_DEFAULT FROM ROUTE_TABLE WHERE ID_INFRA_TYPE = 1";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_ROUTE_TABLE_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_ROUTE_TABLE_KEY"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
                return form;
            }
        });
		
		return cmpList;
	}

	public List<CloudComponentForm> getCloudSubnet(JdbcTemplate jdbcTemplate) {
		
		String GET_AWS_SQL = "SELECT "
				+ "CLOUD_SUBNET.ID, "
				+ "CLOUD_SUBNET.ID_CLOUD_VPC, "
				+ "CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "CLOUD_SUBNET.FL_DEFAULT, "
				+ "COUNT(CLOUD_COMPUTE_EC2.ID_SUBNET) AS USAGE_COUNT "
				+ "FROM CLOUD_SUBNET "
				+ "LEFT JOIN CLOUD_COMPUTE_EC2 "
				+ "on CLOUD_COMPUTE_EC2.ID_SUBNET = CLOUD_SUBNET.ID "
				+ " AND CLOUD_COMPUTE_EC2.ID_INFRA_TYPE = 1 "
				+ " WHERE CLOUD_SUBNET.ID_INFRA_TYPE = 1 "
				+ "GROUP BY CLOUD_SUBNET.ID, "
				+ "CLOUD_SUBNET.ID_CLOUD_VPC, "
				+ "CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "CLOUD_SUBNET.FL_DEFAULT ";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_SUBNET_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_SUBNET_KEY"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
            	form.setTX_USAGE_COUNTER(resulSet.getString("USAGE_COUNT"));
                return form;
            }
        });
		
		return cmpList;
	}

	
public List<CloudComponentForm> getCloudSubnetByKey(JdbcTemplate jdbcTemplate, String key) {
		
		String GET_AWS_SQL = "SELECT "
				+ "CLOUD_SUBNET.ID, "
				+ "CLOUD_SUBNET.ID_CLOUD_VPC, "
				+ "CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "CLOUD_SUBNET.FL_DEFAULT, "
				+ "COUNT(CLOUD_COMPUTE_EC2.ID_SUBNET) AS USAGE_COUNT "
				+ "FROM CLOUD_SUBNET "
				+ "LEFT JOIN CLOUD_COMPUTE_EC2 "
				+ "on CLOUD_COMPUTE_EC2.ID_SUBNET = CLOUD_SUBNET.ID "
				+ " AND CLOUD_COMPUTE_EC2.ID_INFRA_TYPE = 1 "
				+ " WHERE CLOUD_SUBNET.ID_INFRA_TYPE = 1 "
				+ " AND CLOUD_SUBNET.TX_SUBNET_KEY = '" + key + "'" 
				+ "GROUP BY CLOUD_SUBNET.ID, "
				+ "CLOUD_SUBNET.ID_CLOUD_VPC, "
				+ "CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "CLOUD_SUBNET.FL_DEFAULT ";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_SUBNET_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_SUBNET_KEY"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
            	form.setTX_USAGE_COUNTER(resulSet.getString("USAGE_COUNT"));
                return form;
            }
        });
		
		return cmpList;
	}

	public List<CloudComponentForm> getCloudComputeEC2(JdbcTemplate jdbcTemplate) {

		String GET_AWS_SQL = "SELECT "
				+ "				 CLOUD_COMPUTE_EC2.ID AS ID, "
				+ "				 CLOUD_COMPUTE_EC2.ID_CLOUD_VPC AS ID_CLOUD_VPC, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_NAME AS TX_CLOUD_COMPUTE_NAME, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY AS TX_CLOUD_COMPUTE_KEY, "
				+ "				 CLOUD_COMPUTE_EC2.TX_IP AS TX_IP, "
				+ "				 CLOUD_COMPUTE_EC2.FL_DEFAULT AS FL_DEFAULT, "
				+ "				 CLOUD_COMPUTE_EC2.FL_STATUS AS FL_STATUS, "
				+ "				 CLOUD_COMPUTE_EC2.ID_SUBNET AS ID_SUBNET, "
				+ "				 CLOUD_COMPUTE_EC2.ID_SEC_GROUP AS ID_SEC_GROUP, "
				+ "				 CLOUD_SUBNET.TX_SUBNET_NAME AS TX_SUBNET_NAME, "
				+ "				 CLOUD_SUBNET.TX_SUBNET_KEY AS TX_SUBNET_KEY, "
				+ "				 CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_NAME AS TX_SECURITY_GROUP_NAME, "
				+ "				 CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY AS TX_SECURITY_GROUP_KEY, "
				+ "				 COUNT(LAB_SERVICE_INFRASTRUCTURE.ID_CLOUD_COMPUTE) AS USAGE "
				+ "				 FROM CLOUD_COMPUTE_EC2 "
				+ "				 JOIN CLOUD_SUBNET "
				+ "				 on CLOUD_SUBNET.ID = CLOUD_COMPUTE_EC2.ID_SUBNET "
				+ "				 JOIN CLOUD_SECURITY_GROUP "
				+ "				 on CLOUD_SECURITY_GROUP.ID = CLOUD_COMPUTE_EC2.ID_SEC_GROUP "
				+ "				LEFT JOIN LAB_SERVICE_INFRASTRUCTURE "
				+ "				 on LAB_SERVICE_INFRASTRUCTURE.ID_CLOUD_COMPUTE = CLOUD_COMPUTE_EC2.ID "
				+ "				 WHERE CLOUD_COMPUTE_EC2.ID_INFRA_TYPE = 1 "
				+ "				  AND CLOUD_SUBNET.ID_INFRA_TYPE = 1 "
				+ "				  AND CLOUD_SECURITY_GROUP.ID_INFRA_TYPE = 1 "
				+ "				  GROUP BY 	 CLOUD_COMPUTE_EC2.ID, "
				+ "				 CLOUD_COMPUTE_EC2.ID_CLOUD_VPC, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_NAME, "
				+ "				 CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY, "
				+ "				 CLOUD_COMPUTE_EC2.TX_IP, "
				+ "				 CLOUD_COMPUTE_EC2.FL_DEFAULT, "
				+ "				 CLOUD_COMPUTE_EC2.FL_STATUS, "
				+ "				 CLOUD_COMPUTE_EC2.ID_SUBNET, "
				+ "				 CLOUD_COMPUTE_EC2.ID_SEC_GROUP, "
				+ "				 CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "				 CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "				 CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_NAME, "
				+ "				 CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_CLOUD_COMPUTE_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_CLOUD_COMPUTE_KEY"));
            	form.setTX_IP(resulSet.getString("TX_IP"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
            	form.setFL_STATUS(resulSet.getInt("FL_STATUS")==1?true:false);
            	form.setID_COMPUTE_EC2_SUBNET(resulSet.getInt("ID_SUBNET"));
            	form.setID_COMPUTE_EC2_SEC_GROUP(resulSet.getInt("ID_SEC_GROUP"));
            	form.setTX_COMPUTE_EC2_SUBNET_NAME(resulSet.getString("TX_SUBNET_NAME"));
            	form.setTX_COMPUTE_EC2_SEC_GROUP_NAME(resulSet.getString("TX_SECURITY_GROUP_NAME"));
            	form.setTX_COMPUTE_EC2_SUBNET_KEY(resulSet.getString("TX_SUBNET_KEY"));
            	form.setTX_COMPUTE_EC2_SEC_GROUP_KEY(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	form.setTX_USAGE_COUNTER(resulSet.getString("USAGE"));
            	
                return form;
            }
        });
		
		return cmpList;
	}
	
	public List<CloudComponentForm> getCloudComputeEC2ByKey(JdbcTemplate jdbcTemplate, String key) {

		String GET_AWS_SQL = "SELECT "
				+ "CLOUD_COMPUTE_EC2.ID, "
				+ "CLOUD_COMPUTE_EC2.ID_CLOUD_VPC, "
				+ "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_NAME, "
				+ "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY, "
				+ "CLOUD_COMPUTE_EC2.TX_IP, "
				+ "CLOUD_COMPUTE_EC2.FL_DEFAULT, "
				+ "CLOUD_COMPUTE_EC2.FL_STATUS, "
				+ "CLOUD_COMPUTE_EC2.ID_SUBNET, "
				+ "CLOUD_COMPUTE_EC2.ID_SEC_GROUP, "
				+ "CLOUD_SUBNET.TX_SUBNET_NAME, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_NAME, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY "
				+ "FROM CLOUD_COMPUTE_EC2 "
				+ "JOIN CLOUD_SUBNET "
				+ "on CLOUD_SUBNET.ID = CLOUD_COMPUTE_EC2.ID_SUBNET "
				+ "JOIN CLOUD_SECURITY_GROUP "
				+ "on CLOUD_SECURITY_GROUP.ID = CLOUD_COMPUTE_EC2.ID_SEC_GROUP "
				+ "WHERE CLOUD_COMPUTE_EC2.ID_INFRA_TYPE = 1 "
				+ " AND CLOUD_SUBNET.ID_INFRA_TYPE = 1 "
				+ " AND CLOUD_SECURITY_GROUP.ID_INFRA_TYPE = 1 "
				+ " AND CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY = '" + key + "'";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_CLOUD_COMPUTE_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_CLOUD_COMPUTE_KEY"));
            	form.setTX_IP(resulSet.getString("TX_IP"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
            	form.setFL_STATUS(resulSet.getInt("FL_STATUS")==1?true:false);
            	form.setID_COMPUTE_EC2_SUBNET(resulSet.getInt("ID_SUBNET"));
            	form.setID_COMPUTE_EC2_SEC_GROUP(resulSet.getInt("ID_SEC_GROUP"));
            	form.setTX_COMPUTE_EC2_SUBNET_NAME(resulSet.getString("TX_SUBNET_NAME"));
            	form.setTX_COMPUTE_EC2_SEC_GROUP_NAME(resulSet.getString("TX_SECURITY_GROUP_NAME"));
            	form.setTX_COMPUTE_EC2_SUBNET_KEY(resulSet.getString("TX_SUBNET_KEY"));
            	form.setTX_COMPUTE_EC2_SEC_GROUP_KEY(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	
            	
                return form;
            }
        });
		
		return cmpList;
	}

	public List<CloudComponentForm> getRouteTableForVPC(int id) {
		JobService js = new JobService();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(js.getDataSource());
		
		String GET_RT_VPC= "SELECT ID, TX_ROUTE_TABLE_KEY FROM ROUTE_TABLE WHERE ID_CLOUD_VPC = " + id;
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_RT_VPC, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_ROUTE_TABLE_KEY"));
            	return form;
            }
        });
		return cmpList;
	}

	public List<CloudComponentForm> getNACLForVPC(int id) {
		JobService js = new JobService();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(js.getDataSource());
		
		String GET_RT_VPC= "SELECT ID, TX_NACL_KEY FROM CLOUD_NACL WHERE ID_CLOUD_VPC = " + id;
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_RT_VPC, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_NACL_KEY"));
            	return form;
            }
        });
		return cmpList;
	}

	public List<CloudComponentForm> getSecGroupForVPC(int id) {
		JobService js = new JobService();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(js.getDataSource());

		String GET_RT_VPC= "SELECT ID, TX_SECURITY_GROUP_KEY FROM CLOUD_SECURITY_GROUP WHERE ID_CLOUD_VPC = " + id;
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_RT_VPC, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	return form;
            }
        });
		return cmpList;
	}

	public List<CloudComponentForm> getSubnetForVPC(int id) {
		JobService js = new JobService();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(js.getDataSource());
		
		String GET_RT_VPC= "SELECT ID, TX_SUBNET_KEY FROM CLOUD_SUBNET WHERE ID_CLOUD_VPC = " + id;
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_RT_VPC, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_SUBNET_KEY"));
            	return form;
            }
        });
		return cmpList;
	}

	public List<CloudComponentForm> getCloudSecurityGroup(JdbcTemplate jdbcTemplate) {
		String GET_AWS_SQL = "SELECT "
				+ "CLOUD_SECURITY_GROUP.ID, "
				+ "CLOUD_SECURITY_GROUP.ID_CLOUD_VPC, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_NAME, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY, "
				+ "CLOUD_SECURITY_GROUP.FL_DEFAULT, "
				+ "COUNT(CLOUD_COMPUTE_EC2.ID_SUBNET) AS USAGE_COUNT "
				+ "FROM CLOUD_SECURITY_GROUP "
				+ "LEFT JOIN CLOUD_COMPUTE_EC2 "
				+ "on CLOUD_COMPUTE_EC2.ID_SEC_GROUP = CLOUD_SECURITY_GROUP.ID "
				+ " AND CLOUD_COMPUTE_EC2.ID_INFRA_TYPE = 1 "
				+ " WHERE CLOUD_SECURITY_GROUP.ID_INFRA_TYPE = 1 "
				+ "GROUP BY CLOUD_SECURITY_GROUP.ID, "
				+ "CLOUD_SECURITY_GROUP.ID_CLOUD_VPC, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_NAME, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY, "
				+ "CLOUD_SECURITY_GROUP.FL_DEFAULT ";
		
		List<CloudComponentForm> cmpList = jdbcTemplate.query(GET_AWS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm form = new CloudComponentForm();
            	form.setID_COMPONENT(resulSet.getInt("ID"));
            	form.setID_PARENT(resulSet.getInt("ID_CLOUD_VPC"));
            	form.setTX_COMPONENT_NAME(resulSet.getString("TX_SECURITY_GROUP_NAME"));
            	form.setTX_COMPONENT_KEY(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	form.setFL_DEFAULT(resulSet.getInt("FL_DEFAULT")==1?true:false);
            	form.setTX_USAGE_COUNTER(resulSet.getString("USAGE_COUNT"));
                return form;
            }
        });
		
		return cmpList;
	}

}
