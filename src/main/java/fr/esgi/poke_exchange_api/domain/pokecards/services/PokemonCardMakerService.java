package fr.esgi.poke_exchange_api.domain.pokecards.services;

import fr.esgi.poke_exchange_api.domain.pokecards.PokeCardMaker;
import fr.esgi.poke_exchange_api.domain.pokecards.models.PokemonCard;
import fr.esgi.poke_exchange_api.domain.pokecards.constants.PokeCardColor;
import fr.esgi.poke_exchange_api.domain.pokecards.constants.PokeCardRarity;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardIdNotValidException;
import fr.esgi.poke_exchange_api.domain.pokecards.exceptions.PokeCardNotFoundException;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.PokeApiRepository;
import fr.esgi.poke_exchange_api.infrastructure.pokeapi.models.pokemon.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonCardMakerService implements PokeCardMaker {

    private final PokeApiRepository repository;

    @Override
    public PokemonCard make(Integer pokemonId) {
        var pokemon = this.repository.findOneById(pokemonId);

        if (pokemon.isEmpty()) {
            throw new PokeCardNotFoundException();
        }

        return this.setPokeCard(pokemon.get());
    }

    @Override
    public List<PokemonCard> makeMany(Integer limit, Integer start) {
        if (this.repository.notExists(start)) {
            throw new PokeCardIdNotValidException();
        }

        var pokeCards = new ArrayList<PokemonCard>();

        for (int i = start; i <= limit; i++, start++) {
            if (this.repository.notExists(start)) {
                break;
            }

            pokeCards.add(this.make(start));
        }

        return pokeCards;
    }

    private PokemonCard setPokeCard(Pokemon pokemon) {
        var pokeCard = new PokemonCard();

        pokeCard.setId(pokemon.getId());
        pokeCard.setName(pokemon.getName());
        pokeCard.setRarity(this.getPokemonRarity(pokemon).toString().toLowerCase());
        pokeCard.setHealth(this.getPokemonHealth(pokemon));
        pokeCard.setHeight(pokemon.getHeight());
        pokeCard.setWeight(pokemon.getWeight());
        pokeCard.setTypes(this.getPokemonTypes(pokemon));
        pokeCard.setColor(this.getCardColor(pokeCard.getTypes().get(0)).toString());
        pokeCard.setGeneration(this.getPokemonGeneration(pokemon));
        pokeCard.setImage(pokemon.getSprites().getFrontDefault());

        return pokeCard;
    }

    private Integer getPokemonHealth(Pokemon pokemon) {
        var stats = pokemon.getStats();

        var health = -1;
        for (var pokemonStat : stats) {
            if (pokemonStat.getStat().getName().equals("hp")) {
                health = pokemonStat.getBaseStat();
            }
        }

        return health;
    }

    private Integer getPokemonGeneration(Pokemon pokemon) {
        var optionalGeneration = this.repository.findPokemonGeneration(pokemon);

        if (optionalGeneration.isEmpty()) {
            throw new PokeCardNotFoundException();
        }

        return optionalGeneration.get();
    }

    private PokeCardRarity getPokemonRarity(Pokemon pokemon) {
        var stats = pokemon.getStats();

        var statsCombo = 0;
        for (var pokemonStat : stats) {
            statsCombo += pokemonStat.getBaseStat();
        }

        statsCombo = statsCombo / stats.size() * pokemon.getBaseExperience();
        if (statsCombo > 25000) {
            return PokeCardRarity.LEGENDARY;
        }
        else if (statsCombo > 15000) {
            return PokeCardRarity.RARE;
        }
        else if (statsCombo > 7500) {
            return PokeCardRarity.UNCOMMON;
        }
        else {
            return PokeCardRarity.COMMON;
        }
    }

    private List<String> getPokemonTypes(Pokemon pokemon) {
        var pokemonTypes = pokemon.getTypes();
        var types = new ArrayList<String>();

        for (var type : pokemonTypes) {
            types.add(type.getType().getName());
        }

        return types;
    }

    private PokeCardColor getCardColor(String type) {
        switch (type) {
            case "grass":
            case "bug":
                return PokeCardColor.GREEN;
            case "fire":
                return PokeCardColor.RED;
            case "water":
            case "ice":
                return PokeCardColor.BLUE;
            case "electric":
                return PokeCardColor.YELLOW;
            case "psychic":
            case "poison":
            case "ghost":
                return PokeCardColor.PURPLE;
            case "fighting":
            case "rock":
            case "ground":
                return PokeCardColor.BROWN;
            case "dark":
                return PokeCardColor.DARK;
            case "steel":
                return PokeCardColor.SILVER;
            case "dragon":
                return PokeCardColor.GOLD;
            case "fairy":
                return PokeCardColor.PINK;
            default:
                return PokeCardColor.WHITE;
        }
    }
}
