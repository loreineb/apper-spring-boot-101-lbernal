package com.apper.theblogservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBloggerRequest {

    @NotBlank(message = "`email` is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "`name` is required")
    private String name;

    @NotBlank(message = "`name` is required")
    @Size(min = 5, message = "`password` must be at least 5 characters")
    private String password;
}

//    @JsonProperty("first_name")
//    @NotBlank(message = "`first_name` is required")
//    private String firstName;
//
//    @JsonProperty("middle_name")
//    private String middleName;
//
//    @JsonProperty("last_name")
//    @NotBlank(message = "`last_name` is required")
//    private String lastName;
//
//    @JsonProperty("birth_date")
//    @NotBlank(message = "`birth_date` is required")
//    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "`birth_date` must be YYYY-MM-DD")
//    private String birthDate;
