package com.kerp.notificationservice.application.services;

import com.kerp.notificationservice.application.ports.in.SendNotificationUseCase;
import com.kerp.notificationservice.application.ports.out.NotificationPushPort;
import com.kerp.notificationservice.application.ports.out.NotificationRepositoryPort;
import com.kerp.notificationservice.application.ports.out.UsersIdsFetcherPort;
import com.kerp.notificationservice.domain.records.NotificationDto;
import com.kerp.notificationservice.domain.records.NotificationEvent;
import com.kerp.notificationservice.infrastructure.mapper.NotificationEventMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationUseCase {
    private final NotificationRepositoryPort notificationRepositoryPort;
    private final NotificationPushPort notificationPushPort;
    private final NotificationEventMapper notificationEventMapper;
    private final UsersIdsFetcherPort usersIdsFetcherPort;

    @Override
    @Transactional
    public void processNotification(NotificationEvent event) {
        // idempotency guard - eventId is unique, protects against Kafka redelivery
            Set<String> resolvedUserIds = resolveTargets(event);

            System.out.println("resolvedUserIds: "+resolvedUserIds);

            List<NotificationDto> notificationDtos = resolvedUserIds.stream()
                    .filter(u->!notificationRepositoryPort.existsByEventIdAndUserId(event.eventId(), u))
                    .map(userId -> notificationEventMapper.toEntity(event, userId))
                    .map(notificationRepositoryPort::save)
                    .toList();

            // push AFTER commit, not before — see note below
            notificationDtos.forEach(n -> notificationPushPort.pushToUser(n.userId(), n));

    }

    private Set<String> resolveTargets(NotificationEvent event) {
        Set<String> userIds = new HashSet<>(event.targetUserIds());
        event.targetRoles().forEach(role -> userIds.addAll(usersIdsFetcherPort.getUsersByRole(role)));
        return userIds;
    }
}
