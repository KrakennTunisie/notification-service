package com.kerp.notificationservice.infrastructure.adapters.out.persistance.repository;

import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.ProcessedJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcessedJobRepository extends JpaRepository<ProcessedJobEntity, UUID> {
}
