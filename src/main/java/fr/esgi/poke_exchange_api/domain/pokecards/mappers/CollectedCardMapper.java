package fr.esgi.poke_exchange_api.domain.pokecards.mappers;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.CollectedCardEntity;
import org.springframework.stereotype.Component;

@Component
public class CollectedCardMapper {

    public CollectedCard from(CollectedCardEntity entity) {
        var collectedCard = new CollectedCard();
        collectedCard.setCardId(entity.getCardId());
        collectedCard.setQuantity(entity.getQuantity());

        return collectedCard;
    }

}
