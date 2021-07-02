package fr.esgi.poke_exchange_api.domain.pokecards.models;

import fr.esgi.poke_exchange_api.domain.pokecards.PokeCard;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class PokemonCard extends PokeCard {

    private Integer generation;
    private Integer health;
    private Integer height;
    private Integer weight;
    private List<String> types;

}


