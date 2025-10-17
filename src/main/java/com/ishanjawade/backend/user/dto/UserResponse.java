package com.ishanjawade.backend.user.dto;

import com.ishanjawade.backend.user.User;
import com.ishanjawade.backend.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private UserRole role;
    private Long entityId;
    private Boolean enabled;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .entityId(user.getEntityId())
                .build();
    }
    private Instant createdAt;
    private Instant updatedAt;
}
