package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.user.models.UserPokemon;
import fr.esgi.poke_exchange_api.domain.user.models.UserPokemonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPokemonResponseMapper {
    public UserPokemonResponse from(UserPokemon userPokemon) {
        var userPokemonResponse = new UserPokemonResponse();
        userPokemonResponse.setId(userPokemon.getId());
        userPokemonResponse.setPokemonId(userPokemon.getPokemonId());

        return userPokemonResponse;
    }
}
