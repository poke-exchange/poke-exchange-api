package fr.esgi.poke_exchange_api.domain.user;

import fr.esgi.poke_exchange_api.domain.user.mappers.UserEntityMapper;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserMapper;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserPokemonEntityMapper;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserPokemonMapper;
import fr.esgi.poke_exchange_api.domain.user.models.PokemonRequestBody;
import fr.esgi.poke_exchange_api.domain.user.models.User;
import fr.esgi.poke_exchange_api.domain.authentication.RegisterRequestBody;
import fr.esgi.poke_exchange_api.domain.user.models.UserPokemon;
import fr.esgi.poke_exchange_api.infrastructure.user.UserPokemonEntity;
import fr.esgi.poke_exchange_api.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper toUser;
    private final UserEntityMapper toUserEntity;
    private final UserRepository repository;
    private final UserPokemonMapper toUserPokemon;
    private final UserPokemonEntityMapper toUserPokemonEntity;


    public List<User> findAll() {
        var users = new ArrayList<User>();
        var usersAsEntity = repository.findAll();

        usersAsEntity.forEach(entity -> users.add(toUser.from(entity)));

        return users;
    }

    public User findOneById(UUID id) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return toUser.from(optionalUser.get());
    }

    public List<UserPokemon> findUserPokemonsById(UUID id) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = toUser.from(optionalUser.get());

        var pokemonsList = user.getPokemons();

        if(pokemonsList.isEmpty()) {
            return new ArrayList<UserPokemon>();
        }

        return user.getPokemons();
    }

    public List<UserPokemon> addPokemonToUser(UUID id, PokemonRequestBody pokemon) {
        var optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        var user = toUser.from(optionalUser.get());

        var pokemonsList = user.getPokemons();

        var pokemons = optionalUser.get().getPokemons();

        pokemonsList.add(toUserPokemon.from(toUserPokemonEntity.from(pokemon)));
        user.setPokemons(pokemonsList);
        repository.save(optionalUser.get());
        return user.getPokemons();
    }

    public User findOneByUsername(String username) {
        var users = findAll().stream();
        var optionalUser = users.filter(user -> user.getUsername().equals(username)).findFirst();

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optionalUser.get();
    }

    public User save(RegisterRequestBody body) {
        var userEntity = toUserEntity.from(body);
        userEntity.setPassword(hashPassword(userEntity.getPassword()));
        var userEntityInserted =  repository.save(userEntity);
        return toUser.from(userEntityInserted);
    }

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
