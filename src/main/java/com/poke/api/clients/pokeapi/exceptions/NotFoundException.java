package com.poke.api.clients.pokeapi.exceptions;

public class NotFoundException extends HttpException {

    public NotFoundException(String message, int httpStatus) {
        super(message, httpStatus);
    }

    @Override
    public String toString() {
        return "NotFoundException: " + getMessage();
    }

}
