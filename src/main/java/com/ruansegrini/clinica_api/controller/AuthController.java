package com.ruansegrini.clinica_api.controller;

import com.ruansegrini.clinica_api.domain.entity.User;
import com.ruansegrini.clinica_api.domain.enums.UserRole;
import com.ruansegrini.clinica_api.dto.request.AuthRequestDTO;
import com.ruansegrini.clinica_api.dto.response.AuthResponseDTO;
import com.ruansegrini.clinica_api.infra.security.JwtService;
import com.ruansegrini.clinica_api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.login(), dto.password()
                ));

        User user = userRepository.findByLogin(dto.login())
                .orElseThrow();

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthRequestDTO dto
    ) {
        String encoded = passwordEncoder.encode(dto.password());
        User user = new User(dto.login(), encoded, UserRole.RECEPTIONIST);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
