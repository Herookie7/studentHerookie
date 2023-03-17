package com.herookie.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String roll;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String clasz;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    // @Column(nullable = false, updatable = false)
    // private LocalDateTime createdAt;

    // @Column(nullable = false)
    // private LocalDateTime updatedAt;

    // @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    // @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedTime;

    @Column(nullable = false)
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // this.createdAt = now;
        // this.updatedAt = now;
        this.createdTime = now;
        this.lastModifiedTime = now;
        this.createdBy = username;
        this.updatedBy = username;
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime now = LocalDateTime.now();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // this.updatedAt = now;
        this.lastModifiedTime = now;
        this.updatedBy = username;
    }

    public String getName() {
        String name = this.firstName + this.lastName;
        return name;
    }    
}