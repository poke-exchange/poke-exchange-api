package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class Pokemon {
    private int id;
    private String name;
    private int base_experience;
    private String height;
    private String is_default;
    private String order;
    private String weight;
    private List<PokemonAbility> abilities;
    private List<NamedAPIResource> forms;
    private List<VersionGameIndex> game_indices;
    private List<PokemonHeldItem> held_items;
    private String location_area_encounters;
    private List<PokemonMoves> moves;
    private PokemonSprites sprites;
    private NamedAPIResource species;
    private List<PokemonStat> stats;
    private List<PokemonType> types;
}
