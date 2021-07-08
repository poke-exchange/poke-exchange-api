package fr.esgi.poke_exchange_api.domain.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CollectedPokemonCard {

    private UUID id;
    private PokemonCard card;
    private Integer quantity;

}
