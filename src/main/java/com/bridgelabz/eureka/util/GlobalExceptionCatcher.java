package com.bridgelabz.eureka.util;

/**********************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:Global exception class for the login and registration application
 *************************************************************************************************/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionCatcher {

	@ExceptionHandler(value = UserExceptionHandler.class)
	public ResponseEntity<Response> registerException(Exception exception) {
		Response response = new Response(exception.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Response> notValidArgument(RuntimeException exception) {
		Response response = new Response(exception.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
	/*
	 * @ExceptionHandler(value = Exception.class) public ResponseEntity<Response>
	 * exception() { Response response = new Response("Something went wrong",
	 * HttpStatus.INTERNAL_SERVER_ERROR); return new ResponseEntity<>(response,
	 * response.getHttpStatus()); }
	 */

}
