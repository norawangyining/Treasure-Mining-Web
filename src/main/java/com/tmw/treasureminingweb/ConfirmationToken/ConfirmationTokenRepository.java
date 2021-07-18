package com.tmw.treasureminingweb.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository getting access of database CONFIRMATION_TOKEN
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Override
    void deleteById(Long id);
}
