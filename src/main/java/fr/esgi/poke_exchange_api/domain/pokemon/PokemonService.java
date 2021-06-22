package fr.esgi.poke_exchange_api.domain.pokemon;

import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PokemonService {
    RestTemplate restTemplate = new RestTemplate();

    public Pokemon getPokemonById(Integer id) {
        final String pokeApi = "https://pokeapi.co/api/v2/pokemon/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        Pokemon pokemon = restTemplate.getForObject(pokeApi, Pokemon.class, params);

        return pokemon;
    }

    public Pokemons getAllPokemons(Integer limit, Integer offset) {
        final String pokeApi = "https://pokeapi.co/api/v2/pokemon?limit={limit}&offset={offset}";

        Map<String, String> params = new HashMap<>();
        params.put("limit", limit.toString());
        params.put("offset", offset.toString());

        Pokemons pokemons = restTemplate.getForObject(pokeApi, Pokemons.class, params);

        return pokemons;
    }
}
