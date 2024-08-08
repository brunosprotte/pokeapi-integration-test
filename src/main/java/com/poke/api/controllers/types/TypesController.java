package com.poke.api.controllers.types;

import com.poke.api.clients.pokeapi.dtos.type.TypeDTO;
import com.poke.api.services.TypesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/types/")
@RequiredArgsConstructor
public class TypesController {

    private final TypesService typesService;

    @Operation(summary = "Pokemon types")
    @GetMapping("{typeId}")
    public TypeDTO types(@PathVariable final String typeId) {
        return typesService.getType(typeId);
    }
}
