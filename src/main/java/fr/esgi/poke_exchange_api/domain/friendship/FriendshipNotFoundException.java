package fr.esgi.poke_exchange_api.domain.friendship;

public class FriendshipNotFoundException extends RuntimeException {

    public FriendshipNotFoundException() {
        super("Friendship not found.");
    }
}
