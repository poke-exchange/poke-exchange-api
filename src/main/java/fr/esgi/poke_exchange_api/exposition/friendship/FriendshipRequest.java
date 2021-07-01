package fr.esgi.poke_exchange_api.exposition.friendship;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class FriendshipRequest {
    private UUID claimer;
    private UUID receiver;
}
