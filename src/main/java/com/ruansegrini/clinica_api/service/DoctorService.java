package com.ruansegrini.clinica_api.service;

import com.ruansegrini.clinica_api.domain.entity.Doctor;
import com.ruansegrini.clinica_api.dto.request.DoctorRequestDTO;
import com.ruansegrini.clinica_api.dto.response.DoctorResponseDTO;
import com.ruansegrini.clinica_api.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorResponseDTO> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorResponseDTO::from)
                .toList();
    }

    public DoctorResponseDTO findById(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        return DoctorResponseDTO.from(doctor);
    }

    public DoctorResponseDTO create(DoctorRequestDTO dto) {
        if (doctorRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Email already registered.");
        }
        if(doctorRepository.existsByCrm(dto.crm())) {
            throw new BusinessException("Crm already registred.");
        }

        Doctor doctor = new Doctor();
        doctor.setName(dto.name());
        doctor.setEmail(dto.email());
        doctor.setPhone(dto.phone());
        doctor.setCrm(dto.crm());
        doctor.setActive(true);

        return DoctorResponseDTO.from(doctorRepository.save(doctor));
    }

    public DoctorResponseDTO updateById(UUID id, DoctorRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        doctor.setName(dto.name());
        doctor.setPhone(dto.phone());

        return DoctorResponseDTO.from(doctorRepository.save(doctor));
    }

    public void deactivate(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        doctor.setActive(false);
        doctorRepository.save(doctor);
    }
}
