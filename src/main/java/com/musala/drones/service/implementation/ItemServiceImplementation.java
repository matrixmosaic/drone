package com.musala.drones.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drones.model.Item;
import com.musala.drones.repository.ItemDao;
import com.musala.drones.service.ItemService;




/**
 * @author George J. Budeba
 *
 */
@Service
public class ItemServiceImplementation implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	Query query;
	
	@Override
	public Item addAndGetInstance(Item item) {
		return itemDao.saveAndFlush(item);

	}
	@Override
	public void save(Item item) {
		 itemDao.save(item);

	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.save(item);
	}

	@Override
	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		itemDao.deleteById(id);
	}
	
	
	

	
	
	
	
	
	
	

}
