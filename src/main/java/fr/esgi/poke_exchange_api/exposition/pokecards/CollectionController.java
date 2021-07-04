package fr.esgi.poke_exchange_api.exposition.pokecards;

import fr.esgi.poke_exchange_api.domain.pokecards.services.CollectionService;
import fr.esgi.poke_exchange_api.exposition.pokecards.models.Collection;
import fr.esgi.poke_exchange_api.exposition.pokecards.validators.CollectionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionValidator validator;
    private final CollectionService collectionService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Collection> findUserCollection(@PathVariable UUID id) {
        if (this.validator.isNotExistingUser(id)) {
            return ResponseEntity.badRequest().build();
        }

        var cards = this.collectionService.findUserCollection(id);

        return ResponseEntity.ok(new Collection(id, cards));
    }

    @PostMapping
    public ResponseEntity<?> saveCollection(@RequestBody Collection request) {
        if (!this.validator.isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        this.collectionService.saveCollection(request);

        return ResponseEntity.ok().build();
    }
}
