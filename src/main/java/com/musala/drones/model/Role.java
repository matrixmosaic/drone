package com.musala.drones.model;



import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "dm_approle", schema = "dn_main")
@Audited
@AuditTable( value = "aud_approle", schema="dn_aud")
@NamedQueries({
    @NamedQuery(name = "RoleByIdNamedQuery",
            query = "SELECT DISTINCT r " +
                    "FROM Role r " +
                    "WHERE r.roleId = :roleId"),
    
    
  
        @NamedQuery(name = "RoleByCodeNamedQuery",
                query = "SELECT DISTINCT r " +
                        "FROM Role r " +
                        "WHERE r.roleCode = :roleCode"),
    
    @NamedQuery(name = "RoleByIdListNamedQuery",
    query = "SELECT DISTINCT r " +
            "FROM Role r " +
            "WHERE r.roleId IN :roleIds")
})



@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6202579465929327766L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	
	@Column(name="role_name", length = 100)
	private String roleName;
	
	@Column(name="role_code", length = 100)
	private String roleCode;
	
	@Column(name="description", length = 200)
	private String description;
	


	@NotAudited
	@ManyToMany(mappedBy="userRoles")
	private Set<AppUser> appUser; // = new HashSet;
	


	/**
	 * 
	 */
	public Role() {
		super();
	}
	
	

	














	



	/**
	 * @param roleId
	 * @param roleName
	 * @param roleCode
	 * @param description
	 */
	public Role(Role role) {
		this.roleId = role.getRoleId();
		this.roleName = role.getRoleName();
		this.roleCode = role.getRoleCode();
		this.description = role.getDescription();
	}






















	/**
	 * @param roleId
	 * @param roleName
	 * @param roleCode
	 * @param description
	 */
	public Role(Integer roleId, String roleName, String roleCode, String description) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.description = description;
	}








	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the appUser
	 */
	public Set<AppUser> getAppUser() {
		return appUser;
	}

	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(Set<AppUser> appUser) {
		this.appUser = appUser;
	}






















	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", description="
				+ description + "]";
	}






















	@Override
	public int hashCode() {
		return Objects.hash(description, roleCode, roleId, roleName);
	}






















	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(description, other.description) && Objects.equals(roleCode, other.roleCode)
				&& Objects.equals(roleId, other.roleId) && Objects.equals(roleName, other.roleName);
	}






























}
