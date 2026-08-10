package com.kerp.notificationservice.infrastructure.adapters.out.client;

public final class InternalApiPaths {

    private InternalApiPaths() {}

    public static final String ATTACHMENTS_BASE = "/api/billing/documents";
    public static final String ATTACHMENT_CONTENT = "/{id}/file-content";

    public static final String USERS_BASE = "/api/iam/users";
    public static final String USERS_CONTENT = "/role/{roleName}";
}