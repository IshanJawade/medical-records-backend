package com.ishanjawade.backend.reception.dto;

import jakarta.validation.constraints.Email;

public record ReceptionUpdateReq(
        String name,
        String phone,
        @Email String email
) {}
