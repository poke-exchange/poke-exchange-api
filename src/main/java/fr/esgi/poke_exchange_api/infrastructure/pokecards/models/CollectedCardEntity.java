package fr.esgi.poke_exchange_api.infrastructure.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class CollectedCardEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(columnDefinition = "integer default 1")
    private Integer quantity;
}
