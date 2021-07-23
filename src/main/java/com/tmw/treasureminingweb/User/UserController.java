package com.tmw.treasureminingweb.User;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationToken;
import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Providing apis for registering and login
 */
@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public UserController(UserService userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping(path = "register")
    public String register(){
        return "register";
    }

    @GetMapping(path = "login")
    public String login(){
        return "login";
    }

    @PostMapping(path = "register")
    public String register(User user){
        userService.signUp(user);
        return "redirect:/user/login";
    }

    @GetMapping(path = "confirm")
    public String confirmMail(@RequestParam("token") String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);

        confirmationTokenService.confirmUser(confirmationToken);

        return "/login";
    }

    @GetMapping(path = "test")
    public List<User> test(){
//         TODO
        return userService.test();
    }
}
