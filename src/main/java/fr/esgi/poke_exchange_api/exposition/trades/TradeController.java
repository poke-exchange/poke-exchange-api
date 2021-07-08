package fr.esgi.poke_exchange_api.exposition.trades;

import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.trades.ImpossibleTradeException;
import fr.esgi.poke_exchange_api.domain.trades.TradeNotFoundException;
import fr.esgi.poke_exchange_api.domain.trades.TradeService;
import fr.esgi.poke_exchange_api.domain.trades.models.Trade;
import fr.esgi.poke_exchange_api.domain.trades.validators.TradeValidator;
import fr.esgi.poke_exchange_api.exposition.trades.models.TradeValidation;
import fr.esgi.poke_exchange_api.exposition.trades.models.TradeProposal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;
    private final TradeValidator validator;

    @PostMapping("/send")
    public ResponseEntity<?> sendTrade(@RequestBody TradeProposal trade) {
        if (!this.validator.isValid(trade)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            this.tradeService.send(trade.getTrader(), trade.getRecipient());
            return ResponseEntity.ok().build();

        } catch (ImpossibleTradeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptTrade(@RequestBody TradeValidation validation) {
        try {
            this.tradeService.accept(validation.getTrade(), validation.getRecipient());
            return ResponseEntity.ok().build();

        } catch (TradeNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (ImpossibleTradeException exception) {
            return ResponseEntity.badRequest().build();
        } catch (PokeCardNotFoundException exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelTrade(@RequestBody TradeValidation validation) {
        try {
            this.tradeService.cancel(validation.getTrade(), validation.getRecipient());
            return ResponseEntity.ok().build();

        } catch (TradeNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (ImpossibleTradeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{id}/pending")
    public ResponseEntity<List<Trade>> findAllPendingOf(@PathVariable("id") UUID userId) {
        if (!this.validator.userExists(userId)) {
            return ResponseEntity.badRequest().build();
        }

        var response = this.tradeService.findAllPendingOf(userId);
        return ResponseEntity.ok(response);
    }

}
