package com.ruansegrini.clinica_api.controller;

import com.ruansegrini.clinica_api.dto.request.PatientRequestDTO;
import com.ruansegrini.clinica_api.dto.response.PatientResponseDTO;
import com.ruansegrini.clinica_api.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable UUID id,
                                                     @RequestBody @Valid PatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        patientService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}
