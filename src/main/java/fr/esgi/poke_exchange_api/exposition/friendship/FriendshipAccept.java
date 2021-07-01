package fr.esgi.poke_exchange_api.exposition.friendship;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class FriendshipAccept {

    private UUID receiver;

}
