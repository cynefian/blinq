/**
 * Created on May 15, 2017
 */
package com.gsd.sreenidhi.exceptions;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

//import org.apache.maven.shared.invoker.MavenInvocationException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.DOMException;

import com.gsd.sreenidhi.forms.Constants;
import com.gsd.sreenidhi.logging.Log;

//import com.sun.deploy.net.proxy.ProxyConfigException;
//import com.sun.deploy.net.proxy.ProxyUnavailableException;

/**
 * This is the Core Exception Class for FABRICS Exception handling
 *
 * @author Sreenidhi, Gundlupet
 *
 */
@SuppressWarnings("serial")
public class SystemException extends Exception {
	/**
	 * Root cause Exception
	 */
	private Exception exception;
	private String errorCode = "Unknown_Exception";
	public static Logger logger;
	public String exceptionMessage = "";
	
	private static final String CAUSE = "Cause: ";

	/**
	 * Constructor for the FabricsException object.
	 *
	 */
	public SystemException() {
		super();
	}

	/**
	 * Creates a new FabricsException wrapping another exception, and with no detail
	 * message.
	 * 
	 * @param exception
	 *            the wrapped exception.
	 * @throws ExceptionHandler
	 *             Generic FABRICS Exception Object that handles all exceptions
	 */
	public SystemException(Exception exception, HttpSession session) throws SystemException {

		this.exception = exception;
		logger = LoggerFactory.getLogger(SystemException.class);
		
		String cause = (exception.getCause() != null) ? exception.getCause().toString() : "Unknown Cause";

		if (exception instanceof IllegalArgumentException) {
			exceptionMessage = "IllegalArgumentException ====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof IllegalAccessException) {
			exceptionMessage = "IllegalAccessException ====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof FileNotFoundException) {
			exceptionMessage = "FileNotFoundException ====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof UnknownHostException) {
			exceptionMessage = "UnknownHostException ====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof SQLException) {
			exceptionMessage = "SQL Exception ====>" + CAUSE + cause + "\n " + exception.getMessage();
		}  else if (exception instanceof AWTException) {
			exceptionMessage = "AWTException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof InterruptedException) {
			exceptionMessage = "InterruptedException====>" + CAUSE + cause + "\n " + exception.getMessage();
		}  else if (exception instanceof MalformedURLException) {
			exceptionMessage = "MalformedURLException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof ParserConfigurationException) {
			exceptionMessage = "ParserConfigurationException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof DOMException) {
			exceptionMessage = "DOMException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof TransformerConfigurationException) {
			exceptionMessage = "TransformerConfigurationException====>" + CAUSE + cause + "\n "
					+ exception.getMessage();
		} else if (exception instanceof TransformerException) {
			exceptionMessage = "TransformerException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof UnsupportedEncodingException) {
			exceptionMessage = "UnsupportedEncodingException====>" + CAUSE + cause + "\n " + exception.getMessage();
		}else if (exception instanceof JSONException) {
			exceptionMessage = "JSONException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else if (exception instanceof NullPointerException) {
			exceptionMessage = "NullPointerException====>" + CAUSE + cause + "\n " + exception.getMessage();
		} else {
			exceptionMessage = "System Exception ====> \n " + CAUSE + cause + "\n " + exception.getMessage();
		}
		((Log) logger).logMessage(null, "SystemException",
				exceptionMessage + "\n" + getExceptionTrace(exception), Constants.LOG_FATAL, false);
		//DBExecutor.recordError(exceptionMessage, FabricsBase.getExceptionTrace(exception));

		resolveException(null, null, null, exception);
	}
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {		
		System.out.println("Spring MVC Exception Handling");
		logger.error("Spring MVC Exception Handling");
		logger.error("Error: ", exception);		
		return new ModelAndView("error/exception","exception","ExceptionHandler message: " + exception.toString());
	}


	/**
	 * Creates a FabricsException with the specified detail message.
	 * 
	 * @param message
	 *            the detail message.
	 */
	public SystemException(String message) {
		this(message, null);
		return;
	}

	/**
	 * Creates a new FabricsException wrapping another exception, and with a detail
	 * message.
	 * 
	 * @param message
	 *            the detail message.
	 * @param exception
	 *            the wrapped exception.
	 */
	public SystemException(Exception exception, String message) {
		super(message);
		this.exception = exception;
		return;
	}

	/**
	 * Constructs a FabricsException with a message string, and a base exception.
	 * 
	 * @param message
	 *            Exception Msssage
	 * @param cause
	 *            Base Exception
	 */

	public SystemException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * Method to return the String representation of this exception
	 * 
	 * @return String representation of this exception
	 */
	@Override
	public String toString() {
		if (exception instanceof SystemException) {
			return ((SystemException) exception).toString();
		}
		return exception == null ? super.toString() : exception.toString();
	}

	/**
	 * Gets the wrapped exception.
	 *
	 * @return the wrapped exception.
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Retrieves (recursively) the root cause exception.
	 *
	 * @return the root cause exception.
	 */
	public Exception getRootCause() {
		if (exception instanceof SystemException) {
			return ((SystemException) exception).getRootCause();
		}
		return exception == null ? this : exception;
	}

	/**
	 * Method to get the Stack Trace
	 * 
	 * @param t
	 *            Throwable Object, whose stack trace need to be collected
	 * @return Stack Trace as a string
	 * @throws ExceptionHandler
	 *             Generic FABRICS Exception Object that handles all exceptions
	 */
	public static String getStackTrace(Throwable t) throws SystemException {
		String ret = null;

		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.flush();
			ret = sw.toString();
			sw.close();
			pw.close();
		} catch (IOException e) {
			((Log) logger).logMessage(e, "FABRICS Base Exceptions",
					"IO Exception:" + e.getMessage() + "\n" + getExceptionTrace(e), Constants.LOG_ERROR,
					true);
		}
		return ret;

	}
	/**
	 * @param e
	 *            Exception
	 * @return Exception Trace String
	 */
	public static String getExceptionTrace(Exception e) {
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