package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloggerDetails {
    private String id;

    private String name;
    private String email;

    @JsonProperty("date_registration")
    private LocalDateTime dateRegistration;
}