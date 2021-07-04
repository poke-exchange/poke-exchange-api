package fr.esgi.poke_exchange_api.domain.pokecards.exceptions;

public class EmptyCollectionRequestException extends RuntimeException {

    public EmptyCollectionRequestException() {
        super("Collection is empty.");
    }

}
