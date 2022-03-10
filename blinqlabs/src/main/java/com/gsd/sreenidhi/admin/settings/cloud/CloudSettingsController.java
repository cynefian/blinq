package com.gsd.sreenidhi.admin.settings.cloud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.settings.cloud.AWSCloudActions;
import com.gsd.sreenidhi.admin.settings.cloud.CloudAuthForm;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsActions;
import com.gsd.sreenidhi.admin.settings.devops.DevOpsToolsForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;
import com.gsd.sreenidhi.admin.workbench.AWSActions;
import com.gsd.sreenidhi.admin.workbench.UserServicesForm;
import com.gsd.sreenidhi.admin.workbench.WorkbenchForm;
import com.gsd.sreenidhi.job.JobServiceActions;
import com.gsd.sreenidhi.utils.Generator;

@Controller
@RestController
public class CloudSettingsController {


	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public CloudSettingsController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@RequestMapping(value="/admin/settings/cloud/aws", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView awsCloudSetings(HttpSession session) {
		
		AWSCloudActions awscloud = new AWSCloudActions();
		List<CloudAuthForm> authList = awscloud.getAuthentication(jdbcTemplate);
		List<CloudComponentForm> vpcList = awscloud.getCloudVPC(jdbcTemplate);
		
		 List<CloudComponentForm> naclList = awscloud.getCloudNACL(jdbcTemplate);
		 List<CloudComponentForm> sgList = awscloud.getCloudSecurityGroup(jdbcTemplate); 
		 List<CloudComponentForm> rtList = awscloud.getCloudRouteTables(jdbcTemplate); 
		 List<CloudComponentForm> subnetList = awscloud.getCloudSubnet(jdbcTemplate); 
		 List<CloudComponentForm> ec2List = awscloud.getCloudComputeEC2(jdbcTemplate);
		 
		
		String mainTab = "Cloud";
		String subTab = "AWS";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/aws");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		mv.addObject("authList",authList);
		mv.addObject("vpcList",vpcList);
		
		 mv.addObject("naclList",naclList);
		 mv.addObject("sgList",sgList);
		 mv.addObject("rtList",rtList);
		 mv.addObject("subnetList",subnetList);
		 mv.addObject("ec2List",ec2List);
		
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/updateCredentials", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CREDENTIALS_MODIFY')")
	public ModelAndView updateAwsCredentials(@ModelAttribute("cloudAuthForm") @Valid CloudAuthForm cloudAuthForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		AWSCloudActions awscloud = new AWSCloudActions();
		awscloud.updateAuthentication(jdbcTemplate, cloudAuthForm);
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/gcp", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView gcpCloudSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "GCP";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/gcp");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/azure", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_VIEW')")
	public ModelAndView azureCloudSetings(HttpSession session) {
		
		String mainTab = "Cloud";
		String subTab = "Azure";
		ModelAndView mv = new ModelAndView("admin/settings/cloud/azure");
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/linkVPC", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView linkAWSVPCSetting(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String CHECK_EXISTS_SQL = "SELECT ID FROM CLOUD_SUBNET WHERE TX_SUBNET_KEY = '" + cloudComponentForm.getTX_COMPONENT_KEY() +"' AND ID_INFRA_TYPE = 1";
		List<CloudComponentForm> vpcList = jdbcTemplate.query(CHECK_EXISTS_SQL, new RowMapper<CloudComponentForm>() {
            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	CloudComponentForm vpc = new CloudComponentForm();
            	vpc.setID_COMPONENT(resulSet.getInt("ID"));
            	return vpc;
            }
        });
		
		if(vpcList.size()==0) {
			String LINK_AWS_VPC = "IF NOT EXISTS (SELECT * FROM CLOUD_VPC WHERE ID_VPC_KEY = ? )"
					+ "INSERT INTO CLOUD_VPC(ID_INFRA_TYPE, ID_VPC_KEY, TX_VPC_NAME, FL_DEFAULT) VALUES ((SELECT ID FROM INFRA_TYPE WHERE TX_INFRA_TYPE=?),?,?,?)";
			final KeyHolder vpcId = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_VPC,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, cloudComponentForm.getTX_COMPONENT_KEY());
					preparedStatement.setString(2, "AWS");
					preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_KEY());
					preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_NAME());
					preparedStatement.setInt(5, cloudComponentForm.isFL_DEFAULT()==true?1:0);
					return preparedStatement;
				}
			},vpcId);
			cloudComponentForm.setID_COMPONENT(vpcId.getKey().intValue());
			
		}else {
			cloudComponentForm.setID_COMPONENT(vpcList.get(0).getID_COMPONENT());
		}
		
		
		ModelAndView mv = null ;
		if(cloudComponentForm.isFL_DEFAULT()==true) {
			mv = updateVPCDefaults(cloudComponentForm, result, model, redirectAttrs, session);
		}
		mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/newVPC", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView createNewAWSVPC(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		CloudActions ca = new CloudActions();
		AWSActions aa = new AWSActions();
		
		String infraKey = Generator.randomString(16);
		String tfFilePath = ca.validateSystemPath(infraKey, "VPC");

		if (!"ERROR".equalsIgnoreCase(tfFilePath)) {
			UserServicesForm usrSrvcFrm =  aa.createVPC(jdbcTemplate, tfFilePath,cloudComponentForm.getTX_COMPONENT_NAME());
		}
		
		cloudComponentForm.setID_COMPONENT(0);
		
		ModelAndView mv = null ;
		if(cloudComponentForm.isFL_DEFAULT()==true) {
			mv = updateVPCDefaults(cloudComponentForm, result, model, redirectAttrs, session);
		}
		mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updateRouteTable", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateRouteTable(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String LINK_AWS_RT = "IF NOT EXISTS (SELECT * FROM ROUTE_TABLE WHERE ID_CLOUD_VPC = ? )"
				+ "INSERT INTO ROUTE_TABLE(ID_CLOUD_VPC, TX_ROUTE_TABLE_NAME, TX_ROUTE_TABLE_KEY, FL_DEFAULT, ID_INFRA_TYPE) VALUES (?,?,?,?,1) "
				+ "ELSE "
				+ " UPDATE ROUTE_TABLE SET TX_ROUTE_TABLE_KEY = ? WHERE ID_CLOUD_VPC = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_RT);
				preparedStatement.setInt(1, cloudComponentForm.getID_PARENT());
				preparedStatement.setInt(2, cloudComponentForm.getID_PARENT());
				preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_NAME());
				preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_KEY());
				preparedStatement.setInt(5, 1);
				preparedStatement.setString(6, cloudComponentForm.getTX_COMPONENT_KEY());
				preparedStatement.setInt(7, cloudComponentForm.getID_PARENT());
				
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updateNACL", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateNACL(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String LINK_AWS_NACL = "IF NOT EXISTS (SELECT * FROM CLOUD_NACL WHERE ID_CLOUD_VPC = ? )"
				+ "INSERT INTO CLOUD_NACL(ID_CLOUD_VPC, TX_NACL_NAME, TX_NACL_KEY, FL_DEFAULT, ID_INFRA_TYPE) VALUES (?,?,?,?,1) "
				+ "ELSE "
				+ " UPDATE CLOUD_NACL SET TX_NACL_KEY = ? WHERE ID_CLOUD_VPC = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_NACL);
				preparedStatement.setInt(1, cloudComponentForm.getID_PARENT());
				preparedStatement.setInt(2, cloudComponentForm.getID_PARENT());
				preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_NAME());
				preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_KEY());
				preparedStatement.setInt(5, 1);
				preparedStatement.setString(6, cloudComponentForm.getTX_COMPONENT_KEY());
				preparedStatement.setInt(7, cloudComponentForm.getID_PARENT());
				
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/addLinkSubnet", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView addLinkSubnet(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		if("LINK".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {
			
			String CHECK_EXISTS_SQL = "SELECT ID FROM CLOUD_SUBNET WHERE TX_SUBNET_KEY = '" + cloudComponentForm.getTX_COMPONENT_KEY() +"' AND ID_INFRA_TYPE = 1";
			List<CloudComponentForm> snList = jdbcTemplate.query(CHECK_EXISTS_SQL, new RowMapper<CloudComponentForm>() {
	            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	CloudComponentForm sn = new CloudComponentForm();
	            	sn.setID_COMPONENT(resulSet.getInt("ID"));
	            	return sn;
	            }
	        });
			
			if(snList.size()==0) {
				String LINK_AWS_SN = "IF NOT EXISTS (SELECT * FROM CLOUD_SUBNET WHERE TX_SUBNET_KEY = ? )"
						+ "INSERT INTO CLOUD_SUBNET(ID_CLOUD_VPC, TX_SUBNET_NAME, TX_SUBNET_KEY, FL_DEFAULT, ID_INFRA_TYPE) VALUES (?,?,?,?,1) ";
					
				final KeyHolder subnetId = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_SN,
								Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, cloudComponentForm.getTX_COMPONENT_KEY());
						preparedStatement.setInt(2, cloudComponentForm.getID_PARENT());
						preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_NAME());
						preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_KEY());
						preparedStatement.setInt(5, cloudComponentForm.isFL_DEFAULT()==true?1:0);
						
						return preparedStatement;
					}
				}, subnetId);
				cloudComponentForm.setID_COMPONENT(subnetId.getKey().intValue());
			}else {
				cloudComponentForm.setID_COMPONENT(snList.get(0).getID_COMPONENT());
			}
		
		}else if("CREATE".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {
			
		}
		
		ModelAndView mv = null ;
		if(cloudComponentForm.isFL_DEFAULT()==true) {
			mv = updateSubnetDefaults(cloudComponentForm, result, model, redirectAttrs, session);
		}
		
		mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/addLinkEC2", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView addLinkEC2(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		if("LINK".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {

			String LINK_AWS_EC2 = "IF NOT EXISTS (SELECT * FROM CLOUD_COMPUTE_EC2 WHERE TX_CLOUD_COMPUTE_KEY = ? )"
					+ "INSERT INTO CLOUD_COMPUTE_EC2(ID_CLOUD_VPC, TX_CLOUD_COMPUTE_NAME, TX_CLOUD_COMPUTE_KEY, TX_IP, FL_DEFAULT, ID_SUBNET, ID_SEC_GROUP,ID_INFRA_TYPE, FL_STATUS) VALUES (?,?,?,?,?,?,?,?,?) ";
				
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_EC2);
					preparedStatement.setString(1, cloudComponentForm.getTX_COMPONENT_KEY());
					preparedStatement.setInt(2, cloudComponentForm.getID_PARENT());
					preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_NAME());
					preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_KEY());
					preparedStatement.setString(5, (cloudComponentForm.getTX_IP()==null||"".equalsIgnoreCase(cloudComponentForm.getTX_IP().trim()))?"0.0.0.0":cloudComponentForm.getTX_IP());
					preparedStatement.setInt(6, cloudComponentForm.isFL_DEFAULT()==true?1:0);
					preparedStatement.setInt(7, cloudComponentForm.getID_COMPUTE_EC2_SUBNET());
					preparedStatement.setInt(8, cloudComponentForm.getID_COMPUTE_EC2_SEC_GROUP());
					preparedStatement.setInt(9, 1);
					preparedStatement.setInt(10, cloudComponentForm.isFL_STATUS()==true?1:0);
					return preparedStatement;
				}
			});

			
		}else if("CREATE".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {
			AWSActions aa = new AWSActions();
			CloudActions ca = new CloudActions();
			
			List<RulesForm> ruleList = aa.getRules(jdbcTemplate, null);
			List<RulesForm> defaultRuleList = aa.getDefaultRule(jdbcTemplate);
			RulesForm rule = null;
				rule = defaultRuleList.get(0);
			
			
				String infraKey = Generator.randomString(16);
				String tfFilePath = ca.validateSystemPath(infraKey, "EC2");

				if (!"ERROR".equalsIgnoreCase(tfFilePath)) {
					UserServicesForm usrSrvcFrm =  aa.createEC2Instance(jdbcTemplate, tfFilePath, rule);
				}
		}
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updateSecurityGroup", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateSecurityGroup(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		if("LINK".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {
			
			String CHECK_EXISTS_SQL = "SELECT ID FROM CLOUD_SECURITY_GROUP WHERE TX_SECURITY_GROUP_KEY = '" + cloudComponentForm.getTX_COMPONENT_KEY() +"' AND ID_INFRA_TYPE = 1";
			List<CloudComponentForm> sgList = jdbcTemplate.query(CHECK_EXISTS_SQL, new RowMapper<CloudComponentForm>() {
	            public CloudComponentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	CloudComponentForm sg = new CloudComponentForm();
	            	sg.setID_COMPONENT(resulSet.getInt("ID"));
	            	return sg;
	            }
	        });
			
			if(sgList.size()==0) {
				String LINK_AWS_SG = "IF NOT EXISTS (SELECT * FROM CLOUD_SECURITY_GROUP WHERE TX_SECURITY_GROUP_KEY = ? )"
						+ "INSERT INTO CLOUD_SECURITY_GROUP(ID_CLOUD_VPC, TX_SECURITY_GROUP_NAME, TX_SECURITY_GROUP_KEY, FL_DEFAULT, ID_INFRA_TYPE) VALUES (?,?,?,?,1) ";
				final KeyHolder sgId = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(LINK_AWS_SG,
								Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, cloudComponentForm.getTX_COMPONENT_KEY());
						preparedStatement.setInt(2, cloudComponentForm.getID_PARENT());
						preparedStatement.setString(3, cloudComponentForm.getTX_COMPONENT_NAME());
						preparedStatement.setString(4, cloudComponentForm.getTX_COMPONENT_KEY());
						preparedStatement.setInt(5, cloudComponentForm.isFL_DEFAULT()==true?1:0);
						return preparedStatement;
					}
				},sgId);
				cloudComponentForm.setID_COMPONENT(sgId.getKey().intValue());
			}else {
				cloudComponentForm.setID_COMPONENT(sgList.get(0).getID_COMPONENT());
			}

			
		}else if("CREATE".equalsIgnoreCase(cloudComponentForm.getTX_ACTION())) {
			
		}
		ModelAndView mv = null ;
		if(cloudComponentForm.isFL_DEFAULT()==true) {
			mv = updateSGDefaults(cloudComponentForm, result, model, redirectAttrs, session);
		}
		
		mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updatesubnetDefaults", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateSubnetDefaults(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
			String RESET_ALL_SUBNETS = "UPDATE CLOUD_SUBNET SET FL_DEFAULT = 0 WHERE ID_INFRA_TYPE = 1";
			String UPDATE_DEF_SUBNET = "UPDATE CLOUD_SUBNET SET FL_DEFAULT = 1 WHERE ID = ? AND ID_INFRA_TYPE = 1";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(RESET_ALL_SUBNETS);
					return preparedStatement;
				}
			});
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEF_SUBNET);
					preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
					return preparedStatement;
				}
			});
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updateSGDefaults", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateSGDefaults(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
			String RESET_ALL_SUBNETS = "UPDATE CLOUD_SECURITY_GROUP SET FL_DEFAULT = 0 WHERE ID_INFRA_TYPE = 1";
			String UPDATE_DEF_SUBNET = "UPDATE CLOUD_SECURITY_GROUP SET FL_DEFAULT = 1 WHERE ID = ? AND ID_INFRA_TYPE = 1";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(RESET_ALL_SUBNETS);
					return preparedStatement;
				}
			});
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEF_SUBNET);
					preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
					return preparedStatement;
				}
			});
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/deleteSubnet", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView deleteSubnet(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
			String CHECK_SUBNET_IN_RULE = "SELECT ID FROM CLOUD_RULES WHERE ID_SUBNET = " + cloudComponentForm.getID_COMPONENT();
			List<RulesForm> ruleList = jdbcTemplate.query(CHECK_SUBNET_IN_RULE, new RowMapper<RulesForm>() {
	            public RulesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	RulesForm form = new RulesForm();
	            	form.setID(resulSet.getInt("ID"));
	                return form;
	            }
	        });
			String redirection = "";
			AWSCloudActions awscloud = new AWSCloudActions();
			AWSActions aa = new AWSActions();
			List<CloudComponentForm> subnetList = null;
			List<RulesForm> subnetRulesList = null;
			if(ruleList!=null && ruleList.size()>0) {
				
				redirection = "/admin/settings/cloud/aws/remap/subnet";
				subnetList = awscloud.getCloudSubnet(jdbcTemplate);
				subnetRulesList = aa.getRules(jdbcTemplate, " AND CLOUD_SUBNET.ID = "+ cloudComponentForm.getID_COMPONENT());
				
			}else {
				String DELETE_SUBNETS = "DELETE FROM CLOUD_SUBNET WHERE ID = ?";
				
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBNETS);
						preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
						return preparedStatement;
					}
				});
				
				redirection = "redirect:/admin/settings/cloud/aws";
			}
			
		ModelAndView mv = new ModelAndView(redirection);
		if(ruleList!=null && ruleList.size()>0) {
			mv.addObject("subnets",subnetList);
			mv.addObject("subnetRules", subnetRulesList);
			mv.addObject("subnetComponent",cloudComponentForm);
		}
		return mv;
	}
	
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/deleteSecGroup", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView deleteSecGroup(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
			String CHECK_SUBNET_IN_RULE = "SELECT ID FROM CLOUD_RULES WHERE ID_SEC_GROUP = " + cloudComponentForm.getID_COMPONENT();
			List<RulesForm> ruleList = jdbcTemplate.query(CHECK_SUBNET_IN_RULE, new RowMapper<RulesForm>() {
	            public RulesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	RulesForm form = new RulesForm();
	            	form.setID(resulSet.getInt("ID"));
	                return form;
	            }
	        });
			String redirection = "";
			AWSCloudActions awscloud = new AWSCloudActions();
			AWSActions aa = new AWSActions();
			List<CloudComponentForm> sgList = null;
			List<RulesForm> sgRulesList = null;
			
			
			if(ruleList!=null && ruleList.size()>0) {
				
				redirection = "/admin/settings/cloud/aws/remap/secGroup";
				sgList = awscloud.getCloudSecurityGroup(jdbcTemplate);
				sgRulesList = aa.getRules(jdbcTemplate, " AND CLOUD_SECURITY_GROUP.ID = "+ cloudComponentForm.getID_COMPONENT());
				
			}else {
				String DELETE_SG = "DELETE FROM CLOUD_SECURITY_GROUP WHERE ID = ?";
				
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SG);
						preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
						return preparedStatement;
					}
				});
				
				redirection = "redirect:/admin/settings/cloud/aws";
			}
			
		ModelAndView mv = new ModelAndView(redirection);
		if(ruleList!=null && ruleList.size()>0) {
			mv.addObject("secGroups",sgList);
			mv.addObject("sgRulesList", sgRulesList);
			mv.addObject("sgComponent",cloudComponentForm);
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/vpc/updateVPCDefaults", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateVPCDefaults(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
			String RESET_ALL_VPCS = "UPDATE CLOUD_VPC SET FL_DEFAULT = 0";
			String UPDATE_DEF_VPC = "UPDATE CLOUD_VPC SET FL_DEFAULT = 1 WHERE ID = ?";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(RESET_ALL_VPCS);
					return preparedStatement;
				}
			});
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEF_VPC);
					preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
					return preparedStatement;
				}
			});
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/rules", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView getAwsRules(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		AWSActions aa = new AWSActions();
		List<RulesForm> ruleList = aa.getRules(jdbcTemplate, null);
		List<RulesForm> defaultRuleList = aa.getDefaultRule(jdbcTemplate);
		
		DevOpsActions da = new DevOpsActions();
		List<DevOpsToolsForm> dTools = da.getDOCatTools(jdbcTemplate, null);
		
		AWSCloudActions awscloud = new AWSCloudActions();
		List<CloudComponentForm> vpcList = awscloud.getCloudVPC(jdbcTemplate);
		List<CloudComponentForm> sgList = awscloud.getCloudSecurityGroup(jdbcTemplate); 
		List<CloudComponentForm> subnetList = awscloud.getCloudSubnet(jdbcTemplate); 
		
		
		String mainTab = "Cloud";
		String subTab = "AWS";
		ModelAndView mv = new ModelAndView("/admin/settings/cloud/aws/rules");
		mv.addObject("ruleList",ruleList);
		mv.addObject("defaultRuleList",defaultRuleList );
		mv.addObject("dTools",dTools);
		mv.addObject("mainTab",mainTab);
		mv.addObject("subTab", subTab);
		mv.addObject("vpcList",vpcList);
		mv.addObject("sgList",sgList);
		mv.addObject("subnetList",subnetList);
		
		return mv;
	}
	
	
	@RequestMapping("/admin/settings/cloud/aws/rules/getVPCRouteTable")
	public @ResponseBody List<CloudComponentForm> getVPCRouteTable(@RequestParam("key") int id,  HttpSession session) {
		AWSCloudActions aca = new AWSCloudActions();
		List<CloudComponentForm> rtList = aca.getRouteTableForVPC(id);
		return rtList;
	}
	
	@RequestMapping("/admin/settings/cloud/aws/rules/getVPCNACL")
	public @ResponseBody List<CloudComponentForm> getVPCNACL(@RequestParam("key") int id,  HttpSession session) {
		AWSCloudActions aca = new AWSCloudActions();
		List<CloudComponentForm> nacList = aca.getNACLForVPC(id);
		return nacList;
	}
	
	@RequestMapping("/admin/settings/cloud/aws/rules/getVPCSecGroup")
	public @ResponseBody List<CloudComponentForm> getVPCSecGroup(@RequestParam("key") int id,  HttpSession session) {
		AWSCloudActions aca = new AWSCloudActions();
		List<CloudComponentForm> sgList = aca.getSecGroupForVPC(id);
		return sgList;
	}
	@RequestMapping("/admin/settings/cloud/aws/rules/getVPCSubnet")
	public @ResponseBody List<CloudComponentForm> getVPCSubnet(@RequestParam("key") int id,  HttpSession session) {
		AWSCloudActions aca = new AWSCloudActions();
		List<CloudComponentForm> snList = aca.getSubnetForVPC(id);
		return snList;
	}
	
	

	@RequestMapping(value="/admin/settings/cloud/aws/newRule", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView postNewAwsRules(@ModelAttribute("rulesBean") @Valid RulesForm rulesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String Rules_SQL = "INSERT INTO CLOUD_RULES(ID_TOOL, ID_VPC, ID_NACL, ID_ROUTE_TABLE, ID_SEC_GROUP, ID_SUBNET, ID_INFRA_TYPE) VALUES (?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(Rules_SQL);
				preparedStatement.setInt(1,rulesBean.getID_DEVOPS_TOOL());
				preparedStatement.setInt(2,rulesBean.getID_VPC());
				preparedStatement.setInt(3,rulesBean.getID_NACL());
				preparedStatement.setInt(4,rulesBean.getID_ROUTE_TABLE());
				preparedStatement.setInt(5,rulesBean.getID_SEC_GROUP());
				preparedStatement.setInt(6,rulesBean.getID_SUBNET());
				preparedStatement.setInt(7,1);
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws/rules");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/deleteRule", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView deleteAwsRules(@ModelAttribute("rulesBean") @Valid RulesForm rulesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String Rules_SQL = "DELETE FROM CLOUD_RULES WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(Rules_SQL);
				preparedStatement.setInt(1,rulesBean.getID());
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws/rules");
		return mv;
	}
	
	
	@RequestMapping(value="/admin/settings/cloud/aws/remapSubnet", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView remapSubnet(@ModelAttribute("rulesBean") @Valid RulesForm rulesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String Rules_SQL = "UPDATE CLOUD_RULES SET ID_SUBNET = ? WHERE ID_SUBNET = ? AND ID_INFRA_TYPE = 1";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(Rules_SQL);
				preparedStatement.setInt(1,rulesBean.getID_SUBNET());
				preparedStatement.setInt(2,rulesBean.getID());
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws/rules");
		return mv;
	}
	
	@RequestMapping(value="/admin/settings/cloud/aws/remapSecGroup", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView remapSecGroup(@ModelAttribute("rulesBean") @Valid RulesForm rulesBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String Rules_SQL = "UPDATE CLOUD_RULES SET ID_SEC_GROUP = ? WHERE ID_SEC_GROUP = ? AND ID_INFRA_TYPE = 1";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(Rules_SQL);
				preparedStatement.setInt(1,rulesBean.getID_SUBNET());
				preparedStatement.setInt(2,rulesBean.getID());
				return preparedStatement;
			}
		});
		
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws/rules");
		return mv;
	}


	@RequestMapping(value="/admin/settings/cloud/aws/vpc/deleteEC", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView deleteEC(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		
		String DELETE_SG = "DELETE FROM CLOUD_COMPUTE_EC2 WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SG);
				preparedStatement.setInt(1, cloudComponentForm.getID_COMPONENT());
				return preparedStatement;
			}
		});
				
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	

			
	@RequestMapping(value="/admin/settings/cloud/aws/ec2/updateIP", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView updateEC2IP(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		
		String UPDATE_EC2_IP = "UPDATE CLOUD_COMPUTE_EC2 SET TX_IP = ? WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EC2_IP);
				preparedStatement.setString(1, cloudComponentForm.getTX_IP());
				preparedStatement.setInt(2, cloudComponentForm.getID_COMPONENT());
				return preparedStatement;
			}
		});
				
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	

	@RequestMapping(value="/admin/settings/cloud/aws/ec2/changeState", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_CLOUD_CONFIG_MODIFY')")
	public ModelAndView changeEC2State(@ModelAttribute("cloudComponentBean") @Valid CloudComponentForm cloudComponentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		
		String UPDATE_EC2_STATE = "UPDATE CLOUD_COMPUTE_EC2 SET FL_STATUS = ? WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EC2_STATE);
				preparedStatement.setInt(1, cloudComponentForm.isFL_STATUS()==true?1:0);
				preparedStatement.setInt(2, cloudComponentForm.getID_COMPONENT());
				return preparedStatement;
			}
		});
				
		ModelAndView mv = new ModelAndView("redirect:/admin/settings/cloud/aws");
		return mv;
	}
	
}

