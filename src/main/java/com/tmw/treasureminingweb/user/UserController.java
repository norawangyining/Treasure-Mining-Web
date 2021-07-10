package com.tmw.treasureminingweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Providing apis for registering and login
 */
@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "register")
    public List<User> register(){
//        TODO
        return new LinkedList<>();
    }

    @GetMapping(path = "login")
    public List<User> login(){
//         TODO
        return new LinkedList<>();
    }

}
