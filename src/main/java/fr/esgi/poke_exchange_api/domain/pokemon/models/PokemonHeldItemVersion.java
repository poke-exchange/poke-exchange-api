package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonHeldItemVersion {
    private NamedAPIResource version;
    private Integer rarity;
}
