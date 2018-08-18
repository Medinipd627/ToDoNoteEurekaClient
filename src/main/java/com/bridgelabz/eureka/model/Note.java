package com.bridgelabz.eureka.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.bridgelabz.eureka.util.Description;
import com.bridgelabz.eureka.util.Label;
import io.swagger.annotations.ApiModelProperty;

/************************************************************************************
 * Created By:Medini P.D 
 * Date:- 16/07/2018 
 * Purpose:Pojo class for the Note
 *****************************************************************************************/
@Document(indexName = "note", type = "Notes")
public class Note {
	@Id
	@ApiModelProperty(hidden = true)
	private String id;

	@ApiModelProperty(hidden = true)
	private String createdDate;

	@ApiModelProperty(hidden = true)
	private String Trash;

	@ApiModelProperty(hidden = true)
	private String userId;

	@ApiModelProperty(hidden = true)
	private List<Label> labelName;

	@ApiModelProperty(hidden = true)
	private String color;

	private String archive;
	// private String description;
	private Description description;

	private String title;
	private String pin;

	public String getTrash() {
		return Trash;
	}

	/**
	 * @param trash
	 */
	public void setTrash(String trash) {
		Trash = trash;
	}

	/**
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getArchive() {
		return archive;
	}

	/**
	 * @param archive
	 */
	public void setArchive(String archive) {
		this.archive = archive;
	}

	/**
	 * @return
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return
	 */
	public List<Label> getLabelName() {
		return labelName;
	}

	/**
	 * @param newLabelList
	 */
	public void setLabelName(List<Label> newLabelList) {
		this.labelName = newLabelList;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * @param string
	 */
	public void setCreatedDate(String string) {
		this.createdDate = string;
	}

	/**
	 * @return
	 */
	public String getuserId() {
		return userId;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		this.userId = string;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", createdDate=" + createdDate + ", Trash=" + Trash + ", title=" + title
				+ ", description=" + description + ", pin=" + pin + ", archive=" + archive + ", userId=" + userId + "]";
	}

	/**
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
