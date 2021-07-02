package fr.esgi.poke_exchange_api.domain.pokecards.exceptions;

public class PokeCardIdNotValidException extends RuntimeException {

    public PokeCardIdNotValidException() {
        super("No pokemon referenced with this id.");
    }

}
