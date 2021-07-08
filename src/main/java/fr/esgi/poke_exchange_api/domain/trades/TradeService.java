package fr.esgi.poke_exchange_api.domain.trades;

import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.pokecards.services.CollectionService;
import fr.esgi.poke_exchange_api.domain.trades.mappers.TradeMapper;
import fr.esgi.poke_exchange_api.domain.trades.models.Trade;
import fr.esgi.poke_exchange_api.domain.trades.models.Trader;
import fr.esgi.poke_exchange_api.domain.trades.validators.TradeValidator;
import fr.esgi.poke_exchange_api.infrastructure.trades.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository repository;
    private final CollectionService service;
    private final TradeValidator validator;
    private final ExchangeCardService exchangeService;
    private final TradeMapper mapper;

    public boolean isNotPending(Trade trade) {
        return !trade.getState().equals(TradeState.PENDING.toString());
    }

    public Trade findOneById(UUID tradeId) {
        var entity = this.repository.findById(tradeId);

        if (entity.isEmpty()) {
            throw new TradeNotFoundException();
        }

        var trade = this.mapper.from(entity.get());
        var tradeCard = this.service.findOneByCardId(trade.getTrader().getCard().getCardId());
        var recipientCard = this.service.findOneByCardId(trade.getRecipient().getCard().getCardId());

        trade.getTrader().getCard().setId(tradeCard.getId());
        trade.getRecipient().getCard().setId(recipientCard.getId());

        return trade;
    }

    public List<Trade> findAllPending() {
        var entities = this.repository.findAll().stream()
                .filter(trade -> trade.getState().equals(TradeState.PENDING.toString()))
                .collect(Collectors.toList());

        var trades = new ArrayList<Trade>();
        for (var entity : entities) {
            trades.add(this.mapper.from(entity));
        }

        for (var trade : trades) {
            var traderCard = this.service.findOneByCardId(trade.getTrader().getCard().getCardId());
            var recipientCard = this.service.findOneByCardId(trade.getRecipient().getCard().getCardId());

            trade.getTrader().setCard(traderCard);
            trade.getRecipient().setCard(recipientCard);
        }

        return trades;
    }

    public List<Trade> findAllPendingOf(UUID user) {
        var pendingTrades = this.findAllPending();

        return pendingTrades.stream()
                .filter(trade -> trade.getRecipient().getId().equals(user))
                .collect(Collectors.toList());
    }

    public void send(Trader trader, Trader recipient) {
        var traderOwns = this.validator.ownsCard(trader);
        var recipientOwns = this.validator.ownsCard(recipient);

        if (!traderOwns || !recipientOwns) {
            throw new ImpossibleTradeException();
        }

        var entity = this.mapper.from(null, trader, recipient, TradeState.PENDING.toString());
        this.repository.save(entity);
    }

    public void accept(UUID tradeId, UUID approver) {
        var trade = this.findOneById(tradeId);

        if (this.isNotPending(trade) || !approver.equals(trade.getRecipient().getId())) {
            throw new ImpossibleTradeException();
        }

        var traderCard = this.service.findOneByCardId(trade.getTrader().getCard().getCardId());
        var approverCard = this.service.findOneByCardId(trade.getRecipient().getCard().getCardId());
        var trader = new Trader(trade.getTrader().getId(), traderCard);
        var recipient = new Trader(trade.getRecipient().getId(), approverCard);

        if (!this.validator.ownsCard(trader) || !this.validator.ownsCard(recipient)) {
            this.cancel(trade.getId(), approver);
            throw new PokeCardNotFoundException();
        }

        trade = this.exchangeService.exchange(trade);
        trade.setState(TradeState.ACCEPTED.toString());

        var entity = this.mapper.from(tradeId, trade.getTrader(), trade.getRecipient(), trade.getState());
        this.repository.save(entity);

    }

    public void cancel(UUID tradeId, UUID approver) {
        var trade = this.findOneById(tradeId);

        if (this.isNotPending(trade) || !approver.equals(trade.getRecipient().getId())) {
            throw new ImpossibleTradeException();
        }

        var entity = this.mapper.from(tradeId, trade.getTrader(),
                trade.getRecipient(), TradeState.CANCELLED.toString());

        this.repository.save(entity);
    }
}
