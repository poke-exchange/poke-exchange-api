package fr.esgi.poke_exchange_api.exposition.pokecards;

import fr.esgi.poke_exchange_api.domain.pokecards.services.CollectionService;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final UserService userService;
    private final CollectionService collectionService;

    @GetMapping("/user/{id}")
    public ResponseEntity<CollectionResponse> findUserCollection(@PathVariable UUID id) {
        try {
            this.userService.findOneById(id);
            var cards = this.collectionService.findUserCollection(id);

            return ResponseEntity.ok(new CollectionResponse(id, cards));

        } catch (UserNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}
