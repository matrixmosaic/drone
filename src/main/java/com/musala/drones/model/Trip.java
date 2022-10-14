package com.musala.drones.model;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.musala.drones.dto.operation.TripDto;

/**
 * @author George J. Budeba
 *
 */
@Entity
@Table(name = "dm_trip", schema="dn_main")
@Audited
@AuditTable( value = "aud_trip", schema="dn_aud")

@NamedQueries({
	 @NamedQuery(name = "TripWithLoadItemsBySerialNumberNamedQuery", 
			  query =  "SELECT DISTINCT tp " + 
			  "FROM Trip tp " +
			  "LEFT JOIN FETCH  tp.drone dn " +
			  "LEFT JOIN FETCH  tp.load  ld " +
			  "LEFT JOIN FETCH  tp.destination   " +
			  "LEFT JOIN  ld.items  " +
			  "LEFT JOIN  ld.type  " +
			   "WHERE  dn.serialNumber  = :serialNumber " ),
   
})
public class Trip {
	@Id
	@Column(name = "trip_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tripId;
	

	
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "drone_id", nullable = true)
	private Drone drone;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "station_id", nullable = true)
	private DeliveryStation destination;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "office_id", nullable = true)
	private DeliveryStation office;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = true)
	private Status status;
	
	

    
    
	@OneToOne(optional = true, mappedBy = "trip", fetch = FetchType.LAZY)
	@JoinColumn(name = "load_id", nullable = true)
	private Load load;

    
    
    @Column(name = "distace", precision =12, scale = 4)
    private BigDecimal distance;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "departure_datetime")
	private Date departureDatetime;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "arrive_datetime")
	private Date arriveDatetime;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "return_datetime")
	private Date returnDatetime;

	/**
	 * 
	 */
	public Trip() {
		super();
	}

	public Trip(TripDto tripForm, Drone drone2, Load load) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the tripId
	 */
	public Long getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	/**
	 * @return the drone
	 */
	public Drone getDrone() {
		return drone;
	}

	/**
	 * @param drone the drone to set
	 */
	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	/**
	 * @return the destination
	 */
	public DeliveryStation getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(DeliveryStation destination) {
		this.destination = destination;
	}

	/**
	 * @return the office
	 */
	public DeliveryStation getOffice() {
		return office;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(DeliveryStation office) {
		this.office = office;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the distance
	 */
	public BigDecimal getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	/**
	 * @return the departureDatetime
	 */
	public Date getDepartureDatetime() {
		return departureDatetime;
	}

	/**
	 * @param departureDatetime the departureDatetime to set
	 */
	public void setDepartureDatetime(Date departureDatetime) {
		this.departureDatetime = departureDatetime;
	}

	/**
	 * @return the arriveDatetime
	 */
	public Date getArriveDatetime() {
		return arriveDatetime;
	}

	/**
	 * @param arriveDatetime the arriveDatetime to set
	 */
	public void setArriveDatetime(Date arriveDatetime) {
		this.arriveDatetime = arriveDatetime;
	}

	/**
	 * @return the returnDatetime
	 */
	public Date getReturnDatetime() {
		return returnDatetime;
	}

	/**
	 * @param returnDatetime the returnDatetime to set
	 */
	public void setReturnDatetime(Date returnDatetime) {
		this.returnDatetime = returnDatetime;
	}

	/**
	 * @return the load
	 */
	public Load getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(Load load) {
		this.load = load;
	}
	
	
	
}
