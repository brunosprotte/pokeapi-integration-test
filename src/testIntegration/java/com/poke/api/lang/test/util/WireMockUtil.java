package com.poke.api.lang.test.util;

import org.testcontainers.containers.localstack.LocalStackContainer;

import java.util.HashMap;
import java.util.Map;

public class WireMockUtil {

    public static final Map<String, Map<String, String>> POKEAPI_CONFIGS = new HashMap<>() {
        {
            put("pokeapi", new HashMap<>() {
                {
                    put("TYPES_URI", "/type/");
                }
            });
        }
    };
    static String wiremockEndpoint = System.getProperty("app.wiremock.endpoint");
    LocalStackContainer localStackContainer;

    public WireMockUtil(LocalStackContainer localStackContainer) {
        this.localStackContainer = localStackContainer;
    }
}
