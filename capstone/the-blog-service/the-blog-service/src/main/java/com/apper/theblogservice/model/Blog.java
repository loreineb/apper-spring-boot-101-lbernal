package com.apper.theblogservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG")
@Data
public class Blog {

    @Id
    @Column(name = "BLOG ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String blogId;

    @Column(name = "BLOGGER ID")
    private String bloggerId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }

}