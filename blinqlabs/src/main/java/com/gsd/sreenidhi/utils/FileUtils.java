package com.gsd.sreenidhi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.gsd.sreenidhi.exceptions.SystemException;

public class FileUtils {
	/**
	 * @param propFile
	 *            Name of Property File
	 * @return Properties
	 * @throws IOException 
	 * @throws CheetahException
	 *             Generic Exception Object that handles all exceptions
	 */
	public Properties getProperties(String propFile) throws SystemException, IOException {
		Properties properties = null;

		try {
			properties = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			StringBuffer path = new StringBuffer(
					"resources" + File.separator + "properties" + File.separator + propFile);
			properties.load(new FileInputStream(path.toString()));
			
			if (properties.isEmpty()) {
				
			} else {
				
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return properties;
	}

}
