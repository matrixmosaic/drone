package com.musala.drones.service;

import java.util.List;

import com.musala.drones.model.Role;



public interface RoleService {

	
	void addRole(Role role) ;
	
	void editRole(int roleId,   String name, String description);
	
	void deleteRole(int roleId);
	
	Role findRoleById(int roleId);
	
	//List<Role> findAllByRoleName(String rolename);
	
	List<Role> findAllRoles();
	
	Role findRoleByName(String rolename);
	
	String findRoleNameById(int roleId);

	Role addAndGetInstance( Role roleForm);

	boolean isRoleNameOccupied(String roleName);

	

	Role getRoleByIdNQ(Integer roleId);

	List<Role> getRoleByIdLListNQ(List<Integer> roleIds);

	Role getRoleByCodeNamedQuery(String roleCode);
}

