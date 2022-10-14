package com.musala.drones.repository;
/**
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.Item;


/**
 * @author New
 *
 */
public interface ItemDao  extends JpaRepository<Item, Long>{

}
