package fr.esgi.poke_exchange_api.infrastructure.pokeapi;

import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PokeApiRepository {

    private final String url;
    private final RestTemplate http;

    @Autowired
    public PokeApiRepository() {
        this.url = "https://pokeapi.co/api/v2/pokemon";
        this.http = new RestTemplate();
    }

    public Pokemon findOneById(Integer id) {
        final String pokeApi = this.url + "/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        return this.http.getForObject(pokeApi, Pokemon.class, params);
    }

    public Pokemons findAll(Integer limit, Integer offset) {
        final String pokeApi = this.url + "?limit={limit}&offset={offset}";

        Map<String, String> params = new HashMap<>();
        params.put("limit", limit.toString());
        params.put("offset", offset.toString());

        return this.http.getForObject(pokeApi, Pokemons.class, params);
    }
}
