package com.kerp.notificationservice.infrastructure.adapters.out.persistance.entity;

import com.kerp.notificationservice.domain.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(
        name = "notifications",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "idx_notification_event_user_id",
                        columnNames = {
                                "event_id",
                                "user_id"
                        }
                )
        }
)
@Getter
@Setter
public class NotificationEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String channel;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String message;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    @Column(nullable = false)
    private Instant occurredAt;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column
    private Instant deletedAt;

    public void markAsRead() {
        this.status = NotificationStatus.READ;
    }

    /**
     * Soft deletes the notification.
     */
    public void delete() {
        this.deletedAt = Instant.now();
    }

    /**
     * Restores a soft-deleted notification.
     */
    public void restore() {
        this.deletedAt = null;
    }

    /**
     * Returns true if the notification has been soft deleted.
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }
}
