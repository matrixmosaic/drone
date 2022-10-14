package com.musala.drones.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.musala.drones.model.Drone;
import com.musala.drones.model.State;
import com.musala.drones.repository.DroneDao;
import com.musala.drones.service.DroneService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class DroneServiceImplementation implements DroneService {

	@Autowired
	private DroneDao droneDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	private Drone drone;
	
	@Override
	public Drone addAndGetInstance(Drone drone) {
		return droneDao.saveAndFlush(drone);

	}
	@Override
	public void save(Drone drone) {
		 droneDao.save(drone);

	}

	@Override
	public void updateDrone(Drone drone) {
		// TODO Auto-generated method stub
		droneDao.save(drone);
	}

	@Override
	public void deleteDrone(Long id) {
		// TODO Auto-generated method stub
		droneDao.deleteById(id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Drone findDroneBySerialNumber(String serialNumber) {
		 query = entityManager.createNamedQuery("DroneBySerialNumberNamedQuery")
	                .setParameter("serialNumber", serialNumber);

	        return (Drone) DataAccessUtils.singleResult(query.getResultList());
	}
	
	
	@Transactional
	@Override
	@SuppressWarnings("unchecked")

	public List<Drone> findDroneByStateNumberNamedQuery(State state) {
		  query = entityManager.createNamedQuery(
		 "DroneByStateNumberNamedQuery")
		 .setParameter("state", state);
		  
		  return query.getResultList();
		 
}
	
	
	
	@Transactional
	@Override
	@SuppressWarnings("unchecked")

	public List<Drone> findAllDroneNamedQuery() {
		  query = entityManager.createNamedQuery(
		 "AllDroneNamedQuery"); 
		  
		  
		  return query.getResultList();
		 
}
	
	
	

}
