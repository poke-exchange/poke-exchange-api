package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PokemonMoveVersion {
    private NamedAPIResource move_learn_method;
    private NamedAPIResource version_groupe;
    private Integer level_learned_at;
}
