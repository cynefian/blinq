package com.gsd.sreenidhi.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.subscriptions.SubscriberController;
import com.gsd.sreenidhi.system.properties.PropertiesBean;
import com.gsd.sreenidhi.system.properties.PropertyConfiguration;

@Configuration
@PropertySource("classpath:application.properties")
public class SendMail{
	
		   private Thread t;
		   private String emailMessage;
		   private String rcptEmail;
		   private String copyEmail;
		   private String emailSubject;
		   private boolean sendToAdminFlag;
		   
		      
	public void sendMessage(String message, String toEmail, String ccEmail, String subject, boolean sendToAdmin) {
		
		final Logger l = LoggerFactory.getLogger(SendMail.class);
		
		emailMessage = message;
   		rcptEmail = toEmail;
   		copyEmail = ccEmail;
   		emailSubject = subject;
   		sendToAdminFlag = sendToAdmin;
   		l.info("Attempting to send email message.");

		
		PropertiesBean appProperties;
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(PropertyConfiguration.class, PropertiesBean.class);) {
				appProperties = context.getBean(PropertiesBean.class);
	         	l.info("Property configuration has been loaded " + appProperties.toString());
	        }
		
		
		try {
			
			Properties props = new Properties();

			props.put("mail.smtp.host", appProperties.getSmtpHost());// for gmail use smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", appProperties.getSmtpPort());
			props.put("mail.smtp.socketFactory.port", appProperties.getSmtpPort());
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "true");

			final String user = appProperties.getSmtpUsername();
			String pwd = appProperties.getSmtpPassword();
			
			final String passwd = pwd;
			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, passwd);
				}
			});

			mailSession.setDebug(true); // Enable the debug mode

			MimeMessage msg = new MimeMessage(mailSession);

			// --[ Set the FROM, TO, DATE and SUBJECT fields
			msg.setFrom(new InternetAddress(appProperties.getFromEmail()));
			
			String[] toReceipients = rcptEmail.split(";");
			for (String addr : toReceipients) {
				msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(addr.trim()));
			}

	
			if (copyEmail != null && !"".equalsIgnoreCase(copyEmail)) {

				String[] ccReceipients = copyEmail.split(";");
				for (String addr : ccReceipients) {
					msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(addr.trim()));
				}
			}

			msg.setSentDate(new Date());
			if (emailSubject != null && !"".equalsIgnoreCase(emailSubject)) {
				msg.setSubject(emailSubject);
			} else {
				msg.setSubject(appProperties.getDefaultSubject());
			}

			// --[ Create the body of the mail
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(emailMessage, "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);

			// --[ Ask the Transport class to send our mail message
			
			Transport transport = mailSession.getTransport("smtp");
	        transport.connect(appProperties.getSmtpHost(), appProperties.getFromEmail(), appProperties.getFromPassword());
	        
	        transport.sendMessage(msg, msg.getAllRecipients());
	        transport.close();
	         
	        l.info("Message Sent Successfully.");
			
					

		} catch (Exception e) {
			
		}
	
	}
}
