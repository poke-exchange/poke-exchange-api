package fr.esgi.poke_exchange_api.domain.trades.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    private UUID id;
    private Trader trader;
    private Trader recipient;
    private String state;

}
