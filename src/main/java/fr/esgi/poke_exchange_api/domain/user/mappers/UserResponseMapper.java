package fr.esgi.poke_exchange_api.domain.user.mappers;

import fr.esgi.poke_exchange_api.domain.user.models.User;
import fr.esgi.poke_exchange_api.domain.user.models.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {
    public UserResponse from(User user) {
        var userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEloPoints(user.getEloPoints());

        return userResponse;
    }
}
