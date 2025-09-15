package com.ishanjawade.backend.doctor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorCreateReq(
        Long userId,
        @NotBlank String name,
        @Size(max = 32) String npi,
        @Size(max = 64) String specialty,
        @Size(max = 32) String phone
) {}
