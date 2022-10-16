package com.musala.drones.service;

import com.musala.drones.model.State;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface StateService {

	State addAndGetInstance(State state);
	void save(State state);

	void updateState(State state);



	State findStateByCodeNamedQuery(String code);
	void deleteState(Integer id);


}
