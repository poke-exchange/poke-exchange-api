package fr.esgi.poke_exchange_api.domain.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CollectedPokemonCard {

    private PokemonCard card;
    private Integer quantity;

}
