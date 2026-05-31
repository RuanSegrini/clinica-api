package com.ruansegrini.clinica_api.repository;

import com.ruansegrini.clinica_api.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCrm(String crm);
}
