package com.gsd.sreenidhi.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gsd.sreenidhi.controller.CookieController;
import com.gsd.sreenidhi.register.Register;

@Component
public class RegisterBeanValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterBeanValidator.class);

	public boolean supports(Class<?> clazz) {
		return RegisterBeanValidator.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		 ValidationUtils.rejectIfEmpty(errors, "password", null, "Password can not be empty");		 
		 //Register register = (Register) target;		 
		 //errors.rejectValue("errorField", null, "RegisterBeanValidator: error");
		 
	}

}
