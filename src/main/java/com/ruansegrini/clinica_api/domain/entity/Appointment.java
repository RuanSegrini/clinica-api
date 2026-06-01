package com.ruansegrini.clinica_api.domain.entity;

import com.ruansegrini.clinica_api.domain.enums.AppointmentStatus;
import com.ruansegrini.clinica_api.domain.enums.CancelReason;
import jakarta.persistence.*;
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
@Table(name = "tb_appointment",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_appointment_doctor_datetime",
                        columnNames = {"doctor_id", "dateTime"}
                )
        }
)
public class Appointment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private CancelReason cancelReason;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = AppointmentStatus.SCHEDULED;
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel(CancelReason reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Cancel reason is required.");
        }

        this.status = AppointmentStatus.CANCELED;
        this.cancelReason = reason;
    }

    public void complete() {
        this.status = AppointmentStatus.COMPLETED;
        this.cancelReason = null;
    }

    public void markNoShow() {
        this.status = AppointmentStatus.NO_SHOW;
        this.cancelReason = null;
    }

    public boolean isCompleted() {
        return status == AppointmentStatus.COMPLETED;
    }

    public boolean isNoShow() {
        return status == AppointmentStatus.NO_SHOW;
    }

    public boolean isCanceled() {
        return status == AppointmentStatus.CANCELED;
    }

    public boolean isScheduled() {
        return status == AppointmentStatus.SCHEDULED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}