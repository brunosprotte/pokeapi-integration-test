package com.poke.api.services;

import com.poke.api.clients.pokeapi.PokeApiClient;
import com.poke.api.clients.pokeapi.dtos.type.TypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypesService {

    private final PokeApiClient pokeApiClient;

    public TypeDTO getType(String typeId) {
        return pokeApiClient.types(typeId);
    }
}
