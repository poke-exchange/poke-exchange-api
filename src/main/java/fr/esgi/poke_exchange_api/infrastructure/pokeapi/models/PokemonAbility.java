package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonAbility {

    @JsonProperty("is_hidden")
    private boolean isHidden;

    private Integer slot;

    private NamedAPIResource ability;

}
