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
@Table(name = "df_load_model", schema="dn_dict")

@NamedQueries({
    @NamedQuery(name = "ModelByCodeNamedQuery",
            query = "SELECT DISTINCT m " +
                    "FROM Model m " +
                    "WHERE m.code = :code"),
    
})


//Lightweight, Middleweight, Cruiserweight, Heavyweight
public class Model {
	@Id
	@Column(name = "model_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer modelId;
	
	
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
private List<Drone> drones;

@Column(name = "name", length = 255)
@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
private String name;

@Column(name = "code", length = 255)
@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
private String code;

@Column(columnDefinition = "TEXT",name ="description")
private String description;

public Model() {
	super();
}

/**
 * @return the modelId
 */
public Integer getModelId() {
	return modelId;
}

/**
 * @param modelId the modelId to set
 */
public void setModelId(Integer modelId) {
	this.modelId = modelId;
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
