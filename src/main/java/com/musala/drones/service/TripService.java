package com.musala.drones.service;

/**
 * 
 */

import java.util.List;

import com.musala.drones.model.Trip;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface TripService {

	Trip addAndGetInstance(Trip trip);
	void save(Trip trip);

	void updateTrip(Trip trip);
    void deleteTrip(Long id);
	Trip findTripWithLoadItemsBySerialNumberNamedQuery(String serialNumber);


}
