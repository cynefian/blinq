package com.gsd.sreenidhi.admin.workbench;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.RulesForm;
import com.gsd.sreenidhi.amazonaws.services.s3.auth.AWS4SignerBase;
import com.gsd.sreenidhi.amazonaws.services.s3.auth.AWS4SignerForAuthorizationHeader;
import com.gsd.sreenidhi.amazonaws.services.s3.util.HttpUtils;
import com.gsd.sreenidhi.utils.JSONUtils;

public class AWSActions {
	
	static byte[] HmacSHA256(String data, byte[] key) throws Exception {
	    String algorithm="HmacSHA256";
	    Mac mac = Mac.getInstance(algorithm);
	    mac.init(new SecretKeySpec(key, algorithm));
	    return mac.doFinal(data.getBytes("UTF-8"));
	}

	static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception {
	    byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
	    byte[] kDate = HmacSHA256(dateStamp, kSecret);
	    byte[] kRegion = HmacSHA256(regionName, kDate);
	    byte[] kService = HmacSHA256(serviceName, kRegion);
	    byte[] kSigning = HmacSHA256("aws4_request", kService);
	    return kSigning;
	}
	
	  public static byte[] EncodeBase64(byte[] rawData) {
	        return Base64.encodeBase64(rawData);
	    }
	  
	  // this function converts the generic JS ISO8601 date format to the specific format the AWS API wants
	  public String getAmzDate(String dateStr) {
	    String[] chars = {":","-"};
	    for (int i=0;i<chars.length;i++) {
	      while (dateStr.indexOf(chars[i]) != -1) {
	        dateStr = dateStr.replace(chars[i],"");
	      }
	    }
	    dateStr = dateStr.split(".")[0] + "Z";
	    return dateStr;
	  }

	public void createSubnet() throws IOException {
		URL url = new URL("https://ec2.amazonaws.com/?Action=CreateSubnet"
				+ "&VpcId=vpc-f1c6548b"
				+ "&CidrBlock=10.0.1.0/24"
				+ "&AUTHPARAMS");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("param1", "val");
		 
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
		
		int status = con.getResponseCode();
		
		Reader streamReader = null;
		if (status > 299) {
		    streamReader = new InputStreamReader(con.getErrorStream());
		} else {
		    streamReader = new InputStreamReader(con.getInputStream());
		}
		
		
		Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-amz-content-sha256", AWS4SignerBase.EMPTY_BODY_SHA256);
        headers.put("X-Amz-Security-Token", "XXXXXX+XXXXXXXXXXXXXXX/5N+XXXXXXXXX/XXXXX/XXXXXX/XXXXXXXX/XXXXXXXX=");
        AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(
                url, "GET", "execute-api", "us-west-2");
        String authorization = signer.computeSignature(headers, 
                                                       null, // no query parameters
                                                       AWS4SignerBase.EMPTY_BODY_SHA256, 
                                                       "XXXXXX", 
                                                           "S+XXXXXX+XXXXXX/XXXX");



        headers.put("Authorization", authorization);
        String response = HttpUtils.invokeHttpRequest(url, "GET", headers, null);
		
	}

	public List<RulesForm> getRules(JdbcTemplate jdbcTemplate, String  filter) {
		
		String queryFilter = "";
		if(filter!=null) {
			queryFilter = filter;
		}else {
			queryFilter = "";
		}

		String GET_AWS_RULES_SQL = "SELECT "
				+ "CLOUD_RULES.ID AS ID_AWS_RULE, "
				+ "CLOUD_RULES.TX_NAME AS TX_AWS_RULE, "
				+ "CLOUD_RULES.ID_TOOL AS ID_TOOL, "
				+ "CLOUD_RULES.ID_VPC AS ID_VPC, "
				+ "CLOUD_RULES.ID_NACL AS ID_NACL, "
				+ "CLOUD_RULES.ID_ROUTE_TABLE AS ID_ROUTE_TABLE, "
				+ "CLOUD_RULES.ID_SEC_GROUP AS ID_SEC_GROUP, "
				+ "CLOUD_RULES.ID_SUBNET AS ID_SUBNET, "
				+ "DEVOPS_TOOLS.TX_TOOL_NAME AS TX_TOOL_NAME, "
				+ "DEVOPS_TOOLS.TX_TOOL_IMAGE AS TX_TOOL_IMAGE, "
				+ "CLOUD_VPC.ID_VPC_KEY AS TX_VPC_KEY, "
				+ "CLOUD_NACL.TX_NACL_KEY AS TX_NACL_KEY, "
				+ "ROUTE_TABLE.TX_ROUTE_TABLE_KEY AS TX_ROUTE_TABLE_KEY, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY AS TX_SECURITY_GROUP_KEY, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY AS TX_SUBNET_KEY "
				+ "FROM CLOUD_RULES "
				+ "JOIN DEVOPS_TOOLS ON CLOUD_RULES.ID_TOOL = DEVOPS_TOOLS.ID "
				+ "JOIN CLOUD_VPC ON CLOUD_RULES.ID_VPC = CLOUD_VPC.ID "
				+ "JOIN CLOUD_NACL ON CLOUD_RULES.ID_NACL = CLOUD_NACL.ID "
				+ "JOIN ROUTE_TABLE ON CLOUD_RULES.ID_ROUTE_TABLE = ROUTE_TABLE.ID "
				+ "JOIN CLOUD_SECURITY_GROUP ON CLOUD_RULES.ID_SEC_GROUP = CLOUD_SECURITY_GROUP.ID "
				+ "JOIN CLOUD_SUBNET ON CLOUD_RULES.ID_SUBNET = CLOUD_SUBNET.ID "
				+ "WHERE CLOUD_RULES.ID_INFRA_TYPE = 1 " + queryFilter;
		List<RulesForm> ruleList = jdbcTemplate.query(GET_AWS_RULES_SQL, new RowMapper<RulesForm>() {
            public RulesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	RulesForm rule = new RulesForm();
            	rule.setID(resulSet.getInt("ID_AWS_RULE"));
            	rule.setTX_NAME(resulSet.getString("TX_AWS_RULE"));
            	rule.setID_DEVOPS_TOOL(resulSet.getInt("ID_TOOL"));
            	rule.setID_VPC(resulSet.getInt("ID_VPC"));
            	rule.setID_NACL(resulSet.getInt("ID_NACL"));
            	rule.setID_ROUTE_TABLE(resulSet.getInt("ID_ROUTE_TABLE"));
            	rule.setID_SEC_GROUP(resulSet.getInt("ID_SEC_GROUP"));
            	rule.setID_SUBNET(resulSet.getInt("ID_SUBNET"));
            	rule.setTX_DEVOPS_TOOL(resulSet.getString("TX_TOOL_NAME"));
            	rule.setTX_DEVOPS_IMG(resulSet.getString("TX_TOOL_IMAGE"));
            	rule.setTX_VPC(resulSet.getString("TX_VPC_KEY"));
            	rule.setTX_NACL(resulSet.getString("TX_NACL_KEY"));
            	rule.setTX_ROUTE_TABLE(resulSet.getString("TX_ROUTE_TABLE_KEY"));
            	rule.setTX_SEC_GROUP(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	rule.setTX_SUBNET(resulSet.getString("TX_SUBNET_KEY"));
                return rule;
            }
        });
		return ruleList;
	}

	public List<RulesForm> getDefaultRule(JdbcTemplate jdbcTemplate) {
		String GET_AWS_RULES_SQL = "SELECT "
				+ "CLOUD_VPC.ID AS ID_VPC, "
				+ "CLOUD_NACL.ID AS ID_NACL, "
				+ "ROUTE_TABLE.ID AS ID_ROUTE_TABLE, "
				+ "CLOUD_SECURITY_GROUP.ID AS ID_SEC_GROUP, "
				+ "CLOUD_SUBNET.ID AS ID_SUBNET, "
				+ "CLOUD_VPC.ID_VPC_KEY AS TX_VPC_KEY, "
				+ "CLOUD_NACL.TX_NACL_KEY AS TX_NACL_KEY, "
				+ "ROUTE_TABLE.TX_ROUTE_TABLE_KEY AS TX_ROUTE_TABLE_KEY, "
				+ "CLOUD_SECURITY_GROUP.TX_SECURITY_GROUP_KEY AS TX_SECURITY_GROUP_KEY, "
				+ "CLOUD_SUBNET.TX_SUBNET_KEY AS TX_SUBNET_KEY "
				+ "FROM CLOUD_VPC "
				+ "JOIN CLOUD_NACL ON CLOUD_VPC.ID = CLOUD_NACL.ID_CLOUD_VPC "
				+ "JOIN ROUTE_TABLE ON CLOUD_VPC.ID = ROUTE_TABLE.ID_CLOUD_VPC "
				+ "JOIN CLOUD_SECURITY_GROUP ON CLOUD_VPC.ID = CLOUD_SECURITY_GROUP.ID_CLOUD_VPC "
				+ "JOIN CLOUD_SUBNET ON CLOUD_VPC.ID = CLOUD_SUBNET.ID_CLOUD_VPC "
				+ " WHERE CLOUD_VPC.FL_DEFAULT = 1 "
			//	+ " AND CLOUD_SECURITY_GROUP.FL_DEFAULT = 1 "
				+ " AND CLOUD_SUBNET.FL_DEFAULT = 1 "
				+ " AND CLOUD_VPC.ID_INFRA_TYPE = 1"
				;
				
		List<RulesForm> ruleList = jdbcTemplate.query(GET_AWS_RULES_SQL, new RowMapper<RulesForm>() {
            public RulesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	RulesForm rule = new RulesForm();
            	rule.setID_VPC(resulSet.getInt("ID_VPC"));
            	rule.setID_NACL(resulSet.getInt("ID_NACL"));
            	rule.setID_ROUTE_TABLE(resulSet.getInt("ID_ROUTE_TABLE"));
            	rule.setID_SEC_GROUP(resulSet.getInt("ID_SEC_GROUP"));
            	rule.setID_SUBNET(resulSet.getInt("ID_SUBNET"));
            	rule.setTX_VPC(resulSet.getString("TX_VPC_KEY"));
            	rule.setTX_NACL(resulSet.getString("TX_NACL_KEY"));
            	rule.setTX_ROUTE_TABLE(resulSet.getString("TX_ROUTE_TABLE_KEY"));
            	rule.setTX_SEC_GROUP(resulSet.getString("TX_SECURITY_GROUP_KEY"));
            	rule.setTX_SUBNET(resulSet.getString("TX_SUBNET_KEY"));
                return rule;
            }
        });
		return ruleList;
	}
	
	
	public UserServicesForm createEC2Instance(JdbcTemplate jdbcTemplate, String tfFilePath, RulesForm rule) {

		TerraFormActions tfa = new TerraFormActions();
		AWSCloudActions aca = new AWSCloudActions();
		UserServicesForm usf = new UserServicesForm();
		
		boolean initStat;
		boolean execStat;
		boolean resStat;
		boolean implStatus = false;
		
		try {
			initStat = tfa.initializeTF(tfFilePath);

			if (initStat) {
				String param = "-var 'awsprops = {region = \"us-east-1\", vpc = \""+rule.getTX_VPC()+"\", ami = \"ami-0d8403aa6d4276e9e\", itype = \"i3.large\", subnet = \""+rule.getTX_SUBNET()+"\", publicip = true, keyname = \"aws_ubuntu1_key\", secgroupid = \""+rule.getTX_SEC_GROUP()+"\" }'";
				execStat = tfa.executeTF(tfFilePath,  param);
				if (execStat) {
					resStat = tfa.statusTF(tfFilePath);
					if (resStat) {

						String result = tfa.readResult(tfFilePath);
						if (result != null) {
							JSONUtils ju = new JSONUtils();
							JSONArray resources = ju.getJsonArray(result, "resources");
							JSONArray instances = ju.getJsonArray(resources.getJSONObject(0), "instances");
							JSONObject attributes = ju.getJsonObject(instances.getJSONObject(0).toString(),
									"attributes");
							String ip = ju.getJsonString(attributes.toString(), "public_ip");
							String key_ec2 = ju.getJsonString(attributes.toString(), "id");
							String name_ec2 = ju.getJsonString(resources.getJSONObject(0).toString(), "name");
							String ami = ju.getJsonString(attributes.toString(), "ami");

							usf.setTX_IP(ip);
							usf.setTX_ID_EC2(key_ec2);
							usf.setTX_ID_AMI(ami);
							usf.setTX_NAME_EC2(name_ec2);

							String JOB_SRVC_STATUS_SQL = " INSERT INTO CLOUD_COMPUTE_EC2(ID_CLOUD_VPC, TX_CLOUD_COMPUTE_NAME, TX_CLOUD_COMPUTE_KEY, TX_ID_AMI, TX_IP, ID_SUBNET, ID_SEC_GROUP, ID_INFRA_TYPE, FL_DEFAULT, FL_STATUS) "
									+ " VALUES(?,?,?,?,?,?,?,?,?,?)";

							final RulesForm r = rule;
							final String ipAddr = ip;
							final String ec2key = key_ec2;
							final String ec2name = name_ec2;
							final String ec2ami = ami;

							jdbcTemplate.update(new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection)
										throws SQLException {
									PreparedStatement preparedStatement = connection
											.prepareStatement(JOB_SRVC_STATUS_SQL);
									preparedStatement.setInt(1,
											aca.getCloudVPCByKey(jdbcTemplate, r.getTX_VPC()).get(0)
													.getID_COMPONENT());
									preparedStatement.setString(2, ec2name);
									preparedStatement.setString(3, ec2key);
									preparedStatement.setString(4, ec2ami);
									preparedStatement.setString(5, ipAddr);
									preparedStatement.setInt(6,
											aca.getCloudSubnetByKey(jdbcTemplate, r.getTX_SUBNET()).get(0)
													.getID_COMPONENT());
									preparedStatement
											.setInt(7,
													aca.getSecGroupForVPC(aca.getCloudVPCByKey(jdbcTemplate, r.getTX_VPC())
															.get(0).getID_COMPONENT()).get(0)
															.getID_COMPONENT());
									preparedStatement.setInt(8, 1); // Infra TYe - AWS
									preparedStatement.setInt(9, 0);
									preparedStatement.setInt(10, 0); // status

									return preparedStatement;
								}
							});
							implStatus = true;
						} else {
							System.out.println("Error reading terraform state");
						}
					} else {
						implStatus = false;
					}
				} else {
					implStatus = false;
				}
			} else {
				implStatus = false;
			}
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		usf.setImplStat(implStatus);
		return usf;
	}

	public UserServicesForm createVPC(JdbcTemplate jdbcTemplate, String tfFilePath, String vpcName) {
		TerraFormActions tfa = new TerraFormActions();
		AWSCloudActions aca = new AWSCloudActions();
		UserServicesForm usf = new UserServicesForm();
		
		boolean initStat;
		boolean execStat;
		boolean resStat;
		
		try {
			initStat = tfa.initializeTF(tfFilePath);

			if (initStat) {
				String param = "-var 'awsprops = {region = \"us-east-1\", vpc-name = \""+vpcName+"\" }'";
				execStat = tfa.executeTF(tfFilePath, param);
				if (execStat) {
					resStat = tfa.statusTF(tfFilePath);
					if (resStat) {
						String result = tfa.readResult(tfFilePath);
						
						
						JSONUtils ju = new JSONUtils();
						
						JSONArray modules = ju.getJsonArray(result, "modules");
						JSONObject resources = ju.getJsonObject(modules.getJSONObject(0).toString(), "resources");
						
						//Route Table information
						String aws_rt = ju.getJsonString(resources.toString(), "aws_default_route_table.public");
						String aws_rt_primary = ju.getJsonString(aws_rt.toString(), "primary");
						String rt_id = ju.getJsonString(aws_rt_primary.toString(), "id");
						
						//NACL information
						
						//VPC Information
						String aws_vpc = ju.getJsonString(resources.toString(), "aws_vpc.main-vpc");
						String aws_vpc_primary = ju.getJsonString(aws_vpc.toString(), "primary");
						
						String vpc_id = ju.getJsonString(aws_vpc_primary.toString(), "id");
						
					}
				}
			}
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
