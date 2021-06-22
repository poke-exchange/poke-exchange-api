package fr.esgi.poke_exchange_api.domain.pokemon.mappers;

import fr.esgi.poke_exchange_api.domain.authentication.RegisterRequestBody;
import fr.esgi.poke_exchange_api.domain.pokemon.models.NamedAPIResource;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import fr.esgi.poke_exchange_api.domain.pokemon.models.PokemonsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PokemonsResponseMapper {
    public PokemonsResponse from(Pokemons pokemons) {
        PokemonsResponse pokemonsResponse = new PokemonsResponse();

        pokemonsResponse.setCount(pokemons.getCount());
        pokemonsResponse.setPrevious(pokemons.getPrevious());
        pokemonsResponse.setNext(pokemons.getNext());
        pokemonsResponse.setResults(getResultsIds(pokemons.getResults()));
        return pokemonsResponse;
    }

    public List<Integer> getResultsIds(List<NamedAPIResource> results) {

        List<Integer> ids = new ArrayList<Integer>();

        return results.stream().map(url -> {
            String[] parts = url.getUrl().split("/");
            return Integer.parseInt(parts[parts.length - 1]);
        }).collect(Collectors.toList());
    }
}
