package fr.esgi.poke_exchange_api.infrastructure.pokeapi;

import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon.Pokemon;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PokeApiRepository {

    private final String url;
    private final RestTemplate http;

    @Autowired
    public PokeApiRepository() {
        this.url = "https://pokeapi.co/api/v2/pokemon";
        this.http = new RestTemplate();
    }

    public Optional<Pokemon> findOneById(Integer id) {
        try {
            if (this.notExists(id)) {
                return Optional.empty();
            }

            final String pokeApi = this.url + "/{id}";

            Map<String, String> params = new HashMap<>();
            params.put("id", id.toString());
            
            var response = this.http.getForObject(pokeApi, Pokemon.class, params);
            if (response == null) {
                return Optional.empty();
            }

            return Optional.of(response);
//
        } catch (RestClientException exception) {
            return Optional.empty();
        }
    }

    public boolean notExists(Integer pokemonId) {
        return pokemonId < PokemonRangeIds.firstId || pokemonId > PokemonRangeIds.lastId;
    }

    public Optional<Integer> findPokemonGeneration(Pokemon pokemon) {
        if (this.notExists(pokemon.getId())) {
            return Optional.empty();
        }

        var url = pokemon.getSpecies().getUrl();
        var species = this.http.getForObject(url, PokemonSpecies.class);

        if (species == null) {
            return Optional.empty();
        }

        var generationUrl = species.getGeneration().getUrl().split("/");
        try {
            var response = Integer.parseInt(generationUrl[generationUrl.length - 1]);
            return Optional.of(response);

        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    public Integer firstPokemonId() {
        return PokemonRangeIds.firstId;
    }

    public Integer lastPokemonId() {
        return PokemonRangeIds.lastId;
    }
}
