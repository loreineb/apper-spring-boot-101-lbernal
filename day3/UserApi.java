package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        System.out.println(request); //since nauuna yung sout kesa sa thrown exception, lalabas pa rin sa intellij terminal
        LocalDate birthDate = LocalDate.parse(request.getBirthDate()); //turns the String birthday to LocalDate
        LocalDate now = LocalDate.now();
        Period period = birthDate.until(now);
        //returns a Period object with the duration from birthDate up to the current date
        int age = period.getYears();

        /*Testing via sout
        System.out.println(birthDate);
        System.out.println(birthDate.getClass().getSimpleName());
        System.out.println(age);
        return new CreateUserResponse("Random"); */

        if (age >= 15) {
            return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
        }
        throw new InvalidUserAgeException();
    }
}