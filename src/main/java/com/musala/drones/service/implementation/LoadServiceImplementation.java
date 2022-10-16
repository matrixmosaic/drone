package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drones.model.Load;
import com.musala.drones.repository.LoadDao;
import com.musala.drones.service.LoadService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class LoadServiceImplementation implements LoadService {

	@Autowired
	private LoadDao loadDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;

	
	@Override
	public Load addAndGetInstance(Load load) {
		return loadDao.saveAndFlush(load);

	}
	@Override
	public void save(Load load) {
		 loadDao.save(load);

	}

	@Override
	public void updateLoad(Load load) {
		// TODO Auto-generated method stub
		loadDao.save(load);
	}

	@Override
	public void deleteLoad(Long id) {
		// TODO Auto-generated method stub
		loadDao.deleteById(id);
	}
	
	
	

	
	
	
	
	
	
	

}
