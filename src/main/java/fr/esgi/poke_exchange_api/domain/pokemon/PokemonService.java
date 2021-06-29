package fr.esgi.poke_exchange_api.domain.pokemon;

import fr.esgi.poke_exchange_api.domain.pokemon.models.Claim;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class PokemonService {

    private final int NUMBER_OF_POKEMONS = 898;
    private final String baseApi = "https://pokeapi.co/api/v2/pokemon";

    RestTemplate restTemplate = new RestTemplate();

    public Pokemon getPokemonById(Integer id) {
        final String pokeApi = baseApi + "/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        return restTemplate.getForObject(pokeApi, Pokemon.class, params);
    }

    public Pokemons getAllPokemons(Integer limit, Integer offset) {
        final String pokeApi = baseApi + "?limit={limit}&offset={offset}";

        Map<String, String> params = new HashMap<>();
        params.put("limit", limit.toString());
        params.put("offset", offset.toString());

        Pokemons pokemons = restTemplate.getForObject(pokeApi, Pokemons.class, params);

        return pokemons;
    }

    public Pokemon getRandomPokemon() {
        Pokemon pokemon = getPokemonById(generateRandomIntIntRange(1, NUMBER_OF_POKEMONS));
        return pokemon;
    }

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public Pokemon claim(Claim claim) {
        if (claim.getLastClaimDate().plusWeeks(1).isBefore(LocalDateTime.now())) {
            return getRandomPokemon();
        }
        throw new PokemonClaimedException();
    }
}
