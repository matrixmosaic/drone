package com.musala.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drones.model.Role;


public interface RoleDao extends JpaRepository<Role, Integer>{

	 //@Query("select u from role u where u.roleName like :roleName")
	 Role findByRoleName(String roleName);
	 
	// @Query("select u from role u where u.category like :category")
	 List<Role> findByDescription(String description);

	Role findFirstByRoleNameIgnoreCase(String roleName);
	 
	 //Role findByRoleNameAndPassword(String roleName, String password);
	 
		
	 }