package fr.esgi.poke_exchange_api.infrastructure.pokecards;

import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.PokemonCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardRepository extends JpaRepository<PokemonCardEntity, Integer> { }

