package fr.esgi.poke_exchange_api.infrastructure.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "pokemons")
@RequiredArgsConstructor
public class UserPokemonEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Integer pokemonId;

    @ManyToOne
    @JoinColumn(name="user_entity_id")
    private UserEntity user;

    @Column(nullable = false)
    private Boolean isActive;
}
