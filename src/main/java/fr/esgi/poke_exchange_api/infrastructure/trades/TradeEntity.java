package fr.esgi.poke_exchange_api.infrastructure.trades;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "trade")
@RequiredArgsConstructor
public class TradeEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID trader;

    @Column(name = "trader_card")
    private Integer traderCard;

    @Column(name = "trader_card_quantity")
    private Integer traderCardQuantity;

    private UUID recipient;

    @Column(name = "recipient_card")
    private Integer recipientCard;

    @Column(name = "recipient_card_quantity")
    private Integer recipientCardQuantity;

    private String state;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
