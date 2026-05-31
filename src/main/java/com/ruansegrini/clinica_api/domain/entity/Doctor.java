package com.ruansegrini.clinica_api.domain.entity;


import com.ruansegrini.clinica_api.domain.enums.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_doctor",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_doctor_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_doctor_crm", columnNames = "crm")
})
public class Doctor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
