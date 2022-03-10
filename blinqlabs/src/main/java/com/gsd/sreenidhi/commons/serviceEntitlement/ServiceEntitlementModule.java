package com.gsd.sreenidhi.commons.serviceEntitlement;

import com.gsd.sreenidhi.utils.Generator;

public class ServiceEntitlementModule {
	
	public String genetateSEN(boolean registered) {
		String sen="";
		String randomizer = Generator.randomString(16);
		
		if(registered) {
			sen=sen+"SEN-"+randomizer;
		}else {
			sen=sen+"TEN-"+randomizer;
		}
		return sen;
	}

}
