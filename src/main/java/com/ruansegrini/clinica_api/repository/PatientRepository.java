package com.ruansegrini.clinica_api.repository;

import com.ruansegrini.clinica_api.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
