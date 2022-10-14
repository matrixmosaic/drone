package com.musala.drones.service;

/**
 * 
 */

import java.util.List;

import com.musala.drones.model.Model;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface ModelService {

	Model addAndGetInstance(Model model);
	void save(Model model);

	void updateModel(Model model);



	Model findModelByCodeNamedQuery(String code);
	void deleteModel(Integer id);


}
