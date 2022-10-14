/**
 * 
 */
package com.musala.drones.service.implementation;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.musala.drones.model.AppUser;


/**
 * @author George J. Budeba
 *
 */
public class CustomUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private AppUser user;
    
    
    public CustomUserDetails(AppUser user) {
        System.out.println("1..............CustomUserDetails Called................");
        System.out.println("2..............CustomUserDetails Called................"+user.getPassword()); 

        this.user = user;
      
    }
    
    public CustomUserDetails(UserDetails serDetails) {
		// TODO Auto-generated constructor stub
	}

	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
    	
    	System.out.println(user.getUserRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getRoleName().toString())).collect(Collectors.toList()));
        return user.getUserRoles().stream().map(authority -> new SimpleGrantedAuthority((authority.getRoleCode().toString()))).collect(Collectors.toList());
    }
    
 
    public int getId() {
        return user.getUserId().intValue();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUserName();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNotLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return user.isAccountEnabled();
    }
    public AppUser getUserDetails() {
        return user;
    }
}