package fr.esgi.poke_exchange_api.domain.pokecards.mappers;

import fr.esgi.poke_exchange_api.domain.pokecards.models.PokemonCard;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.models.PokemonCardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PokemonCardMapper {

    public PokemonCardEntity fromCard(PokemonCard card) {
        var entity = new PokemonCardEntity();
        entity.setId(card.getId());
        entity.setName(card.getName());
        entity.setRarity(card.getRarity());
        entity.setColor(card.getColor());
        entity.setImage(card.getImage());
        entity.setHealth(card.getHealth());
        entity.setGeneration(card.getGeneration());
        entity.setHeight(card.getHeight());
        entity.setWeight(card.getWeight());
        entity.setTypes(this.concatTypes(card.getTypes()));

        return entity;
    }

    public PokemonCard fromEntity(PokemonCardEntity entity) {
        var card = new PokemonCard();
        card.setId(entity.getId());
        card.setName(entity.getName());
        card.setRarity(entity.getRarity());
        card.setColor(entity.getColor());
        card.setImage(entity.getImage());
        card.setHealth(entity.getHealth());
        card.setGeneration(entity.getGeneration());
        card.setHeight(entity.getHeight());
        card.setWeight(entity.getWeight());
        card.setTypes(this.splitTypes(entity.getTypes()));

        return card;
    }

    private List<String> splitTypes(String types) {
        return Arrays.asList(types.split(";"));
    }

    private String concatTypes(List<String> types) {
        var stringBuilder = new StringBuilder();

        for (var type : types) {
            stringBuilder.append(type).append(";");
        }

        return stringBuilder.toString();
    }
}
