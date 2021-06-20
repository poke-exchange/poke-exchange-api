package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonStat {
    private NamedAPIResource stat;
    private Integer effort;
    private Integer base_stat;
}
