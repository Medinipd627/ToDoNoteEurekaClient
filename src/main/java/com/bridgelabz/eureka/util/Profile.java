package com.bridgelabz.eureka.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/***********************************************************************************
 * Created By:Medini P.D 
 * Date:- 03/07/2018 
 * Purpose:DProfile implementation to check the active instance
 ************************************************************************************/
class Profile {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		Resource resource;
		String activeProfile;

		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

		// get active profile
		activeProfile = System.getProperty("spring.profiles.active");

		// choose different property files for different active profile
		if ("development".equals(activeProfile)) {
			resource = new ClassPathResource(
					"/home/bridgelabz/Documents/java programs/ToDoApplication/src/main/resources/applicationDevelopment.properties");
			System.out.println(activeProfile + " profile selected");
		} else if ("test".equals(activeProfile)) {
			resource = new ClassPathResource(
					"/home/bridgelabz/Documents/java programs/ToDoApplication/src/main/resources/applicationTest.properties");
			System.out.println(activeProfile + " profile selected");
		} else {
			resource = new ClassPathResource(
					"/home/bridgelabz/Documents/java programs/ToDoApplication/src/main/resources/applicationProduction.properties");
			System.out.println(activeProfile + " profile selected");
		}

		// load the property file
		propertySourcesPlaceholderConfigurer.setLocation(resource);

		return propertySourcesPlaceholderConfigurer;
	}
}