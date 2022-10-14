/**
 * 
 */
package com.musala.drones.config.security;

/**
 * @author User
 *
 */
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.musala.drones.model.AppUser;
import com.musala.drones.repository.AppUserDao;
import com.musala.drones.service.implementation.CustomUserDetails;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AppUserDao userDao;

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			String authToken = header.substring(7);
			String username = null;

			try {

				username = jwtTokenUtil.getUsernameFromToken(authToken);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					if (jwtTokenUtil.validateToken(authToken, username)) {
						// here username should be validated with database and get authorities from
						// database if valid
						
						AppUser user = null;
				    	
				    	 try {
							user = userDao.findByUserName(username);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							  System.out.println("This User is not in databade");
							e.printStackTrace();
						}
				    	
				        if(user==null){
				            System.out.println("This User not found");
				            throw new UsernameNotFoundException("Username not found");
				        }

				      
				        UserDetails userDetails = new CustomUserDetails(user);
						
						

						if (jwtTokenUtil.validateToken(authToken, userDetails.getUsername())) {

							UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
							usernamePasswordAuthenticationToken
									.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						}

					} else {
						System.out.println("Token has been expired");
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
				}
			} catch (Exception e) {
				System.out.println("Unable to get JWT Token, possibly expired");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		chain.doFilter(request, response);
		// }
	}
}
