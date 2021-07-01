package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.poke_exchange_api.domain.pokemon.models.NamedAPIResource;
import fr.esgi.poke_exchange_api.domain.pokemon.models.PokemonHeldItemVersion;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonHeldItem {

    private NamedAPIResource item;

    @JsonProperty("version_details")
    private List<PokemonHeldItemVersion> versionDetails;

}
