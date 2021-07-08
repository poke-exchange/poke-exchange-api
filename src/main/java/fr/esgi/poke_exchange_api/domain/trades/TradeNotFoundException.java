package fr.esgi.poke_exchange_api.domain.trades;

public class TradeNotFoundException extends RuntimeException {

    public TradeNotFoundException() {
        super("Trade not found.");
    }

}
