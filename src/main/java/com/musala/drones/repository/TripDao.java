package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.Trip;


/**
 * @author New
 *
 */
public interface TripDao  extends JpaRepository<Trip, Long>{

}
