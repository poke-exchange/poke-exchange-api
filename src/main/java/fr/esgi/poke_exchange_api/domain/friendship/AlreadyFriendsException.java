package fr.esgi.poke_exchange_api.domain.friendship;

public class AlreadyFriendsException extends RuntimeException {

    public AlreadyFriendsException() {
        super("Users are already friends.");
    }

}
