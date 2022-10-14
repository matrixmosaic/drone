
package com.musala.drones.dto.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.drones.model.AppUser;
import com.musala.drones.model.Office;
import com.musala.drones.model.Role;


/**
 * @author George J. Budeba
 *
 */
public class RemoteUserResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1812225352771899635L;

	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</code>.
	 *
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	private String officeCode;
	 private String fisrstName;
	 private String officeName;
	 private String fullName;
	 private String secondName;

	@Column(name="last_name", length = 100)
	 private String lastName;
	
	@JsonProperty("authorities")
	List<Role> authority;

	Office office;

	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return the password
	 */
	@JsonIgnore
	String password;

	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * <code>null</code>.
	 *
	 * @return the username (never <code>null</code>)
	 */
	String username;
	private String token;

	/**
	 * 
	 */
	public RemoteUserResp() {
	}

	public RemoteUserResp(String username2, String password2, String token2, AppUser user) {
		this.setPassword(password2);
		this.setUsername(username2);
		this.setFisrstName(user.getFisrstName());
		this.setSecondName(user.getSecondName());
		this.setLastName(user.getLastName());
		this.setToken(token2);
		this.fullName=user.getFullName();
		this.setOffice(new Office(user.getOffice()));
		authority = new ArrayList<Role>();

		if (!(user.getUserRoles() == null)) {
			(user.getUserRoles()).forEach(role -> {
				authority.add(new Role(role));

			});
		}
		
		if (!(user.getOffice() == null)) {

this.officeCode=user.getOffice().getCode();
this.officeName=user.getOffice().getName();
		}

	}

	/**
	 * @return the authority
	 */
	public List<Role> getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(List<Role> authority) {
		this.authority = authority;
	}

	/**
	 * @return the office
	 */
	public Office getOffice() {
		return office;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(Office office) {
		this.office = office;
	}

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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the fisrstName
	 */
	public String getFisrstName() {
		return fisrstName;
	}

	/**
	 * @param fisrstName the fisrstName to set
	 */
	public void setFisrstName(String fisrstName) {
		this.fisrstName = fisrstName;
	}

	/**
	 * @return the secondName
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * @param secondName the secondName to set
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the officeCode
	 */
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * @param officeCode the officeCode to set
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @param officeName the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

}
