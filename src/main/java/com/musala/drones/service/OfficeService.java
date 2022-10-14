/**
 * 
 */
package com.musala.drones.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.musala.drones.model.Office;



/**
 * @author George J. Budeba
 *
 */
public interface OfficeService  {
	
	void addOffice(Office office);
	Office addAndGetInstance(Office office);
	
	List<Office> findByNameIgnoreCase(String OfficeName);
	
	 List<Office> findByNameIgnoreCase(String name, Sort sort);

	 List<Office> findByEmail(String email);
	 List<Office> findByFax(String  fax);
	 List<Office> findByHeadName(String headName);
	 List<Office> findByName(String name);
	 List<Office> findByOfficeType(String name);
	 
	 List<Office> findByPostalAddress(String postalAddress);
	
	 
	 Office findByOfficeId(Long officeId);

	Office findByUid(String uid);
	
	Office findByCode(String code);

	 Page<Office> findAll(Pageable page);
	 List<Office> findAll();

      List<Office> findTop12ByName(String name);
      List<Office> findOfficeListNMQ();
   

  
}
