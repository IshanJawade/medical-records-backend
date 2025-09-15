package com.ishanjawade.backend.doctor.dto;

import com.ishanjawade.backend.doctor.Doctor;

public record DoctorRes(
        Long id,
        String name,
        String npi,
        String specialty,
        String phone
) {
    public static DoctorRes of(Doctor d) {
        return new DoctorRes(
                d.getId(), d.getName(), d.getNpi(), d.getSpecialty(), d.getPhone()
        );
    }
}