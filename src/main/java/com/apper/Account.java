package com.apper;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private String id;
    private Double balance;

    private String firstName;
    private String lastName;
    private String username;
    private String clearPassword;
    private String verificationCode;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;
}


