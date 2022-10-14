package com.musala.drones.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonInclude;





/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "dm_appuser", schema = "dn_main")
@Audited
@AuditTable( value = "aud_appuser", schema="dn_aud")
@NamedQueries({
    @NamedQuery(name = "userWithOfficeNamedQuery",
            query = "SELECT DISTINCT u " +
                    "FROM AppUser u " +
                    "LEFT JOIN FETCH u.office " +
                    "WHERE u.userName = :userName"),

    @NamedQuery(name = "userWithOfficeAndRolesByIdNamedQuery",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
            "LEFT JOIN FETCH u.office " +
            "LEFT JOIN FETCH u.userRoles " +
            "WHERE u.userName = :userName"),
    @NamedQuery(name = "userWithRolesAndOfficeByUsernameNamedQuery",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
            "LEFT JOIN FETCH u.office " +
            "LEFT JOIN FETCH u.userRoles " +
            "WHERE u.userName = :userName"),
    
    @NamedQuery(name = "userWithRolesAndOfficeByOfficeNamedQuery",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
            "LEFT JOIN FETCH u.userRoles " +
            "LEFT JOIN FETCH u.office " +
            "WHERE u.office = :office"),
    
    @NamedQuery(name = "userWithRolesByIdNamedQuery",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
            "LEFT JOIN FETCH u.userRoles " +
            "WHERE u.userName = :userName"),


		
		
    @NamedQuery(name = "SingleUserById",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
    		"WHERE u.userId = :userId  "),

    @NamedQuery(name = "SingleUserByUsername",
    query = "SELECT DISTINCT u " +
            "FROM AppUser u " +
    		"WHERE u.userName = :userName  "),
	  @NamedQuery(name = "UsersAndOfficeAndRolesByRolesCode", 
		query ="SELECT DISTINCT u " + 
		"FROM AppUser u " + 
		"LEFT JOIN FETCH u.office off " +
		"LEFT JOIN FETCH u.userRoles " +
		"WHERE u.userRoles IN :roles " ),
	  
	  
	  @NamedQuery(name = "AllUsersAndOfficeAndRoles", 
			query ="SELECT DISTINCT u " + 
			"FROM AppUser u " + 
			"LEFT JOIN FETCH u.office off " +
			"LEFT JOIN FETCH u.userRoles " +
			"WHERE  u.accountEnabled = TRUE " ),
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUser implements Serializable {






	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", fisrstName="
				+ fisrstName + ", secondName=" + secondName + ", lastName=" + lastName + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", jobTitle=" + jobTitle + ", initials="
				+ initials + ", credentialsExpirationDate=" + credentialsExpirationDate + ", accountExpirationDate="
				+ accountExpirationDate + ", accountNotLocked=" + accountNotLocked + ", accountEnabled="
				+ accountEnabled + ", creationDate=" + creationDate + ", inactivationDate=" + inactivationDate + "]";
	}




	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

   @Column(length = 70,name = "uuid")
	private String uuid =UUID.randomUUID().toString();


    /**
 * @return the uuid
 */
public String getUuid() {
	return uuid;
}

/**
 * @param uuid the uuid to set
 */
public void setUuid(String uuid) {
	this.uuid = uuid;
}
	private static final long serialVersionUID = 9178661439383356177L;

	@NotAudited
   @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, targetEntity =Role.class)
	    @JoinTable(name = "dm_user_permission", schema = "dn_main",
	             joinColumns = { @JoinColumn(name = "user_id") },
	             inverseJoinColumns = { @JoinColumn(name = "role_id") })
  private Set<Role> userRoles = new HashSet<Role>();


	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private AppUser assignee;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "office_id", nullable = true)
	private Office office;



	
	

	@Column(name="user_name", length = 100)
	 private String userName;

	@Column(name="password", length = 100)
	 private String password;

	@Column(name="first_name", length = 100)
	 private String fisrstName;

	@Column(name="second_name", length = 100)
	 private String secondName;

	@Column(name="last_name", length = 100)
	 private String lastName;

	@Column(name="gender", length = 100)
	 private String gender;

	 @Transient
	 private String phoneNumber;

	 @Column(name="email", length = 100)
	 private String email;

	 @Column(name="job_title", length = 100)
	 private String jobTitle;

	 @Column(name="initials", length = 100)
	 private String initials;




	/*
	 * Corresponds to isAccountNonExpired() in org.springframework.security.core.userdetails.UserDetails   Interface
	 *  which Indicates whether the user's account has expired. An expired account cannot
	 * be authenticated. Returns: true if the user's account is valid (ie
	 * non-expired), false if no longer valid (ie expired)
	 */		@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	 @Column(name = "cred_exp_date", nullable = true)
		 private Date credentialsExpirationDate;



	/*
	 * Corresponds to isCredentialsNonExpired() in org.springframework.security.core.userdetails.UserDetails   Interface
	 *  which Indicates whether the user's credentials (password) has expired. Expired credentials
	 * prevent authentication. Returns: true if the user's credentials are valid (ie
	 * non-expired), false if no longer valid (ie expired)
	 */		@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	 @Column(name = "acc_exp_date", nullable = true)
		 private Date accountExpirationDate;





	/*
	 * Corresponds to isAccountNonLocked() in org.springframework.security.core.userdetails.UserDetails   Interface
	 *  which Indicates whether the user is locked or unlocked. A locked user cannot be authenticated. Returns:
	 * true if the user is not locked, false otherwise
	 */
	 @Column(name = "acc_not_locked", nullable = true)
	 private boolean accountNotLocked=true;


	/*
	 * Corresponds to isEnabled() in org.springframework.security.core.userdetails.UserDetails   Interface which Indicates whether the user is enabled or
	 * disabled. A disabled user cannot be authenticated. Returns: true if the user
	 * is enabled, false otherwise
	 */
	 @Column(name = "enabled", nullable = true)
	 private boolean accountEnabled=true;









	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	 private Date creationDate;// = new Date(System.currentTimeMillis());

	 @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	 private Date inactivationDate;

	
	public AppUser() {
		super();
	}

	/**
	 * @param userId
	 * @param userName
	 */
	public String getFullName() {

		if (this.secondName != null) {
			return (this.getFisrstName() + "  " + this.secondName.substring(0, 1).toUpperCase() + ".  "
					+ this.getLastName());
		}

		return (this.getFisrstName() + "  " + this.getLastName());
	}










	/**
	 * @param fisrstName
	 * @param lastName
	 */
	public AppUser(String fisrstName, String lastName) {
		this.fisrstName = fisrstName;
		this.lastName = lastName;
	}

	


	/**
	 * @param userId
	 * @param userName
	 * @param fisrstName
	 * @param secondName
	 * @param lastName
	 */
	public AppUser(Long userId, String userName, String fisrstName, String secondName, String lastName) {
		//super();
		this.userId = userId;
		this.userName = userName;
		this.fisrstName = fisrstName;
		this.secondName = secondName;
		this.lastName = lastName;
	}





	/**
	 * @param userId
	 * @param userName
	 * @param fisrstName
	 * @param secondName
	 * @param lastName
	 * @param gender
	 * @param phoneNumber
	 * @param email
	 * @param jobTitle
	 * @param initials
	 */
	public AppUser(AppUser user) {
		//super();
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.fisrstName = user.getFisrstName();
		this.secondName = user.getSecondName();
		this.lastName = user.getLastName();
		this.gender = user.getGender();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.jobTitle = user.getJobTitle();
		this.initials = user.getInitials();
	}

	

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userRoles
	 */
	public Set<Role> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}








	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the initials
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * @param initials the initials to set
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}



	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the inactivationDate
	 */
	public Date getInactivationDate() {
		return inactivationDate;
	}

	/**
	 * @param inactivationDate the inactivationDate to set
	 */
	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}

	




	/**
	 * @return the credentialsExpirationDate
	 */
	public Date getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}



	/**
	 * @param credentialsExpirationDate the credentialsExpirationDate to set
	 */
	public void setCredentialsExpirationDate(Date credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}



	/**
	 * @return the accountExpirationDate
	 */
	public Date getAccountExpirationDate() {
		return accountExpirationDate;
	}



	/**
	 * @param accountExpirationDate the accountExpirationDate to set
	 */
	public void setAccountExpirationDate(Date accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
	}




	/* TRANSIENT FIELDS */

	@Transient
	private String positionIdentity;

	@Transient
	private String departmentIdentity;

	@Transient
	private String officeIdentity;

	@Transient
	private String[] roleIdentities;


	/**
	 * @return the accountNotLocked
	 */
	public boolean isAccountNotLocked() {
		return accountNotLocked;
	}







	/**
	 * @param accountNotLocked the accountNotLocked to set
	 */
	public void setAccountNotLocked(boolean accountNotLocked) {
		this.accountNotLocked = accountNotLocked;
	}







	/**
	 * @return the accountEnabled
	 */
	public boolean isAccountEnabled() {
		return accountEnabled;
	}







	/**
	 * @param accountEnabled the accountEnabled to set
	 */
	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}







	/**
	 * @return the positionIdentity
	 */
	public String getPositionIdentity() {
		return positionIdentity;
	}




	/**
	 * @param positionIdentity the positionIdentity to set
	 */
	public void setPositionIdentity(String positionIdentity) {
		this.positionIdentity = positionIdentity;
	}




	/**
	 * @return the departmentIdentity
	 */
	public String getDepartmentIdentity() {
		return departmentIdentity;
	}




	/**
	 * @param departmentIdentity the departmentIdentity to set
	 */
	public void setDepartmentIdentity(String departmentIdentity) {
		this.departmentIdentity = departmentIdentity;
	}




	/**
	 * @return the officeIdentity
	 */
	public String getOfficeIdentity() {
		return officeIdentity;
	}




	/**
	 * @param officeIdentity the officeIdentity to set
	 */
	public void setOfficeIdentity(String officeIdentity) {
		this.officeIdentity = officeIdentity;
	}







	/**
	 * @return the roleIdentities
	 */
	public String[] getRoleIdentities() {
		return roleIdentities;
	}







	/**
	 * @param roleIdentities the roleIdentities to set
	 */
	public void setRoleIdentities(String[] roleIdentities) {
		this.roleIdentities = roleIdentities;
	}












	/**
	 * @return the assignee
	 */
	public AppUser getAssignee() {
		return assignee;
	}







	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(AppUser assignee) {
		this.assignee = assignee;
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





}
