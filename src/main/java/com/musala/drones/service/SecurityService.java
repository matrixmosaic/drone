/**
 * 
 */
package com.musala.drones.service;

/**
 * @author George J. Budeba
 *
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
