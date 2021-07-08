package fr.esgi.poke_exchange_api.infrastructure.trades;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeRepository extends JpaRepository<TradeEntity, UUID> { }
