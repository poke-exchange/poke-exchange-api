package fr.esgi.poke_exchange_api.domain.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CollectedCard {

    private UUID id;
    private Integer cardId;
    private Integer quantity;

}
