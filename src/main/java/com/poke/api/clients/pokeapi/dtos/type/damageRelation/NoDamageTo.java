package com.poke.api.clients.pokeapi.dtos.type.damageRelation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoDamageTo {
    String name;
    String url;
}
