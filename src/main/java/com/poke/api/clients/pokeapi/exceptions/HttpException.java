package com.poke.api.clients.pokeapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpException extends RuntimeException {

    private int statusHttp;

    public HttpException(String message, int statusHttp) {
        super(message);
        this.statusHttp = statusHttp;
    }
}
