package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drones.model.Drone;


/**
 * @author New
 *
 */
@Repository
public interface DroneDao  extends JpaRepository<Drone, Long>{

}
