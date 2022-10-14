/**
 * 
 */
package com.musala.drones.service.implementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.musala.drones.service.SecurityService;


/**
 * @author George J. Budeba
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService{
  //  @Autowired
   // private AuthenticationManager authManager;
    
   
    //@Autowired
   // private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    
    @Bean(name ="authenticationManagerBean")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    
    
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() throws Exception {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(this.userDetailsServiceImplBean());
	        authProvider.setPasswordEncoder(this.passwordEncoder());
	        return authProvider;
	    }
	    
	  
	    
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(this.userDetailsServiceImplBean()).passwordEncoder(passwordEncoder());
	    }
	    
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder(10);
	    };
	    
	    
		   @Autowired
		    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		    	
		    	 auth.userDetailsService(this.userDetailsServiceImplBean()).passwordEncoder(this.passwordEncoder());
		    }
    
    
    
    
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = null;
		try {
			userDetails = this.userDetailsServiceImplBean().loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("autologi username:"+ username + "\n");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

      //  authManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
            System.out.println(" SecurityContextHolder.getContext() username:"+ username + "\n");
        }
    }
    
    
    
    public UserDetails getUserDetailsByUserName(String username) {
        UserDetails userDetails = null;
		try {
			userDetails = this.userDetailsServiceImplBean().loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("UsernameNotFoundException Failed for:"+ username + "\n");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getUserDetailsByUserName Failed for:"+ username + "\n");
			e.printStackTrace();
		}
    
		return userDetails;
    }
          
    
    @Bean
    public UserDetailsServiceImpl userDetailsServiceImplBean() throws Exception {
        return new UserDetailsServiceImpl();
    }
    
}
