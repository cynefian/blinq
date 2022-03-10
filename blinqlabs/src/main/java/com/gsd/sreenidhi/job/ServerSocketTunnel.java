package com.gsd.sreenidhi.job;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

import org.springframework.util.ResourceUtils;

import java.security.KeyStore;

import com.gsd.sreenidhi.utils.Generator;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



public class ServerSocketTunnel {

	  //public static void main(String[] args) throws Exception {
		public static String captureSocket(String host, int port, String command) throws Exception{
			String result;
			
			StringBuffer sb = new StringBuffer();
			JSch jsch = new JSch();
		    Session session = null;
		    File file = ResourceUtils.getFile("classpath:auth/aws-ubuntu1_key.ppk");
		    String privateKeyPath = file.getAbsolutePath();
		    try {
		        jsch.addIdentity(privateKeyPath);	    
		        session = jsch.getSession("ubuntu", host, port);
		        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
		        java.util.Properties config = new java.util.Properties(); 
		        config.put("StrictHostKeyChecking", "no");
		        session.setConfig(config);
		    } catch (JSchException e) {
		        throw new RuntimeException("Failed to create Jsch Session object.", e);
		    }
	       
		    try {
		        session.connect();
		        Channel channel = session.openChannel("exec");
		        ((ChannelExec) channel).setCommand(command);
		        ((ChannelExec) channel).setPty(true);
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        
		        InputStream in=channel.getInputStream();
		        channel.connect();
		        
		        try{Thread.sleep(1000);}catch(Exception ee){}
		        
		        byte[] tmp=new byte[1024];
		        while(true){
		          while(in.available()>0){
		            int i=in.read(tmp, 0, 1024);
		            if(i<0)break;
		            sb.append(new String(tmp, 0, i));
		          }
		          if(channel.isClosed()){
		            break;
		          }
		          try{Thread.sleep(1000);}catch(Exception ee){}
		        }
		        
		        channel.disconnect();
		        session.disconnect();
		        result = sb.toString();
		    } catch (JSchException e) {
		        throw new RuntimeException("Error durring SSH command execution. Command: " + command);
		    }
		    return result;
		}
		
		public static void main(String[] args) throws Exception {
			
				int port = 0;
				String container = "PORT";
					try {
						while(!"".equalsIgnoreCase(container.trim())) {
							port = Generator.getPortNumber();
							System.out.println("PORT: " + port);
							container = captureSocket("34.229.128.108", 22, "sudo netstat -ntlp | grep :"+ port);
							System.out.println("Res: " + container.trim());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Final PORT: " + port);
		}
}
