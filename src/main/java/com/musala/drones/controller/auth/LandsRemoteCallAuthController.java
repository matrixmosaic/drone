package com.musala.drones.controller.auth;

/**
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drones.config.security.JwtTokenUtil;
import com.musala.drones.dto.global.GeneralStatus;
import com.musala.drones.dto.remote.RemoteUserReq;
import com.musala.drones.dto.remote.RemoteUserResp;
import com.musala.drones.model.AppUser;
import com.musala.drones.service.UserService;



/**
 * @author George J. Budeba george.bugeba@lands.go.tz
 *
 */

@RestController
@RequestMapping("api/auth")
public class LandsRemoteCallAuthController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	private String token;
	RemoteUserResp remoteUserResp;

	@Autowired
	UserService userService;
	AppUser user;
	
	HttpHeaders responseHeaders;

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * 
	 * This method takes in the principal credentials, validates them and issues timed tokens for authorized users.
	 */
	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralStatus<RemoteUserResp>>  authenticateRemotePrincipal(@RequestBody RemoteUserReq remoteUserReq) {
		token = null;
		System.out.println("Remote User:" + remoteUserReq.toString());

		Assert.notNull(remoteUserReq, "Remote User Req must not be  null");
		
		 responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		if (remoteUserReq.getUsername() == null || remoteUserReq.getPassword() == null ) {

		 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(null,"Username or password fields cannot be null","400"), responseHeaders, HttpStatus.OK);

		}
		
		
		
		
		user = userService.findUserWithOfficeAndRolesByIdNQ(remoteUserReq.getUsername());
		
		if(user == null) {
			
		 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(null,"Invalid Username or password combination","401"), responseHeaders, HttpStatus.OK);

			
		}
		
		
		
		if(user.isAccountEnabled() == false || user.isAccountNotLocked() ==false) {
		
		 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(null,"Your account is locked, Please Contact the ILCMS Administrator for more assistance","432"), responseHeaders, HttpStatus.OK);

			
		}
		
		if(remoteUserReq.getAttempts() > 5) {
			
			
			
				user.setAccountEnabled(false);
				user.setAccountNotLocked(false);
				userService.saveInstance(user);
			 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(null,"Your account is locked, Please Contact the ILCMS Administrator for more assistance","432"), responseHeaders, HttpStatus.OK);

				
		
			
		}

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(remoteUserReq.getUsername(), remoteUserReq.getPassword()));
		} catch (Exception ex) {
			System.out.println("User Not Found");
		 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(null,"Invalid Username or password combination","401"), responseHeaders, HttpStatus.OK);

		}

		token = jwtTokenUtil.generateToken(remoteUserReq.getUsername());
		remoteUserResp = new RemoteUserResp(remoteUserReq.getUsername(), remoteUserReq.getPassword(), token, user);
	 	return new ResponseEntity<>(new GeneralStatus<RemoteUserResp>(remoteUserResp,"Authentication Success","200"), responseHeaders, HttpStatus.OK);

	}

}
