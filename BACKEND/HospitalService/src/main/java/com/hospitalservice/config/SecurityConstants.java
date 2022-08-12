package com.hospitalservice.config;



public class SecurityConstants {
	

	
	public static final long EXPIRATION_TIME=600000;//10 min
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String TOKEN_SECRET="dasjfnoiduf";
	public static final String SIGNUP_URL="/users";
	
//	 public static String getTokenSecret()
//	    {
//	        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
//	        return appProperties.getTokenSecret();
//	    }

}
