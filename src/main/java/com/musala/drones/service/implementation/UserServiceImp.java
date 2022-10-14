/**
 * 
 */
package com.musala.drones.service.implementation;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musala.drones.model.AppUser;
import com.musala.drones.model.Role;
import com.musala.drones.repository.AppUserDao;
import com.musala.drones.service.UserService;



/**
 * @author George J. Budeba
 *
 */
 @Service
public class UserServiceImp implements UserService{
 @PersistenceContext
	    private EntityManager entityManager;
	    
	@Autowired
	private AppUserDao userDao;
	private BCryptPasswordEncoder passw;
	
	@Override
	@Transactional
	public void addUser(AppUser user) {
		
		// PASSWORD CRYPTING
				 passw = new BCryptPasswordEncoder(10);
				user.setPassword(passw.encode(user.getPassword()));
	userDao.save(user);
		
	}

	


	

	
	@Transactional
	public void updateUser(AppUser user) {
		
		
		userDao.save(user);		
		
		/**end setting user */
	}

	@Transactional
	public void deleteUser(Long userId) {
		AppUser user = findUserById(userId);
		userDao.delete(user);
	}

	@Transactional
	public AppUser findUserById(Long userId) {
		return userDao.findById(userId).get();
	}

	//@Transactional
	public AppUser findByUserName(String firstName) {
		return userDao.findByUserName(firstName);
	}

	
	@Transactional
	public List<AppUser> findAllUsers() {
		return userDao.findAll();
	}

	@Transactional
	public String findUserNameById(Long userId) {
		return userDao.findById(userId).get().getUserName();
	}

	@Transactional
	public void saveUser(AppUser user) {
		String pass= user.getPassword();
		BCryptPasswordEncoder passw = new BCryptPasswordEncoder(11);
		String encodedPassw = passw.encode(pass);
		user.setPassword(encodedPassw );
		userDao.save(user);
		
	}

	
	@Transactional
	public AppUser  findByUserNameAndPassword(String userName,String password) {
		
		return userDao.findByUserNameAndPassword(userName, password);
	}
	
	@Transactional
	public boolean isUserEmailUnique(String email) {
		AppUser user = userDao.findByEmail(email);
		return ( user == null);
	}
	
	

	@Transactional
	public boolean isUserNameUnique(String userName) {
		AppUser user = userDao.findByUserName(userName);
		return ( user == null);
	}



public String getPrincipal() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	return authentication.getName();
}







@Override
public AppUser addAndGetInstance(AppUser appUserForm) {
	// TODO Auto-generated method stub
	 passw = new BCryptPasswordEncoder(10);
	 appUserForm.setPassword( passw.encode(appUserForm.getPassword()));
	return userDao.save(appUserForm);
}

@Transactional
@Override
public AppUser addAndGetUser(AppUser appUserForm) {
	// TODO Auto-generated method stub
	 passw = new BCryptPasswordEncoder(10);
	 appUserForm.setPassword( passw.encode(appUserForm.getPassword()));
	return userDao.saveAndFlush(appUserForm);
}

@Override
public AppUser saveInstance(AppUser appUserForm) {
	// TODO Auto-generated method stub
	 passw = new BCryptPasswordEncoder(10);
	 appUserForm.setPassword( passw.encode(appUserForm.getPassword()));
	return userDao.save(appUserForm);
}







@Override
public boolean isUsernameUccupied(String username) {
	// TODO Auto-generated method stub
	return (userDao.findFirstByUserNameIgnoreCase(username) !=null);
}







@SuppressWarnings("unchecked")
@Override
public AppUser findUserWithOfficeNQ(String principal) {
	// TODO Auto-generated method stub
	Query query = entityManager.createNamedQuery("userWithOfficeNamedQuery")
            .setParameter("userName", principal);

    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}







@Override
public Long findIdByUserName(String username) {
	// TODO Auto-generated method stub
	return null;
}







@Override
public AppUser findIdAndUsernameByUserName(String userName) {
	// TODO Auto-generated method stub
	return userDao.findIdAndUsernameByUserName(userName);
}







@SuppressWarnings("unchecked")
@Override
public AppUser findUserWithOfficeAndRolesByIdNQ(String userName) {
	// TODO Auto-generated method stub
			Query query = entityManager.createNamedQuery("userWithOfficeAndRolesByIdNamedQuery")
		            .setParameter("userName", userName);

		    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}

@SuppressWarnings("unchecked")
@Override
public AppUser findUserWithRolesByIdNQ(String userName) {
	// TODO Auto-generated method stub
			Query query = entityManager.createNamedQuery("userWithRolesByIdNamedQuery")
		            .setParameter("userName", userName);

		    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}





@SuppressWarnings("unchecked")
@Override
public AppUser findSingleUserByIdNQ(Long userId) {
	// TODO Auto-generated method stub
			Query query = entityManager.createNamedQuery("SingleUserById")
		            .setParameter("userId", userId);

		    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}

@Override
@SuppressWarnings("unchecked")
public AppUser findSingleUserByUsernameNQ(String userName) {
	// TODO Auto-generated method stub
			Query query = entityManager.createNamedQuery("SingleUserByUsername")
		            .setParameter("userName", userName);

		    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}



@SuppressWarnings("unchecked")
@Override
public List<AppUser> findUserAndActiveTasksAndRolesByOfficeCode(String code) {
	Query query  = entityManager.createNamedQuery("UserAndActiveTasksAndRolesByOfficeCode")
            .setParameter("code", code);

    return ( query.getResultList());
}


@SuppressWarnings("unchecked")
@Override
public List<AppUser> findUsersAndOfficeAndRolesByRolesCode(Set<Role> roles) {
	Query query  = entityManager.createNamedQuery("UsersAndOfficeAndRolesByRolesCode")
            .setParameter("roles", roles);

    return ( query.getResultList());
}




@SuppressWarnings("unchecked")
@Override
public List<AppUser> findAllUsersAndOfficeAndRoles() {
	Query query  = entityManager.createNamedQuery("AllUsersAndOfficeAndRoles");
         

    return ( query.getResultList());
}


@Override
@SuppressWarnings("unchecked")
public AppUser findSingleUserWithRolesAndOfficeByUsernameNamedQuery(String userName) {
	// TODO Auto-generated method stub
			Query query = entityManager.createNamedQuery("userWithRolesAndOfficeByUsernameNamedQuery")
		            .setParameter("userName", userName);

		    return (AppUser) DataAccessUtils.singleResult(query.getResultList());
}






}
