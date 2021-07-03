package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.ResourceLink;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Pokemon {

    private Integer id;

    private String name;

    @JsonProperty("base_experience")
    private int baseExperience;

    private Integer height;

    private Integer weight;

    private PokemonSprites sprites;

    private ResourceLink species;

    private List<PokemonStat> stats;

    private List<PokemonType> types;

}
