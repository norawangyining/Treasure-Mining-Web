package com.tmw.treasureminingweb.ConfirmationToken;

import com.tmw.treasureminingweb.User.User;
import com.tmw.treasureminingweb.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service of token
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

    public ConfirmationToken getConfirmationToken(String token){
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.getConfirmationTokenByToken(token);

        if(confirmationToken.isPresent()){
            return confirmationToken.get();
        }
        return null;
    }
}
