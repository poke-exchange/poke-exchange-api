package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonResponse {

    private int id;

    private String name;

    @JsonProperty("base_experience")
    private int baseExperience;

    private String height;

    @JsonProperty("is_default")
    private String isDefault;

    private String order;

    private String weight;

    private List<PokemonAbility> abilities;

    private List<NamedAPIResource> forms;

    @JsonProperty("game_indices")
    private List<VersionGameIndex> gameIndices;

    @JsonProperty("held_items")
    private List<PokemonHeldItem> heldItems;

    @JsonProperty("location_area_encounters")
    private String locationAreaEncounters;

    private List<PokemonMoves> moves;

    private PokemonSprites sprites;

    private NamedAPIResource species;

    private List<PokemonStat> stats;

    private List<PokemonType> types;

}
