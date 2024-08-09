package com.poke.api.clients.pokeapi.dtos.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poke.api.clients.pokeapi.dtos.type.damageRelation.DamageRelation;
import com.poke.api.clients.pokeapi.dtos.type.damageRelation.Generation;
import com.poke.api.clients.pokeapi.dtos.type.name.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO implements Serializable {
    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("damage_relations")
    DamageRelation damageRelations;

    @JsonProperty("past_damage_relations")
    List<DamageRelation> pastDamageRelations;

    @JsonProperty("generation")
    Generation generation;

    @JsonProperty("move_damage_class")
    String moveDamageClass;

    @JsonProperty("names")
    List<Name> names;

    @JsonProperty("pokemon")
    List<Pokemon> pokemon;

    @JsonProperty("moves")
    List<Move> moves;
}
