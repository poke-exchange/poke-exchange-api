package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonHeldItem {
    private NamedAPIResource item;
    private List<PokemonHeldItemVersion> version_details;
}