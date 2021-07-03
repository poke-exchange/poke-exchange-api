package fr.esgi.poke_exchange_api.domain.pokecards;

import org.springframework.stereotype.Component;

import java.util.List;

public interface PokeCardMaker {
    PokeCard make(Integer pokemonId);
    List<?> makeMany(Integer limit, Integer start);
}
