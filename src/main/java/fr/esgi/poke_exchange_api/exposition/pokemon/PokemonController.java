package fr.esgi.poke_exchange_api.exposition.pokemon;

import fr.esgi.poke_exchange_api.domain.pokemon.PokemonService;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/pokemons")
@RequiredArgsConstructor
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping()
    public String test() {
        return "Pokemons endpoint";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pokemonService.getPokemonById(id));
        } catch(RuntimeException e) {
            System.out.println("the fuck " + e);
            return ResponseEntity.notFound().build();
        }
    }
}
