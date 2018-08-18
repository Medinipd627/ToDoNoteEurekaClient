package com.bridgelabz.eureka.util;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/****************************************************************************************
 * Created By:Medini P.D
 * Date:- 25/07/2018
 * Purpose:LabelRepository interface which extends the mongoDB and providing all functions
 *******************************************************************************************/
@Repository
public interface LabelRepository extends MongoRepository<Label, String>{
	List<Label> findLabelsByUserId(String userId);
}
