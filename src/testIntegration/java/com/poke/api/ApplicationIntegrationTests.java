package com.poke.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poke.api.clients.pokeapi.dtos.type.TypeDTO;
import com.poke.api.lang.test.AbstractIT;
import com.poke.api.repository.TypeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationIntegrationTests extends AbstractIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    @DisplayName("TypesService")
    void get_types() throws Exception {


        final String response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(body)
//                .headers(getHeaders())
                .when()
                .post("/poke/types/5")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .asString();

        Map<String, String> mapResponse = new ObjectMapper().readValue(response, Map.class);
        String typeId = mapResponse.get("typeId");

        Optional<TypeDTO> typeOnDatabase = typeRepository.findById(5);

//        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);

        assertTrue(typeOnDatabase.isPresent(), "Certificado should be stored on database");

    }

}
