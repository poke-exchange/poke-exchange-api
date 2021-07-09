package fr.esgi.poke_exchange_api.domain.pokecards.exceptions;

public class BonusAlreadyReceivedException extends RuntimeException {

    public BonusAlreadyReceivedException() {
        super("Daily bonus already received.");
    }

}
