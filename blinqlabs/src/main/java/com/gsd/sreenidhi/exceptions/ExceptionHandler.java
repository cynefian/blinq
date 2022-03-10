package com.gsd.sreenidhi.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.gsd.sreenidhi.forms.Constants;
import com.gsd.sreenidhi.logging.Log;

@Component
public class ExceptionHandler implements HandlerExceptionResolver{
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {		
			System.out.println("Spring MVC Exception Handling");
			logger.error("Spring MVC Exception Handling");
			logger.error("Error: ", exception);		
		
		ExceptionForm eF = new ExceptionForm();
		eF.setExceptionCause(exception.getCause()!=null?exception.getCause().toString():"Unknown");
		eF.setExceptionHashCode(exception.hashCode());
		eF.setExceptionLocalizedMessage(exception.getLocalizedMessage()!=null?exception.getLocalizedMessage():"Unknown");
		eF.setExceptionMessage(exception.getMessage()!=null?exception.getMessage():"");
		try {
			eF.setExceptionStackTrace(exception.getStackTrace()!=null?SystemException.getStackTrace(exception):"Unknown");
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		
		return new ModelAndView("error/exception","exception",eF);
	}

	
	
}
