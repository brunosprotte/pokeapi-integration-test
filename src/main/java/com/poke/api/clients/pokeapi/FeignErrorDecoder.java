package com.poke.api.clients.pokeapi;

import com.poke.api.clients.pokeapi.exceptions.NotFoundException;
import com.poke.api.exceptions.ServerErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        String readableResponse = responseReader(response);

        return switch (response.status()) {
            case 404 -> new NotFoundException(readableResponse, response.status());
            case 500 -> new ServerErrorException(readableResponse);
            default -> new Exception("Server error");
        };
    }

    private String responseReader(Response response) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader;
        try {
            if (Objects.isNull(response.body())) {
                return "No error details.";
            }

            bufferedReader = new BufferedReader(new InputStreamReader(response.body().asInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new ServerErrorException(e.getMessage());
        }

        return stringBuilder.toString();
    }

}
