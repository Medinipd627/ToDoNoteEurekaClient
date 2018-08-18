package com.bridgelabz.eureka.util;
/******************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose:Note exception for login and registration
 *********************************************************************************************/
    public class NoteExceptionHandler extends RuntimeException{
    	
	private static final long serialVersionUID = 8037704665476484234L;
	public NoteExceptionHandler(String message) {
		super(message);
	}
}
