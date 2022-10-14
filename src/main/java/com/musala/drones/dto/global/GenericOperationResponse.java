package com.musala.drones.dto.global;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 */

/**
 * @author George J. Budeba
 *
 */
public class GenericOperationResponse {
	
	
	public GenericOperationResponse(String statusCode, String message) {
		super();
		this.timestamp = Calendar.getInstance().getTime();
		this.statusCode = statusCode;
		this.message = message;
		
	}
	private Date timestamp;// = Calendar.getInstance().getTime();
	private String statusCode;
	private String message;

	/**
	 * @param timestamp
	 * @param statusCode
	 * @param message
	 */
	public GenericOperationResponse(Date timestamp, String statusCode, String message) {
	//	super();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.message = message;
	}
	/**
	 * 
	 */
	public GenericOperationResponse() {
		super();
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
