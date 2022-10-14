package com.musala.drones.dto.operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.musala.drones.model.Load;
import com.musala.drones.model.Trip;

/**
 * @author George J. Budeba
 *
 */

public class TripDto {
	
	private Long tripId;
	
	private String droneSerialNumber;
	
	private String destinationCode;
	
	private String officeCode;
	
	private String statusCode;
	
    private LoadDto load;

    private BigDecimal distance;
	
    private Date departureDatetime;
	
	private Date arriveDatetime;
	
	private Date returnDatetime;

	/**
	 * 
	 */
	public TripDto() {
		super();
	}

	/**
	 * @param tripId
	 * @param droneSerialNumber
	 * @param destinationCode
	 * @param officeCode
	 * @param statusCode
	 * @param load
	 * @param distance
	 * @param departureDatetime
	 * @param arriveDatetime
	 * @param returnDatetime
	 */
	

	public TripDto(Trip trip) {
		this.tripId = trip.getTripId();
		this.droneSerialNumber =  trip.getDrone().getSerialNumber();
		this.destinationCode =  trip.getDestination().getCode();
		this.officeCode =  trip.getOffice().getCode();
		this.statusCode =  trip.getStatus().getCode();
		this.load =  new LoadDto(trip.getLoad());
		this.distance =  trip.getDistance();
		this.departureDatetime =  trip.getDepartureDatetime();
		this.arriveDatetime =  trip.getArriveDatetime();
		this.returnDatetime =  trip.getReturnDatetime();
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
	 * @return the droneSerialNumber
	 */
	public String getDroneSerialNumber() {
		return droneSerialNumber;
	}

	/**
	 * @param droneSerialNumber the droneSerialNumber to set
	 */
	public void setDroneSerialNumber(String droneSerialNumber) {
		this.droneSerialNumber = droneSerialNumber;
	}

	/**
	 * @return the destinationCode
	 */
	public String getDestinationCode() {
		return destinationCode;
	}

	/**
	 * @param destinationCode the destinationCode to set
	 */
	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	/**
	 * @return the officeCode
	 */
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * @param officeCode the officeCode to set
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	
	/**
	 * @return the load
	 */
	public LoadDto getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(LoadDto load) {
		this.load = load;
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

	
}
