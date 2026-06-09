package com.ruansegrini.clinica_api.service;

import com.ruansegrini.clinica_api.domain.entity.Patient;
import com.ruansegrini.clinica_api.dto.request.PatientRequestDTO;
import com.ruansegrini.clinica_api.dto.response.PatientResponseDTO;
import com.ruansegrini.clinica_api.exception.BusinessException;
import com.ruansegrini.clinica_api.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public List<PatientResponseDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(PatientResponseDTO::from)
                .toList();
    }

    @Transactional
    public PatientResponseDTO findById(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return PatientResponseDTO.from(patient);
    }

    @Transactional
    public PatientResponseDTO create(PatientRequestDTO dto) {
        if (patientRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Email already registered.");
        }
        if (patientRepository.existsByCpf(dto.cpf())) {
            throw new BusinessException("CPF already registred.");
        }

        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setEmail(dto.email());
        patient.setPhone(dto.phone());
        patient.setCpf(dto.cpf());
        patient.setActive(true);

        return PatientResponseDTO.from(patientRepository.save(patient));
    }

    @Transactional
    public PatientResponseDTO update(UUID id, PatientRequestDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        patient.setName(dto.name());
        patient.setPhone(dto.phone());

        return PatientResponseDTO.from(patientRepository.save(patient));
    }

    @Transactional
    public void deactivate(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        patient.setActive(false);
        patientRepository.save(patient);
    }
}
