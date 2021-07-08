package fr.esgi.poke_exchange_api.exposition.trades.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class TradeValidation {

    private UUID trade;
    private UUID recipient;

}
