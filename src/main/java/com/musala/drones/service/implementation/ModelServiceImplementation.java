package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.musala.drones.model.Model;
import com.musala.drones.repository.ModelDao;
import com.musala.drones.service.ModelService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class ModelServiceImplementation implements ModelService {

	@Autowired
	private ModelDao modelDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;

	
	@Override
	public Model addAndGetInstance(Model model) {
		return modelDao.saveAndFlush(model);

	}
	@Override
	public void save(Model model) {
		 modelDao.save(model);

	}

	@Override
	public void updateModel(Model model) {
		// TODO Auto-generated method stub
		modelDao.save(model);
	}

	@Override
	public void deleteModel(Integer id) {
		// TODO Auto-generated method stub
		modelDao.deleteById(id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Model findModelByCodeNamedQuery(String code) {
		 query = entityManager.createNamedQuery("ModelByID")
				 .setParameter("code", code);

	        return (Model) DataAccessUtils.singleResult(query.getResultList());
	}
	
	
	
	
	
	
	

}
