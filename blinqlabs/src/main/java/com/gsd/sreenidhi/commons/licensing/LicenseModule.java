package com.gsd.sreenidhi.commons.licensing;

import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.utils.AES256_Encryption;

public class LicenseModule {

public String generateLicense(String expiryDate, String productCode, String productName, String sen, String machineId) {
		
		String orig = expiryDate+":"+productCode+":"+productName+":"+machineId;
		
		Bcrypt bcrypt = new Bcrypt();
		AES256_Encryption aes = new AES256_Encryption();
		
		String prCode_enc = bcrypt.encode(productCode);
		String prName_enc = bcrypt.encode(productName);
		String mcId_enc = bcrypt.encode(machineId);
		
		String exp_enc = aes.encrypt(expiryDate, "BigSecret");
		String sen_enc = aes.encrypt(sen, "BigSecret");
		
		String encryptedLicense = prCode_enc +"\n" +prName_enc + "\n" + mcId_enc + "\n" +sen_enc+"\n"+ exp_enc; 
		
		return encryptedLicense;
		
	}
}
