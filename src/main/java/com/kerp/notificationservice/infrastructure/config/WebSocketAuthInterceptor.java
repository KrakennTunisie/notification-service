package com.kerp.notificationservice.infrastructure.config;

import com.kerp.notificationservice.application.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;


    @Override
    public Message<?> preSend(
            Message<?> message,
            MessageChannel channel
    ) {


        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(
                        message,
                        StompHeaderAccessor.class
                );

        System.out.println("STOMP command = " + accessor.getCommand());

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authorization = accessor.getFirstNativeHeader("Authorization");
            try {
                Authentication authentication =
                        jwtService.authenticate(authorization.substring(7));
                accessor.setUser(authentication);
                System.out.println("AUTH OK = " + authentication.getName());
            } catch (Exception e) {
                System.out.println("AUTH EXCEPTION: " + e.getClass().getName() + " - " + e.getMessage());
                e.printStackTrace();
            }
        }

        return message;
    }
}
