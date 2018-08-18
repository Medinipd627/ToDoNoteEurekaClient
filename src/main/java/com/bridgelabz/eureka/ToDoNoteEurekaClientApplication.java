package com.bridgelabz.eureka;

/***************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/08/2018 
 * Purpose: NoteToDoEurekaServerApplication main class
 ***************************************************************************************/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EntityScan(basePackages = "NoteToDoUrukaServer") 
public class ToDoNoteEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoNoteEurekaClientApplication.class, args);
	}
}
