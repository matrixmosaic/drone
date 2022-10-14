package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.DeliveryStation;


/**
 * @author New
 *
 */
public interface DeliveryStationDao  extends JpaRepository<DeliveryStation, Integer>{

}
