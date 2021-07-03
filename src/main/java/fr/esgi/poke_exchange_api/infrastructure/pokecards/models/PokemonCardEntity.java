package fr.esgi.poke_exchange_api.infrastructure.pokecards.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pokemon_card")
@RequiredArgsConstructor
public class PokemonCardEntity {

    @Id
    private Integer id;

    private String name;

    private String rarity;

    private String color;

    private String image;

    private Integer generation;

    private Integer health;

    private Integer height;

    private Integer weight;

    private String types;
}
