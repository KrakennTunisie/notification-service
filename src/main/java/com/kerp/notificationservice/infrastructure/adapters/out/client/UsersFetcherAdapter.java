package com.kerp.notificationservice.infrastructure.adapters.out.client;

import com.kerp.notificationservice.application.ports.out.UsersIdsFetcherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersFetcherAdapter implements UsersIdsFetcherPort {

    private final UsersClient usersClient;
    @Override
    public List<String> getUsersByRole(String role) {
        return usersClient.getUsersIdsByRole(role);
    }
}
