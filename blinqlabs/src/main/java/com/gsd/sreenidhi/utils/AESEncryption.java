package com.gsd.sreenidhi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * This is the Encryption and decryption for fabric framework - web app
 * functionality AES:Advanced Encryption Standard Algorithm
 * 
 * @author Sreenidhi, Gundlupet
 *
 */

public class AESEncryption {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6880466273169443960L;
	private static SecretKeySpec secretKey;
	private static byte[] key;

	/**
	 * @param myKey Key
	 * @throws FabricsException Generic FABRICS Exception Object that handles all exceptions
	 */
	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		}
	}


	public static String encrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			
		}
		return null;
	}

	public static String decrypt(String strToDecrypt, String secret)  {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			
		}
		return null;
	}
	

	private static void main(String args[]) {
		System.out.println(encrypt("devopsadmin","8l!nqLabs@dm1n"));
		System.out.println(decrypt("5M2KTLMkiBwf2rwKL3kajP+Jx9VJa1QWWFuwF9PZUi8RjpR6BxHyW387VrSSNq9m","8l!nqLabs@dm1n"));
	}
}