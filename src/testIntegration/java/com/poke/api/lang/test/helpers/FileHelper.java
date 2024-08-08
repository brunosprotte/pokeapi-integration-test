package com.poke.api.lang.test.helpers;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;

public abstract class FileHelper {
    public static void validateResponseJson(
            final String responseActual, final String responseExpectedFolderPath) throws JSONException {
        JSONAssert.assertEquals(
                getJsonStringExpectedFromClasspath(responseExpectedFolderPath),
                responseActual,
                new CustomComparator(JSONCompareMode.STRICT));
    }

    public static String getJsonStringExpectedFromClasspath(String pathJsonExpected) {
        try {
            File file = ResourceUtils.getFile(format("classpath:%s", pathJsonExpected));
            InputStream is = new FileInputStream(file);
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(
                    format("Unable to parse file [%s] from classpath", pathJsonExpected), e);
        }
    }
}
