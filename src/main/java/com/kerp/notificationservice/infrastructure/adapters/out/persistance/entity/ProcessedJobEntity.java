package com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "processed_jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , unique = true)
    private UUID idEvent;

    @Column(nullable = false)
    private Instant processedAt;
}
