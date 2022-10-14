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

import com.musala.drones.model.Item;
import com.musala.drones.model.Trip;
import com.musala.drones.repository.TripDao;
import com.musala.drones.service.TripService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class TripServiceImplementation implements TripService {

	@Autowired
	private TripDao tripDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	private Trip trip;
	
	@Override
	public Trip addAndGetInstance(Trip trip) {
		return tripDao.saveAndFlush(trip);

	}
	@Override
	public void save(Trip trip) {
		 tripDao.save(trip);

	}

	@Override
	public void updateTrip(Trip trip) {
		// TODO Auto-generated method stub
		tripDao.save(trip);
	}

	@Override
	public void deleteTrip(Long id) {
		// TODO Auto-generated method stub
		tripDao.deleteById(id);
	}
	
	
	@Transactional
	@Override
	@SuppressWarnings("unchecked")

	public Trip findTripWithLoadItemsBySerialNumberNamedQuery(String serialNumber) {
		  trip= new Trip();
		  query = entityManager.createNamedQuery(
		 "TripWithLoadItemsBySerialNumberNamedQuery")
		 .setParameter("serialNumber", serialNumber);
		 
		   trip = (Trip) DataAccessUtils.singleResult(query.getResultList());;
		 
			   
			 
			   
			   if (trip != null) {
			
				Hibernate.initialize(trip.getLoad());
				Hibernate.initialize(trip.getLoad().getItems());
				Hibernate.initialize(trip.getLoad().getType());
			 
			}
		return trip;
		 
}
	

	
	
	
	
	
	
	

}
