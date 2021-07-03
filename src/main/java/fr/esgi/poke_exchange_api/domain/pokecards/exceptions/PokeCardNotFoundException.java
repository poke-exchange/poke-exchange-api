package fr.esgi.poke_exchange_api.domain.pokecards.exceptions;

public class PokeCardNotFoundException extends RuntimeException {

    public PokeCardNotFoundException() {
        super("PokeCard not found.");
    }

}
