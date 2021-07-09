package fr.esgi.poke_exchange_api.infrastructure.pokecards.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "daily_bonus")
@NoArgsConstructor
@AllArgsConstructor
public class DailyBonusEntity {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "last_time_received")
    private LocalDateTime lastTimeReceived;
}
