package com.kerp.notificationservice.infrastructure.adapters.out.persistance;

import com.kerp.notificationservice.application.ports.out.NotificationRepositoryPort;
import com.kerp.notificationservice.domain.records.NotificationDto;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.NotificationEventEntity;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.repository.NotificationEventRepository;
import com.kerp.notificationservice.infrastructure.mapper.NotificationEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final NotificationEventRepository notificationEventRepository;

    private final NotificationEventMapper notificationEventMapper;

    @Override
    public NotificationDto save(NotificationEventEntity notificationEventEntity) {
       return notificationEventMapper.toDto(notificationEventRepository.save(notificationEventEntity));
    }

    @Override
    public boolean existsByEventId(String eventId) {
        return notificationEventRepository.existsByEventId(eventId);
    }

    @Override
    public boolean existsByEventIdAndUserId(String eventId, String userId) {
        return notificationEventRepository.existsByEventIdAndUserId(eventId, userId);
    }

    @Override
    public void saveAll(List<NotificationEventEntity> notificationEventEntities) {
        notificationEventRepository.saveAll(notificationEventEntities);
    }
}
