/**
 * 
 */
package com.musala.drones.config.scheduller;


import java.math.BigDecimal;
import java.text.DateFormat;
/**
 * @author George J. Budeba
 *
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.drones.model.Drone;
import com.musala.drones.service.DroneService;

@Component
public class JobScheduller {

	private static final Logger log = LoggerFactory.getLogger(JobScheduller.class);

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	 List<Drone> drones;
	 @Autowired
	 DroneService  droneService;
	
    //  periodic task to check drones battery levels, auto runs at 12:00 AM every day
    @Scheduled(cron="0 0 0 * * *")
	public void reportCurrentTime() {
    	
    	drones = new ArrayList<>();
    	drones = droneService.findAllDroneNamedQuery();
		  
		 
    	  if (drones != null ) {
    		  log.info( "DRONE BATTERY LEVEL STAUS AS OF "+ dayFormat.format(new Date()),dateFormat.format(new Date()));
    		for(Drone drone:drones) {
    			
    			
    			if(drone.getBatteryCapacity().compareTo(BigDecimal.valueOf(25)) < 0) {
    				
    				log.warn( drone.toString(),dateFormat.format(new Date()));
    			}
    			else {
    				
    				log.info( drone.toString(), dateFormat.format(new Date()));
    			}
    		}

    		}
    	
		
	}
}
