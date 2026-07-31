package com.kerp.notificationservice.application.ports.out;

public interface ProcessedEventPort {

    boolean isAlreadyProcessed(String eventId);
    void markAsProcessed(String eventId);
}
