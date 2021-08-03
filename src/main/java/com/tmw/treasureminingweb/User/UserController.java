package com.tmw.treasureminingweb.User;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationToken;
import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @GetMapping(path = "login")
    public ModelAndView login(@RequestParam(name = "name", required = false, defaultValue = "gougou") String name, Model model){
        ModelAndView m = new ModelAndView("login");
        m.addObject("name","gougou");
        return m;
    }

    @PostMapping(path = "register")
    public ModelAndView register(User user){
        userService.signUp(user);
        return new ModelAndView("redirect:/user/login") ;
    }

    @GetMapping(path = "confirm")
    public ModelAndView confirmMail(@RequestParam("token") String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);

        confirmationTokenService.confirmUser(confirmationToken);

        return new ModelAndView("login");
    }

    public ModelAndView resetPassWord(@RequestParam("token") String token, @RequestParam("password") String newPassword) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);
        User user = confirmationToken.getUser();
        userService.resetPassword(user,newPassword);
        return new ModelAndView("login");
    }

}
