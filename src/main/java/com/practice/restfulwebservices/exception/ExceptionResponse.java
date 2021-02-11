package com.practice.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

	private String message="catch me if you can";
	private String detail;
	private Date timeStamp;

	public ExceptionResponse(String message, String detail, Date timeStamp) {
		super();
		this.message = message;
		this.detail = detail;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

}
