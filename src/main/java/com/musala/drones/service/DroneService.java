package com.musala.drones.service;

/**
 * 
 */

import java.util.List;

import com.musala.drones.model.Drone;
import com.musala.drones.model.State;



/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface DroneService {

	Drone addAndGetInstance(Drone drone);
	void save(Drone drone);

	void updateDrone(Drone drone);

	void deleteDrone(Long id);


	Drone findDroneBySerialNumber(String serialNumber);
	List<Drone> findDroneByStateNumberNamedQuery(State state);
	List<Drone> findAllDroneNamedQuery();


}
