package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.user.models.PokemonRequestBody;
import fr.esgi.poke_exchange_api.infrastructure.user.UserPokemonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPokemonEntityMapper {
    public UserPokemonEntity from(PokemonRequestBody body) {
        var entity = new UserPokemonEntity();
        entity.setPokemonId(body.getPokemonId());
        entity.setIsActive(false);
        return entity;
    }
}
