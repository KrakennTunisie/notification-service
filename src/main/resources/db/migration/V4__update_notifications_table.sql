ALTER TABLE notifications
DROP CONSTRAINT IF EXISTS idx_notification_event_id;

CREATE UNIQUE INDEX IF NOT EXISTS idx_notification_event_user_id
    ON notifications(event_id, user_id);