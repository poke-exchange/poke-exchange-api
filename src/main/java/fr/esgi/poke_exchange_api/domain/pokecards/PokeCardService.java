package fr.esgi.poke_exchange_api.domain.pokecards;

import java.util.List;

public interface PokeCardService {

    PokeCard findOneById(Integer id);
    List<?> findMany(Integer limit, Integer start);
    List<?> findAll();

}
