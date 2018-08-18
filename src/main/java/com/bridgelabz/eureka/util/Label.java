package com.bridgelabz.eureka.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModelProperty;

/*******************************************************************************************
 * Created By:Medini P.D 
 * Date:- 25/07/2018 
 * Purpose:Lable pojo class for creating a label
 ******************************************************************************************/

@Document(indexName = "label", type = "Label")

public class Label {
	@Id
	@ApiModelProperty(hidden = true)
	private String id;
	
	@ApiModelProperty(hidden = true)
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String lableName) {
		this.labelName = lableName;
	}
}
