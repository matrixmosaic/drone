/**
 * 
 */
package com.musala.drones.service;

import java.util.List;
import java.util.Set;

import com.musala.drones.model.AppUser;
import com.musala.drones.model.Role;



/**
 * @author George J. Budeba
 *
 */
public interface UserService {

	
	
	//void addUser(  String userName, String password,  String region,  String district,String email,   String phone, Set<Role> userRoles, boolean active);
	
	
	void updateUser(AppUser user);
	
	void deleteUser(Long userId);
	
	AppUser findUserById(Long userId);
	
	AppUser findByUserName(String username);
	
	boolean isUsernameUccupied(String username);
	
	public String getPrincipal();
	
	AppUser findByUserNameAndPassword(String userName,String password);
	
	public void saveUser(AppUser user) ;
	
	List<AppUser> findAllUsers();
	
	 boolean isUserEmailUnique(String email);
	
	boolean isUserNameUnique(String userName); 
	
	String findUserNameById(Long userId);

	Long  findIdByUserName(String username);

	void addUser(AppUser user);

	AppUser addAndGetInstance(AppUser appUserForm);

	AppUser findUserWithOfficeNQ(String principal);
	AppUser findIdAndUsernameByUserName(String userName);

	AppUser findUserWithOfficeAndRolesByIdNQ(String userName);

	AppUser findUserWithRolesByIdNQ(String userName);


	AppUser findSingleUserByIdNQ(Long userId);

	AppUser findSingleUserByUsernameNQ(String userName);

	AppUser saveInstance(AppUser appUserForm);

	AppUser addAndGetUser(AppUser appUserForm);

	List<AppUser> findUserAndActiveTasksAndRolesByOfficeCode(String code);

	AppUser findSingleUserWithRolesAndOfficeByUsernameNamedQuery(String userName);

	List<AppUser> findUsersAndOfficeAndRolesByRolesCode(Set<Role> roles);

	List<AppUser> findAllUsersAndOfficeAndRoles();
	
}
