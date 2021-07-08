package fr.esgi.poke_exchange_api.exposition.trades.models;

import fr.esgi.poke_exchange_api.domain.trades.models.Trader;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TradeProposal {

    private Trader trader;
    private Trader recipient;

}
