package com.musala.drones.dto.global;

/**
 * 
 */

import java.util.Objects;

/**
 * @author George J. Budeba
 *
 */
public class GeneralStatus <T>{
	private String code;
    private String status;
   // private HttpStatus httpStatusCode;
    private T data;
	/**
	 * 
	 */
	public GeneralStatus() {
		super();
	}
	/**
	 * @param statusCode
	 * @param status
	 */
	public GeneralStatus(String statusCode, String status) {
		super();
		this.code = statusCode;
		this.status = status;
	}
	
	public GeneralStatus(T data, String status,String code) {
		this.data =data;
		this.code = code;
		this.status = status;
	}
	@Override
	public String toString() {
		return "StatusCode [statusCode=" + code + ", status=" + status + "]";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public int hashCode() {
		return Objects.hash(code, data, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneralStatus<?> other = (GeneralStatus<?> ) obj;
		return Objects.equals(code, other.code) && Objects.equals(data, other.data)
				&& Objects.equals(status, other.status);
	}

	
	

}
