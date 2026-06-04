package com.ruansegrini.clinica_api.infra.security;

import com.ruansegrini.clinica_api.domain.entity.User;
import com.ruansegrini.clinica_api.domain.enums.UserRole;
import com.ruansegrini.clinica_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByLogin("admin").isEmpty()) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), UserRole.ADMIN);
            userRepository.save(admin);
        }
    }
}