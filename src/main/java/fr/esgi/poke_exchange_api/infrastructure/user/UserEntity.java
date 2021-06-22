package fr.esgi.poke_exchange_api.infrastructure.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "elo_points", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer eloPoints;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "pokemons",
            joinColumns = @JoinColumn(name = "user_entity_id")
    )
    @Column(name = "pokemons")
    private List<Integer> pokemons = new ArrayList<Integer>();

    @Column(name = "last_claim_date", nullable = true)
    private LocalDateTime lastClaimDate;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered;
}
