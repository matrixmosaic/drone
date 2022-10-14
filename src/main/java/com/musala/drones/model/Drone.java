package com.musala.drones.model;

import java.math.BigDecimal;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.musala.drones.dto.operation.DroneDto;
/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "dm_drone", schema="dn_main")
@Audited
@AuditTable( value = "aud_drone", schema="dn_aud")




@NamedQueries({
    @NamedQuery(name = "DroneBySerialNumberNamedQuery",
            query = "SELECT DISTINCT d " +
                    "FROM Drone d " +
                    "WHERE d.serialNumber = :serialNumber"),
    
    @NamedQuery(name = "DroneByStateNumberNamedQuery",
    query = "SELECT DISTINCT d " +
            "FROM Drone d " +
            "LEFT JOIN FETCH  d.model   " +
            "LEFT JOIN FETCH  d.state   " +
            "WHERE d.state = :state"),
    
    @NamedQuery(name = "AllDroneNamedQuery",
    query = "SELECT DISTINCT d " +
            "FROM Drone d "),
    
})



public class Drone {
	
	@Id
	@Column(name = "drone_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer droneId;
	
	@Column(name = "serial_number", length = 255)
	private String serialNumber;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id", nullable = true)
	private Model model;
	
	@Column(name = "battery_capacit", scale = 2, precision = 4) //battery capacity (percentage);
	private BigDecimal batteryCapacity;
	
    @Column(name = "weight", scale = 4, precision = 7)
    private BigDecimal weight;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", nullable = true)
	private State state;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drone")
	private List<Trip> trips;

	/**
	 * 
	 */
	public Drone() {
		super();
	}
	

	
	public static String getString(Drone drone, Model model, State state) {
		return "Drone [droneId=" + drone.getDroneId() + ", serialNumber=" + drone.getSerialNumber() + ", model=" + model.getName()
				+ ", batteryCapacity=" + drone.getBatteryCapacity() + ", weight=" + drone.getWeight() + ", state=" + state + "]";
	}
	
	


	@Override
	public String toString() {
		return "Drone [serialNumber=" + serialNumber + ", batteryCapacity=" + batteryCapacity + "]";
	}



	/**
	 * @param droneId
	 * @param serialNumber
	 * @param model
	 * @param batteryCapacity
	 * @param weight
	 * @param state
	 * @param trips
	 */
	public Drone(DroneDto droneForm, Model model,State state) {
		super();
		this.droneId = droneForm.getDroneId();
		this.serialNumber = droneForm.getSerialNumber();
		this.model = model;
		this.batteryCapacity = droneForm.getBatteryCapacity();
		this.weight = droneForm.getWeight();
		this.state = state;
		
	}




	public static Drone setDrone(Drone drone, DroneDto droneForm, Model model, State state) {
		if (droneForm.getSerialNumber() != null) {
			//drone.droneId = drone.getDroneId();
			drone.serialNumber = droneForm.getSerialNumber();
		}
		if (model != null) {
			drone.model = model;
		}
		if (droneForm.getBatteryCapacity() != null) {
			drone.batteryCapacity = droneForm.getBatteryCapacity();
		}
		if (droneForm.getWeight() != null) {
			drone.weight = droneForm.getWeight();
		}
		if (state != null) {
			drone.state = state;
		}
		return drone;
	}



	/**
	 * @return the droneId
	 */
	public Integer getDroneId() {
		return droneId;
	}

	/**
	 * @param droneId the droneId to set
	 */
	public void setDroneId(Integer droneId) {
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
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
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
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
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

	
	
	
}
