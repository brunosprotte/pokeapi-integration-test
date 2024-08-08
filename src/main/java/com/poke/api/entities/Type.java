package com.poke.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Type implements Serializable {

    @Id
    String id;

    String name;
}
