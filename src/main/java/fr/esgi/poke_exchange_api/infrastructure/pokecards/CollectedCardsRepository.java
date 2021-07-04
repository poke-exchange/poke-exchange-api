package fr.esgi.poke_exchange_api.infrastructure.pokecards;

import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.CollectedCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollectedCardsRepository extends JpaRepository<CollectedCardEntity, UUID> { }
