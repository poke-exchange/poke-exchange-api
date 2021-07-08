package fr.esgi.poke_exchange_api.domain.trades.mappers;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.trades.models.Trade;
import fr.esgi.poke_exchange_api.domain.trades.models.Trader;
import fr.esgi.poke_exchange_api.infrastructure.trades.TradeEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TradeMapper {

    public Trade from(TradeEntity entity) {
        var trade = new Trade();

        trade.setId(entity.getId());

        var traderCard = this.setCollectedCard(entity.getTraderCard(), entity.getTraderCardQuantity());
        trade.setTrader(this.setTrader(entity.getTrader(), traderCard));

        var recipientCard = this.setCollectedCard(
                entity.getRecipientCard(), entity.getRecipientCardQuantity());
        trade.setRecipient(this.setTrader(entity.getRecipient(), recipientCard));

        trade.setState(entity.getState());

        return trade;
    }

    public TradeEntity from(UUID id, Trader trader, Trader recipient, String state) {
        var entity = new TradeEntity();

        if (id != null) {
            entity.setId(id);
        } else {
            entity.setCreatedAt(LocalDateTime.now());
        }

        entity.setTrader(trader.getId());
        entity.setTraderCard(trader.getCard().getCardId());
        entity.setTraderCardQuantity(trader.getCard().getQuantity());
        entity.setRecipient(recipient.getId());
        entity.setRecipientCard(recipient.getCard().getCardId());
        entity.setRecipientCardQuantity(trader.getCard().getQuantity());
        entity.setState(state);
        entity.setLastUpdate(LocalDateTime.now());

        return entity;
    }

    private CollectedCard setCollectedCard(Integer cardId, Integer quantity) {
        var card = new CollectedCard();
        card.setCardId(cardId);
        card.setQuantity(quantity);

        return card;
    }

    private Trader setTrader(UUID user, CollectedCard card) {
        return new Trader(user, card);
    }
}
