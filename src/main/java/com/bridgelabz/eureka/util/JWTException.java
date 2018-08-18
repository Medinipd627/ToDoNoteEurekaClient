package com.bridgelabz.eureka.util;

/*********************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/07/2018 
 * Purpose:JWTException for the login and registration
 ***********************************************************************************************/
public class JWTException extends RuntimeException {
	private static final long serialVersionUID = 4328986007283940610L;

	public JWTException(String message) {
		super(message);
	}
}
