package com.kerp.notificationservice.infrastructure.adapters.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "iam-server", path = InternalApiPaths.USERS_BASE)
public interface UsersClient {

    @GetMapping(InternalApiPaths.USERS_CONTENT)
    List<String> getUsersIdsByRole(@PathVariable("roleName") String role);
}
