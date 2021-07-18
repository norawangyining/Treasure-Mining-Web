package com.tmw.treasureminingweb.ConfirmationToken;

import com.tmw.treasureminingweb.user.User;
import com.tmw.treasureminingweb.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of confirmationToken
 */
@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    public void saveConfirmationTokenByUser(User user){
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
    }

    /**
     * save a token
     * @param confirmationToken
     */
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    /**
     * delelte a token
     * @param id
     */
    public void deleteConfirmationToken(Long id){
        confirmationTokenRepository.deleteById(id);
    }

    /**
     * set user to be enable
     * @param confirmationToken
     */
    public void confirmUser(ConfirmationToken confirmationToken){
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        deleteConfirmationToken(confirmationToken.getId());
    }
}
