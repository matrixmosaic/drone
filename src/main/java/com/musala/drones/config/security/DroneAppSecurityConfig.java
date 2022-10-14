package com.musala.drones.config.security;

/**
 * 
 */

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.musala.drones.service.implementation.AuthenticationEntryPoint;


/**
 *@author GEORGE J. BUDEBA
 *0766148689
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class DroneAppSecurityConfig {

	   
	   
	   @Autowired
	   AuthenticationEntryPoint authenticationEntryPoint;
	   
	   @Autowired
		private JwtAuthenticationTokenFilter jwtauthFilter;
	   
	  

       @Bean
       public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	   http.csrf().disable();
    	    http.headers().frameOptions().disable();
           http.cors().and().csrf().disable()
                 //   .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                   .authorizeRequests()
                    .antMatchers("/api/test/**").permitAll()
                   .antMatchers("/api**").authenticated()
     	          .antMatchers("/login**").permitAll()
     	          .antMatchers("/login/**").permitAll()
     	         .antMatchers("/db**").permitAll()
     	        .antMatchers("/db/**").permitAll()
     	       .antMatchers("**/db/**").permitAll()
                   .anyRequest().authenticated()
           .and()
           .formLogin()
           .loginPage("/login").permitAll()
           .defaultSuccessUrl("/index")
            .failureHandler(customAuthenticationFailureHandler())
           .failureUrl("/login?error=true")
           .usernameParameter("username").passwordParameter("password")
           
           .and()
           .logout()
           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           .logoutSuccessUrl("/login?logout=true")
           .invalidateHttpSession(true)
           .permitAll()
           .and()
   		.addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);
   	
   	 http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
   
   
           return http.build();
       }
	   
	 
	    

	
	   
	 /*   @Bean
	    public PasswordEncoder passwordEncoder(){
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }*/
	
	    
	    
	   
	    
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
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control","Access-Control-Allow-Private-Network", "Content-Type", "corsOrigin","Accept","Referer","User-Agent","*"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	}


