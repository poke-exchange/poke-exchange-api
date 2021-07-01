package fr.esgi.poke_exchange_api.infrastructure.friendship;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class FriendshipEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private UUID claimer;

    @Column(nullable = false)
    private UUID receiver;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean accepted;

    @Column(name = "created", nullable = false,
            insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
