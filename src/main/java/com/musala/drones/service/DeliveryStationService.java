package com.musala.drones.service;

/**
 * 
 */

import java.util.List;

import com.musala.drones.model.DeliveryStation;


/**
 * @author George J. Budeba
 * georgejbudeba@gmail.com
 *
 */
public interface DeliveryStationService {

	DeliveryStation addAndGetInstance(DeliveryStation deliveryStation);
	void save(DeliveryStation deliveryStation);

	void updateDeliveryStation(DeliveryStation deliveryStation);



	DeliveryStation findDeliveryStationByCodeNamedQuery(String code);
	void deleteDeliveryStation(Integer id);
	DeliveryStation findDeliveryStationByNameNamedQuery(String name);


}
