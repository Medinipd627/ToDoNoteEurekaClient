package com.bridgelabz.eureka.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/***************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/07/2018 
 * Purpose: Application config class for the login and registration application
 ***************************************************************************************/
@Configuration
public class ApplicationConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
