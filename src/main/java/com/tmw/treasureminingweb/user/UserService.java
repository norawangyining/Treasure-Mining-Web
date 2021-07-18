package com.tmw.treasureminingweb.user;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Business logic for User login and register
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ConfirmationTokenService confirmationTokenService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);

    @Autowired
    public UserService(UserRepository userRepository, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    /**
     * get user by email
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    /**
     * sign up a user
     * 1. encrypt the password
     * 2. set a new confirmation token and save it in the db
     * @param user
     */
    public void signUp(User user){
        encryptAndSavePassword(user);
        confirmationTokenService.saveConfirmationTokenByUser(user);
    }

    /**
     * encrypt password based on the passwordEncoder and save
     * @param user
     */
    private void encryptAndSavePassword(User user){
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public List<User> test() {
        User user = new User();
        user.setEmail("530106857@qq,com");
        userRepository.save(new User());
        final Optional<User> optionalUser = userRepository.findByEmail("530106857@qq,com");
        return optionalUser.map(Arrays::asList).orElse(null);
    }

}
