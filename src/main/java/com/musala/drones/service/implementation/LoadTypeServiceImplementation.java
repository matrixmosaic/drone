package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.musala.drones.model.LoadType;
import com.musala.drones.repository.LoadTypeDao;
import com.musala.drones.service.LoadTypeService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class LoadTypeServiceImplementation implements LoadTypeService {

	@Autowired
	private LoadTypeDao loadTypeDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	
	@Override
	public LoadType addAndGetInstance(LoadType loadType) {
		return loadTypeDao.saveAndFlush(loadType);

	}
	@Override
	public void save(LoadType loadType) {
		 loadTypeDao.save(loadType);

	}

	@Override
	public void updateLoadType(LoadType loadType) {
		// TODO Auto-generated method stub
		loadTypeDao.save(loadType);
	}

	@Override
	public void deleteLoadType(Integer id) {
		// TODO Auto-generated method stub
		loadTypeDao.deleteById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LoadType findLoadTypeByCodeNamedQuery(String code) {
		// TODO Auto-generated method stub
		 query = entityManager.createNamedQuery("LoadTypeByCodeNamedQuery")
	                .setParameter("code", code);

	        return (LoadType) DataAccessUtils.singleResult(query.getResultList());
	}
	
	
	

	
	
	
	
	
	
	

}
