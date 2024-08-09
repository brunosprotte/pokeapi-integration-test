package com.poke.api.lang.test.wiremock;

import com.poke.api.lang.test.helpers.FileHelper;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.poke.api.lang.test.util.WireMockUtil.POKEAPI_CONFIGS;

public class ApiWireMock {

    public static void stubPokeApiTypes(Integer httpStatus, String folderPath) {
        stubFor(get(
                        urlPathMatching(POKEAPI_CONFIGS.get("pokeapi").get("TYPES_URI") + ".*")
                )
                        .willReturn(
                                aResponse()
                                        .withStatus(httpStatus)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(FileHelper.getJsonStringExpectedFromClasspath(folderPath))
                        )
        );
    }
}
