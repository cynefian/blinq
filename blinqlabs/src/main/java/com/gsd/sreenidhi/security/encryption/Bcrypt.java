package com.gsd.sreenidhi.security.encryption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gsd.sreenidhi.register.RegisterFormController;

public class Bcrypt {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterFormController.class);
	
	public String encode(String toBeEncoded) {
		String hashedPassword = null ;
		int i = 0;
		while (i < 16) {
			String password = toBeEncoded;
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPassword = passwordEncoder.encode(password);
			i++;
		}
		return hashedPassword;
	}

	public boolean matches(String password, String hash) {
		boolean match = false;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		match = passwordEncoder.matches(password, hash);
	
		
		return match;
	}
}
