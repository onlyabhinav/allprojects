package org.abhinav.bugtracker.controller;

import org.abhinav.bugtracker.model.vo.UserVO;

public class ServiceRequestController
{

    public String ping()
    {
        return "Service is running";
    }

    public UserVO login( String username, String password )
    {

        System.out.println( "Login request for " + username + " with password: " + password );

        UserVO res = new UserVO();

        return res;
    }

}
