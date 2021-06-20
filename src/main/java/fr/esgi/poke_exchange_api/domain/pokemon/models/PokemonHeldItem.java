package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonHeldItem {
    private NamedAPIResource item;
    private PokemonHeldItemVersion version_details;

    private class PokemonHeldItemVersion {
        private NamedAPIResource version;
        private Integer rarity;
    }
}
