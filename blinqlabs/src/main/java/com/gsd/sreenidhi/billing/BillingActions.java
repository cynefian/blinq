package com.gsd.sreenidhi.billing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.ResourceUtils;

import com.gsd.sreenidhi.admin.invoice.InvoiceControllerForm;
import com.gsd.sreenidhi.admin.invoice.UserAttachmentForm;
import com.gsd.sreenidhi.admin.services.EntitlementActions;
import com.gsd.sreenidhi.admin.services.SENForm;
import com.gsd.sreenidhi.admin.services.SENRatesForm;
import com.gsd.sreenidhi.admin.tickets.WorkLogForm;
import com.gsd.sreenidhi.commons.serviceEntitlement.TierRolloverForm;
import com.gsd.sreenidhi.mail.EmailForm;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.user.UserForm;
import com.gsd.sreenidhi.user.messages.MessageActions;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 

public class BillingActions {

	private static final Logger logger = LoggerFactory.getLogger(BillingActions.class);

	static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
 	static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
 	static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
 	static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
 	static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL, BaseColor.BLUE);
 	static Font grayFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.GRAY);
 	
 	
 	static Calendar tierBalance;
 	static String totalTime;
 	static List<SENRatesForm> senRatesList;
 	static List<WorkLogForm> senActivityList;
 	static String tier = "";
 	static String rate = "";
 	static String limit = "";
    
	public List<ServiceBillingForm> getEligibleBillingCycles(JdbcTemplate jdbcTemplate, String today, String ydayDate) {
		
		
		String GET_ELIGIBLE_RECORDS = "SELECT "
				+ "  SERVICE_BILLING_CYCLE.ID AS ID_SERVICE_BILLING_CYCLE, "
				+ "  SERVICE_BILLING_CYCLE.TS_START_DATE AS TS_START_DATE_SERVICE_BILLING_CYCLE, "
				+ "  SERVICE_BILLING_CYCLE.TS_END_DATE AS  TS_END_DATE_SERVICE_BILLING_CYCLE, "
				+ "  SERVICE_BILLING_CYCLE.FL_COMPLETE AS FL_COMPLETE_SERVICE_BILLING_CYCLE, "
				+ "  SERVICE_ENTITLEMENT.ID AS ID_SERVICE_ENTITLEMENT, "
				+ "  SERVICE_ENTITLEMENT.TX_ENTITLEMENT AS TX_ENTITLEMENT, "
				+ "  SERVICE_ENTITLEMENT.TS_START AS TS_START_ENTITLEMENT, "
				+ "  SERVICE_ENTITLEMENT.TS_END AS TS_END_ENTITLEMENT, "
				+ "  SERVICE_ENTITLEMENT.FL_ACTIVE AS FL_ACTIVE_ENTITLEMENT, "
				+ "  SERVICE_ENTITLEMENT_TYPE.ID AS ID_SERVICE_ENTITLEMENT_TYPE, "
				+ "  SERVICE_ENTITLEMENT_TYPE.TX_ENTITLEMENT_TYPE AS TX_ENTITLEMENT_TYPE, "
				+ "  SERVICE_ENTITLEMENT_TYPE.TX_DESCRIPTION AS TX_ENTITLEMENT_TYPE_DESCRIPTION, "
				+ "  SERVICE_ENTITLEMENT_TYPE.FL_MONTHLY_BILLING AS FL_ENTITLEMENT_TYPE_MONTHLY_BILLING, "
				+ "  ACCOUNTS.ID AS ID_ACCOUNT, "
				+ "  ACCOUNTS.TX_EMAIL AS TX_EMAIL, "
				+ "  USERS.ID AS ID_USER, "
				+ "  USERS.TX_FIRSTNAME AS USER_TX_FIRSTNAME, "
				+ "  USERS.TX_LASTNAME AS USER_TX_LASTNAME "
				+ "FROM "
				+ "  SERVICE_BILLING_CYCLE "
				+ "  JOIN SERVICE_ENTITLEMENT ON SERVICE_ENTITLEMENT.ID = SERVICE_BILLING_CYCLE.ID_ENTITLEMENT "
				+ "  JOIN ACCOUNTS ON ACCOUNTS.ID = SERVICE_ENTITLEMENT.ID_ACCOUNT "
				+ "  JOIN SERVICE_ENTITLEMENT_TYPE ON SERVICE_ENTITLEMENT_TYPE.ID = SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE "
				+ "  JOIN USERS ON USERS.ID_ACCOUNT = ACCOUNTS.ID "
				+ "WHERE "
				+ "  SERVICE_ENTITLEMENT.FL_ACTIVE = 1 "
				+ "  AND SERVICE_ENTITLEMENT_TYPE.FL_MONTHLY_BILLING = 1 "
				+ "  AND SERVICE_BILLING_CYCLE.FL_COMPLETE = 0 "
				+ "  AND coalesce(try_convert(date, SERVICE_BILLING_CYCLE.TS_END_DATE, 103), " + 
				"                convert(date, SERVICE_BILLING_CYCLE.TS_END_DATE) " + 
				"               ) <= '"+ydayDate+"'";
		
		
		
		List<ServiceBillingForm> srvcBillingList = jdbcTemplate.query(GET_ELIGIBLE_RECORDS, new RowMapper<ServiceBillingForm>() {
			public ServiceBillingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				ServiceBillingForm bill = new ServiceBillingForm();
				bill.setID_SERVICE_BILLING_CYCLE(resulSet.getInt("ID_SERVICE_BILLING_CYCLE"));
				bill.setTS_START_DATE_SERVICE_BILLING_CYCLE(resulSet.getString("TS_START_DATE_SERVICE_BILLING_CYCLE"));
				bill.setTS_END_DATE_SERVICE_BILLING_CYCLE(resulSet.getString("TS_END_DATE_SERVICE_BILLING_CYCLE"));
				bill.setFL_COMPLETE_SERVICE_BILLING_CYCLE(resulSet.getInt("FL_COMPLETE_SERVICE_BILLING_CYCLE"));
				bill.setID_SERVICE_ENTITLEMENT(resulSet.getInt("ID_SERVICE_ENTITLEMENT"));
				bill.setTX_ENTITLEMENT(resulSet.getString("TX_ENTITLEMENT"));
				bill.setTS_START_ENTITLEMENT(resulSet.getString("TS_START_ENTITLEMENT"));
				bill.setTS_END_ENTITLEMENT(resulSet.getString("TS_END_ENTITLEMENT"));
				bill.setFL_ACTIVE_ENTITLEMENT(resulSet.getInt("FL_ACTIVE_ENTITLEMENT"));
				bill.setID_SERVICE_ENTITLEMENT_TYPE(resulSet.getInt("ID_SERVICE_ENTITLEMENT_TYPE"));
				bill.setTX_ENTITLEMENT_TYPE(resulSet.getString("TX_ENTITLEMENT_TYPE"));
				bill.setTX_ENTITLEMENT_TYPE_DESCRIPTION(resulSet.getString("TX_ENTITLEMENT_TYPE_DESCRIPTION"));
				bill.setFL_ENTITLEMENT_TYPE_MONTHLY_BILLING(resulSet.getInt("FL_ENTITLEMENT_TYPE_MONTHLY_BILLING"));
				bill.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
				bill.setTX_EMAIL(resulSet.getString("TX_EMAIL"));
				bill.setID_USER(resulSet.getInt("ID_USER"));
				bill.setTX_FIRSTNAME(resulSet.getString("USER_TX_FIRSTNAME"));
				bill.setTX_LASTNAME(resulSet.getString("USER_TX_LASTNAME"));
				
				return bill;
			}
		});
		
		logger.info("Eligible Invoicing Records: " + srvcBillingList.size());
		return srvcBillingList;
	}

	public void generateInvoice(JdbcTemplate jdbcTemplate, ServiceBillingForm serviceBillingForm, String today) {
		
		logger.info("Generating Invoices");
		// TODO Get hours
		EntitlementActions ea = new EntitlementActions();
		senActivityList = ea.getEntitlementActivities(null, jdbcTemplate, Integer.toString(serviceBillingForm.getID_SERVICE_ENTITLEMENT()), serviceBillingForm.getTS_START_DATE_SERVICE_BILLING_CYCLE(), serviceBillingForm.getTS_END_DATE_SERVICE_BILLING_CYCLE());
		
		
		Calendar c = Calendar.getInstance();
 		c.set(0, 0, 0, 0, 0, 0);
 		int hours=0;
 		int mins=0;
 		
 		for(int i=0;i<senActivityList.size();i++) {
 			String log = senActivityList.get(i).getTX_WORKLOG();
 			String[] logarr = log.split(" ");
 			for(int j=0;j<logarr.length;j++) {
 				if(logarr[j].contains("w")) {
 					String unit =logarr[j].replace("w", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*40;
 				}else if(logarr[j].contains("W")) {
 					String unit =logarr[j].replace("W", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*40;
 				}else if(logarr[j].contains("d")) {
 					String unit =logarr[j].replace("d", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*8;
 				}else if(logarr[j].contains("D")) {
 					String unit =logarr[j].replace("D", ""); 
 					hours = hours + Integer.parseInt(unit.trim())*8;
 				}else if(logarr[j].contains("h")) {
 					String unit =logarr[j].replace("h", ""); 
 					hours = hours + Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("H")) {
 					String unit =logarr[j].replace("H", ""); 
 					hours = hours + Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("m")) {
 					String unit =logarr[j].replace("m", "");
 					mins = mins+Integer.parseInt(unit.trim());
 				}else if(logarr[j].contains("M")) {
 					String unit =logarr[j].replace("M", ""); 
 					mins = mins+Integer.parseInt(unit.trim());
 				}
 			}
 		}
 		
 		int hourappend = 0;
 		int minremain = 0;
 		if(mins>=60) {
 			hourappend = mins/60;
 			minremain = mins-(hourappend*60);
 		}else {
 			minremain = mins;
 		}
 		
 		hours = hours + hourappend;
 		
 		totalTime = hours +" hours, "+ minremain + " minutes";
 		
 		//TODO Remove hours from rollover usage
 		//Retrieving Rollover hours
		List<TierRolloverForm> senRollover = ea.getEntitlementRollOvers(jdbcTemplate, Integer.toString(serviceBillingForm.getID_SERVICE_ENTITLEMENT()));
		int availRolloverHours = 0;
		int totalRolloverHours = 0;
		if(senRollover!=null && senRollover.size()>0) {
			for(int i=0;i<senRollover.size();i++) {
				availRolloverHours = availRolloverHours + senRollover.get(i).getNUM_ROLLOVER_BALANCE();
				totalRolloverHours = totalRolloverHours + senRollover.get(i).getNUM_ROLLOVER_ORIG();
			}
		}
		
		
		//billing hours = logged hours - rollover hours
 		int billingHours = hours - totalRolloverHours ;
 		int bill = 0;
 		if(billingHours<0) {
 			bill = 0;
 		}else{
 			bill = billingHours;
 		}
 		
 		if(minremain>0) {
 			bill = bill +1;
 		}
		
		
 		
		// TODO Get tiers
 		senRatesList = ea.getSENRates(null, jdbcTemplate, Integer.toString(serviceBillingForm.getID_SERVICE_ENTITLEMENT()));
		// TODO Calculate rate and cost
		
 		for(int i=0;i<senRatesList.size();i++) {
 			if(i<(senRatesList.size()-1)) {
 				if((bill < Integer.parseInt(senRatesList.get(i).getTX_LIMIT()))
 						|| (bill == Integer.parseInt(senRatesList.get(i).getTX_LIMIT())
 		 						&& minremain == 0)){
 					tier =  senRatesList.get(i).getTX_TIER();
 					rate = senRatesList.get(i).getTX_COST();
 					limit = senRatesList.get(i).getTX_LIMIT();
 					break;
 	 			}
 			}else {
 				tier =  senRatesList.get(i).getTX_TIER();
				rate = senRatesList.get(i).getTX_COST();
				limit = senRatesList.get(i).getTX_LIMIT();
				break;
 			}
 		}
 		
 		
 		int negateHours = 0 - bill;
 		int negateMins = 0 - minremain;
 		String tierBln = "";
 		if(!"".equals(limit) && limit != null) {
 			try {
				tierBalance  = CalendarUtils.addTime(Integer.parseInt(limit), 0, negateHours, negateMins);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
 			 tierBln = tierBalance.get(Calendar.HOUR) + " hours " + tierBalance.get(Calendar.MINUTE) + "mins";
 		}else {
 			 tierBln = Integer.toString(bill) + " hours";
 		}
 		
 		
 		//PDF
 		    Document document = new Document();
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    PdfWriter writer;
            try {
                writer = PdfWriter.getInstance(document, out);
                document.open();
	            addMetaData(document);
	            addTitlePage(document, serviceBillingForm.getTX_EMAIL());
	            addInfoPage(document);
	            addContent(document);
	            document.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
        	// insert attachment
            
            String encodedAttachment = "data:application/pdf;base64,"+Base64.getEncoder().encodeToString(out.toByteArray());
            final KeyHolder attachKey = new GeneratedKeyHolder();
            String INSERT_BILLING_ATTACHMENT = "INSERT INTO ATTACHMENTS(TX_ATTACHMENT, TX_NAME, TX_TYPE, TX_SIZE) VALUES (?,?,?,?)";
            jdbcTemplate.update(new PreparedStatementCreator() {
    			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
    				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BILLING_ATTACHMENT,
    						Statement.RETURN_GENERATED_KEYS);
    				preparedStatement.setString(1, encodedAttachment);
    				preparedStatement.setString(2, serviceBillingForm.getTX_ENTITLEMENT()+"_"+serviceBillingForm.getTS_END_DATE_SERVICE_BILLING_CYCLE().replace(" ", "").replace("/", "").replace(":", "")+".pdf");
    				preparedStatement.setString(3, "application/pdf");
    				preparedStatement.setString(4, Integer.toString(out.size()));
    				return preparedStatement;
    			}
    		}, attachKey);
    		
    		final int attachId = attachKey.getKey().intValue();
    		
    		final String INSERT_SEN_ATTACHMENT_SQL = "INSERT INTO SERVICE_BILLING_ATTACHMENTS(ID_SERVICE_BILLING_CYCLE, ID_ATTACHMENT) VALUES (?, ?)";
    		jdbcTemplate.update(new PreparedStatementCreator() {
    			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
    				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SEN_ATTACHMENT_SQL);
    				preparedStatement.setInt(1, serviceBillingForm.getID_SERVICE_BILLING_CYCLE());
    				preparedStatement.setInt(2, attachId);
    				return preparedStatement;
    			}
    		});
            
		//Update billing cycle
		String UPDATE_SERVICE_BILLING = "UPDATE SERVICE_BILLING_CYCLE SET FL_COMPLETE = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_BILLING);
				preparedStatement.setInt(1, 1);
				preparedStatement.setInt(2, serviceBillingForm.getID_SERVICE_BILLING_CYCLE());
				return preparedStatement;
			}
		});
		
		
		//Update Rollover
		final String INSERT_TIER_ROLLOVER_SQL = "INSERT INTO SERVICE_TIER_ROLLOVER(ID_SERVICE_BILLING_CYCLE, NUM_ROLLOVER, NUM_BALANCE, FL_ACTIVE) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TIER_ROLLOVER_SQL);
				preparedStatement.setInt(1, serviceBillingForm.getID_SERVICE_BILLING_CYCLE());
				preparedStatement.setInt(2, (!"".equals(tierBalance) && tierBalance!=null)?tierBalance.get(Calendar.HOUR):0);
				preparedStatement.setInt(3, (!"".equals(tierBalance) && tierBalance!=null)?tierBalance.get(Calendar.HOUR):0);
				preparedStatement.setInt(4, 1);
				return preparedStatement;
			}
		});
		
		
		if(serviceBillingForm!=null) {
			int userId = serviceBillingForm.getID_USER();
			String userEmail = serviceBillingForm.getTX_EMAIL();
			
			String hrefRedirect = fetchURLProperties() + "/user/orderInvoice";
			MessageActions ma = new MessageActions();
			BillingForm bf = new BillingForm();
			ma.insertMessage(jdbcTemplate, "New Service Report", bf.getSYSTEM_MESSAGE(), userId, 2, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
			
			EmailForm ef = new EmailForm();
			String tempalte = ef.getEmailTemplate();
			
			String emailMessage = bf.getEMAIL_MESSAGE(); 
			
			String msg = tempalte.replace("${username}", userEmail);
			msg = msg.replace("${title}", "There's an update for you.");
			msg = msg.replace("${message}", emailMessage);
			msg = msg.replace("${image}", bf.getImage());
			msg = msg.replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
			msg = msg.replace("${disclaimer}", ef.getDisclaimer());
			if(!"".equals(userEmail)) {
				SendMail sm = new SendMail();
				
				final String emailMsg = msg;
				
			    ExecutorService service = Executors.newFixedThreadPool(4);
			    service.submit(new Runnable() {
			        public void run() {
			        	sm.sendMessage(emailMsg,userEmail, null, "Blinqlabs Update - You have a new Service Report.", false);
			        }
			    });
			    
				  
			}
		}
		
		
		
		
		
	}
	
	
	  private static void addMetaData(Document document) {
          document.addTitle("Monthly Report");
          document.addSubject("Entitlement Report");
          document.addKeywords("Java, PDF, iText");
          document.addAuthor("Blinqlabs");
          document.addCreator("Blinqlabs");
      }
    
	  private static void addTitlePage(Document document, String account)
	            throws DocumentException {
	       
		  Paragraph main = new Paragraph();
		  main.setAlignment(Element.ALIGN_CENTER);
	        
		   Image img = null;
			try {
				//img = Image.getInstance(fetchURLProperties()+"/resources/img/logos/white-logo-only.png");
				img = Image.getInstance(fetchURLProperties()+"/resources/images/report/report-cover.png");
			} catch (BadElementException | IOException e) {
				e.printStackTrace();
			}
			if(img!=null) {
				img.scalePercent(90f);
				main.add(img);
				main.setAlignment(Element.ALIGN_CENTER);
				addEmptyLine(main, 1);
			}
		
	        
		  
	        Paragraph preface = new Paragraph();
	        preface.setAlignment(Paragraph.ALIGN_CENTER);
	        
	        addEmptyLine(main, 5);
	        
	        Paragraph title = new Paragraph("Entitlement Report", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        
	        preface.add(title);

	        addEmptyLine(preface, 4);
	        // Will create: Report generated by: _name, _date
	        preface.add(new Paragraph("Report Date: " + new Date(), smallBold));
	        preface.add(new Paragraph("Account: " + account, smallBold));
	        addEmptyLine(preface, 10);
	       

	        Paragraph link = new Paragraph("https://blinqlabs.com", blueFont);
	        link.setAlignment(Paragraph.ALIGN_CENTER);
	        
	        preface.add(link);
	        preface.setAlignment(Element.ALIGN_CENTER);
			
	        document.add(main);
	        document.add(preface);
	        document.newPage();
	        
	        
	        Paragraph note = new Paragraph("This is an automated report. Please contact support if you have any concerns.",grayFont);
	        note.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(note);
	        document.newPage();
	    }

	    private static void addContent(Document document) throws DocumentException {
	    	
	    	Paragraph preface = new Paragraph();
	        preface.setAlignment(Paragraph.ALIGN_CENTER);
	        preface.add(new Paragraph("Worklog", catFont));

	        // add a table
	        createTable(document);
	    }

	    private static void addInfoPage(Document document) throws DocumentException {
	    	
	    	 	Paragraph preface = new Paragraph();
		        preface.setAlignment(Paragraph.ALIGN_LEFT);
		        // We add one empty line
		        addEmptyLine(preface, 1);
		        // Lets write a big header
		        preface.add(new Paragraph("Hour Usage: " + totalTime, smallBold));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph("Current Tier: " + tier, smallBold));
		        addEmptyLine(preface, 1);
		        preface.add(new Paragraph("Tier Rate: $" + rate, smallBold));
		        addEmptyLine(preface, 2);
		        if(!"".equals(tierBalance)&& tierBalance!=null) {
		        	preface.add(new Paragraph("Balance Hours from Tier: " + tierBalance.get(Calendar.HOUR) + " hours " + tierBalance.get(Calendar.MINUTE) + "mins", smallBold));
		        }else {
		        	preface.add(new Paragraph("Balance Hours from Tier: " + 0 + " hours " + 0 + "mins", smallBold));
		        }
		        
		        addEmptyLine(preface, 3);
		        
		        preface.add(new Paragraph("", redFont));
		        try {
					document.add(preface);
				} catch (DocumentException e) {
					e.printStackTrace();
				}

		        
		        preface = new Paragraph();
		        preface.setAlignment(Paragraph.ALIGN_CENTER);
		        PdfPTable table = new PdfPTable(3);

		        // t.setBorderColor(BaseColor.GRAY);
		        // t.setPadding(4);
		        // t.setSpacing(4);
		        // t.setBorderWidth(1);

		        PdfPCell c1 = new PdfPCell(new Phrase("Tier Name"));
		        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c1);

		        c1 = new PdfPCell(new Phrase("Rate"));
		        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c1);

		        c1 = new PdfPCell(new Phrase("Limit(s)"));
		        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(c1);
		        table.setHeaderRows(1);

		        for(int i=0;i<senRatesList.size();i++) {
		        	table.addCell(senRatesList.get(i).getTX_TIER());
		        	table.addCell(senRatesList.get(i).getTX_COST());
		        	table.addCell(senRatesList.get(i).getTX_LIMIT());
		        }

		        document.add(table);
		        
		        // Start a new page
		        document.newPage();
	    	
	    }
	    
	    private static void createTable(Document document)
	            throws DocumentException {
	        PdfPTable table = new PdfPTable(3);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);

	        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Ticket"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Log"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        
	        c1 = new PdfPCell(new Phrase("Description"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        table.setHeaderRows(1);

	        
	        for (int i=0;i<senActivityList.size();i++) {
	        	table.addCell(senActivityList.get(i).getTX_DATE());
	        	table.addCell(Integer.toString(senActivityList.get(i).getID_TICKET()));
	        	table.addCell(senActivityList.get(i).getTX_WORKLOG());
	        	table.addCell(senActivityList.get(i).getTX_DESCRIPTION());
	        }
	        
	        document.add(table);

	    }

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	    
	    
		public static String fetchURLProperties() {
			
			String baseUrl = "";
			Properties properties = new Properties();
			try {
				File file = ResourceUtils.getFile("classpath:application.properties");
				InputStream in = new FileInputStream(file);
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(properties!=null) {
				baseUrl =  properties.getProperty("application.url");
			}
			return baseUrl;
		}

		public List<ServiceBillingForm> getEligibleExpiredCycles(JdbcTemplate jdbcTemplate, String expiry) {
			String GET_EXPIRED_RECORDS = "SELECT SERVICE_TIER_ROLLOVER.ID AS ID_SERVICE_TIER_ROLLOVER, "
					+ "SERVICE_BILLING_CYCLE.ID AS ID_SERVICE_BILLING_CYCLE, "
					+ "SERVICE_BILLING_CYCLE.ID_ENTITLEMENT AS ID_ENTITLEMENT "
					+ "FROM SERVICE_TIER_ROLLOVER "
					+ "JOIN SERVICE_BILLING_CYCLE ON SERVICE_BILLING_CYCLE.ID = SERVICE_TIER_ROLLOVER.ID_SERVICE_BILLING_CYCLE "
					+ "WHERE  SERVICE_TIER_ROLLOVER.FL_ACTIVE = 1 "
					+ "AND coalesce(try_convert(date, SERVICE_BILLING_CYCLE.TS_END_DATE, 103), "
					+ "	convert(date, SERVICE_BILLING_CYCLE.TS_END_DATE) "
					+ "	 ) <= '"+expiry+"'";
			
			List<ServiceBillingForm> srvcBillingList = jdbcTemplate.query(GET_EXPIRED_RECORDS, new RowMapper<ServiceBillingForm>() {
				public ServiceBillingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					ServiceBillingForm bill = new ServiceBillingForm();
					bill.setID_SERVICE_BILLING_CYCLE(resulSet.getInt("ID_SERVICE_BILLING_CYCLE"));
					bill.setID_SERVICE_TIER_ROLLOVER(resulSet.getInt("ID_SERVICE_TIER_ROLLOVER"));
					bill.setID_SERVICE_ENTITLEMENT(resulSet.getInt("ID_SERVICE_BILLING_CYCLE"));
					
					return bill;
				}
			});
							
			return srvcBillingList;
		}

		public void setServiceExpiry(JdbcTemplate jdbcTemplate, ServiceBillingForm serviceBillingForm) {
			String UPDATE_SRVC_EXPIRY = "UPDATE SERVICE_TIER_ROLLOVER SET FL_ACTIVE = ? WHERE ID = ?";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SRVC_EXPIRY);
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, serviceBillingForm.getID_SERVICE_TIER_ROLLOVER());
					
					return preparedStatement;
				}
			});
			
			if(serviceBillingForm!=null) {
				int userId = serviceBillingForm.getID_USER();
				String userEmail = serviceBillingForm.getTX_EMAIL();
				
				MessageActions ma = new MessageActions();
				BillingForm bf = new BillingForm();
				ma.insertMessage(jdbcTemplate, "Hours Expired", bf.getEXPIRED_ROLLOVER_MESSAGE(), userId, 2, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
				
				EmailForm ef = new EmailForm();
				String tempalte = ef.getEmailTemplate();
				
				String emailMessage =  bf.getGENERIC_EMAIL_MESSAGE();
				
				String msg = tempalte.replace("${username}", userEmail);
				msg = msg.replace("${title}", "There's an update for you.");
				msg = msg.replace("${message}", emailMessage);
				msg = msg.replace("${image}", bf.getImage());
				msg = msg.replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
				msg = msg.replace("${disclaimer}", ef.getDisclaimer());
				if(!"".equals(userEmail)) {
					SendMail sm = new SendMail();
					  
					  final String emailMsg = msg;
						
					    ExecutorService service = Executors.newFixedThreadPool(4);
					    service.submit(new Runnable() {
					        public void run() {
					        	sm.sendMessage(emailMsg,userEmail, null, "Blinqlabs Update - You have a new message.", false);
					        }
					    });
				}
			}
			logger.info("Service Rollover Expiry update successful.");
			
			
		}

		public List<SENForm> getExpiredSEN(JdbcTemplate jdbcTemplate, String today) {
			String GET_EXPIRED_RECORDS = "SELECT ID FROM SERVICE_ENTITLEMENT "
					+ "WHERE FL_ACTIVE = 1 " 
					+ "AND coalesce(try_convert(date, TS_END, 103), "
					+ "	convert(date, TS_END) "
					+ "	 ) <= '"+today+"'";
			List<SENForm> expiredSenList = jdbcTemplate.query(GET_EXPIRED_RECORDS, new RowMapper<SENForm>() {
				public SENForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					SENForm sen = new SENForm();
					sen.setID(resulSet.getInt("ID"));
					return sen;
				}
			});
			return expiredSenList;
		}
		
		
		public void setSENExpiry(JdbcTemplate jdbcTemplate, int id) {
			String UPDATE_SEN_EXPIRY = "UPDATE SERVICE_ENTITLEMENT SET FL_ACTIVE = ? WHERE ID = ?";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEN_EXPIRY);
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, id);
					
					return preparedStatement;
				}
			});
			
			
			String getUserAccount = "SELECT "
					+ " USERS.ID AS ID_USER, "
					+ " ACCOUNTS.TX_EMAIL AS TX_EMAIL"
					+ " FROM SERVICE_ENTITLEMENT "
					+ " JOIN ACCOUNTS ON ACCOUNTS.ID = SERVICE_ENTITLEMENT.ID_ACCOUNT"
					+ " JOIN USERS ON USERS.ID_ACCOUNT = ACCOUNTS.ID_ACCOUNT"
					+ " AND SERVICE_ENTITLEMENT.ID = " + id;
			
			List<UserForm> userAttachFormList = jdbcTemplate.query(getUserAccount, new RowMapper<UserForm>() {
				public UserForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					UserForm ua = new UserForm();
					ua.setEmail(resulSet.getString("TX_EMAIL"));
					ua.setId(resulSet.getInt("ID_USER"));
					return ua;
				}
			});
			
				String userEmail= userAttachFormList.get(0).getEmail();
				int userId  = userAttachFormList.get(0).getId();
				
				MessageActions ma = new MessageActions();
				BillingForm bf = new BillingForm();
				ma.insertMessage(jdbcTemplate, "Service Expirey", bf.getEXPIRED_SEN_MESSAGE(), userId, 2, 0, 0, CalendarUtils.dateToString(new Date()), null, null, 0, null);
				
				EmailForm ef = new EmailForm();
				String tempalte = ef.getEmailTemplate();
				
				String emailMessage = bf.getGENERIC_EMAIL_MESSAGE(); 
				
				String msg = tempalte.replace("${username}", userEmail);
				msg = msg.replace("${title}", "There's an update for you.");
				msg = msg.replace("${message}", emailMessage);
				msg = msg.replace("${image}", bf.getImage());
				msg = msg.replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
				msg = msg.replace("${disclaimer}", ef.getDisclaimer());
				if(!"".equals(userEmail)) {
					SendMail sm = new SendMail();
					final String emailMsg = msg;
					
				    ExecutorService service = Executors.newFixedThreadPool(4);
				    service.submit(new Runnable() {
				        public void run() {
				        	sm.sendMessage(emailMsg,userEmail, null, "Blinqlabs Update - You have a new message.", false);
				        }
				    });
				    
					  
				}
			
				logger.info("Service Entitlement Expiry update successful.");
		}


}
