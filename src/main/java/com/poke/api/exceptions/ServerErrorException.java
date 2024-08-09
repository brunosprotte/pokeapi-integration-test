package com.poke.api.exceptions;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ServerErrorException: " + getMessage();
    }

}
