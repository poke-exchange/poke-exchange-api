package fr.esgi.poke_exchange_api.domain.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
