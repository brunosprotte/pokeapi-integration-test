package com.poke.api.repository;

import com.poke.api.clients.pokeapi.dtos.type.TypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TypeRepository extends JpaRepository<TypeDTO, Serializable> {
}
