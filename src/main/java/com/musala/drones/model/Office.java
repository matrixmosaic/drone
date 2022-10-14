package com.musala.drones.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "df_office", schema = "dn_dict")
public class Office {

	
	@Id
	@Column(name = "office_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long officeId;
	
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "office")
	private Set<AppUser> staff = new HashSet<AppUser>();
	
	

	@Column(name = "active", length = 100)
	private boolean active; 
	//digital_code;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "fax", length = 100)
	private String fax;
	
	@Column(name = "head_name", length = 100)
	private String headName;
	//host_address: varchar(255
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "code", length = 100)
	private String code;
	
		
	
	@Column(name="office_phone", length = 100)
	private String officePhone;
	
	@Column(name="postal_address", length = 100)
	private String postalAddress;
	
	@Column(name="street_address", length = 100)
	private String streetAddress;
	
	@Column(name="webSite", length = 100)
	private String webSite;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "office")
	private List<Trip> trips;
	

	/**
	 * 
	 */
	public Office() {
		super();
	}




	/**
	 * @param officeId
	 * @param active
	 * @param email
	 * @param fax
	 * @param headName
	 * @param name
	 * @param code
	 * @param webSite
	 */
	
	public Office(Office office) {
		if (office != null) {
			this.officeId = office.getOfficeId();
			this.active = office.isActive();
			this.email = office.getEmail();
			this.fax = office.getFax();
			this.headName = office.getHeadName();
			this.name = office.getName();
			this.code = office.getCode();
			this.webSite = office.getWebSite();
		}
	}

	public Long getOfficeId() {
		return officeId;
	}


	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}


	public Set<AppUser> getStaff() {
		return staff;
	}


	public void setStaff(Set<AppUser> staff) {
		this.staff = staff;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getHeadName() {
		return headName;
	}


	public void setHeadName(String headName) {
		this.headName = headName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getOfficePhone() {
		return officePhone;
	}


	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}


	public String getPostalAddress() {
		return postalAddress;
	}


	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getWebSite() {
		return webSite;
	}


	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	
}
