/**
 * 
 */
package com.musala.drones.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "df_drone_state", schema="dn_dict")
@NamedQueries({
    @NamedQuery(name = "StateByCodeNamedQuery",
            query = "SELECT DISTINCT s " +
                    "FROM State s " +
                    "WHERE s.code = :code"),
    
})

//(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
public class State {
	@Id
	@Column(name = "state_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stateId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	private List<Drone> drones;
	
	@Column(name = "name", length = 255)
	@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String name;
	
	@Column(name = "code", length = 255)
	@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String code;
	
	@Column(columnDefinition = "TEXT",name ="description")
	private String description;

	/**
	 * 
	 */
	public State() {
		super();
	}

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the drones
	 */
	public List<Drone> getDrones() {
		return drones;
	}

	/**
	 * @param drones the drones to set
	 */
	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	
	
}
