package fr.esgi.poke_exchange_api.exposition.pokecards;

import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.EmptyCollectionRequestException;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedPokemonCard;
import fr.esgi.poke_exchange_api.domain.pokecards.services.CollectionService;
import fr.esgi.poke_exchange_api.domain.pokecards.services.PokemonCardService;
import fr.esgi.poke_exchange_api.exposition.pokecards.models.Collection;
import fr.esgi.poke_exchange_api.exposition.pokecards.models.PokemonCollection;
import fr.esgi.poke_exchange_api.exposition.pokecards.validators.CollectionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionValidator validator;
    private final CollectionService collectionService;
    private final PokemonCardService pokemonService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Collection> findUserCollection(@PathVariable UUID id) {
        if (this.validator.isNotExistingUser(id)) {
            return ResponseEntity.badRequest().build();
        }

        var cards = this.collectionService.findUserCollection(id);

        return ResponseEntity.ok(new Collection(id, cards));
    }

    @GetMapping("/user/{id}/cards")
    public ResponseEntity<PokemonCollection> findUserPokemonCollection(@PathVariable UUID id) {
        if (this.validator.isNotExistingUser(id)) {
            return ResponseEntity.badRequest().build();
        }

        var cards = this.collectionService.findUserCollection(id);

        var pokemonCards = new ArrayList<CollectedPokemonCard>();
        for (var card : cards) {
            var pokemon = this.pokemonService.findOneById(card.getCardId());
            var collectedPokemonCard = new CollectedPokemonCard();
            collectedPokemonCard.setId(card.getId());
            collectedPokemonCard.setCard(pokemon);
            collectedPokemonCard.setQuantity(card.getQuantity());

            pokemonCards.add(collectedPokemonCard);
        }

        return ResponseEntity.ok(new PokemonCollection(id, pokemonCards));
    }

    @PostMapping
    public ResponseEntity<?> saveCollection(@RequestBody Collection request) {
        if (!this.validator.isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            this.collectionService.saveCollection(request);

        } catch (EmptyCollectionRequestException exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserCardQuantity(
            @PathVariable("id") UUID userId, @RequestBody CollectedCard card)
    {
        try {
            var isUpdated = this.collectionService.updateCardQuantity(userId, card);

            if (!isUpdated) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            return ResponseEntity.ok().build();

        } catch (PokeCardNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
