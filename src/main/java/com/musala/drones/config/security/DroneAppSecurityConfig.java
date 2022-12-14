package com.musala.drones.config.security;

/**
 * 
 */

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.musala.drones.service.implementation.UserDetailsServiceImpl;



/**
 *@author GEORGE J. BUDEBA
 *0766148689
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class DroneAppSecurityConfig {

	   
	   
	   @Autowired
	   JwtAuthenticationEntryPoint authenticationEntryPoint;
	   
	   @Autowired
		private JwtAuthenticationTokenFilter jwtauthFilter;
	   
	   
	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;
	   
	  

       @Bean
       public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	   http.csrf().disable();
    	    http.headers().frameOptions().disable();
           http.cors().and().csrf().disable()
                   .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                   .authorizeRequests()
                    .antMatchers("/api/test/**").permitAll()
                   .antMatchers("/api**").authenticated()
     	          .antMatchers("/login**").permitAll()
     	          .antMatchers("/login/**").permitAll()
     	         .antMatchers("/db**").permitAll()
     	        .antMatchers("/db/**").permitAll()
     	       .antMatchers("**/db/**").permitAll()
     	      .antMatchers("/health**").permitAll()
     	     .antMatchers("/health/**").permitAll()
    	      .antMatchers("/actuator**").permitAll()
    	      .antMatchers("/api/auth/**").permitAll()
    	      .antMatchers("/api/auth**").permitAll()

     	      .antMatchers("/actuator/**").permitAll()
     	     .antMatchers("/docs/**").permitAll()
     	    .antMatchers("/docs**").permitAll()
     	   .antMatchers("/api-docs/**").permitAll()
     	  .antMatchers("/api-docs**").permitAll()
    	  .antMatchers("/v3/api-docs/**").permitAll()
    	  .antMatchers("/v3/api-docs**").permitAll()
     	   .antMatchers("/swagger-ui/**").permitAll()
     	   .antMatchers("/bus/v3/api-docs/**").permitAll()

     	      
     	     .anyRequest().authenticated();

           http.addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);
	
   
   
           return http.build();
       }
	   
	 
	    

	    @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedMethods("*");
                }
            };
        }
	    
	    
	  
	    
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/bus/v3/api-docs/**","docs/swagger-ui/**", "docs/bus/v3/api-docs/**","/api-docs/**","docs/api-docs/**");
	    }

	 
	    
	    @Bean
	    public HttpFirewall defaultHttpFirewall() {
	        return new DefaultHttpFirewall();
	    }
	  
	    @Bean
	    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	        StrictHttpFirewall firewall = new StrictHttpFirewall();
	        firewall.setAllowUrlEncodedSlash(true);
	        firewall.setAllowSemicolon(true);
	        return firewall;
	    }
	    
	    
	   
	    
	  
	    @Bean
	    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
	        return new CustomAuthenticationFailureHandler();}
	    
	    
	    
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5000","http://localhost:4000","154.118.224.58","154.118.224.58:3000","http://localhost:3000","http://154.118.224.57:3000","http://154.118.224.57","*"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS", "DELETE", "PUT","*"));
	        //configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control","Access-Control-Allow-Private-Network", "Content-Type", "corsOrigin","Accept","Referer","User-Agent","*"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	    
	    
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder(10);
	    }
	    
		

		
	 
		  @Bean
		    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		        return authenticationManagerBuilder.build();
		    }
	 
		   
	    
	}


