package com.kerp.notificationservice.infrastructure.adapters.out.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketsEvents {

    private final SimpUserRegistry simpUserRegistry;

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        System.out.println("=== CONNECTED ===");

        simpUserRegistry.getUsers().forEach(user -> {
            System.out.println(user.getName());

            user.getSessions().forEach(session -> {
                System.out.println(" session=" + session.getId());

                session.getSubscriptions().forEach(sub ->
                        System.out.println(" subscription=" + sub.getDestination())
                );
            });
        });
    }

    @EventListener
    public void handleDisconnect(
            SessionDisconnectEvent event
    ) {

        System.out.println(
                "DISCONNECTED session="
                        + event.getSessionId()
        );

        System.out.println(
                "USER="
                        + event.getUser()
        );
    }
}
