package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonAbility {
    private boolean is_hiden;
    private Integer slot;
    private NamedAPIResource ability;
}
