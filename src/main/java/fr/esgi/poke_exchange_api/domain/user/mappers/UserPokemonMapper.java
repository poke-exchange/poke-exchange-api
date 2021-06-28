package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.user.models.UserPokemon;
import fr.esgi.poke_exchange_api.infrastructure.user.UserPokemonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPokemonMapper {
    public UserPokemon from(UserPokemonEntity entity) {
        var userPokemon = new UserPokemon();
        userPokemon.setId(entity.getId());
        userPokemon.setPokemonId(entity.getPokemonId());
        userPokemon.setIsActive(entity.getIsActive());
        return userPokemon;
    }
}
