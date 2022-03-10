package com.gsd.sreenidhi.security.licensing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.gsd.sreenidhi.commons.licensing.LicenseModule;
import com.gsd.sreenidhi.commons.serviceEntitlement.ServiceEntitlementModule;
import com.gsd.sreenidhi.admin.products.ProductController;
import com.gsd.sreenidhi.admin.products.ProductForm;
import com.gsd.sreenidhi.forms.Constants;
import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.tables.Accounts;
import com.gsd.sreenidhi.user.tickets.UserTicketsForm;
import com.gsd.sreenidhi.utils.AES256_Encryption;

@Controller
@SessionAttributes("licenseBean")
public class ProductLicenseContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductLicenseContoller.class);
	
	
private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	public ProductLicenseContoller(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	 @ModelAttribute("licenseBean")
	 public License createLicenseFormBean() {
	 	return new License();
	 }
	 
	 @RequestMapping(value="/user/userLicenses", method=RequestMethod.GET)  
	 public ModelAndView retrieveProductLicense(HttpSession session, @RequestParam(value="page", required=false) String page, @RequestParam(value="filter", required=false) String filter , @RequestParam(value="type", required=false) String type) { 
			int pg=0;
			
			boolean evaluation = false;
			boolean registered = false;
			boolean active = false;
			boolean expired = false;
			
		String t = null;
		if(type == null) {
			t = "1111";
		}else {
			t = type;
		}
			
		
		if(t.charAt(0)=='1') {
			evaluation = true;
		}
		
		if(t.charAt(1)=='1') {
			registered = true;
		}
		
		if(t.charAt(2)=='1') {
			active = true;
		}
		
		if(t.charAt(3)=='1') {
			expired = true;
		}
		
		
		String statusQuery = "";
		if(evaluation) {
			statusQuery = statusQuery + " AND TX_LICENSE_TYPE = 'EVALUATION' ";
		}
		if(registered) {
			statusQuery = statusQuery + " AND TX_LICENSE_TYPE = 'REGISTERED' ";
		}
		
		
		final boolean activeStatus = active;
		final boolean expiredStatus = expired;
		
		 if(page==null) {
				pg=1;
			}else {
				pg = Integer.parseInt(page);
			}
		 int countOnPage = 10;
			
		 final String userId = session.getAttribute("userId").toString().trim();
		 final int usrId = Integer.parseInt(userId);
		
		 String queryStringPlaceHolder = "";
		 if(filter!=null) {
			 
			 if("all".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=""; 
			 }else if("pending".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (1,2,3,6)";
			 } 
			 else if("evaluation".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND L.TX_LICENSE_TYPE = 'EVALUATION'";
			 } 
			 else if("registered".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND L.TX_LICENSE_TYPE = 'REGISTERED'";
			 } 
			 else  {
				 queryStringPlaceHolder=""; 
			 } 
		 }	else {
			 queryStringPlaceHolder="";
		 }
		 
		 
		 final String GET_LICENSE_COUNT_SQL =  "SELECT COUNT(*) AS COUNT "
				 + "FROM   LICENSES L "
				 + "       JOIN PRODUCTS P "
				 + "         ON L.TX_PRODUCT = P.ID "
				 + "WHERE  P.FL_LICENSE = 1 "
				 + "   AND L.ID_ACCOUNT = "+ userId + queryStringPlaceHolder
				 + "";
			
			List<License> liccountList = this.jdbcTemplate.query(GET_LICENSE_COUNT_SQL, new RowMapper<License>() {
	            public License mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	License tkt = new License();
	            	
	            	tkt.setQueryLicenseCount(resulSet.getInt("COUNT"));
	            	return tkt;
	            }
	        });
		 	
	 		int totalCount =  liccountList.get(0).getQueryLicenseCount();
	 		double rounder = (double)totalCount/(double) countOnPage;
		 	int pages = (int) Math.ceil(rounder);
		 	
		 	
		 
		 
			
		 final String GET_LICENES_QUERY_SQL = " DECLARE @PageNumber AS INT, @RowspPage AS INT"
				 + " SET @PageNumber = " + pg
				 + " SET @RowspPage =  " + countOnPage
				 + " SELECT * FROM ( "
				 + "			SELECT									 ROW_NUMBER() OVER(ORDER BY L.ID DESC) AS NUMBER, "
				 + "					    					 L.ID, "
				 + "       L.ID_ACCOUNT, "
				 + "       L.TX_LICENSE, "
				 + "       P.TX_PRODUCT_NAME, "
				 + "       L.TX_VALIDITY, "
				 + "       L.TX_LICENSE_TYPE "
				 + " FROM   LICENSES L "
				 + "       JOIN PRODUCTS P "
				 + "         ON L.TX_PRODUCT = P.ID "
				 + "  WHERE  P.FL_LICENSE = 1 "
				 + "   AND L.ID_ACCOUNT =  " + usrId + queryStringPlaceHolder
				 + "												 ) AS TBL "
				 + " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
			
			List<License> licenseList = this.jdbcTemplate.query(GET_LICENES_QUERY_SQL, new RowMapper<License>() {
	            public License mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
	            	Date dt = new Date();
	            	String today = sdf.format(dt);
	            	
	            	if(activeStatus) {
	            		if (today.compareTo(resulSet.getString("TX_VALIDITY")) <= 0) {
	            			License lic = new License();
	    	            	lic.setId(resulSet.getInt("ID"));
	    	            	lic.setId_account(resulSet.getInt("ID_ACCOUNT"));
	    	            	lic.setTx_license(resulSet.getString("TX_LICENSE"));
	    	            	lic.setLicensedProduct(resulSet.getString("TX_PRODUCT_NAME"));
	    	            	lic.setLicenseValidity(resulSet.getString("TX_VALIDITY"));
	    	            	lic.setLicenseType(resulSet.getString("TX_LICENSE_TYPE"));
	    	            	return lic;
		            	}
	            	}
	            	
	            	if(expiredStatus) {
	            		if (today.compareTo(resulSet.getString("TX_VALIDITY")) > 0) {
	            			License lic = new License();
	    	            	lic.setId(resulSet.getInt("ID"));
	    	            	lic.setId_account(resulSet.getInt("ID_ACCOUNT"));
	    	            	lic.setTx_license(resulSet.getString("TX_LICENSE"));
	    	            	lic.setLicensedProduct(resulSet.getString("TX_PRODUCT_NAME"));
	    	            	lic.setLicenseValidity(resulSet.getString("TX_VALIDITY"));
	    	            	lic.setLicenseType(resulSet.getString("TX_LICENSE_TYPE"));
	    	            	return lic;
		            	}
	            	}
	            	
	            	return null;
	            	
	            }
	        });
		 
			ModelAndView mv =  new ModelAndView("user/license");
			mv.addObject("licenseList", licenseList);
			
			 ProductController pc = new ProductController(this.jdbcTemplate.getDataSource());
			 List<ProductForm> productList =pc.getProductList(1,1);
			 
			 mv.addObject("productList", productList);
			 mv.addObject("currentFilter",filter);
			 mv.addObject("currentPage",pg);
			 mv.addObject("totalPages",pages);
			 mv.addObject("activeStatus",activeStatus);
			 mv.addObject("expiredStatus",expiredStatus);
			 mv.addObject("evalStatus",evaluation);
			 mv.addObject("regStatus",registered);
			 
			
			
		 return mv;
	 }  
	 
	 
	 @RequestMapping(value="userLicenses", method=RequestMethod.POST)
	public ModelAndView generateProductLicense(@ModelAttribute("licenseBean") @Valid License licenseBean, 
			   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
		
		boolean licenseGenerated = false;
		 logger.info("ProductLicenseController post called");
		
		//Calculate expiry
			SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
			Calendar dt = null;
			dt = Calendar.getInstance();
			int licensePeriod = Constants.TRIAL_DAYS;
			dt.add(Calendar.DATE, licensePeriod);
			String expiryDate = sdf.format(dt.getTime());
			
			
			LicenseModule lm = new LicenseModule();
		 
			ServiceEntitlementModule senMod = new ServiceEntitlementModule();
			
			String sen = senMod.genetateSEN(true);
			
		final String licenseKey = lm.generateLicense(expiryDate, licenseBean.getSelectProduct(), licenseBean.getSelectProduct(), sen, licenseBean.getMachineId());
		logger.info("License has been generated successfully: " + licenseKey);
		if(licenseKey.length()>5) {
			licenseGenerated = true;	
		}
		
		
		
				
		final String userId = session.getAttribute("userId").toString().trim();
		final int usrId = Integer.parseInt(userId);
		final String licProduct =  licenseBean.getSelectProduct();
		final String licValidity = expiryDate;
		final String licType = "EVALUATION";
				
		
		final String INSERT_LICENSE_SQL = "INSERT INTO LICENSES (TX_LICENSE, ID_ACCOUNT, TX_PRODUCT, TX_VALIDITY, TX_LICENSE_TYPE) VALUES (?,?,?,?,?)";    	
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LICENSE_SQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, licenseKey);
				preparedStatement.setInt(2, usrId);
				preparedStatement.setString(3, licProduct );
				preparedStatement.setString(4, licValidity );
				preparedStatement.setString(5, licType);
				return preparedStatement;
			}
		});	
		
		String msgtype;
		String msgContent;
		if(licenseGenerated) {
			msgtype = "successmessage";
			msgContent = "License generated successfully!";
		}else {
			msgtype = "failuremessage";
			msgContent = "License generation failed.";
		}
		
		ModelAndView mv = new ModelAndView("redirect:/user/userLicenses?page=1&filter=all",msgtype, msgContent);
		
		return mv;
	}
	 
	 

	 
	 
}
