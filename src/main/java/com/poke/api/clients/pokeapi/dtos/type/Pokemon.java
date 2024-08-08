package com.poke.api.clients.pokeapi.dtos.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    Integer slot;
    Pokemon pokemon;
}
