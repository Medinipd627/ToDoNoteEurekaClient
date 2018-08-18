package com.bridgelabz.eureka.util;
/****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:User exception handler for the login and registration
 ****************************************************************************************************/
public class UserExceptionHandler extends RuntimeException{
	
	private static final long serialVersionUID = -359309893066954824L;

	public UserExceptionHandler(String message) {
		super(message);
	}
	
}
