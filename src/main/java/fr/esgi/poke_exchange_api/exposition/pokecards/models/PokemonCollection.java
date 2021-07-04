package fr.esgi.poke_exchange_api.exposition.pokecards.models;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedPokemonCard;
import fr.esgi.poke_exchange_api.domain.pokecards.models.PokemonCard;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PokemonCollection {

    private final UUID userId;
    private final List<CollectedPokemonCard> cards;

}
