package fr.esgi.poke_exchange_api.domain.pokemon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PokemonsResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<Integer> results;
}
