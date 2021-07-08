package fr.esgi.poke_exchange_api.domain.trades.validators;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import fr.esgi.poke_exchange_api.domain.pokecards.services.CollectionService;
import fr.esgi.poke_exchange_api.domain.trades.models.Trader;
import fr.esgi.poke_exchange_api.domain.user.UserNotFoundException;
import fr.esgi.poke_exchange_api.domain.user.UserService;
import fr.esgi.poke_exchange_api.exposition.trades.models.TradeProposal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TradeValidator {

    private final CollectionService collectionService;
    private final UserService userService;

    public boolean isValid(TradeProposal trade) {
        if (this.tradersAreEqual(trade.getTrader(), trade.getRecipient())) {
            return false;
        }

        var trader = trade.getTrader();
        var recipient = trade.getRecipient();

        return this.ownsCard(trader) && this.ownsCard(recipient)
                && this.tradeCardHasEnoughQuantity(trader.getCard())
                && this.tradeCardHasEnoughQuantity(recipient.getCard());
    }

    public boolean tradersAreEqual(Trader a, Trader b) {
        return a.getId().equals(b.getId());
    }

    public boolean tradeCardHasEnoughQuantity(CollectedCard card) {
        return card.getQuantity() > 0;
    }

    public boolean ownsCard(Trader trader) {
        var collection = this.collectionService.findUserCollection(trader.getId());
        var exist = collection.stream()
                .filter(card -> card.getCardId().equals(trader.getCard().getCardId()))
                .findFirst();

        return exist.isPresent();
    }

    public boolean userExists(UUID userId) {
        try {
            this.userService.findOneById(userId);
            return true;

        }catch (UserNotFoundException exception) {
            return false;
        }
    }

}
