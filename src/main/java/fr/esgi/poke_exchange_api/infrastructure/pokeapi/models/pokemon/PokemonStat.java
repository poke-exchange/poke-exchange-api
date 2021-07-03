package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.ResourceLink;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonStat {

    private ResourceLink stat;

    private Integer effort;

    @JsonProperty("base_stat")
    private Integer baseStat;
}
