package com.bridgelabz.eureka.util;
/*****************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018 
 * Purpose: Preconditions to check the user defined conditions
 *****************************************************************************************/
public class PreConditions {

	public static <T> T CheckNotNull(T resource) {
		if (resource != null) {
			throw new UserExceptionHandler(("email.registered"));
		}
		return resource;
	}
	public static <T> T CheckNull(T resource) {
		if (resource == null) {
			throw new UserExceptionHandler(("invalid email id"));
		}
		return resource;
	}
	public static <T> T CheckPass(T resource)
	{
		if(resource==null)
		{
			throw new  UserExceptionHandler(("invalid password"));
		}
		return resource;
	}
}