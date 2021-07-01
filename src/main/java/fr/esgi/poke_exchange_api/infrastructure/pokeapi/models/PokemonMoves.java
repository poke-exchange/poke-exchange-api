package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonMoves {

    private NamedAPIResource move;

    @JsonProperty("version_group_details")
    private List<PokemonMoveVersion> versionGroupDetails;

}
