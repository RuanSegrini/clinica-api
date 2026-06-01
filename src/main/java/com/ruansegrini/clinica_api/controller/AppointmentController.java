package com.ruansegrini.clinica_api.controller;

import com.ruansegrini.clinica_api.domain.enums.CancelReason;
import com.ruansegrini.clinica_api.dto.request.AppointmentRequestDTO;
import com.ruansegrini.clinica_api.dto.response.AppointmentResponseDTO;
import com.ruansegrini.clinica_api.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(
            @PathVariable UUID id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(
            @RequestBody @Valid AppointmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.create(dto));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancel(
            @PathVariable UUID id,
            @RequestParam CancelReason reason) {

        return ResponseEntity.ok(
                appointmentService.cancel(id, reason)
        );
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<AppointmentResponseDTO> complete(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                appointmentService.complete(id)
        );
    }

    @PatchMapping("/{id}/no-show")
    public ResponseEntity<AppointmentResponseDTO> markNoShow(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                appointmentService.markNoShow(id)
        );
    }
}
