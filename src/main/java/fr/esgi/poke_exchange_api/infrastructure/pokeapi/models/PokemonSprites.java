package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonSprites {

    @JsonProperty("front_default")
    private String frontDefault;

    @JsonProperty("front_shiny")
    private String frontShiny;

    @JsonProperty("front_female")
    private String frontFemale;

    @JsonProperty("front_shiny_female")
    private String frontShinyFemale;

    @JsonProperty("back_default")
    private String backDefault;

    @JsonProperty("back_shiny")
    private String backShiny;

    @JsonProperty("back_female")
    private String backFemale;

    @JsonProperty("back_shiny_female")
    private String backShinyFemale;

}
