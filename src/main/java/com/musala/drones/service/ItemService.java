package com.musala.drones.service;

import com.musala.drones.model.Item;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface ItemService {

	Item addAndGetInstance(Item item);
	void save(Item item);

	void updateItem(Item item);
    void deleteItem(Long id);


}
