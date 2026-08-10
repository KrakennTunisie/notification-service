package com.kerp.notificationservice.application.ports.out;

import com.kerp.notificationservice.domain.records.NotificationDto;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.NotificationEventEntity;

import java.util.List;

public interface NotificationRepositoryPort {
    NotificationDto save(NotificationEventEntity notificationEventEntity);

    boolean existsByEventId(String eventId);

    boolean existsByEventIdAndUserId(String eventId, String userId);

    void saveAll(List<NotificationEventEntity> notificationEventEntities);
}
