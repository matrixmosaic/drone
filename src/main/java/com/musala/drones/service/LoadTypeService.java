package com.musala.drones.service;

/**
 * 
 */

import java.util.List;

import com.musala.drones.model.LoadType;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface LoadTypeService {

	LoadType addAndGetInstance(LoadType loadType);
	void save(LoadType loadType);

	void updateLoadType(LoadType loadType);



	LoadType findLoadTypeByCodeNamedQuery(String code);
	void deleteLoadType(Integer id);


}
