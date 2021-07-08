package fr.esgi.poke_exchange_api.domain.trades;

public class ImpossibleTradeException extends RuntimeException {

    public ImpossibleTradeException() {
        super("Trade impossible.");
    }

}
