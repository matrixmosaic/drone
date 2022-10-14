package com.musala.drones.config.persistance;

/**
 * 
 */

import org.hibernate.envers.RevisionListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.musala.drones.model.DroneRevisionEntity;


/**
 * @author New
 *
 */

@Component
@Configuration
public class DroneRevisionListener implements RevisionListener {
	
	@Override
	public void newRevision(Object revisionEntity) {
		DroneRevisionEntity rev = (DroneRevisionEntity) revisionEntity;
		String ipAddress = null;
		
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		
		Object details =
			    SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		
	        try {
				if (authentication instanceof UserDetails) {
				    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				    rev.setUserName(userDetails.getUsername());
				} else {
				    rev.setUserName(authentication.getName());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (details instanceof WebAuthenticationDetails)
			    ipAddress = ((WebAuthenticationDetails) details).getRemoteAddress();

	     	if (ipAddress != null) {
			try {
				rev.setSourceIp(ipAddress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	

	}

}
