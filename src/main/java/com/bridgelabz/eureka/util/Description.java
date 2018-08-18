package com.bridgelabz.eureka.util;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class Description implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> simpleDescription;
	private List<Link> linkDescription;

	public Description() {
	}

	public List<String> getSimpleDescription() {
		return simpleDescription;
	}

	public void setSimpleDescription(List<String> simpleDescription) {
		this.simpleDescription = simpleDescription;
	}

	public List<Link> getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(List<Link> linkDescription) {
		this.linkDescription = linkDescription;
	}

	@Override
	public String toString() {
		return "Description [description=" + simpleDescription + ", linkDescription=" + linkDescription + "]";
	}

	public String[] split(String string) {
		return null;
	}

}
