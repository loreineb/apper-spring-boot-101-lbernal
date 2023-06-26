package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyRegistered extends RuntimeException {}

//extend RuntimeException so it outputs the proper response status
//not extend Throwable