package fr.esgi.poke_exchange_api.domain.user.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Integer> pokemons;
    private LocalDateTime lastClaimDate;
    private Integer eloPoints;
}
