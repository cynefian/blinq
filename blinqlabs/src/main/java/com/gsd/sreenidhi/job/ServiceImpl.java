package com.gsd.sreenidhi.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.CloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.RulesForm;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsActions;
import com.gsd.sreenidhi.admin.settings.devops.ToolConfigForm;
import com.gsd.sreenidhi.admin.workbench.AWSActions;
import com.gsd.sreenidhi.admin.workbench.LabActions;
import com.gsd.sreenidhi.admin.workbench.TerraFormActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;
import com.gsd.sreenidhi.utils.JSONUtils;
import com.gsd.sreenidhi.utils.NetworkUtils;

public class ServiceImpl {
	public HashMap<Integer, String> JOB_SEQUENCE = new HashMap<Integer, String>();

	public ServiceImpl() {
		this.JOB_SEQUENCE.put(1, "Indentifying Infrastructure");
		this.JOB_SEQUENCE.put(2, "Checking available Infrastructure");
		this.JOB_SEQUENCE.put(3, "Reading Infrastructure Data");
		this.JOB_SEQUENCE.put(4, "Provisioning New Infrastructure");
		this.JOB_SEQUENCE.put(5, "Initializing Infrastructure");
		this.JOB_SEQUENCE.put(6, "Starting Infrastructure Services");
		this.JOB_SEQUENCE.put(7, "Configuring Services");
		this.JOB_SEQUENCE.put(8, "Creating Container");
		this.JOB_SEQUENCE.put(9, "Loading Container");
		this.JOB_SEQUENCE.put(10, "Starting Container");
		this.JOB_SEQUENCE.put(11, "Validating Container");
		this.JOB_SEQUENCE.put(12, "Ready");
	}

	public HashMap<Integer, String> getJOB_SEQUENCE() {
		return JOB_SEQUENCE;
	}

	public UserServicesForm implementTask(JdbcTemplate jdbcTemplate, JobServiceStatusForm jobServiceStatusForm,
			UserServicesForm usf) {

		List<UserServicesForm> infraList = null;
		UserServicesForm selectedInfra = null;
		boolean implStatus = false;
		String ip = "";
		String key_ec2 = "";
		String name_ec2 = "";
		String ami = "";
		
		LabActions la = new LabActions();
		AWSCloudActions aca = new AWSCloudActions();
		AWSActions aa = new AWSActions();
		CloudActions ca = new CloudActions();
		
		if ("Indentifying Infrastructure".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {
			implStatus = proessIdentifyingInfrastructure(jdbcTemplate, jobServiceStatusForm, usf, implStatus);
		} else if ("Checking available Infrastructure".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {
			implStatus = proessCheckingAvailableInfrastructure(jdbcTemplate, jobServiceStatusForm, usf, implStatus);
		} else if ("Reading Infrastructure Data".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {
			
			int id_srvc = la.getUserServicesByKey(jdbcTemplate, jobServiceStatusForm.getTX_SRVC_KEY()).get(0).getID_SERVICE();
			
			List<RulesForm> ruleList = aa.getRules(jdbcTemplate, null);
			List<RulesForm> defaultRuleList = aa.getDefaultRule(jdbcTemplate);
			RulesForm rule = null;

			if (ruleList != null && ruleList.size() > 0) {
				for (int i = 0; i < ruleList.size(); i++) {
					if (ruleList.get(i).getTX_DEVOPS_TOOL().trim()
							.equalsIgnoreCase(jobServiceStatusForm.getTX_TOOL().trim())) {
						rule = ruleList.get(i);
					}
				}
			} else {
				rule = defaultRuleList.get(0);
			}
			
			if(rule!=null) {
				String infraCondition = " WHERE CLOUD_COMPUTE_EC2.ID_CLOUD_VPC = " + rule.getID_VPC() +" AND CLOUD_COMPUTE_EC2.ID_SUBNET = " + rule.getID_SUBNET() + " AND CLOUD_COMPUTE_EC2.ID_SEC_GROUP = " + rule.getID_SEC_GROUP() + " ";
				
				infraList = readExistingInfrastructure(jdbcTemplate, infraCondition);
				if (infraList != null && infraList.size() > 0) {
					usf = infraList.get(0);

					usf.setID_SERVICE(id_srvc);
					
					String UPDATE_SERVICE_STATUS_SQL = "UPDATE USER_SERVICE_STATUS SET FL_INIT = ?, FL_COMPLETE = ? "
							+ " WHERE TX_SRVC_KEY = ? AND TX_STATUS in ('Provisioning New Infrastructure', 'Initializing Infrastructure', 'Starting Infrastructure Services')";

					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_STATUS_SQL);
							preparedStatement.setInt(1, 1);
							preparedStatement.setInt(2, 1);
							preparedStatement.setString(3, jobServiceStatusForm.getTX_SRVC_KEY());
							return preparedStatement;
						}
					});
				} else {
					// create new infrastructure
				}
				implStatus = true;
			}else {
				implStatus = false;
				throw new NullPointerException("Unknown Rule");
			}
		
		} else if ("Provisioning New Infrastructure".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			if (usf.getTX_ID_EC2() == null || "".equalsIgnoreCase(usf.getTX_ID_EC2())) {
				
				List<RulesForm> ruleList = aa.getRules(jdbcTemplate, null);
				List<RulesForm> defaultRuleList = aa.getDefaultRule(jdbcTemplate);
				RulesForm rule = new RulesForm();

				if (ruleList != null && ruleList.size() > 0) {
					for (int i = 0; i < ruleList.size(); i++) {
						if (ruleList.get(i).getTX_DEVOPS_TOOL().trim()
								.equalsIgnoreCase(jobServiceStatusForm.getTX_TOOL().trim())) {
							rule = ruleList.get(i);
						}
					}
				} else {
					rule = defaultRuleList.get(0);
				}

				// create AWS Infrastructure System.getProperty("user.home")
				String infraKey = Generator.randomString(16);
				String tfFilePath = ca.validateSystemPath(infraKey, "EC2");

				if (!"ERROR".equalsIgnoreCase(tfFilePath)) {
				
					UserServicesForm usrSrvcFrm =  aa.createEC2Instance(jdbcTemplate, tfFilePath, rule);
					
					usf.setTX_IP(usrSrvcFrm.getTX_IP());
					usf.setTX_ID_EC2(usrSrvcFrm.getTX_ID_EC2());
					usf.setTX_ID_AMI(usrSrvcFrm.getTX_ID_AMI());
					usf.setTX_NAME_EC2(usrSrvcFrm.getTX_NAME_EC2());
					implStatus = usrSrvcFrm.isImplStat();
					setTaskError(jdbcTemplate, implStatus, jobServiceStatusForm);
				}
			} else {
				implStatus = true;
			}
			
		} else if ("Initializing Infrastructure".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			ServerSocketTunnel sst = new ServerSocketTunnel();
			try {
				DevOpsActions da = new DevOpsActions();
				String loc = da.getToolLocationById(jdbcTemplate, jobServiceStatusForm.getID_TOOL());
				if (loc != null && !"".equalsIgnoreCase(loc.trim())) {
					sst.captureSocket(usf.getTX_IP(), 22, "sudo docker pull " + loc.trim() + ":latest");
					implStatus = true;
				} else {
					implStatus = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				implStatus = false;
			}
			
			if(implStatus == true) {
				final int ec2id = usf.getID_EC2();
				final int srvcId = usf.getID_SERVICE();
				String UPDATE_LAB_SERVICE_INFRASTRUCTURE_SQL = "UPDATE LAB_SERVICE SET ID_INFRASTRUCTURE = ? WHERE ID = ? ";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection
								.prepareStatement(UPDATE_LAB_SERVICE_INFRASTRUCTURE_SQL);
						preparedStatement.setInt(1, ec2id);
						preparedStatement.setInt(2, srvcId);
						return preparedStatement;
						
					}
				});
			}

		} else if ("Starting Infrastructure Services".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			try {
				NetworkUtils.pingIP(ip);
				
				implStatus = true;
			} catch (IOException e) {
				implStatus = false;
				e.printStackTrace();
			}

		} else if ("Configuring Services".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			if (usf.getTX_ID_EC2() != null) {
				
				final int ec2id = usf.getID_INFRASTRUCTURE();
				final int srvcId = usf.getID_SERVICE();
				
				String CONFIGURE_SERVICE_INFRASTRUCTURE_SQL = "IF NOT EXISTS (SELECT * FROM LAB_SERVICE_INFRASTRUCTURE WHERE ID_LAB_SERVICE = ?) "
						+ " INSERT INTO LAB_SERVICE_INFRASTRUCTURE(ID_CLOUD_COMPUTE, ID_LAB_SERVICE, TS_TIMESTAMP_CREATE, FL_STATUS, FL_INITIALIZED) VALUES (?,?,?,?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection
								.prepareStatement(CONFIGURE_SERVICE_INFRASTRUCTURE_SQL);
						preparedStatement.setInt(1, srvcId);
						preparedStatement.setInt(2, ec2id);
						preparedStatement.setInt(3, srvcId);
						preparedStatement.setString(4, CalendarUtils.dateToStringDateTimeReadable(new Date()));
						preparedStatement.setInt(5, 1);
						preparedStatement.setInt(6, 1);
						return preparedStatement;
					}
				});

				implStatus = true;
			} else {
				implStatus = false;
			}

		} else if ("Creating Container".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {
			ServerSocketTunnel sst = new ServerSocketTunnel();
			DevOpsActions da = new DevOpsActions();
			String loc = da.getToolLocationById(jdbcTemplate, jobServiceStatusForm.getID_TOOL());
			int containerPort =  da.getToolPortById(jdbcTemplate, jobServiceStatusForm.getID_TOOL());
			List<ToolConfigForm> dConfList = da.getToolConfigurations(jdbcTemplate,  jobServiceStatusForm.getID_TOOL());
			int exposedPort = identifyAvailablePort(usf.getTX_IP());
			String command = null;
			String container = null;
			try {
				command = "sudo docker run -d -p " + exposedPort + ":"
						+ containerPort + " --name " + jobServiceStatusForm.getTX_SRVC_KEY() + " " + loc +":latest";
				container = sst.captureSocket(usf.getTX_IP(), 22, command);
				
				String UPDATE_SRVC_CONFIG_SQL = "UPDATE LAB_SERVICE SET TX_PORT = ?, TX_ID_CONTAINER = ? WHERE TX_LAB_SRVC_KEY = ? ";
				final String cntnr = container;
				final int srvcId = usf.getID_SERVICE();
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection
								.prepareStatement(UPDATE_SRVC_CONFIG_SQL);
						preparedStatement.setInt(1,exposedPort);
						preparedStatement.setString(2,cntnr);
						preparedStatement.setString(3,jobServiceStatusForm.getTX_SRVC_KEY());
						return preparedStatement;
					}
				});
				
				try {
					Thread.sleep(30000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				if(dConfList!=null && dConfList.size()>0) {
					System.out.println("Attempting to execute configurations");
					for(int i=0;i<dConfList.size();i++) {
						String cmd =null;
						String result = null;
						if("SCRIPT".equalsIgnoreCase(dConfList.get(i).getTX_TYPE())) {
							cmd = dConfList.get(i).getTX_COMMAND().replace("{container}", jobServiceStatusForm.getTX_SRVC_KEY().trim().replaceAll("\r", "").replaceAll("\n", ""));
							result = sst.captureSocket(usf.getTX_IP(), 22, cmd);
						}if("FILE".equalsIgnoreCase(dConfList.get(i).getTX_TYPE())) {
							cmd = dConfList.get(i).getTX_FILE().replace("{container}", jobServiceStatusForm.getTX_SRVC_KEY().trim().replaceAll("\r", "").replaceAll("\n", ""));
							result = sst.captureSocket(usf.getTX_IP(), 22, cmd);
						}else {
							//Nothing to configure
						}
						
						if(cmd!=null) {
							if(result.contains("failed to resize tty, using default size")) {
								result = result.replace("failed to resize tty, using default size", "");
							}
							String INSERT_TOOL_CONFIG_RESULT = "INSERT INTO LAB_SERVICE_TOOL_CONFIG(ID_LAB_SRVC, ID_CONFIG, TX_CONFIG, TX_RESULT, FL_CONFIG_RESULT) VALUES (?,?,?,?,?)";
							final int confId = dConfList.get(i).getID();
							final String res = result.trim();
							final String cmnd = cmd.trim();
							
							jdbcTemplate.update(new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement preparedStatement = connection
											.prepareStatement(INSERT_TOOL_CONFIG_RESULT);
									preparedStatement.setInt(1,srvcId);
									preparedStatement.setInt(2,confId);
									preparedStatement.setString(3,cmnd);
									preparedStatement.setString(4,res);
									preparedStatement.setInt(5,1);
									return preparedStatement;
								}
							});
						}
					}
				}
				
				command = "sudo ufw allow " + exposedPort;
				sst.captureSocket(usf.getTX_IP(), 22, command);
				
				implStatus = true;
			} catch (Exception e) {
				e.printStackTrace();
				implStatus = false;
			}

		} else if ("Loading Container".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			implStatus = true;

		} else if ("Starting Container".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			implStatus = true;

		} else if ("Validating Container".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {

			implStatus = true;

		} else if ("Ready".equalsIgnoreCase(jobServiceStatusForm.getTX_STATUS())) {
			int srvcId = usf.getID_SERVICE();
			String UPDATE_SRVC_STATUE = "UPDATE LAB_SERVICE SET FL_SERVICE_STATUS = ?, FL_INITIALIZED = ? WHERE ID = ? ";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(UPDATE_SRVC_STATUE);
					preparedStatement.setInt(1,1);
					preparedStatement.setInt(2,1);
					preparedStatement.setInt(3, srvcId);
					return preparedStatement;
				}
			});
			
			implStatus = true;

		}
		setTaskError(jdbcTemplate, implStatus, jobServiceStatusForm);

		usf.setImplStat(implStatus);
		return usf;
	}

	private boolean proessCheckingAvailableInfrastructure(JdbcTemplate jdbcTemplate,
			JobServiceStatusForm jobServiceStatusForm, UserServicesForm usf, boolean implStatus) {
		//infraList = readExistingInfrastructure(jdbcTemplate);
		implStatus = true;
		return true;
	}

	private boolean proessIdentifyingInfrastructure(JdbcTemplate jdbcTemplate,
			JobServiceStatusForm jobServiceStatusForm, UserServicesForm usf, boolean implStatus) {
		
		return true;
	}

	private int identifyAvailablePort(String tx_IP) {
		int port = 0;
		String container = "PORT";
		ServerSocketTunnel sst = new ServerSocketTunnel();

		try {
			while (!"".equalsIgnoreCase(container.trim())) {
				port = Generator.getPortNumber();
				System.out.println("PORT: " + port);
				container = sst.captureSocket(tx_IP, 22, "sudo netstat -ntlp | grep :" + port);
				System.out.println("Res: " + container.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return port;
	}

	public void setTaskError(JdbcTemplate jdbcTemplate, boolean implStatus, JobServiceStatusForm jobServiceStatusForm) {

		String SET_ERROR_FLAG = "UPDATE USER_SERVICE_STATUS SET FL_ERROR = ? WHERE ID = ?";

		int stat = 0;
		if (implStatus == false) {
			stat = 1;
		}

		final int status = stat;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(SET_ERROR_FLAG);
				preparedStatement.setInt(1, status);
				preparedStatement.setInt(2, jobServiceStatusForm.getID_SRVC());
				return preparedStatement;
			}
		});

	}

	

	private List<UserServicesForm> readExistingInfrastructure(JdbcTemplate jdbcTemplate, String filter) {
		
		String queryCOndition = "";
		if(filter==null) {
			queryCOndition = "";
		}else {
			queryCOndition = filter;
		}
		UserServicesForm selectedInfra = null;
		String GET_INFRASTRUCTURE_SQL = "SELECT " + "CLOUD_COMPUTE_EC2.ID AS EC2_ID, "
				+ "CLOUD_COMPUTE_EC2.ID_CLOUD_VPC AS TX_ID_VPC, " + "CLOUD_COMPUTE_EC2.TX_IP AS TX_IP, "
				+ "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_NAME AS TX_CLOUD_COMPUTE_NAME , "
				+ "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY AS TX_CLOUD_COMPUTE_KEY , "
				+ "CLOUD_COMPUTE_EC2.TX_ID_AMI AS TX_ID_AMI, " + "CLOUD_COMPUTE_EC2.FL_STATUS AS FL_STATUS, "
				+ "Count(LAB_SERVICE_INFRASTRUCTURE.ID) AS TX_SERVICES_COUNT " 
				+ "FROM CLOUD_COMPUTE_EC2 "
				+ "LEFT JOIN LAB_SERVICE_INFRASTRUCTURE "
				+ "ON LAB_SERVICE_INFRASTRUCTURE.ID_CLOUD_COMPUTE = CLOUD_COMPUTE_EC2.ID " 
				+ queryCOndition
				+ "GROUP BY "
				+ "CLOUD_COMPUTE_EC2.ID, " + "CLOUD_COMPUTE_EC2.ID_CLOUD_VPC, " + "CLOUD_COMPUTE_EC2.TX_IP, "
				+ "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_NAME, " + "CLOUD_COMPUTE_EC2.TX_CLOUD_COMPUTE_KEY, "
				+ "CLOUD_COMPUTE_EC2.TX_ID_AMI, " + "CLOUD_COMPUTE_EC2.FL_STATUS "
				+ "HAVING Count(LAB_SERVICE_INFRASTRUCTURE.ID)<10";

		List<UserServicesForm> infraList = jdbcTemplate.query(GET_INFRASTRUCTURE_SQL,
				new RowMapper<UserServicesForm>() {
					public UserServicesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						UserServicesForm infra = new UserServicesForm();
						infra.setID_INFRASTRUCTURE(resulSet.getInt("EC2_ID"));
						infra.setTX_IP(resulSet.getString("TX_IP"));
						infra.setTX_ID_VPC(resulSet.getString("TX_ID_VPC"));
						infra.setTX_NAME_EC2(resulSet.getString("TX_CLOUD_COMPUTE_NAME"));
						infra.setTX_ID_EC2(resulSet.getString("TX_CLOUD_COMPUTE_KEY"));
						infra.setTX_ID_AMI(resulSet.getString("TX_ID_AMI"));
						infra.setFL_INFRASTRUCTURE_STATUS(resulSet.getInt("FL_STATUS") == 1 ? true : false);
						infra.setTX_SERVICES_COUNT(resulSet.getInt("TX_SERVICES_COUNT"));
						return infra;

					}
				});
		return infraList;
	}

}
