package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.domain.pokecards.PokeCardService;
import fr.esgi.poke_exchange_api.domain.pokecards.models.PokemonCard;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.PokeApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonCardService implements PokeCardService {

    private final PokemonCardMakerService cardMakerService;
    private final PokeApiRepository repository;

    @Override
    public PokemonCard findOneById(Integer id) {
        return this.cardMakerService.make(id);
    }

    @Override
    public List<PokemonCard> findMany(Integer limit, Integer fromId) {
        return this.cardMakerService.makeMany(limit, fromId);
    }

    @Override
    public List<PokemonCard> findAll() {
        var first = this.repository.firstPokemonId();
        final var last = this.repository.lastPokemonId();
        var cards = new ArrayList<PokemonCard>();

        for (var i = first; i <= last; i++, first++) {
            cards.add(this.cardMakerService.make(i));
        }

        return cards;
    }
}
