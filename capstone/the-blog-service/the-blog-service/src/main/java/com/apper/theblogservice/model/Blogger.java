package com.apper.theblogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOGGER")
@Data
public class Blogger {

    @Id
    @Column(name = "BLOGGER ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bloggerId;

    @Column(name = "COMPLETE_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

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