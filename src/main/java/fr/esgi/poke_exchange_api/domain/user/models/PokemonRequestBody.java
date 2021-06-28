package fr.esgi.poke_exchange_api.domain.user.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class PokemonRequestBody {

    @NotNull
    @NotEmpty
    @NotBlank
    private final Integer pokemonId;
}
