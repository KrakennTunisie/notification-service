package com.kerp.notificationservice.infrastructure.adapters.out.persistance.repository;

import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.NotificationEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationEventRepository extends JpaRepository<NotificationEventEntity, UUID> {
    boolean existsByEventId(String eventId);

    boolean existsByEventIdAndUserId(String eventId, String userId);
}
