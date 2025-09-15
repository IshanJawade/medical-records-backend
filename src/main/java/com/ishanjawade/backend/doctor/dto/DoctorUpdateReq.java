package com.ishanjawade.backend.doctor.dto;

    public record DoctorUpdateReq(
            String name,
            String specialty,
            String phone
    ) {}

