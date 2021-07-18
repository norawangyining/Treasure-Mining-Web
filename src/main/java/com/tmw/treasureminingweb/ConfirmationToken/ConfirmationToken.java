package com.tmw.treasureminingweb.ConfirmationToken;

import com.tmw.treasureminingweb.user.User;
import lombok.AllArgsConstructor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * POJO representing a confirmation token corresponding to a user
 */
@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationToken;

    private LocalDate createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        this.createdDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }
}
