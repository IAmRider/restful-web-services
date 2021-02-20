package com.practice.restfulwebservices.entity;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User Resource Details")

public class User {

	private int id;

	@Size(min = 3, message = "name should be greater than 2 letters")
	@ApiModelProperty(notes = "name should be greater than 2 letters")
	private String name;

	@Past(message = "you have entered invalid birthdate")
	@ApiModelProperty(notes = "birth date should be in past")
	private Date birthdate;

	public User(int id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}

}
