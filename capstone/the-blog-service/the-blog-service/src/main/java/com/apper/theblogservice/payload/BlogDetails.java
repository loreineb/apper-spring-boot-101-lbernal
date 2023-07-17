package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"blogger_id", "title", "body", "created_at", "last_updated"})
public class BlogDetails {

    @JsonProperty("blogger_id")
    private String bloggerId;

    private String title;

    private String body;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdate;
}
