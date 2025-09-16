package com.ishanjawade.backend.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PatientCreateReq(
        Long userId,
        @NotBlank @Size(max=64) String mrn,
        @NotBlank @Size(max=120) String name,
        LocalDate dob,
        String sex,
        String phone,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String zip
) {}
