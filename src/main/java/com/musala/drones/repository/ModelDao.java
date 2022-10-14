package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.Model;


/**
 * @author New
 *
 */
public interface ModelDao  extends JpaRepository<Model, Integer>{

}
