package fr.esgi.poke_exchange_api.exposition.user;

import fr.esgi.poke_exchange_api.domain.pokemon.models.Pokemons;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserResponseMapper;
import fr.esgi.poke_exchange_api.domain.user.UserService;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.models.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserResponseMapper toUserResponse;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        var users = userService.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var response = new ArrayList<UserResponse>();
        users.forEach(user -> response.add(toUserResponse.from(user)));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findOneById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(toUserResponse.from(userService.findOneById(id)));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponse> findOneByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(toUserResponse.from(userService.findOneByUsername(username)));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pokemons")
    public ResponseEntity<List<Integer>> findAllUserPokemons(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.findUserPokemonsById(id));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/pokemons")
    public ResponseEntity<List<Integer>> addPokemonToUser(@PathVariable UUID id,
                                                    @RequestBody Map<String, Integer> pokemonId) {
        try {
            return ResponseEntity.ok(userService.addPokemonToUser(id, pokemonId.get("pokemonId")));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
