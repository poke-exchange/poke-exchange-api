package fr.esgi.poke_exchange_api.domain.pokecards;

import fr.esgi.poke_exchange_api.domain.pokecards.constants.PokeCardRarity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class PokeCard {
    protected String name;
    protected PokeCardRarity rarity;
    protected String color;
    protected String image;
}
