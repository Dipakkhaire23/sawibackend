package com.sawi_health.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC INFO =================

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;
    private String riskType; // AGGRESSIVE / MODERATE / CONSERVATIVE
    private int riskScore;

    private  AuthProvider provider;

    // ================= ROLE =================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    // ================= PROFILE IMAGE =================

    private String imageName;
    private String imageType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    // ================= STATUS =================

    @Column(nullable = false)
    private boolean active = true;

    // ================= AUDIT =================

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ================= AUTO TIMESTAMP =================

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}