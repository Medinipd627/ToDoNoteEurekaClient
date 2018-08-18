package com.bridgelabz.eureka.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bridgelabz.eureka.util.Label;

import io.swagger.annotations.ApiModelProperty;


//@Document(collection = "note")
public class NoteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(hidden = true)
	private String id;
	
	private String title;
	private String description;
	@ApiModelProperty(hidden = true)
	private String trash;
	@ApiModelProperty(hidden = true)
	private String archive;
	@ApiModelProperty(hidden = true)
	private String pin;
	
	@ApiModelProperty(hidden = true)
	@Field("label")
	private List<Label> label;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the trash
	 */
	public String getTrash() {
		return trash;
	}

	/**
	 * @param trash the trash to set
	 */
	public void setTrash(String trash) {
		this.trash = trash;
	}

	/**
	 * @return the archive
	 */
	public String getArchive() {
		return archive;
	}

	/**
	 * @param archive the archive to set
	 */
	public void setArchive(String archive) {
		this.archive = archive;
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return the label
	 */
	public List<Label> getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(List<Label> label) {
		this.label = label;
	}
}