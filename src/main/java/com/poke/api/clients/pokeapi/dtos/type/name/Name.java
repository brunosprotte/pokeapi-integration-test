package com.poke.api.clients.pokeapi.dtos.type.name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    String name;
    Language language;
}
