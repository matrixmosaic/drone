package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.musala.drones.model.State;
import com.musala.drones.repository.StateDao;
import com.musala.drones.service.StateService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class StateServiceImplementation implements StateService {

	@Autowired
	private StateDao stateDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	
	@Override
	public State addAndGetInstance(State state) {
		return stateDao.saveAndFlush(state);

	}
	@Override
	public void save(State state) {
		 stateDao.save(state);

	}

	@Override
	public void updateState(State state) {
		// TODO Auto-generated method stub
		stateDao.save(state);
	}

	@Override
	public void deleteState(Integer id) {
		// TODO Auto-generated method stub
		stateDao.deleteById(id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public State findStateByCodeNamedQuery(String  code) {
		 query = entityManager.createNamedQuery("StateByCodeNamedQuery")
	                .setParameter("code", code);

	        return (State) DataAccessUtils.singleResult(query.getResultList());
	}
	
	
	
	
	
	
	

}
