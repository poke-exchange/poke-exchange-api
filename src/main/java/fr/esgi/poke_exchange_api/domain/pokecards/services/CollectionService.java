package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.EmptyCollectionRequestException;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.pokecards.mappers.CollectedCardMapper;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.exposition.pokecards.models.Collection;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.CollectedCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectedCardsRepository repository;
    private final CollectedCardMapper mapper;

    public List<CollectedCard> findUserCollection(UUID userId) {
        var collections = this.repository.findAll();

        var userCollection = collections.stream()
                .filter(collection -> collection.getUserId().equals(userId))
                .collect(Collectors.toList());

        var collection = new ArrayList<CollectedCard>();

        for (var item : userCollection) {
            collection.add(this.mapper.from(item));
        }

        if (!collection.isEmpty()) {
            collection.sort(Comparator.comparingInt(CollectedCard::getCardId));
        }

        return collection;
    }

    public void saveCollection(Collection request) {
        var userCollection = this.findUserCollection(request.getUserId());

        if (collectionsAreEqual(request.getCards(), userCollection)) {
            return;
        }

        if (request.getCards().isEmpty()) {
            throw new EmptyCollectionRequestException();
        }

        var collection = request.getCards();

        if (!userCollection.isEmpty()) {
            collection = this.mergeCollections(request.getCards(), userCollection);
            collection.sort(Comparator.comparingInt(CollectedCard::getCardId));

            this.deleteUserCollection(request.getUserId());
        }

        for (var card : collection) {
            this.repository.save(this.mapper.from(request.getUserId(), card));
        }
    }

    public boolean updateCardQuantity(UUID user, CollectedCard card) {
        var entityOptional = this.repository.findAll().stream()
                .filter(entity -> entity.getUserId().equals(user))
                .filter(entity -> entity.getCardId().equals(card.getCardId()))
                .findFirst();

        if (entityOptional.isEmpty()) {
            throw new PokeCardNotFoundException();
        }

        var entity = entityOptional.get();

        if (card.getQuantity().equals(0)) {
            this.repository.deleteById(entity.getId());
            return true;
        }

        entity.setQuantity(card.getQuantity());
        var modifiedEntity = this.repository.save(entity);

        return modifiedEntity.getQuantity().equals(card.getQuantity());
    }

    private void deleteCardFromUserCollection(UUID user, CollectedCard card) {
        var entityOptional = this.repository.findAll().stream()
                .filter(entity -> entity.getUserId().equals(user))
                .filter(entity -> entity.getCardId().equals(card.getCardId()))
                .findFirst();

        if (entityOptional.isEmpty()) {
            throw new PokeCardNotFoundException();
        }

        this.repository.deleteById(entityOptional.get().getId());
    }

    private void deleteUserCollection(UUID user) {
        var entities = this.repository.findAll().stream()
                .filter(card -> card.getUserId().equals(user))
                .collect(Collectors.toList());

        this.repository.deleteAll(entities);
    }

    private List<CollectedCard> mergeCollections(List<CollectedCard> collectionA, List<CollectedCard> collectionB) {
        var collection = new ArrayList<CollectedCard>();
        var currentCardIds = this.sortAndMap(collectionB, "id");

        var tmpA = this.copyCollection(collectionA);
        for (var card : tmpA) {
            if (!currentCardIds.contains(card.getCardId())) {
                var cardIdCollection = this.sortAndMap(collection, "id");

                if (!cardIdCollection.contains(card.getCardId())) {
                    collection.add(card);
                }

                collectionA.remove(card);
            }
        }

        var tmpB = this.copyCollection(collectionB);
        for (var cardA : collectionA) {
            for (var cardB : tmpB) {
                if (cardA.getCardId().equals(cardB.getCardId())
                        &&
                    (cardA.getQuantity() > 0 || cardA.getQuantity().equals(cardB.getQuantity())))
                {
                    collection.add(cardA);
                    collectionB.remove(cardB);
                }
                else if (cardA.getCardId().equals(cardB.getCardId())
                        && cardA.getQuantity() == 0)
                {
                    collectionB.remove(cardB);
                }
            }
        }

        collection.addAll(collectionB);

        return collection;
    }

    private List<CollectedCard> copyCollection(List<CollectedCard> collection) {
        var copiedCollection = new ArrayList<CollectedCard>();

        for (var card : collection) {
            var copy = new CollectedCard();
            copy.setCardId(card.getCardId());
            copy.setQuantity(card.getQuantity());
            copiedCollection.add(copy);
        }

        return copiedCollection;
    }

    private boolean collectionsAreEqual(List<CollectedCard> collectionA, List<CollectedCard> collectionB) {
        if (collectionA.size() != collectionB.size()) {
            return false;
        }

        var idsOfA = this.sortAndMap(collectionA, "id");
        var idsOfB = this.sortAndMap(collectionB, "id");
        var quantitiesOfA = this.sortAndMap(collectionA, "quantity");
        var quantitiesOfB = this.sortAndMap(collectionB, "quantity");


        return idsOfA.equals(idsOfB) && quantitiesOfA.equals(quantitiesOfB);
    }

    private List<Integer> sortAndMap(List<CollectedCard> collection, String property) {
        collection.sort(Comparator.comparingInt(CollectedCard::getCardId));
        var result = collection.stream()
                .map(CollectedCard::getCardId)
                .collect(Collectors.toList());

        if (property.equals("quantity")) {
            result = collection.stream()
                    .map(CollectedCard::getQuantity)
                    .collect(Collectors.toList());
        }

        return result;
    }
}


