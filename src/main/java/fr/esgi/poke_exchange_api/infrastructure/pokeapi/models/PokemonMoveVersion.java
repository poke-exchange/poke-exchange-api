package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonMoveVersion {

    @JsonProperty("move_learn_method")
    private NamedAPIResource moveLearnMethod;

    @JsonProperty("version_group")
    private NamedAPIResource version_group;

    @JsonProperty("level_learned_at")
    private Integer levelLearnedAt;

}
