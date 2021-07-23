package com.tmw.treasureminingweb.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository getting access of database CONFIRMATION_TOKEN
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Override
    void deleteById(Long id);

    Optional<ConfirmationToken> getConfirmationTokenByToken(String token);
}
