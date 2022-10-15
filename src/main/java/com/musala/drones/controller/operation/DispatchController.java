/**
 * 
 */
package com.musala.drones.controller.operation;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drones.dto.global.GeneralStatus;
import com.musala.drones.dto.global.GenericOperationResponse;
import com.musala.drones.dto.operation.DroneDto;
import com.musala.drones.dto.operation.TripDto;
import com.musala.drones.model.Drone;
import com.musala.drones.model.Item;
import com.musala.drones.model.Load;
import com.musala.drones.model.LoadType;
import com.musala.drones.model.Model;
import com.musala.drones.model.State;
import com.musala.drones.model.Trip;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.ItemService;
import com.musala.drones.service.LoadService;
import com.musala.drones.service.LoadTypeService;
import com.musala.drones.service.ModelService;
import com.musala.drones.service.StateService;
import com.musala.drones.service.TripService; 

/**
 * @author George J. Budeba george.bugeba@lands.go.tz
 *
 */

@RestController
@RequestMapping("api/ops")
public class DispatchController {
	
	/* START ADD TRANSACTION DOCUMENT */
	HttpHeaders responseHeaders;
	 Drone drone;
	 Model model;
	 State state;
	 @Autowired
	 DroneService  droneService;
	 @Autowired
	 StateService stateService;
	 
	 @Autowired
	 ModelService modelService;
	 
	 @Autowired
	 ItemService itemService;
	static final Logger logger = Logger.getLogger(DispatchController.class)  ;  //.getLogger(DispatchController.class);
	 
	 @Autowired
	 TripService tripService;
	 
	 
	 @Autowired
	 LoadService  loadService;
	private Load load;
	
	 @Autowired
	 LoadTypeService  loadTypeService;
	private LoadType loadType;
      private Item item;
	private Trip trip;
	private List<Drone> drones;

	
	@GetMapping("/hello")
	String sayHello() {
		
		return "Hello";
	}
	
	
	

	/*registerDrone*/

	@CrossOrigin
	@RequestMapping(value = "/drone/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	ResponseEntity<GenericOperationResponse> registerDrone(@RequestBody DroneDto droneForm) {
		responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (droneForm == null || droneForm.getModel() == null) {

			return new ResponseEntity<>(new GenericOperationResponse( "400", "Drone informtion cannot be null"),
					responseHeaders, HttpStatus.BAD_REQUEST);
	
		}
		
		  model = modelService.findModelByCodeNamedQuery(droneForm.getModel());
		  
		  if(state == null) {
			  
			  state = stateService.findStateByCodeNamedQuery("IDLE");

		  }
		  else {
			  
			  state = stateService.findStateByCodeNamedQuery(droneForm.getState());

		  }
		  drone = new  Drone(droneForm, model, state);
		  droneService.addAndGetInstance(drone);
		  
		  
		return new ResponseEntity<>(new GenericOperationResponse( "200", "Drone Registered successifully" ), responseHeaders,
				HttpStatus.OK);


	}

	/*End registerDrone*/
	
	
	/*updateDrone*/

	@CrossOrigin
	@RequestMapping(value = "/drone/update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	ResponseEntity<GenericOperationResponse> updateDrone(@RequestBody DroneDto droneForm) {
		responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (droneForm == null || droneForm.getModel() == null || droneForm.getSerialNumber() == null) {

			return new ResponseEntity<>(new GenericOperationResponse( "400", "Drone informtion cannot be null"),
					responseHeaders, HttpStatus.BAD_REQUEST);
	
		}
		
		  model = modelService.findModelByCodeNamedQuery(droneForm.getModel());
		  
		  if(state == null) {
			  
			  state = stateService.findStateByCodeNamedQuery("IDLE");

		  }
		  else {
			  
			  state = stateService.findStateByCodeNamedQuery(droneForm.getState());

		  }
		   if( (droneForm.getWeight()).compareTo(BigDecimal.valueOf(500)) > 0) {
				return new ResponseEntity<>(new GenericOperationResponse( "400", "Drone weight should not exceed 500gm!"),
						responseHeaders, HttpStatus.BAD_REQUEST);
			 
		  }
		   
		   
		   drone = droneService.findDroneBySerialNumber(droneForm.getSerialNumber());
			  
			  //= modelService.findModelByCodeNamedQuery(tripForm.getModel());
	  
	  if (drone == null ) {

			return new ResponseEntity<>(new GenericOperationResponse( "400", "No drone found in the records"),
					responseHeaders, HttpStatus.BAD_REQUEST);
	
		}
		  drone =   Drone.setDrone(drone, droneForm, model, state);
		  droneService.addAndGetInstance(drone);
		  
		  
		return new ResponseEntity<>(new GenericOperationResponse( "200", "Drone Updated successifully" ), responseHeaders,
				HttpStatus.OK);


	}

	/*End updateDrone*/
	
	
	/*loadDrone
	 * 
	 * loading a drone with medication items;
	 * 
	 * */

	@CrossOrigin
	@RequestMapping(value = "/drone/load", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	ResponseEntity<GenericOperationResponse>  loadDrone(@RequestBody TripDto tripForm) {
		responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (tripForm == null || tripForm.getDroneSerialNumber() == null || tripForm.getLoad() == null) {

			return new ResponseEntity<>(new GenericOperationResponse( "400", "Drone informtion cannot be null"),
					responseHeaders, HttpStatus.BAD_REQUEST);
	
		}
		
		  if (tripForm.getLoad().getCode() == null ) {

				return new ResponseEntity<>(new GenericOperationResponse( "400", "Please specify the value foe the load type"),
						responseHeaders, HttpStatus.BAD_REQUEST);
		
			}
		
		  drone = droneService.findDroneBySerialNumber(tripForm.getDroneSerialNumber());
				  
				  //= modelService.findModelByCodeNamedQuery(tripForm.getModel());
		  
		  if (drone == null ) {

				return new ResponseEntity<>(new GenericOperationResponse( "400", "Drone informtion cannot be null"),
						responseHeaders, HttpStatus.BAD_REQUEST);
		
			}
		  
		  
		  if(tripForm.getLoad() == null) {
				return new ResponseEntity<>(new GenericOperationResponse( "400", "Load informtion cannot be null!"),
						responseHeaders, HttpStatus.BAD_REQUEST);
			 
		  }
		  
		  
		 if(!(tripForm.getLoad().getName().matches("[A-Za-z0-9\\-\\_]+"))){
			 return new ResponseEntity<>(new GenericOperationResponse( "400", " The load name must contain only letters, numbers, ‘-‘, ‘_’ "),
						responseHeaders, HttpStatus.BAD_REQUEST);  
		  }
		 
		 
		  
			 if(!(tripForm.getLoad().getCode().matches("[A-Z0-9\\-\\_]+"))){
				 return new ResponseEntity<>(new GenericOperationResponse( "400", " The load code must contain only letters, numbers, ‘-‘, ‘_’ "),
							responseHeaders, HttpStatus.BAD_REQUEST);  
			  }
		  
			 
			 if( (tripForm.getLoad().getWeight()).compareTo(BigDecimal.valueOf(500)) > 0) {
					return new ResponseEntity<>(new GenericOperationResponse( "400", "The load should not exceed 500gm!"),
							responseHeaders, HttpStatus.BAD_REQUEST);
				 
			  }
		  
	          loadType = loadTypeService.findLoadTypeByCodeNamedQuery(tripForm.getLoad().getCode());
	          
	          if(loadType == null) {
					return new ResponseEntity<>(new GenericOperationResponse( "400", "Load type information could not be found!"),
							responseHeaders, HttpStatus.BAD_REQUEST);
				 
			  }
	          
	       
			  
			  
			  load = new Load(tripForm.getLoad(), loadType);
			  load = loadService.addAndGetInstance(load);
			  for(Item itm : load.getItems()) {
				  item = new Item(itm,load);
				  
				  item = itemService.addAndGetInstance(item);
				  
			  }
		
		 
		  trip  = new  Trip(tripForm, drone, load);
		  tripService.save(trip);
		  load.setTrip(trip);
		  loadService.save(load);
		  
		  
		return new ResponseEntity<>(new GenericOperationResponse( "200", "Drone Registered successifully" ), responseHeaders,
				HttpStatus.OK);


	}

	/*End loadDrone*/
	
	/*Start getDroneWithLoadItems*/
	
	/* checking loaded medication items for a given drone */

	@CrossOrigin
	@RequestMapping(value = "/drone/items/get/{serialNumber}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	ResponseEntity<GeneralStatus<TripDto>> getDroneWithLoadItems(@PathVariable String serialNumber) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		if (serialNumber == null) {

			return new ResponseEntity<>(
					new GeneralStatus<TripDto>(null, "Data  provided is empty or drone serial number is not provided", "400"),
					responseHeaders, HttpStatus.OK);

		}

		trip = tripService.findTripWithLoadItemsBySerialNumberNamedQuery(serialNumber);
		
		if (trip == null) {
			return new ResponseEntity<>(
					new GeneralStatus<TripDto>(null, "No drone found with the provided serial number!", "200"),
					responseHeaders, HttpStatus.OK);
		}
		return new ResponseEntity<>(new GeneralStatus<TripDto>(new TripDto(trip) , "Document Updated succefully!", "200"),
				responseHeaders, HttpStatus.OK);

	}

	/*End getDroneWithLoadItems*/
	
	
	/*Start getDroneWithLoadItems*/
	
	/*could checking available drones for loading; */

	@CrossOrigin
	@RequestMapping(value = "/drone/state/get", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	ResponseEntity<GeneralStatus<List<DroneDto>>> getDroneByState(@RequestBody DroneDto droneForm) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		if (droneForm == null || droneForm.getState() == null) {

			return new ResponseEntity<>(
					new GeneralStatus<List<DroneDto>>(null, "Data  provided is empty or drone state is not provided", "400"),
					responseHeaders, HttpStatus.OK);

		}
state =stateService.findStateByCodeNamedQuery(droneForm.getState());

if (state == null) {

	return new ResponseEntity<>(
			new GeneralStatus<List<DroneDto>>(null, "State  provided did not match any record", "400"),
			responseHeaders, HttpStatus.OK);

}

        drones = new ArrayList<>();
		drones = droneService.findDroneByStateNumberNamedQuery(state);
		
		if (drones == null) {
			return new ResponseEntity<>(
					new GeneralStatus<List<DroneDto>>(null, "No drone found with the provided state!", "200"),
					responseHeaders, HttpStatus.OK);
		}
		return new ResponseEntity<>(new GeneralStatus<List<DroneDto>>(DroneDto.getList(drones) , "Document Updated succefully!", "200"),
				responseHeaders, HttpStatus.OK);

	}

	/*End getDroneWithLoadItems*/
	
	
	
/*Start getDroneBatteryCapacityBySerialNumber*/
	
	/* check drone battery level for a given drone; */

	@CrossOrigin
	@RequestMapping(value = "/drone/capacity/get", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	ResponseEntity<GeneralStatus<BigDecimal>> getDroneBatteryCapacityBySerialNumber(@RequestBody DroneDto droneForm) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		if (droneForm == null || droneForm.getSerialNumber() == null) {

			return new ResponseEntity<>(
					new GeneralStatus<BigDecimal>(null, "Data  provided is empty or drone serial number is not provided", "400"),
					responseHeaders, HttpStatus.OK);

		}

		  drone = droneService.findDroneBySerialNumber(droneForm.getSerialNumber());
		  
		 
  if (drone == null ) {

	  return new ResponseEntity<>(
				new GeneralStatus<BigDecimal>(null, "No drone found with the provided serial Number", "400"),
				responseHeaders, HttpStatus.OK);

	}
		return new ResponseEntity<>(new GeneralStatus<BigDecimal>(drone.getBatteryCapacity(), "Drone capacity was retrieved successifully!", "200"),
				responseHeaders, HttpStatus.OK);

	}

	/*End getDroneBatteryCapacityBySerialNumber*/

	

}
