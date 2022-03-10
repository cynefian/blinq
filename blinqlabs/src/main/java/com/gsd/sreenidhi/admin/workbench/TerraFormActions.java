package com.gsd.sreenidhi.admin.workbench;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.gsd.sreenidhi.utils.ProcessUtils;
import com.gsd.sreenidhi.utils.SystemEnvironment;

public class TerraFormActions {

	public boolean initializeTF(String tfFilePath) throws IOException, InterruptedException {
		return ProcessUtils.executeSystemprocesses("terraform init", tfFilePath);
	}

	public boolean executeTF(String tfFilePath, String props) throws IOException, InterruptedException {
		
		String arguements = "";
		if(props!=null) {
			arguements = props;
		}else {
			arguements = "";
		}
		
		if(SystemEnvironment.isWindows()) {
			return ProcessUtils.executeSystemprocesses("terraform apply -auto-approve", tfFilePath);	
		}else if(SystemEnvironment.isUnix()) {
			return ProcessUtils.executeSystemprocesses("terraform apply -auto-approve " + arguements, tfFilePath);
		}
		return false;
	}

	public boolean statusTF(String tfFilePath) throws IOException, InterruptedException {
		return ProcessUtils.executeSystemprocesses("terraform show", tfFilePath);
		
	}

	public String readResult(String tfFilePath) {
		StringBuilder sb = new StringBuilder();
		String content = null;
		try {
		      File myObj = new File(tfFilePath+File.separator+"terraform.tfstate");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        sb.append(data);
		      }
		      myReader.close();
		      content = sb.toString();
		    } catch (FileNotFoundException e) {
		     e.printStackTrace();
		    }
		return content;
	}

}
