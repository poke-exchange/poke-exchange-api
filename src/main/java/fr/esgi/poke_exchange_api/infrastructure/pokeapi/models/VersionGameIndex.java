package fr.esgi.poke_exchange_api.infrastructure.pokeapi.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VersionGameIndex {

    private String name;

    private NamedAPIResource version;

}