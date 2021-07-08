package fr.esgi.poke_exchange_api.domain.trades;

import fr.esgi.poke_exchange_api.domain.pokecards.mappers.CollectedCardMapper;
import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.trades.models.Trade;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.CollectedCardsRepository;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.CollectedCardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExchangeCardService {

    private final CollectedCardsRepository repository;
    private final CollectedCardMapper mapper;

    public Trade exchange(Trade trade) {
        var trader = trade.getTrader();
        var approver = trade.getRecipient();

        save(trader.getId(), trader.getCard());
        save(approver.getId(), approver.getCard());

        var tmp = trader.getCard();
        trader.setCard(approver.getCard());
        approver.setCard(tmp);

        save(trader.getId(), trader.getCard());
        save(approver.getId(), approver.getCard());

        return new Trade(trade.getId(), trader, approver, trade.getState());
    }

    public void save(UUID user, CollectedCard card) {
        var optional = this.repository.findById(card.getId());

        if (optional.isPresent()) {
            var cardEntity = updateCardQuantity(optional.get(), card);

            if (cardEntity.getQuantity().equals(0)) {
                this.repository.deleteById(cardEntity.getId());
            } else {
                this.repository.save(cardEntity);
            }
            return;
        }

        this.repository.save(this.mapper.from(user, card));
    }

    private Integer intervalBetween(CollectedCard a, CollectedCardEntity b) {
        return a.getQuantity() - b.getQuantity();
    }

    private CollectedCardEntity updateCardQuantity(CollectedCardEntity origin, CollectedCard card) {
        var quantityInterval = intervalBetween(card, origin);
        var quantity = (quantityInterval == 0) ? 0 : origin.getQuantity() + quantityInterval;
        origin.setQuantity(quantity);

        return origin;
    }

}
