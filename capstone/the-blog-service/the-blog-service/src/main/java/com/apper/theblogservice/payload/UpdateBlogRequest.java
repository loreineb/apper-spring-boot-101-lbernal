package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBlogRequest {

    @NotBlank(message = "`title` is required")
    private String title;

    @NotBlank(message = "`body` is required")
    private String body;

}