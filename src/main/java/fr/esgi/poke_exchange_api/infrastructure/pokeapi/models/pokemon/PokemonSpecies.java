package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon;

import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.ResourceLink;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonSpecies {

    private Integer id;
    private String name;
    private ResourceLink generation;


}
