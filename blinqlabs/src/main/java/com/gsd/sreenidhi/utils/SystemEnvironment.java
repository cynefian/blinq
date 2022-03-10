package com.gsd.sreenidhi.utils;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Properties;


/**
 * @author Sreenidhi, Gundlupet
 *
 */
public class SystemEnvironment {
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	/**
	 * @param props
	 *            Properties
	 * @throws FabricsException
	 *             Generic FABRICS Exception Object that handles all exceptions
	 */
	public static void processEnvironment(Properties props)  {
		InetAddress networkId = NetworkUtils.getLocalHostLANAddress();
		String properties = "";
		@SuppressWarnings("unchecked")
		Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
		if(enums!=null) {
			while (enums.hasMoreElements()) {
				String key = enums.nextElement();
				String value = props.getProperty(key);
				properties = properties + key + " : " + value + "\n";
			}
		}else {
			properties="";
		}
		
	}
	
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
	
}
