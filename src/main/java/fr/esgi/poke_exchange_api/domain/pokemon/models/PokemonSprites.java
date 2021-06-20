package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonSprites {
    private String front_default;
    private String front_shiny;
    private String front_female;
    private String front_shiny_female;
    private String back_default;
    private String back_shiny;
    private String back_female;
    private String back_shiny_female;
}
