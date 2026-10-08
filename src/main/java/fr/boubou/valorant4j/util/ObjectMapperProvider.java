package fr.boubou.valorant4j.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides the ObjectMapper used to deserialize Henrik API responses.
 */
public final class ObjectMapperProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private ObjectMapperProvider() {
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
