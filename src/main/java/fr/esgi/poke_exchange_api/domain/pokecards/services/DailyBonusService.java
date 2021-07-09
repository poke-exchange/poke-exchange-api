package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.infrastructure.pokecards.DailyBonusRepository;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.DailyBonusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyBonusService {

    private final DailyBonusRepository repository;

    public boolean userCanReceiveDailyBonus(UUID user) {
        var optionalBonus = this.repository.findById(user);

        if (optionalBonus.isEmpty()) {
            return true;
        }

        var bonus = optionalBonus.get();

        return bonus.getLastTimeReceived()
                .plusDays(1)
                .isBefore(LocalDateTime.now());
    }

    public void saveBonus(UUID user) {
        var entityOptional = this.repository.findById(user);

        DailyBonusEntity entity;
        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
            entity.setLastTimeReceived(LocalDateTime.now());
        }
        else {
            entity = new DailyBonusEntity(user, LocalDateTime.now());
        }

        this.repository.save(entity);
    }

}
