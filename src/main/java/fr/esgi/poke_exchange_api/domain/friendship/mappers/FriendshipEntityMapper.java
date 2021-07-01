package fr.esgi.poke_exchange_api.domain.friendship.mappers;

import fr.esgi.poke_exchange_api.exposition.friendship.FriendshipRequest;
import fr.esgi.poke_exchange_api.infrastructure.friendship.FriendshipEntity;
import org.springframework.stereotype.Component;

@Component
public class FriendshipEntityMapper {

    public FriendshipEntity from(FriendshipRequest request) {
        var entity = new FriendshipEntity();
        entity.setClaimer(request.getClaimer());
        entity.setReceiver(request.getReceiver());

        return entity;
    }

}
