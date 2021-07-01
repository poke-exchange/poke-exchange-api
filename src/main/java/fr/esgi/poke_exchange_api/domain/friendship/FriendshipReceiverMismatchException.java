package fr.esgi.poke_exchange_api.domain.friendship;

public class FriendshipReceiverMismatchException extends RuntimeException {

    public FriendshipReceiverMismatchException() {
        super("User not concerned by friendship.");
    }

}
