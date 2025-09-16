package com.ishanjawade.backend.patient.dto;

import java.time.LocalDate;

public record PatientUpdateReq(
        String name,
        LocalDate dob,
        String sex,
        String phone,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String zip
) {}
