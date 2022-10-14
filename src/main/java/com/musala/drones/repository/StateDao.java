package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.State;


/**
 * @author New
 *
 */
public interface StateDao  extends JpaRepository<State, Integer>{

}
