package com.tmw.treasureminingweb.User;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationToken;
import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
    private final EmailService emailService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);

    @Autowired
    public UserService(UserRepository userRepository, ConfirmationTokenService confirmationTokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
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
        ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationTokenByUser(user);
        sendConfirmationMail(user.getUsername(), confirmationToken.getToken());
    }

    /**
     * send confirmation email to user
     * @param userEmail
     * @param token
     */
    public void sendConfirmationMail(String userEmail, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
                        + token);

        emailService.sendEmail(mailMessage);
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


}
