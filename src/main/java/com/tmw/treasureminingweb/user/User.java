package com.tmw.treasureminingweb.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private String contactInfo;
    private LocalDate addDate;
    private Boolean isActivate;
    private Integer loginNum;
    private LocalDate lastVst;
    private Long profileId;

    public User(String name, String email, String password, String contactInfo, LocalDate addDate, Boolean isActivate, Integer loginNum, LocalDate lastVst, Long profileId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactInfo = contactInfo;
        this.addDate = addDate;
        this.isActivate = isActivate;
        this.loginNum = loginNum;
        this.lastVst = lastVst;
        this.profileId = profileId;
    }
}
