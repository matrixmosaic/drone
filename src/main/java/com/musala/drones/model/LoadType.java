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
@Table(name = "df_load_type", schema="dn_dict")

@NamedQueries({
    @NamedQuery(name = "LoadTypeByCodeNamedQuery",
            query = "SELECT DISTINCT s " +
                    "FROM LoadType s " +
                    "WHERE s.code = :code"),
    
})
public class LoadType {
	@Id
	@Column(name = "load_type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loadTypeId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "type")
	private List<Load> loads;
	
	@Column(name = "name", length = 255)
	@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String name;
	
	@Column(name = "code", length = 255)
	@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String code;
	
	@Column(columnDefinition = "TEXT",name ="description")
	private String description;

	public LoadType() {
		super();
	}

	/**
	 * @return the loadTypeId
	 */
	public Integer getLoadTypeId() {
		return loadTypeId;
	}

	/**
	 * @param loadTypeId the loadTypeId to set
	 */
	public void setLoadTypeId(Integer loadTypeId) {
		this.loadTypeId = loadTypeId;
	}

	/**
	 * @return the loads
	 */
	public List<Load> getLoads() {
		return loads;
	}

	/**
	 * @param loads the loads to set
	 */
	public void setLoads(List<Load> loads) {
		this.loads = loads;
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
