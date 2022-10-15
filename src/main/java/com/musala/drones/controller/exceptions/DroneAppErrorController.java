/**
 * 
 */
package com.musala.drones.controller.exceptions;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author New
 *
 */
@RestController
public class DroneAppErrorController implements ErrorController  {


    @RequestMapping("/error")
   public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
        Integer statusCode = Integer.valueOf(status.toString());

        if(statusCode == HttpStatus.NOT_FOUND.value()) {
            return "Drone app could not find the resource you requested.";
        }
        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "There was an exption while processing your request, please contact the Drone app administrator";
        }
        else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
            return "You are not authorize to access the resource you requested.";
        }
        
        else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
            return "the server cannot or will not process the request due to something that is perceived to be a client error (for example, malformed request syntax, invalid request message framing, or deceptive request routing)";
        }
        else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
            return "Method Not Allowed";
        }
        
        else if(statusCode == HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()) {
            return "Unssuported media type. Ensure that you are sending the proper Content-Type with suitable header value.";
        }
        else if(statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            return "Service Not Available";
        }
        else if(statusCode == HttpStatus.FORBIDDEN.value()) {
            return "You have no rights to access the requested resource";
        }
    }
    return "error";
}

    
}
