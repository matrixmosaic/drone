package com.musala.drones.dto.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.musala.drones.model.Drone;
import com.musala.drones.model.Model;
import com.musala.drones.model.State;
import com.musala.drones.model.Trip;
/**
 * @author George J. Budeba
 *
 */

public class DroneDto {
	

	private Long droneId;

	private String serialNumber;
	

	private String model;
	
	private String state;

	private BigDecimal batteryCapacity;
	
    @Column(name = "weight", scale = 4, precision = 7)
    private BigDecimal weight;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drone")
	private List<Trip> trips;
	

	/**
	 * 
	 */
	public DroneDto() {
		super();
	}

	/**
	 * @param droneId
	 * @param serialNumber
	 * @param model
	 * @param state
	 * @param batteryCapacity
	 * @param weight
	 * @param trips
	 */
	public DroneDto(Long droneId, String serialNumber, String model, String state, BigDecimal batteryCapacity,
			BigDecimal weight, List<Trip> trips) {
		super();
		this.droneId = droneId;
		this.serialNumber = serialNumber;
		this.model = model;
		this.state = state;
		this.batteryCapacity = batteryCapacity;
		this.weight = weight;
		this.trips = trips;
	}

	public DroneDto(Drone drone) {
		this.droneId = drone.getDroneId();
		this.serialNumber = drone.getSerialNumber();
		this.model = drone.getModel().getCode();
		this.state = drone.getState().getCode();
		this.batteryCapacity = drone.getBatteryCapacity();
		this.weight = drone.getWeight();
		//this.trips = trips;
	}

	/**
	 * @return the droneId
	 */
	public Long getDroneId() {
		return droneId;
	}

	/**
	 * @param droneId the droneId to set
	 */
	public void setDroneId(Long droneId) {
		this.droneId = droneId;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	/**
	 * @return the batteryCapacity
	 */
	public BigDecimal getBatteryCapacity() {
		return batteryCapacity;
	}

	/**
	 * @param batteryCapacity the batteryCapacity to set
	 */
	public void setBatteryCapacity(BigDecimal batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	/**
	 * @return the weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
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
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	public static List<DroneDto> getList(List<Drone> drones) {
		List<DroneDto> droneDtos = new ArrayList<>();
		for(Drone drone : drones) {
			droneDtos.add(new DroneDto(drone));
		}
		return droneDtos;
	}

	
	
	
}
