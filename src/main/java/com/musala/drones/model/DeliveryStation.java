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
@Table(name = "df_station", schema="dn_dict")




@NamedQueries({
    @NamedQuery(name = "DeliveryStationByNameNamedQuery",
            query = "SELECT DISTINCT d " +
                    "FROM DeliveryStation d " +
                    "WHERE d.name = :name"),
    
})
public class DeliveryStation {
	@Id
	@Column(name = "station_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stationId;
	
	@Column(name = "name", length = 255)
	@Pattern(regexp = "[A-Za-z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String name;
	
	@Column(name = "code", length = 255)
	@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = " The name must contain only letters, numbers, ‘-‘, ‘_’ ")
	private String code;
	
	@Column(columnDefinition = "TEXT",name ="description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "destination")
	private List<Trip> trips;
	
	private Double longitude;
	private Double latitude;
	public DeliveryStation() {
		super();
	}
	/**
	 * @return the stationId
	 */
	public Integer getStationId() {
		return stationId;
	}
	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
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
	/**
	 * @return the trips
	 */
	public List<Trip> getTrips() {
		return trips;
	}
	/**
	 * @param trips the trips to set
	 */
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	
}
