package com.musala.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.musala.drones.model.AppUser;


public interface AppUserDao extends JpaRepository<AppUser, Long>{

	 //@Query("select u from user u where u.userName like :userName")
	AppUser findByUserName(String userName);
	AppUser findByUserNameIgnoreCase(String userName);

	 
	// @Query("select u from user u where u.category like :category")
	AppUser findByEmail(String email);
	 
	AppUser findByUserNameAndPassword(String userName, String password);
	AppUser findFirstByUserNameIgnoreCase(String username);
	 
	/**
	 * @param userId
	 * @param userName
	 * @param fisrstName
	 * @param secondName
	 * @param lastName
	 */
	@Query("select new  com.musala.drones.model.AppUser(au.userId, au.userName, au.fisrstName, au.secondName, au.lastName) from AppUser au  where au.userName like :userName")
	AppUser findIdAndUsernameByUserName(@Param("userName") String userName);
	 }