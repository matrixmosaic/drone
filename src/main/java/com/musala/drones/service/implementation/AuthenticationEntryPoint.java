/**
 * 
 */
package com.musala.drones.service.implementation;
/**
 * @author GEORGE J. BUDEBA
 *
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * @author George J. Budeba
 *
 */
@Configuration
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	/**
	 * Used to make customizable error messages and codes when login fails
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
	  throws IOException {
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    PrintWriter writer = response.getWriter();
	    writer.println("HTTP Status 401 - " + authEx.getMessage());
	}

	@Override
	public void afterPropertiesSet() {
	    setRealmName("YOUR REALM");
	    super.afterPropertiesSet();
	
	
}

}