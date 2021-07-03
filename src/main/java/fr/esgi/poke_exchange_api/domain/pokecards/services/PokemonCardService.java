package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.domain.pokecards.PokeCardService;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.domain.pokecards.mappers.PokemonCardMapper;
import fr.esgi.poke_exchange_api.domain.pokecards.models.PokemonCard;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.PokemonRangeIds;
import fr.esgi.poke_exchange_api.infrastructure.pokecards.PokemonCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonCardService implements PokeCardService {

    private final PokemonCardMakerService cardMakerService;
    private final PokemonCardRepository repository;
    private final PokemonCardMapper mapper;

    @Override
    public PokemonCard findOneById(Integer id) {
        var entity = this.repository.findById(id);

        if(entity.isPresent()) {
            return this.mapper.fromEntity(entity.get());
        }

        var card = this.cardMakerService.make(id);
        var cardEntity = this.mapper.fromCard(card);
        this.repository.save(cardEntity);

        return card;
    }

    @Override
    public List<PokemonCard> findMany(Integer limit, Integer fromId) {
        var pokeCards = new ArrayList<PokemonCard>();

        for (int i = fromId; i <= limit; i++, fromId++) {
            try {
                pokeCards.add(this.findOneById(fromId));

            } catch (PokeCardNotFoundException exception) {
                break;
            }
        }

        if (pokeCards.isEmpty()) {
            throw new PokeCardNotFoundException();
        }

        return pokeCards;
    }

    @Override
    public List<PokemonCard> findAll() {
        var cards = new ArrayList<PokemonCard>();

        if (this.repository.count() == PokemonRangeIds.lastId) {
            var entities = this.repository.findAll();
            entities.forEach(entity -> cards.add(this.mapper.fromEntity(entity)));

            return cards;
        }

        var first = PokemonRangeIds.firstId;
        final var last = PokemonRangeIds.lastId;

        for (var i = first; i <= last; i++, first++) {
            cards.add(this.findOneById(first));
        }

        return cards;
    }
}
