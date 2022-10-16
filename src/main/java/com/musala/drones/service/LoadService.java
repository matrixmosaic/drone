package com.musala.drones.service;

import com.musala.drones.model.Load;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface LoadService {

	Load addAndGetInstance(Load load);
	void save(Load load);

	void updateLoad(Load load);
    void deleteLoad(Long id);


}
