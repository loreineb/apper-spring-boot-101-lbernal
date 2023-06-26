package com.apper;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAccountRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}