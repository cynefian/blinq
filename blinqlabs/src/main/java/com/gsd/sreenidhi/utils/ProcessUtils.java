package com.gsd.sreenidhi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executors;



public class ProcessUtils {
	
	public static boolean executeSystemprocesses(String command, String dir) throws IOException, InterruptedException{
	
		boolean isWindows = System.getProperty("os.name")
				  .toLowerCase().startsWith("windows");
		
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
		    builder.command("cmd.exe", "/c", command);
		} else {
		    builder.command("sh", "-c", command);
		}
		builder.directory(new File(dir));
		Process process = builder.start();
		StreamGobbler streamGobbler = 
		  new StreamGobbler(process.getInputStream(), System.out::println);
		
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		
		if(exitCode==0) {
			return true;
		}else {
			return false;
		}
	}

}
