package fr.esgi.poke_exchange_api.domain.pokemon;

import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import org.springframework.context.annotation.Bean;
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
}
