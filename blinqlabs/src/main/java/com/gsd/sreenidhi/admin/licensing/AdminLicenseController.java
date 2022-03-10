package com.gsd.sreenidhi.admin.licensing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.products.ProductController;
import com.gsd.sreenidhi.admin.products.ProductForm;
import com.gsd.sreenidhi.commons.licensing.LicenseModule;
import com.gsd.sreenidhi.commons.serviceEntitlement.ServiceEntitlementModule;
import com.gsd.sreenidhi.commons.user.RegisterModule;
import com.gsd.sreenidhi.forms.Constants;
import com.gsd.sreenidhi.register.Register;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.security.licensing.ProductLicenseContoller;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;

@Controller
@RequestMapping("/admin/adminLicense")
@SessionAttributes("adminLicenseBean")
public class AdminLicenseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminLicenseController.class);
	
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public AdminLicenseController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("adminLicenseBean")
		 public License createAdminLicenseFormBean() {
		 	return new License();
		 }
		 
		 @RequestMapping(method=RequestMethod.GET)  
		 @PreAuthorize("hasAuthority('PERM_LICENSE')")
		 public ModelAndView licenseForm(HttpSession session, @RequestParam("page") int page) {
			 
			 int countOnPage = 10;
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
				
				final String GET_LICENSE_COUNT_SQL = "SELECT COUNT(*) AS COUNT		 		 FROM LICENSES , "
						+ "					  					 PRODUCTS, "
						+ "					  					 ACCOUNTS "
						+ "					  			 		 WHERE PRODUCTS.FL_LICENSE = 1 "
						+ "					  					 AND LICENSES.TX_PRODUCT = PRODUCTS.ID "
						+ "					  			 		 AND ACCOUNTS.ID = LICENSES.ID_ACCOUNT";
				
				List<License> liccountList = this.jdbcTemplate.query(GET_LICENSE_COUNT_SQL, new RowMapper<License>() {
		            public License mapRow(ResultSet resulSet, int rowNum) throws SQLException {
		            	License lic = new License();
		            	
		            	lic.setQueryLicenseCount(resulSet.getInt("COUNT"));
		            	return lic;
		            }
		        });
			 	
		 		int totalCount =  liccountList.get(0).getQueryLicenseCount();
		 		double rounder = (double)totalCount/(double) countOnPage;
			 	
			 	int pages = (int) Math.ceil(rounder);
				
			 final String GET_LICENES_QUERY_SQL = ""
					 + " DECLARE @PageNumber AS INT, @RowspPage AS INT"
					 + " SET @PageNumber = " + page
					 + " SET @RowspPage = " + countOnPage
					 + " SELECT * FROM ( "
					 + "			SELECT									 ROW_NUMBER() OVER(ORDER BY LICENSES.ID DESC) AS NUMBER, "
					 + "					    						 LICENSES.ID, LICENSES.ID_ACCOUNT, ACCOUNTS.TX_EMAIL, LICENSES.TX_LICENSE, PRODUCTS.TX_PRODUCT_NAME, LICENSES.TX_VALIDITY, LICENSES.TX_LICENSE_TYPE "
					 + "					  			 		 FROM LICENSES , "
					 + "					  					 PRODUCTS, "
					 + "					  					 ACCOUNTS "
					 + "					  			 		 WHERE PRODUCTS.FL_LICENSE = 1 "
					 + "					  					 AND LICENSES.TX_PRODUCT = PRODUCTS.ID "
					 + "					  			 		 AND ACCOUNTS.ID = LICENSES.ID_ACCOUNT "
					 + "					  			 		 "
					 + "												 ) AS TBL "
					 + " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
					 
				List<License> licenseList = this.jdbcTemplate.query(GET_LICENES_QUERY_SQL, new RowMapper<License>() {
		            public License mapRow(ResultSet resulSet, int rowNum) throws SQLException {
		            	License lic = new License();
		            	lic.setId(resulSet.getInt("ID"));
		            	lic.setId_account(resulSet.getInt("ID_ACCOUNT"));
		            	lic.setTx_accountID(resulSet.getString("TX_EMAIL"));
		            	lic.setTx_license(resulSet.getString("TX_LICENSE"));
		            	lic.setLicensedProduct(resulSet.getString("TX_PRODUCT_NAME"));
		            	lic.setLicenseValidity(resulSet.getString("TX_VALIDITY"));
		            	lic.setLicenseType(resulSet.getString("TX_LICENSE_TYPE"));
		                return lic;
		            }
		        });
			 
				ModelAndView mv =  new ModelAndView("admin/license/viewLicenses");
				session.setAttribute("licenseList", licenseList);
				mv.addObject(licenseList);
				
				 ProductController pc = new ProductController(this.jdbcTemplate.getDataSource());
				 List<ProductForm> productList =pc.getProductList(1,1);
				 
				 mv.addObject("productList", productList);
				 mv.addObject("currentPage",page);
				 mv.addObject("totalPages",pages);
				
			 return mv;
		 
		 }
		 
		 
		 
		 @RequestMapping(method=RequestMethod.POST)
		 @PreAuthorize("hasAuthority('PERM_LICENSE')")
			public ModelAndView generateLicense(@ModelAttribute("adminLicenseBean") @Valid License licenseBean, 
					   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 

				
				boolean licenseGenerated = false;
				 logger.info("ProductLicenseController post called");
				
				//Calculate expiry
					SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
					Calendar dt = null;
					dt = Calendar.getInstance();
					
					String sen="UNKNOWN";
					int licensePeriod ;
					if("REGISTERED".equalsIgnoreCase(licenseBean.getLicenseType())) {
						licensePeriod = Constants.REGISTERED_DAYS;
						ServiceEntitlementModule senMod = new ServiceEntitlementModule();
						
						sen=senMod.genetateSEN(true);
						
					}else {
						licensePeriod = Constants.TRIAL_DAYS;
					}
					
					dt.add(Calendar.DATE, licensePeriod);
					String expiryDate = sdf.format(dt.getTime());
					
					LicenseModule lm = new LicenseModule();
				 
				final String licenseKey = lm.generateLicense(expiryDate, licenseBean.getSelectProduct(), licenseBean.getSelectProduct(), sen, licenseBean.getMachineId());
				logger.info("License has been generated successfully: " + licenseKey);
				if(licenseKey.length()>5) {
					licenseGenerated = true;	
				}
				
		
				
				
				 final String GET_USER_ACCOUNT_QUERY_SQL = "SELECT ID, TX_EMAIL FROM ACCOUNTS WHERE TX_EMAIL = '" + licenseBean.getTx_accountID()+"'";
						
						List<UserForm> userList = this.jdbcTemplate.query(GET_USER_ACCOUNT_QUERY_SQL, new RowMapper<UserForm>() {
				            public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				            	UserForm uF = new UserForm();
				            	uF.setId(resulSet.getInt("ID"));
				            	uF.setEmail(resulSet.getString("TX_EMAIL"));
				                return uF;
				            }
				        });
				
				if(userList.isEmpty()) {
					Register rF = new Register();
					rF.setEmail(licenseBean.getTx_accountID());
					rF.setFirstname("Registered");
					rF.setLastname("User");
					final String tempPassword = "PasswordTempo";
					
					RegisterModule rm = new RegisterModule(this.jdbcTemplate.getDataSource());
					rm.registerAccount(rF);
				}
				
				final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
				final String useraccountid = licenseBean.getTx_accountID(); 
				final String licProduct =  licenseBean.getSelectProduct();
				final String licValidity = expiryDate;
				final String licType = licenseBean.getLicenseType();
				
				final KeyHolder licenseId = new GeneratedKeyHolder();
				final String INSERT_LICENSE_SQL = "INSERT INTO LICENSES (TX_LICENSE, ID_ACCOUNT, TX_PRODUCT, TX_VALIDITY, TX_LICENSE_TYPE) VALUES (?,(SELECT ID FROM ACCOUNTS WHERE TX_EMAIL=?),?,?,?)";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LICENSE_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, licenseKey);
						preparedStatement.setString(2, useraccountid);
						preparedStatement.setString(3, licProduct );
						preparedStatement.setString(4, licValidity );
						preparedStatement.setString(5, licType);
						return preparedStatement;
					}
				},licenseId);
				
				
				
				final String senString = sen;
				final String INSERT_SEN_SQL = "INSERT INTO SERVICE_LICENSE_ENTITLEMENT (TX_ENTITLEMENT, ID_ACCOUNT, ID_LICENSE) VALUES (?,?,?)";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SEN_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, senString);
						preparedStatement.setInt(2, Integer.parseInt(useraccountid));
						preparedStatement.setInt(3,  licenseId.getKey().intValue());
						return preparedStatement;
					}
				});	
			
				ModelAndView mv = licenseForm(session,1);
				if(licenseGenerated) {
					mv = new ModelAndView("user/license","successmessage", "License generated successfully!");
				}else {
					mv = new ModelAndView("user/license","failuremessage", "License generation failed.");
				}
				return mv;
		 }
}
