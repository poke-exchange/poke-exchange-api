package fr.esgi.poke_exchange_api.domain.friendship.mappers;

import fr.esgi.poke_exchange_api.domain.friendship.Friendship;
import fr.esgi.poke_exchange_api.infrastructure.friendship.FriendshipEntity;
import org.springframework.stereotype.Component;

@Component
public class FriendshipMapper {

    public Friendship from(FriendshipEntity entity) {
        var friendship = new Friendship();
        friendship.setId(entity.getId());
        friendship.setClaimer(entity.getClaimer());
        friendship.setReceiver(entity.getReceiver());
        friendship.setAccepted(entity.isAccepted());

        return friendship;
    }
}
