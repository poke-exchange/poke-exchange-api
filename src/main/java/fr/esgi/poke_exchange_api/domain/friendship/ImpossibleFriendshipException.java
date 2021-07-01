package fr.esgi.poke_exchange_api.domain.friendship;

public class ImpossibleFriendshipException extends RuntimeException {

    public ImpossibleFriendshipException() {
        super("Claimer and receiver are identical.");
    }

}
