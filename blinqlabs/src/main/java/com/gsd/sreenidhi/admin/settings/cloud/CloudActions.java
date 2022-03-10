package com.gsd.sreenidhi.admin.settings.cloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CloudActions {

	public String validateSystemPath(String infraKey, String type) {
		String file = "";
		if(type!=null && !"".equals(type)) {
			if(type.equalsIgnoreCase("EC2")) {
				file = "createEc2.tf";
			}else if(type.equalsIgnoreCase("VPC")) {
				file = "createVPC.tf";
			}else if(type.equalsIgnoreCase("SUBNET")) {
				file = "createSubnet.tf";
			}else if(type.equalsIgnoreCase("SECURITY_GROUP")) {
				file = "createSecGrp.tf";
			}
		}else {
			
		}
		
		File f = new File(System.getProperty("user.home") + File.separator + "terraform" + File.separator + infraKey);
		if (!f.exists()) {
			f.mkdirs();
		}
		File sourceTemplate = new File(System.getProperty("user.home") + File.separator + "terraform" + File.separator
				+ "templates" + File.separator + file);
		File destFile = new File(f.getAbsolutePath() + File.separator + file);
		if (!sourceTemplate.exists()) {
			System.out.println("Error configuring Terraform Template");
			return "error";
		} else {
			if (!destFile.exists()) {
				try (FileInputStream fis = new FileInputStream(sourceTemplate);
						FileOutputStream fos = new FileOutputStream(destFile)) {
					byte[] buffer = new byte[1024];
					int length;
					while ((length = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return f.getAbsolutePath().toString();
		}
	}
}
