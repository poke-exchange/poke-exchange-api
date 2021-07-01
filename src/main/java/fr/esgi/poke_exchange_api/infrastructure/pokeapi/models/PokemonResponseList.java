package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import fr.esgi.poke_exchange_api.domain.pokemon.models.NamedAPIResource;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonResponseList {

    private Integer count;

    private String next;

    private String previous;

    private List<NamedAPIResource> results;

}
