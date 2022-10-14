package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.Drone;


/**
 * @author New
 *
 */
public interface DroneDao  extends JpaRepository<Drone, Long>{

}
