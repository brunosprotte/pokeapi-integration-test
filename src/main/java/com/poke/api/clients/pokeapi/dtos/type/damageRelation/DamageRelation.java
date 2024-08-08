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
public class DamageRelation {
    @JsonProperty("double_damage_from")
    List<DoubleDamageFrom> doubleDamageFrom;

    @JsonProperty("half_damage_from")
    List<HalfDamageFrom> halfDamageFrom;

    @JsonProperty("no_damage_from")
    List<NoDamageFrom> noDamageFrom;

    @JsonProperty("double_damage_to")
    List<DoubleDamageTo> doubleDamageTo;

    @JsonProperty("to_tamage_to")
    List<HalfDamageTo> halfDamageTo;

    @JsonProperty("no_damage_to")
    List<NoDamageTo> noDamageTo;
}
