package com.bridgelabz.eureka.controller;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**********************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:FeignClient Service class
 **************************************************************************************************/
@FeignClient(name = "ToDoUserEurekaClient",url="http://localhost:8080")
@Service
public interface FeignClientService {
	@RequestMapping(method=RequestMethod.GET, value="user/getAllUsers")
	List<?> getAllUser();
}
