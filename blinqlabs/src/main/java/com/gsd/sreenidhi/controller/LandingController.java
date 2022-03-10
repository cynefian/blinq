package com.gsd.sreenidhi.controller;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.blogs.BlogActions;
import com.gsd.sreenidhi.admin.blogs.BlogCategoryForm;
import com.gsd.sreenidhi.admin.blogs.BlogCommentForm;
import com.gsd.sreenidhi.admin.blogs.BlogController;
import com.gsd.sreenidhi.admin.blogs.BlogForm;
import com.gsd.sreenidhi.admin.faq.FaqController;
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.AccountRecoveryMailForm;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.register.RegisterFormController;
import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.security.passwordManagement.PasswordManagementForm;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.utils.AESEncryption;
import com.gsd.sreenidhi.utils.Base64Encoding;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.gsd.sreenidhi.utils.Generator;



/**
 * @author Sreenidhi Gundlupet
 * sreenidhi.gsd@gmail.com
 * */

@Controller
public class LandingController {
	
	private @Autowired HttpServletRequest request;
	
	private JdbcTemplate jdbcTemplate = null;
	
	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
	
	@Autowired
	public LandingController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}
	
	@RequestMapping("signin")
	public ModelAndView signIn() {
		ModelAndView mv = new ModelAndView("security/signin");
		return mv;
	}
	
	@RequestMapping(value="/recoveryPage", method = RequestMethod.POST)
	public ModelAndView recoveryPage(@ModelAttribute("userBean") @Valid UserForm userBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String CHECK_EMAIL_SQL = "SELECT TX_EMAIL FROM ACCOUNTS WHERE TX_EMAIL = '"+userBean.getEmail().trim()+"'";
		
		
		List<UserForm> uFList = jdbcTemplate.query(CHECK_EMAIL_SQL, new RowMapper<UserForm>() {
			public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserForm uf = new UserForm();
				uf.setEmail(resulSet.getString("TX_EMAIL"));
				
				return uf;
			}
		});
		 
		
		ModelAndView mv = null;
		if(uFList!=null && uFList.size()>0) {
			mv = new ModelAndView("security/account-recovery");
			
			final String code = Generator.randomString(8);
			
			
			final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

			Calendar date = Calendar.getInstance();
			long t= date.getTimeInMillis();
			String requestDate = CalendarUtils.dateToString(new Date(t));
			
			Date afterAddingTenMins=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
			String codeExpiry = CalendarUtils.dateToString(afterAddingTenMins);
			
			String INSERT_ACCOUNT_RECOVERY = "INSERT INTO ACCOUNT_RECOVERY(TX_EMAIL, TX_CODE, TS_CREATE, TX_EXPIRY, FL_ACTIVE) VALUES (?,?,?,?,?)";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_RECOVERY);
					preparedStatement.setString(1, userBean.getEmail().trim());
					preparedStatement.setString(2, code);
					preparedStatement.setString(3, requestDate);
					preparedStatement.setString(4, codeExpiry);
					preparedStatement.setInt(5, 1);
					return preparedStatement;
				}
			});
			
			
			String uri="email="+userBean.getEmail().trim()+"&code="+code;
			String encrypted=AESEncryption.encrypt(uri, "8l!nqLabs@dm1n");
			Base64.Encoder encoder = Base64.getEncoder();
			String data = encoder.encodeToString(encrypted.getBytes());
			String link="https://blinqlabs.com/recovery-link?encrypted="+data;
			
			AccountRecoveryMailForm acmf = new AccountRecoveryMailForm();
			EmailForm ef = new EmailForm();
			
			String tempalte = ef.getEmailTemplate();
			String emailMessage = acmf.getRecoveryMessage(); 
			emailMessage.replace("{email}", userBean.getEmail().trim()).replace("{code}", code).replace("{link}", link);
				SendMail sm = new SendMail();
				
				  
				  final String emailMsg = tempalte.replace("${username}", userBean.getFirstname()+" "+userBean.getLastname()).replace("${title}", "Password Recovery").replace("${message", emailMessage).replace("${image}", "").replace("${dateTime}", CalendarUtils.getCurrentTimeStamp()).replace("${disclaimer}", ef.getDisclaimer());
					
				    ExecutorService service = Executors.newFixedThreadPool(4);
				    service.submit(new Runnable() {
				        public void run() {
				        	  sm.sendMessage(emailMsg, userBean.getEmail().trim(), null, "Blinqlabs Account Recovery", false);
				        }
				    });
				String redirection;
				
				mv.addObject("successmessage","Please check your email for recovery instructions.");
				mv.addObject("tx_email",userBean.getEmail().trim());
			
			
		}else {
			mv = new ModelAndView("security/authenticate");
			mv.addObject("failuremessage","The email you provided does not exist in our system. Please try again.");
		}
		
		return mv;
	}
	

	@RequestMapping("/recover-account")
	public ModelAndView recoverAccount(@ModelAttribute("userBean") @Valid UserForm userBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String validateCodeSql = "SELECT TX_EXPIRY FROM ACCOUNT_RECOVERY WHERE TX_EMAIL = '"+userBean.getEmail()+"' AND TX_CODE = '"+userBean.getTX_2FA()+"' AND FL_ACTIVE = 1 ORDER BY ID DESC";
		
		List<UserForm> uFList = jdbcTemplate.query(validateCodeSql, new RowMapper<UserForm>() {
			public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserForm uf = new UserForm();
				uf.setTX_2FA(resulSet.getString("TX_EXPIRY"));
				return uf;
			}
		});
		
		ModelAndView mv = null;
		if(uFList!=null && uFList.size()>0) {
			
			Calendar date = Calendar.getInstance();
			long t= date.getTimeInMillis();
			String currentDate = CalendarUtils.dateToString(new Date(t));
			
			String expirySet = uFList.get(0).getTX_2FA();
			
			try {
				Date nowDt = CalendarUtils.stringToDate(currentDate);
				Date expDt = CalendarUtils.stringToDate(expirySet);
				
				if(nowDt.before(expDt)) {
					mv = new ModelAndView("security/reset-password");
				}else {
					mv = new ModelAndView("security/expired-code");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			mv = new ModelAndView("security/expired-code");
		}
		mv.addObject("tx_email",userBean.getEmail().trim());
		return mv;
	}
	
	@RequestMapping("/recovery-link")
	public ModelAndView recoveryLink(HttpSession session,@RequestParam("encrypted") final String encrypted) {
		
		Base64.Decoder decoder = Base64.getDecoder();
		String aesEncrypted = new String(decoder.decode(encrypted));
		
		String decrypt = AESEncryption.decrypt(aesEncrypted, "8l!nqLabs@dm1n");
		String[] segments = decrypt.split("&");
		String email = segments[0].replace("email=", "");
		String code = segments[1].replace("code=", "");
		
		String validate_sql = "SELECT TX_EXPIRY FROM ACCOUNT_RECOVERY WHERE TX_EMAIL = '"+email.trim()+"' AND TX_CODE = '"+code.trim()+"' AND FL_ACTIVE = 1 ORDER BY ID DESC";
		
		List<UserForm> uFList = jdbcTemplate.query(validate_sql, new RowMapper<UserForm>() {
			public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UserForm uf = new UserForm();
				uf.setTX_2FA(resulSet.getString("TX_EXPIRY"));
				
				return uf;
			}
		});
		
		ModelAndView mv = null;
		if(uFList!=null && uFList.size()>0) {
			
			Calendar date = Calendar.getInstance();
			long t= date.getTimeInMillis();
			String currentDate = CalendarUtils.dateToString(new Date(t));
			
			String expirySet = uFList.get(0).getTX_2FA();
			
			try {
				Date nowDt = CalendarUtils.stringToDate(currentDate);
				Date expDt = CalendarUtils.stringToDate(expirySet);
				
				if(nowDt.before(expDt)) {
					mv = new ModelAndView("security/reset-password");
				}else {
					mv = new ModelAndView("security/expired-code");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			mv = new ModelAndView("security/expired-code");
		}
		mv.addObject("tx_email",email.trim());
		return mv;
	}
	
	@RequestMapping(value="/changeForgottenPassword", method = RequestMethod.POST)
	public ModelAndView changeForgottenPassword(@ModelAttribute("pwdBean") @Valid PasswordManagementForm pwdBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) {
		
		String changePwdSql = "UPDATE ACCOUNTS SET TX_PASSWORD = ? WHERE TX_EMAIL = ? ";
		Bcrypt bcrypt = new Bcrypt();
		final String newHash = "{bcrypt}"+bcrypt.encode(pwdBean.getNewpassword1());
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(changePwdSql);
				preparedStatement.setString(1, newHash);
				preparedStatement.setString(2, pwdBean.getEmail());
				return preparedStatement;
			}
		});
		
		
		String UpdateRecoverSql = "UPDATE ACCOUNT_RECOVERY SET FL_ACTIVE = ? WHERE TX_EMAIL = ? ";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateRecoverSql);
				preparedStatement.setInt(1, 0);
				preparedStatement.setString(2, pwdBean.getEmail());
				return preparedStatement;
			}
		});
		
		
		ModelAndView mv = new ModelAndView("security/authenticate");
		mv.addObject("successmessage","Password Changed Successfully.");
		return mv;
	}
	
	 
	/* *************************************
	 * Footer Section
	 * *************************************
	 */
	@RequestMapping("/privacyPolicy")
	public ModelAndView privacyPolicy() {
		ModelAndView mv = new ModelAndView("policy/privacyPolicy");
		return mv;
	}
	
	@RequestMapping("/termsofUse")
	public ModelAndView termsofUse() {
		ModelAndView mv = new ModelAndView("policy/termsofUse");
		return mv;
	}
	
	@RequestMapping("/cookieSettings")
	public ModelAndView cookieSettings() {
		ModelAndView mv = new ModelAndView("policy/cookieSettings");
		return mv;
	}
	
	/* *************************************
	 * Showcase Section
	 * *************************************
	 */
	@RequestMapping("/products")
	public ModelAndView showProducts() {
		ModelAndView mv = new ModelAndView("showcase/products");
		return mv;
	}
	
	@RequestMapping("/features")
	public ModelAndView showFeatures() {
		ModelAndView mv = new ModelAndView("showcase/features");
		return mv;
	}
	
	
	@RequestMapping("/services")
	public ModelAndView showServices() {
		ModelAndView mv = new ModelAndView("showcase/services");
		return mv;
	}
	
	@RequestMapping("/enterpriseServices")
	public ModelAndView showEnterpriseServices() {
		ModelAndView mv = new ModelAndView("showcase/enterpriseServices");
		return mv;
	}
	
	@RequestMapping("/smb")
	public ModelAndView showSmb() {
		ModelAndView mv = new ModelAndView("showcase/smb");
		return mv;
	}
	
	@RequestMapping("/oss")
	public ModelAndView showOSS() {
		ModelAndView mv = new ModelAndView("showcase/oss");
		return mv;
	}
	
	@RequestMapping("/dot")
	public ModelAndView showDot() {
		ModelAndView mv = new ModelAndView("showcase/dot");
		return mv;
	}
	
	@RequestMapping("/training-certification")
	public ModelAndView showTrainingCertification() {
		ModelAndView mv = new ModelAndView("showcase/training-certification");
		return mv;
	}
	
	@RequestMapping("/training")
	public ModelAndView training() {
		ModelAndView mv = new ModelAndView("showcase/training");
		return mv;
	}

	@RequestMapping("/analytics")
	public ModelAndView analytics() {
		ModelAndView mv = new ModelAndView("showcase/analytics");
		return mv;
	}
	
	/* *************************************
	 * Resources Section
	 * *************************************
	 */
	
	@RequestMapping("/apidoc")
	public ModelAndView apidoc() {
		ModelAndView mv = new ModelAndView("resources/apidoc");
		return mv;
	}
	
	@RequestMapping("/support")
	public ModelAndView support() {
		ModelAndView mv = new ModelAndView("resources/support");
		return mv;
	}
	
	@RequestMapping("/status")
	public ModelAndView status() {
		ModelAndView mv = new ModelAndView("resources/status");
		return mv;
	}
	
	@RequestMapping("/docs")
	public ModelAndView docs() {
		ModelAndView mv = new ModelAndView("resources/docs");
		return mv;
	}
	
	@RequestMapping("/roadmap")
	public ModelAndView roadmap() {
		ModelAndView mv = new ModelAndView("resources/roadmap");
		return mv;
	}
	
	@RequestMapping("/versions")
	public ModelAndView versions() {
		ModelAndView mv = new ModelAndView("resources/versions");
		return mv;
	}
	
	@RequestMapping("/timeline")
	public ModelAndView timeline() {
		ModelAndView mv = new ModelAndView("resources/timeline");
		return mv;
	}
	
	@RequestMapping("/comingsoon")
	public ModelAndView comingsoon() {
		ModelAndView mv = new ModelAndView("resources/comingsoon");
		return mv;
	}
	
	
	/* *************************************
	 * Company Section
	 * *************************************
	 */
	
	@RequestMapping("/aboutus")
	public ModelAndView aboutus() {
		ModelAndView mv = new ModelAndView("company/aboutus");
		return mv;
	}
	
	@RequestMapping("/whyus")
	public ModelAndView whyus() {
		ModelAndView mv = new ModelAndView("company/whyus");
		return mv;
	}
	
	@RequestMapping("/team")
	public ModelAndView values() {
		ModelAndView mv = new ModelAndView("company/team");
		return mv;
	}
	
	@RequestMapping("/partners")
	public ModelAndView partners() {
		ModelAndView mv = new ModelAndView("company/partners");
		return mv;
	}
	
	@RequestMapping("/portfolio")
	public ModelAndView portfolio() {
		ModelAndView mv = new ModelAndView("company/portfolio");
		return mv;
	}
	
	@RequestMapping("/careers")
	public ModelAndView gallery() {
		ModelAndView mv = new ModelAndView("company/careers");
		return mv;
	}
	
	@RequestMapping("/news")
	public ModelAndView news() {
		ModelAndView mv = new ModelAndView("company/news");
		return mv;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contactus() {
		ModelAndView mv = new ModelAndView("company/contactus");
		return mv;
	}
	
	@RequestMapping("/products/cheetah")
	public ModelAndView cheetah() {
		ModelAndView mv = new ModelAndView("products/cheetah");
		return mv;
	}
	
	/* *************************************
	 * Pricing Section
	 * *************************************
	 */
	@RequestMapping("/sales")
	public ModelAndView sales() {
		ModelAndView mv = new ModelAndView("pricing/pricing");
		return mv;
	}
	
	@RequestMapping("/comparisioncharts")
	public ModelAndView comparisioncharts() {
		ModelAndView mv = new ModelAndView("pricing/comparisioncharts");
		return mv;
	}
	
	/* *************************************
	 * Community Section
	 * *************************************
	 */
	
	@RequestMapping("/blog")
	public ModelAndView blog(HttpSession session,  @RequestParam(value="page", required=false) String page,@RequestParam(value="q", required=false) String q) {
		
		int p;
		if(page==null || page.equals("")) {
			p=1;
		}else {
			p = Integer.parseInt(page);
		}
		BlogActions BA = new BlogActions();
		
		List<BlogForm> blogList = BA.getAllBlogs(p,"p",q, this.jdbcTemplate);
		List<BlogForm> blogcountList = BA.getBlogCounter(p,"p",q, this.jdbcTemplate);
		
		 int countOnPage = 15;
			
		 
	 	
 		int totalCount =  blogcountList.get(0).getBlogCount();
 		double rounder = (double)totalCount/(double) countOnPage;
	 	
	 	int pages = (int) Math.ceil(rounder);
	 	
	 	logger.info("Total count: " + totalCount);
	 	logger.info("Total rounder: " + rounder);
	 	logger.info("Total Pages: " + pages);
		
		
		
		BlogForm blist[][] = new BlogForm[5][3];
		int counter = 0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<3;j++) {
					if(counter<blogList.size()) {
						blist[i][j] = blogList.get(counter);
					}
					counter++;
				}
			}
		
		
		ModelAndView mv = new ModelAndView("showcase/blogs");
		mv.addObject("listCount",blogList.size());
		mv.addObject("bList", blist);
		mv.addObject("blogList", blogList);
		mv.addObject("totalPages",pages);
		mv.addObject("currentPage",1);
		mv.addObject("blogType", "p");
		mv.addObject("query",q);
		return mv;
	
	}
	
	@RequestMapping("/blogs/viewBlog" )
	public ModelAndView getBlogById(HttpSession session, @RequestParam("id") String id, @RequestParam(value="status",required=false) String status) throws SQLException {
		
		BlogActions BA = new BlogActions();
		
		List<BlogForm> blogList = BA.getBlogById(id,status, this.jdbcTemplate);
		List<BlogCategoryForm> blogCatList = BA.getBlogCategories(id,status, this.jdbcTemplate);
		List<BlogCommentForm> blogCommentList = BA.getAllComments(1, jdbcTemplate, id); 
			
		ModelAndView mv = null;
	 	 mv = new ModelAndView("showcase/viewBlog");
		 mv.addObject("blogList",blogList);
		 mv.addObject("blogCatList",blogCatList);
		 mv.addObject("blogCommentList",blogCommentList);
		return mv;
		
		
	
	}
	
	@RequestMapping("/blogs/submitComment" )
	public ModelAndView submitComment(@ModelAttribute("BlogCommentBean") @Valid BlogCommentForm blogCommentBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) throws SQLException {
		
		BlogActions BA = new BlogActions();
		
		BA.addComment(blogCommentBean, jdbcTemplate, session);

		String redirection;
		
			redirection = "redirect:/blogs/viewBlog?id="+blogCommentBean.getID_BLOG();
		
		return new ModelAndView(redirection);
	
	}
	
	
	@RequestMapping(value="/ContactUs/sendMessage", method=RequestMethod.POST)
	public ModelAndView contactUsSendMessage(@ModelAttribute("ContactUsMessageBean") @Valid ContactUsMessageForm contactUsMessageBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) throws SQLException {
		
		String INSERT_CONTACT_MESSAGE_SQL = "INSERT INTO CONTACT_MESSAGE(TX_NAME, TX_EMAIL, TX_PHONE, TX_MESSAGE, TS_TIMESTAMP) VALUES (?,?,?,?,?)";

		Date now = new Date();
		String dt = CalendarUtils.dateToStringDateTimeReadable(now);
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_MESSAGE_SQL);
				preparedStatement.setString(1, contactUsMessageBean.getTX_NAME());
				preparedStatement.setString(2, contactUsMessageBean.getTX_EMAIL());
				preparedStatement.setString(3, contactUsMessageBean.getTX_PHONE());
				preparedStatement.setString(4, contactUsMessageBean.getTX_MESSAGE());
				preparedStatement.setString(5, dt);
				return preparedStatement;
			}
		});
		
		
		String message = "Message from : "+contactUsMessageBean.getTX_NAME()+"<br/>"
				+ "Email: "+contactUsMessageBean.getTX_EMAIL()+"<br/>"
				+ "Phone: "+contactUsMessageBean.getTX_PHONE()+"<br/>"
				+ "<br/>"
				+ "Message: <br/>"
				+ contactUsMessageBean.getTX_MESSAGE();
		
		SendMail sm = new SendMail();
		  
		  
		
		  final String emailMsg = message;
			
		    ExecutorService service = Executors.newFixedThreadPool(4);
		    service.submit(new Runnable() {
		        public void run() {
		        	sm.sendMessage(emailMsg, "info@blinqlabs.com", "support@blinqlabs.com", "Message from " + contactUsMessageBean.getTX_EMAIL(), false);
		        }
		    });
		String redirection;
		
		redirection = "redirect:/contact";
		
		ModelAndView mv = new ModelAndView(redirection);
		mv.addObject("successmessage","Message has been sent.");
		return mv;
	
	}
	
}
