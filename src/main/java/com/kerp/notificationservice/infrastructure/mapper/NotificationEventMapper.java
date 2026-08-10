package com.kerp.notificationservice.infrastructure.mapper;

import com.kerp.notificationservice.domain.enums.NotificationStatus;
import com.kerp.notificationservice.domain.records.NotificationDto;
import com.kerp.notificationservice.domain.records.NotificationEvent;
import com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity.NotificationEventEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationEventMapper {

    public NotificationEventEntity toEntity(NotificationEvent event, String userId) {
        if(event == null){
            return null;
        }

        NotificationEventEntity notificationEvent = new NotificationEventEntity();

        //notificationEvent.setId(UUID.fromString(event.eventId()));
        notificationEvent.setEventId(event.eventId());
        notificationEvent.setEventType(event.eventType());
        notificationEvent.setChannel(event.channel());
        notificationEvent.setUserId(userId);
        notificationEvent.setMessage(event.message());
        notificationEvent.setTitle(event.title());
        notificationEvent.setMetadata(event.metadata());
        notificationEvent.setStatus(NotificationStatus.UNREAD);
        notificationEvent.setOccurredAt(event.occurredAt());

        return notificationEvent;
    }


    public NotificationDto toDto(NotificationEventEntity entity) {
        return new NotificationDto(
                entity.getId(),
                entity.getEventId(),
                entity.getEventType(),
                entity.getChannel(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getMetadata(),
                entity.getStatus(),
                entity.getOccurredAt(),
                entity.getCreatedAt()
        );
    }


}
