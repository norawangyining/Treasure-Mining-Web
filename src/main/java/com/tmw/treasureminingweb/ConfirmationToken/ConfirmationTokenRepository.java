package com.tmw.treasureminingweb.ConfirmationToken;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//storing tokens that were sent by server
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

}
