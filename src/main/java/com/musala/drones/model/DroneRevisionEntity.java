package com.musala.drones.model;

/**
 * 
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.musala.drones.config.persistance.DroneRevisionListener;


/**
 * @author New
 *
 */

@Entity
@Table(name = "aud_revision", schema = "dn_aud")
@RevisionEntity(DroneRevisionListener.class)
public class DroneRevisionEntity implements Serializable {
	public DroneRevisionEntity() {
		super();
	}
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1851010132286538431L;
	
	  @Id
	  @GeneratedValue
	  @RevisionNumber
	  private Long rev;
	  
	  
	  @RevisionTimestamp
	  private Long timestamp;
	  
	  
	  @Column(name="principal", length = 50)
	  private String userName;
	  
	  //@Column(name="task")
	  //private Long taskId;
	  
	  @Column(name="source", length = 20)
	  private String sourceIp;

	public Long getRev() {
		return rev;
	}

	public void setRev(Long rev) {
		this.rev = rev;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	  
	  
	  



}
