package com.ishanjawade.backend.reception.dto;

import com.ishanjawade.backend.reception.Reception;

public record ReceptionRes(
        Long id,
       // Long userId,
        String name,
        String phone,
        String email
) {
    public static ReceptionRes of(Reception r) {
        return new ReceptionRes(r.getId(), r.getName(), r.getPhone(), r.getEmail());
    }
}