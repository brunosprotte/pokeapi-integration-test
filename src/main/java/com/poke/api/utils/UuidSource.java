package com.poke.api.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidSource {

    public UUID getUUID() {
        return UUID.randomUUID();
    }

}
