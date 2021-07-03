package fr.esgi.poke_exchange_api.domain.pokecards.mappers;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.CollectedCardsEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectedCardMapper {

    public CollectedCard from(CollectedCardsEntity entity) {
        var collectedCard = new CollectedCard();
        collectedCard.setCardId(entity.getCardId());
        collectedCard.setQuantity(entity.getQuantity());

        return collectedCard;
    }

}
