package dev.lucasdeabreu.outbox.pollbased.changelog;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "changelogs")
public class ChangelogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String eventType;

    @Column
    private String tableName;

    @Column
    private String data;

    @Column
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime modifiedAt;

    @PrePersist
    public void prePersist() {
        final LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        modifiedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        modifiedAt = LocalDateTime.now();
    }

}
