package fr.esgi.poke_exchange_api.domain.user;

import fr.esgi.poke_exchange_api.domain.user.mappers.UserEntityMapper;
import fr.esgi.poke_exchange_api.domain.user.mappers.UserMapper;
import fr.esgi.poke_exchange_api.domain.user.models.User;
import fr.esgi.poke_exchange_api.domain.authentication.RegisterRequestBody;
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

    public List<Integer> findUserPokemonsById(UUID id) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var pokemonsList = optionalUser.get().getPokemons();
        System.out.println(pokemonsList);
        if(pokemonsList.isEmpty()) {
            return new ArrayList<>();
        }

        return optionalUser.get().getPokemons();
    }

    public List<Integer> addPokemonToUser(UUID id, int pokemonId) {
        var optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var pokemons = optionalUser.get().getPokemons();

        pokemons.add(pokemonId);
        optionalUser.get().setPokemons(pokemons);
        repository.save(optionalUser.get());
        return optionalUser.get().getPokemons();
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
