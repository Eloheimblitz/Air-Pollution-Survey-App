package com.airpollution.survey.dto;

public record UserResponse(Long id, String username, String role, Boolean enabled) {
}
