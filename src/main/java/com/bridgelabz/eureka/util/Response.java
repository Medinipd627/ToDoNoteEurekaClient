package com.bridgelabz.eureka.util;
/*****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:Response class for the login and registration
 ***************************************************************************************************/
import org.springframework.http.HttpStatus;

public class Response {
	private String message;
	private HttpStatus httpStatus;
	private int code;
	public Response() {
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Response(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
