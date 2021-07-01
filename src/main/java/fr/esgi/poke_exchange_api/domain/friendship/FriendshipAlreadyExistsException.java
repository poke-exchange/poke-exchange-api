package fr.esgi.poke_exchange_api.domain.friendship;

public class FriendshipAlreadyExistsException extends RuntimeException {

    public FriendshipAlreadyExistsException() {
        super("Friendship already exists.");
    }

}
