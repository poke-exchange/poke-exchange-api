package fr.esgi.poke_exchange_api.infrastructure.pokecards;

import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.DailyBonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DailyBonusRepository extends JpaRepository<DailyBonusEntity, UUID> { }
