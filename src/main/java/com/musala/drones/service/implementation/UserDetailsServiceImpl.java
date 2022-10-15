/**
 * 
 */
package com.musala.drones.service.implementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.musala.drones.model.AppUser;
import com.musala.drones.repository.AppUserDao;



@Service("userDetailsServiceImpl")


//@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AppUserDao userDao;

	  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	  


    @Override
  //  @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	  System.out.println("username-:"+ userName + "\n");
    	  log.info("loadUserByUsername() : {}", userName);
    	AppUser user = null;
    	
    	 try {
			user = userDao.findByUserName(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  System.out.println("User record not found");
			e.printStackTrace();
		}
    	
        if(user==null){
            System.out.println("This User is not found");
            throw new UsernameNotFoundException("Username not found");
        }

      
        UserDetails userDetals = new CustomUserDetails(user);
        System.out.println("USER PASS"+userDetals.getPassword());
        System.out.println("USER USERNAME"+userDetals.getUsername());

        System.out.println("USER AUTH"+userDetals.getAuthorities());


       log.info("loadUserByUsername() : {}", userName);
        return userDetals;
        
    }
    
    
	
}
