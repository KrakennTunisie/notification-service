package com.kerp.notificationservice.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JwtDecoder jwtDecoder;


    public Authentication authenticate(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        String userId = jwt.getSubject();

        log.info("userID:", userId);

        return new UsernamePasswordAuthenticationToken(
                userId,
                null,
                List.of()
        );
    }
}
