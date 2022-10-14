package com.musala.drones.dto.remote;

/**
 * 
 */

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author George J. Budeba
 *
 */
public class RemoteUserReq implements Serializable{

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = -7992827044523596649L;
	
	

	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return the password
	 */
	String password;
	
	private int attempts;
	String password2;


	/**
	 * Returns the username used to authenticate the user. Cannot return <code>null</code>.
	 *
	 * @return the username (never <code>null</code>)
	 */
	String username;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 */
	public RemoteUserReq() {
	}

	

	@Override
	public String toString() {
		return "RemoteUserReq [password=" + password + ", attempts=" + attempts + ", username=" + username + "]";
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	
	
	

}
