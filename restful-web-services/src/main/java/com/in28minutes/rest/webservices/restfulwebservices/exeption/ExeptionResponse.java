package com.in28minutes.rest.webservices.restfulwebservices.exeption;

import java.util.Date;


public class ExeptionResponse {
	//timestamp
	private Date timeStamp;

	//exeption message
	private String messageExeption;
	
	//details
	private String details;
	
	public ExeptionResponse() {
		
	}

	public ExeptionResponse(Date timeStamp, String messageExeption, String details) {
		super();
		this.timeStamp = timeStamp;
		this.messageExeption = messageExeption;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessageExeption() {
		return messageExeption;
	}

	public String getDetails() {
		return details;
	}
	
	
	

}
