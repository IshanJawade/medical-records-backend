package com.ishanjawade.backend.user.dto;

import com.ishanjawade.backend.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String email;
    private String password;
    private UserRole role;
    private Long entityId;
}
