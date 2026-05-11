package com.airpollution.survey.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {
    @GetMapping
    public Map<String, String> api() {
        return Map.of(
                "name", "Air Pollution Community Health Survey API",
                "status", "ok",
                "health", "/api/health"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }
}
