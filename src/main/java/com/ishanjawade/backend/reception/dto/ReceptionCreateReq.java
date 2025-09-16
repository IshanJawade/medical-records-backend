package com.ishanjawade.backend.reception.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReceptionCreateReq(
        Long userId,
        @NotBlank @Size(max=120) String name,
        @Size(max=32) String phone,
        @Email String email
) {}
