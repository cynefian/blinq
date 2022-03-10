package com.gsd.sreenidhi.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScopeController {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieController.class);
	
	
	@RequestMapping(value = "/scopeSession", method=RequestMethod.GET)
	 public ModelAndView scope(HttpSession session) {		
	
		logger.info("ScopeController scope is called");
		session.setAttribute("sessionObject", "Value in session object");
		//session.setMaxInactiveInterval(15); seconds
		return new ModelAndView("/scope/scope");		 
	}

	@RequestMapping(value = "/invalidate", method=RequestMethod.GET)
	 public ModelAndView invalidate(HttpSession session) {				
		session.invalidate();
		logger.info("ScopeController invalidate is called");
		return new ModelAndView("/scope/scope");		 
	}
	
	
}
