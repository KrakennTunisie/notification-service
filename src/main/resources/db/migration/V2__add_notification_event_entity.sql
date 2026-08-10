CREATE TABLE IF NOT EXISTS notifications
(
    id          UUID          NOT NULL,
    event_id    VARCHAR(255)  NOT NULL,
    event_type  VARCHAR(255)  NOT NULL,
    channel     VARCHAR(255)  NOT NULL,
    user_id     VARCHAR(255)  NOT NULL,
    title       VARCHAR(255)  NOT NULL,
    message     VARCHAR(1000) NOT NULL,
    metadata    JSONB,
    status      VARCHAR(255)  NOT NULL,
    occurred_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS idx_notification_event_id
    ON notifications (event_id);

CREATE INDEX IF NOT EXISTS idx_notification_user_deleted_at
    ON notifications (user_id, deleted_at);

CREATE INDEX IF NOT EXISTS idx_notification_user_status
    ON notifications (user_id, status);