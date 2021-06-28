package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.authentication.RegisterRequestBody;
import fr.esgi.poke_exchange_api.domain.user.models.UserPokemon;
import fr.esgi.poke_exchange_api.infrastructure.user.UserEntity;
import fr.esgi.poke_exchange_api.infrastructure.user.UserPokemonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {
    public UserEntity from(RegisterRequestBody body) {
        var entity = new UserEntity();
        entity.setUsername(body.getUsername());
        entity.setFirstName(body.getFirstName());
        entity.setLastName(body.getLastName());
        entity.setEmail(body.getEmail());
        entity.setPassword(body.getPassword());
        entity.setEloPoints(0);
        entity.setRegistered(LocalDateTime.now());
        entity.setPokemons(new ArrayList<UserPokemonEntity>());
        return entity;
    }
}
