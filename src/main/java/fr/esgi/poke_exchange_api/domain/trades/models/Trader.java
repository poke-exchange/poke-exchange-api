package fr.esgi.poke_exchange_api.domain.trades.models;

import fr.esgi.poke_exchange_api.domain.pokecards.models.CollectedCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader {

    private UUID id;
    private CollectedCard card;

}
