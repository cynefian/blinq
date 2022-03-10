package com.gsd.sreenidhi.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.gsd.sreenidhi.admin.features.FeatureController;
import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.forms.Constants;
import com.gsd.sreenidhi.mail.SendMail;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;

/**
 * This is the logger class
 * 
 * @author Sreenidhi, Gundlupet
 *
 */
public class Log {
	protected String applicationName;

	private static final Logger log = LoggerFactory.getLogger(Log.class);
	
	 private static final Marker MARKER = MarkerFactory.getMarker("myMarker");

	    void logSimpleMessage() {
	        // This sends a simple event to Sentry
	    	log.error("This is a test");
	    }
	    

	public Log(String app_name) {
		// COnstructor
	}

	
	 @SuppressWarnings("deprecation")
	void logWithBreadcrumbs() {
	        // Record a breadcrumb that will be sent with the next event(s),
	        // by default the last 100 breadcrumbs are kept.
		
		  Sentry.record( new
		  BreadcrumbBuilder().setMessage("User made an action").build() );
		 

	        // This sends a simple event to Sentry
	        log.error("This is a test");
	    }

	    void logWithTag() {
	        // This sends an event with a tag named 'logback-Marker' to Sentry
	    	log.error(MARKER, "This is a test");
	    }

	    void logWithExtras() {
	        // MDC extras
	        MDC.put("extra_key", "extra_value");
	        // This sends an event with extra data to Sentry
	        log.error("This is a test");
	    }
	    
	/*
	 * void logWithGlobalTag() { LoggerContext context =
	 * (LoggerContext)LoggerFactory.getILoggerFactory(); // This adds a tag named
	 * 'logback-Marker' to every subsequent Sentry event context.putProperty(MARKER,
	 * "This is a test");
	 * 
	 * // This sends an event to Sentry, and a tag named 'logback-Marker' will be
	 * added. log.info("This is a test"); }
	 * 
	 * void addGlobalExtras() { LoggerContext context =
	 * (LoggerContext)LoggerFactory.getILoggerFactory(); // This adds extra data to
	 * every subsequent Sentry event context.putProperty("extra_key",
	 * "extra_value");
	 * 
	 * // This sends an event to Sentry, and extra data ("extra_key", "extra_value")
	 * will be added. log.info("This is a test"); }
	 */

	    void logException() {
	        try {
	            unsafeMethod();
	        } catch (Exception e) {
	            // This sends an exception event to Sentry
	        	log.error("Exception caught", e);
	        }
	    }

	    void unsafeMethod() {
	        throw new UnsupportedOperationException("You shouldn't call this!");
	    }
	    
	    
	/**
	 * This method is used to Log messages
	 * 
	 * @param e
	 *            Exception
	 * @param location
	 *            The location (Class) which generated the log message
	 * @param message
	 *            The actual log message
	 * @param priority
	 *            Message priority: FATAL, ERROR, WARN, INFO
	 * @throws FabricsException
	 *             Generic FABRICS Exception Object that handles all exceptions
	 */
	public void logMessage(Exception e, String location, String message, String priority)
			throws SystemException {

		
		String exceptionStackTrace;
		String toEmailAddress = "mbfs_devops@daimler.com";

		if (e != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			log.error("Exception: " + e.getMessage() + "\n" + getExceptionTrace(e));
			exceptionStackTrace = sw.toString();

		} else {
			exceptionStackTrace = "\n------- STACK TRACE NOT AVAILABLE ----------- \n "
					+ "Please contact the DevOps Administrator for Assistance.";
		}
		
		switch (priority) {
		case "fatal":
			log.error(location + ":" + message, e);
			break;
		case "error":
			log.error(location + ":" + message, e);
			break;
		case "warn":
			log.warn(location + ":" + message, e);
			break;
		case "info":
			log.info(location + ":" + message, e);
			break;
		case "debug":
			log.debug(location + ":" + message, e);
			break;
		default:
			log.warn(location + ":" + message, e);
			break;
		}
	//	DBExecutor.recordLog(location, message, false);
	}
	
	/**
	 * This method is used to Log messages
	 * 
	 * @param e
	 *            Exception
	 * @param location
	 *            The location (Class) which generated the log message
	 * @param message
	 *            The actual log message
	 * @param priority
	 *            Message priority: FATAL, ERROR, WARN, INFO
	 * @param sendMail
	 *            Boolean flag that checks whether an email message needs to be send
	 *            for the message being logged.
	 * @throws FabricsException
	 *             Generic FABRICS Exception Object that handles all exceptions
	 */
	public void logMessage(Exception e, String location, String message, String priority, boolean sendMail)
			throws SystemException {

		
		String exceptionStackTrace;
		String toEmailAddress = Constants.smtpToEmail;

		if (e != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			log.error("Exception: " + e.getMessage() + "\n" + getExceptionTrace(e));
			exceptionStackTrace = sw.toString();

		} else {
			exceptionStackTrace = "\n------- STACK TRACE NOT AVAILABLE ----------- \n "
					+ "Please contact the DevOps Administrator for Assistance.";
		}
		if (System.getenv("OVERRIDE_CAPABILITIES").equalsIgnoreCase("FALSE") && Constants.sendMail && sendMail) {
			String mailBody = "<body bgcolor=\"#F2F2F2\">"
					+ "<table bgcolor=\"#FFFFFF\" border=\"1\" width=\"90%\" align=\"center\" style=\"table-layout: fixed\">"
					+ "<tr bgcolor=\"#205081\">" + "<th style=\"width=100%;\"  align=\"left\">"
					+ "<span style=\"color:#FFFFFF\">" + "Mercedes-Benz Financial Services" + "<br />DevOps Framework"
					+ "<br />" + "<br />FABRICS - "
					+ "<br />Framework for Automated testing of Business Requirements Integrated with Cucumber and Selenium"
					+ "<br /> " + "</span>" + "</th>" + "</tr>" + "<tr>" + "<td style=\"width=100%;\" align=\"left\">"
					+ "<br />Hello!" + "<br />"
					+ "<table style=\"margin-left:1cm; margin-right:1cm;\" cellpadding=\"10\" style=\"table-layout: fixed; width: 90%\">"
					+ "<tr>" + "<th valign=\"top\" width=\"25%\" bgcolor=\"#F2F5A9\" align=\"left\">" + "Message:"
					+ "</th>" + "<td style=\"display: inline;\" width=\"75%\" align=\"left\">" + message + "</td>"
					+ "</tr>"

					+ "<tr>" + "<th valign=\"top\" width=\"25%\" bgcolor=\"#F2F5A9\" align=\"left\">"
					+ "Source Location:" + "</th>" + "<td style=\"display: inline;\" width=\"75%\" align=\"left\">"
					+ location + "</td>" + "</tr>"

					+ "<tr>" + "<th valign=\"top\" width=\"25%\" bgcolor=\"#F2F5A9\" align=\"left\">" + "Stack Trace:"
					+ "</th>" + "<td width=\"75%\" style=\"word-wrap: break-word\">" + "<div>" + exceptionStackTrace
					+ "</div>" + "</td>" + "</tr>" + "</table>"

					+ "<br />" + "<br />"

					+ "</td>" + "</tr>"

					+ "<tr bgcolor=\"#205081\">" + "<td style=\"width=100%;\" align=\"left\">"
					+ "<span style=\"color:#FFFFFF;\">" + "<br /> -- Jira Mail Handler" + "<br />DevOps" + "</span>"
					+ "</td>" + "</tr>"

					+ "</table>" + "<table  width=\"90%\" align=\"center\">" + "<tr align=\"center\">"
					+ "<td style=\"width=100%; font-size:12px; color:#424242\">"
					+ "<br />This is an automated message. " + "<br />Please do not reply to this message. "
					+ "<br />Please contact your <B>DevOps Administrator</B> if you require assistance."

					+ "</td>" + "</tr>" + "</table>" + "</body>";
		//	email.sendMessage(mailBody, toEmailAddress, null, Constants.smtpEmailSubject, true);
		}

		switch (priority) {
		case "fatal":
			log.error(location + ":" + message, e);
			break;
		case "error":
			log.error(location + ":" + message, e);
			break;
		case "warn":
			log.warn(location + ":" + message, e);
			break;
		case "info":
			log.info(location + ":" + message, e);
			break;
		case "debug":
			log.debug(location + ":" + message, e);
			break;
		default:
			log.warn(location + ":" + message, e);
			break;
		}
	//	DBExecutor.recordLog(location, message, sendMail);
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}

	

	public void breakLine() {
		log.info("");
	}

	
	/**
	 * @param e
	 *            Exception
	 * @return Exception Trace String
	 */
	public String getExceptionTrace(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.ORIGINAL_EXCEPTION_STRING);
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append("\t\t");
			sb.append(element.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
