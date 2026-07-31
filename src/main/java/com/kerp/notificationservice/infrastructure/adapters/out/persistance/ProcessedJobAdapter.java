package com.kerp.notificationservice.infrastructure.adapters.out.persistance;

import com.kerp.notificationservice.application.ports.out.ProcessedEventPort;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.ProcessedJobEntity;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.repository.ProcessedJobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProcessedJobAdapter implements ProcessedEventPort {
    private final ProcessedJobRepository repository;


    @Override
    public boolean isAlreadyProcessed(String eventId) {
        return repository.existsById(UUID.fromString(eventId));
    }

    @Override
    public void markAsProcessed(String eventId) {
        ProcessedJobEntity processedJobEntity = new ProcessedJobEntity();

        processedJobEntity.setProcessedAt(Instant.now());
        processedJobEntity.setIdEvent(UUID.fromString(eventId));

        repository.save(processedJobEntity);
    }
}
