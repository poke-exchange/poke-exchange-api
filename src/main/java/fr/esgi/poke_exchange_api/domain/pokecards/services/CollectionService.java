package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.domain.pokecards.mappers.CollectedCardMapper;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.UserCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final UserCardRepository repository;
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

        return collection;
    }
}
