CREATE UNIQUE INDEX IF NOT EXISTS idx_notification_event_user_id
    ON notifications(event_id, user_id);

CREATE UNIQUE INDEX IF NOT EXISTS uc_processed_jobs_idevent
    ON processed_jobs(id_event);