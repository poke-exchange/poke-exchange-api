package fr.esgi.poke_exchange_api.domain.friendship;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Friendship {
    private Long id;
    private UUID claimer;
    private UUID receiver;
    private boolean accepted;
}
