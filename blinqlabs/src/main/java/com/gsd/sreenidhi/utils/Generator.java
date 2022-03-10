package com.gsd.sreenidhi.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;


public class Generator {
	static final String randomizer = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static final String randomeOTP = "0123456789";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString(int len){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( randomizer.charAt( rnd.nextInt(randomizer.length()) ) );
	   return sb.toString();
	}
	
	public static String generateRandomString(int length) {
		SecureRandom rnd = new SecureRandom();
		byte[] token = new byte[length];
		rnd.nextBytes(token);
		return rnd.toString();
	}
	public String generateUUID() {
		 UUID uuid = UUID.randomUUID();
		 String randomUUIDString = uuid.toString();
		 return randomUUIDString;
	}
	
	public static String generateOTP(int len) {
		 StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( randomeOTP.charAt( rnd.nextInt(randomeOTP.length()) ) );
		   return sb.toString();
	}
	
	public static int getPortNumber() {
		int min = 8090;
		int max = 9000;

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
