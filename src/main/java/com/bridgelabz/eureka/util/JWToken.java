package com.bridgelabz.eureka.util;
import java.security.SignatureException;
/************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose: JW token implementation class for the login and registration
 *************************************************************************************************/
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWToken {
	static Logger logger= LoggerFactory.getLogger(JWToken.class);
	
	public static Claims verifyToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey("passKey")
					.parseClaimsJws(token)
					.getBody();
			return claims;
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
			throw new JWTException("Error in verifying JW Token");
		} catch (ExpiredJwtException e) {
			throw new JWTException("Token Expired");
		}catch(Exception e) {e.printStackTrace();throw new RuntimeException();}
	}
}