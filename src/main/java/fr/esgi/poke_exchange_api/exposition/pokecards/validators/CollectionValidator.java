package fr.esgi.poke_exchange_api.exposition.pokecards.validators;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.UserService;
import fr.esgi.poke_exchange_api.exposition.pokecards.models.Collection;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.PokemonRangeIds;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CollectionValidator {

    private final UserService service;

    public boolean isValid(Collection collection) {
        if (this.isNotExistingUser(collection.getUserId())) {
            return false;
        }

        for (var card : collection.getCards()) {
            if (!this.isExistingCard(card) || card.getQuantity() < 0) {
                return false;
            }
        }

        return !this.containsDuplicates(collection.getCards());
    }

    public boolean containsDuplicates(List<CollectedCard> collection) {
        var set = new HashSet<Integer>();

        for (var card : collection) {
            if (set.contains(card.getCardId())) {
                return true;
            }
            set.add(card.getCardId());
        }

        return false;
    }

    public boolean isNotExistingUser(UUID userId) {
        try {
            this.service.findOneById(userId);
            return false;

        } catch (UserNotFoundException exception) {
            return true;
        }
    }

    public boolean isExistingCard(CollectedCard card) {
        return card.getCardId() >= PokemonRangeIds.firstId
                && card.getCardId() <= PokemonRangeIds.lastId;
    }

}
