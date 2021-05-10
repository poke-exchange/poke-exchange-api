package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.user.models.User;
import fr.esgi.poke_exchange_api.infrastructure.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User from(UserEntity entity) {
        var user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setEloPoints(entity.getEloPoints());

        return user;
    }
}
