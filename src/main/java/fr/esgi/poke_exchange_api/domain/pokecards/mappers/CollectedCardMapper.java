package fr.esgi.poke_exchange_api.domain.pokecards.mappers;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.CollectedCardEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CollectedCardMapper {

    public CollectedCard from(CollectedCardEntity entity) {
        var collectedCard = new CollectedCard();
        collectedCard.setId(entity.getId());
        collectedCard.setCardId(entity.getCardId());
        collectedCard.setQuantity(entity.getQuantity());

        return collectedCard;
    }

    public CollectedCardEntity from(UUID user, CollectedCard card) {
        var entity = new CollectedCardEntity();
        entity.setUserId(user);

        if (card.getId() != null) {
            entity.setId(card.getId());
        }

        entity.setCardId(card.getCardId());
        entity.setQuantity(card.getQuantity());

        return entity;
    }

}
