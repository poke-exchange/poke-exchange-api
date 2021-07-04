package fr.esgi.poke_exchange_api.domain.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CollectedCard {

    private Integer cardId;
    private Integer quantity;

}
