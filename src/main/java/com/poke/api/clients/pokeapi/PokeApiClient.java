package com.poke.api.clients.pokeapi;

import com.poke.api.clients.pokeapi.dtos.type.TypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pokeapi", url = "${pokeapi.base-url}")
public interface PokeApiClient {

    @GetMapping(value = "/type/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TypeDTO types(@PathVariable("id") String id);
}
