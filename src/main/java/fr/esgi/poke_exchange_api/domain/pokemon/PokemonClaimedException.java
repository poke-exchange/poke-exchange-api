package fr.esgi.poke_exchange_api.domain.pokemon;

public class PokemonClaimedException extends RuntimeException {
    public PokemonClaimedException() { super("you have already claimed your weekly pokemon"); }
}
