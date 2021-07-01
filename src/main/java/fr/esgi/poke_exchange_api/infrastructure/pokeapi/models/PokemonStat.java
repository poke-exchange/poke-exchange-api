package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonStat {

    private NamedAPIResource stat;

    private Integer effort;

    @JsonProperty("base_stat")
    private Integer baseStat;
}
