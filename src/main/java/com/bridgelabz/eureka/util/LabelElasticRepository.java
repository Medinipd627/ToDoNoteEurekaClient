package com.bridgelabz.eureka.util;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/******************************************************************************************
 * Created By:Medini P.D Date:- 03/07/2018 Purpose:LabelElasticRepository class
 * which extends the Elastic Search Repository
 *****************************************************************************************/
public interface LabelElasticRepository extends ElasticsearchRepository<Label, String> {

	List<Label> findLabelsByUserId(String userId);

}
