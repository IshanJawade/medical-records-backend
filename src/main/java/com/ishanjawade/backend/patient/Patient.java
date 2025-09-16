package com.ishanjawade.backend.patient;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    /** If you later link to users table, store that user id here (nullable for now). */
//    @Column(name = "user_id")
//    private Long userId;

    /** Medical Record Number, unique per patient. */
    @Column(nullable = false, unique = true, length = 64)
    private String mrn;

    @Column(nullable = false, length = 120)
    private String name;

    private LocalDate dob;

    @Column(length = 16)
    private String sex; // keep simple for MVP

    @Column(length = 32)
    private String phone;

    // Simple flat address for MVP (you can switch to JSONB later).
    @Column(length = 160) private String addressLine1;
    @Column(length = 160) private String addressLine2;
    @Column(length = 80)  private String city;
    @Column(length = 40)  private String state;
    @Column(length = 20)  private String zip;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
