package com.airpollution.survey.dto;

import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        String role,
        Boolean enabled,
        @Size(min = 6, message = "Password must be at least 6 characters") String password
) {
}
