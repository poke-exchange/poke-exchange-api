package fr.esgi.poke_exchange_api.exposition.pokecards.models;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CollectionResponse {

    private final UUID userId;
    private final List<CollectedCard> cards;

}
