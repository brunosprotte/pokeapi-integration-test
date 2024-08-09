package com.poke.api.clients.pokeapi.dtos.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poke.api.clients.pokeapi.dtos.type.damageRelation.Generation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameIndice {
    @JsonProperty("game_index")
    String gameIndex;

    @JsonProperty("generation")
    Generation generation;
}
