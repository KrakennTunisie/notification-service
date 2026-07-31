CREATE TABLE IF NOT EXISTS processed_jobs
(
    id           UUID NOT NULL,
    id_event     UUID NOT NULL,
    processed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_processed_jobs PRIMARY KEY (id)
    );

DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'uc_processed_jobs_idevent'
          AND conrelid = 'processed_jobs'::regclass
    ) THEN
ALTER TABLE processed_jobs
    ADD CONSTRAINT uc_processed_jobs_idevent UNIQUE (id_event);
END IF;
END
$$;