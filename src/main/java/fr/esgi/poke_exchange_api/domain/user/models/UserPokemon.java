package fr.esgi.poke_exchange_api.domain.user.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserPokemon {
    private UUID id;
    private Integer pokemonId;
    private Boolean isActive;
}
