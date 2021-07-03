package fr.esgi.poke_exchange_api.domain.pokecards;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class PokeCard {
    protected Integer id;
    protected String name;
    protected String rarity;
    protected String color;
    protected String image;
}
