package com.airpollution.survey;

import com.airpollution.survey.entity.AppUser;
import com.airpollution.survey.repository.UserRepository;
import com.airpollution.survey.config.DatabaseUrlNormalizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AirPollutionSurveyApplication {

    public static void main(String[] args) {
        DatabaseUrlNormalizer.apply();
        SpringApplication.run(AirPollutionSurveyApplication.class, args);
    }

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                @Value("${app.seed-demo-users}") boolean seedDemoUsers) {
        return args -> {
            if (!seedDemoUsers) {
                return;
            }
            createUserIfMissing(userRepository, passwordEncoder, "admin", "admin123", "ADMIN");
            createUserIfMissing(userRepository, passwordEncoder, "surveyor", "survey123", "SURVEYOR");
        };
    }

    private void createUserIfMissing(UserRepository repository, PasswordEncoder encoder, String username, String password, String role) {
        repository.findByUsername(username).map(existing -> {
            if (existing.getEnabled() == null) {
                existing.setEnabled(true);
                return repository.save(existing);
            }
            return existing;
        }).orElseGet(() -> {
            AppUser user = new AppUser();
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            user.setRole(role);
            user.setEnabled(true);
            return repository.save(user);
        });
    }
}
