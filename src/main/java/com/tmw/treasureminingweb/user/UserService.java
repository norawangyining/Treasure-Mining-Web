package com.tmw.treasureminingweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User test1(){
        return new User();
    }

    public User test2(){
        return new User("gougou","chy17007@bu.edu","123","hhh", LocalDate.now(),true,50,LocalDate.now(),1L);
    }
}
