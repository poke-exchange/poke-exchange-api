package fr.esgi.poke_exchange_api.exposition.pokemon;

import fr.esgi.poke_exchange_api.domain.pokemon.PokemonClaimedException;
import fr.esgi.poke_exchange_api.domain.pokemon.PokemonService;
import fr.esgi.poke_exchange_api.domain.pokemon.mappers.PokemonsResponseMapper;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Claim;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemon;
import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import fr.esgi.poke_exchange_api.domain.pokemon.models.PokemonsResponse;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;
    private final PokemonsResponseMapper toPokemonsResponse;


    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pokemonService.getPokemonById(id));
        } catch(RuntimeException e) {
            System.out.println("the fuck " + e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Pokemon> randomPokemon() {
        try {
            return ResponseEntity.ok(pokemonService.getRandomPokemon());
        } catch(RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(params = { "limit", "offset" })
    public ResponseEntity<PokemonsResponse> getAllPokemons(@RequestParam("limit") int limit,
                                                           @RequestParam("offset") int offset) {
        try {
            return ResponseEntity.ok(toPokemonsResponse.from(pokemonService.getAllPokemons(limit, offset)));
        } catch(RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/claim")
    public ResponseEntity claimPokemon(@RequestBody Claim lastClaim) {
        try {
            return ResponseEntity.ok(pokemonService.claim(lastClaim));
        } catch (PokemonClaimedException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("you have already claimed your weekly pokemon");
        }
    }
}
