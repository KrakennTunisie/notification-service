package com.kerp.notificationservice.application.ports.out;

import java.util.List;

public interface UsersIdsFetcherPort {
    List<String> getUsersByRole(String role);
}
