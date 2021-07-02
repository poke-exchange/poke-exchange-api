package fr.esgi.poke_exchange_api.exposition.pokecards;

import fr.esgi.poke_exchange_api.domain.pokecards.PokeCard;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardIdNotValidException;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.pokecards.services.PokemonCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class PokeCardController {

    private final PokemonCardService service;

    @GetMapping("/{id}")
    public ResponseEntity<PokeCard> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.service.findOneById(id));

        } catch (PokeCardIdNotValidException exception) {
            return ResponseEntity.badRequest().build();

        } catch (PokeCardNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<?>> findMany(
            @RequestParam("limit") Integer limit, @RequestParam("start") Integer id)
    {
        try {
            return ResponseEntity.ok(this.service.findMany(limit, id));

        } catch (PokeCardIdNotValidException exception) {
            return ResponseEntity.badRequest().build();

        } catch (PokeCardNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        try {
            return ResponseEntity.ok(this.service.findAll());

        } catch (PokeCardIdNotValidException exception) {
            return ResponseEntity.badRequest().build();

        } catch (PokeCardNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
