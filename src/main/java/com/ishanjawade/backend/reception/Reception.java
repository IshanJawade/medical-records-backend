package com.ishanjawade.backend.reception;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "receptions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    /** Optional: link to users table if you already have one. */
//    @Column(name = "user_id")
//    private Long userId;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 32)
    private String phone;

    @Column(length = 120)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
