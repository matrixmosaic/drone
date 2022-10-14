package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.musala.drones.model.DeliveryStation;
import com.musala.drones.repository.DeliveryStationDao;
import com.musala.drones.service.DeliveryStationService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class DeliveryStationServiceImplementation implements DeliveryStationService {

	@Autowired
	private DeliveryStationDao deliveryStationDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	
	@Override
	public DeliveryStation addAndGetInstance(DeliveryStation deliveryStation) {
		return deliveryStationDao.saveAndFlush(deliveryStation);

	}
	@Override
	public void save(DeliveryStation deliveryStation) {
		 deliveryStationDao.save(deliveryStation);

	}

	@Override
	public void updateDeliveryStation(DeliveryStation deliveryStation) {
		// TODO Auto-generated method stub
		deliveryStationDao.save(deliveryStation);
	}

	@Override
	public void deleteDeliveryStation(Integer id) {
		// TODO Auto-generated method stub
		deliveryStationDao.deleteById(id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public DeliveryStation findDeliveryStationByNameNamedQuery(String  name) {
		 query = entityManager.createNamedQuery("DeliveryStationByNameNamedQuery")
	                .setParameter("name", name);

	        return (DeliveryStation) DataAccessUtils.singleResult(query.getResultList());
	}
	@Override
	public DeliveryStation findDeliveryStationByCodeNamedQuery(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

}
