package com.example.activityplanner.controller;

import com.example.activityplanner.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserServer userServer;

    @Autowired
    public UserController(UserServer userServer) {
        this.userServer = userServer;
    }
}
