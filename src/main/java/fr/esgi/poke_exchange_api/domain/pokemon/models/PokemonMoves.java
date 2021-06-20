package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonMoves {
    private NamedAPIResource move;
    private List<PokemonMoveVersion> version_group_details;
}
