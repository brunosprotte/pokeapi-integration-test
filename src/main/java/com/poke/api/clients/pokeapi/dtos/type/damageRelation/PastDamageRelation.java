package com.poke.api.clients.pokeapi.dtos.type.damageRelation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PastDamageRelation {

    @JsonProperty("generation")
    List<Generation> generation;

    @JsonProperty("damage_relations")
    DamageRelation damageRelations;

}
