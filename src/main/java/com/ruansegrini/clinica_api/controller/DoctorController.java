package com.ruansegrini.clinica_api.controller;

import com.ruansegrini.clinica_api.dto.request.DoctorRequestDTO;
import com.ruansegrini.clinica_api.dto.response.DoctorResponseDTO;
import com.ruansegrini.clinica_api.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(
            @RequestBody @Valid DoctorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid DoctorRequestDTO dto) {
        return ResponseEntity.ok(doctorService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        doctorService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}