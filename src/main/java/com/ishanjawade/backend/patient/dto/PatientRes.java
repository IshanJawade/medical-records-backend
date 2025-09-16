package com.ishanjawade.backend.patient.dto;

import com.ishanjawade.backend.patient.Patient;

public record PatientRes(
        Long id,
        // Long userId,
        String mrn,
        String name,
        String dob,     // as ISO string for simplicity
        String sex,
        String phone,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String zip
) {
    public static PatientRes of(Patient p) {
        return new PatientRes(
                p.getId(), p.getMrn(), p.getName(),
                p.getDob() == null ? null : p.getDob().toString(),
                p.getSex(), p.getPhone(),
                p.getAddressLine1(), p.getAddressLine2(),
                p.getCity(), p.getState(), p.getZip()
        );
    }
}
