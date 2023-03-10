package com.ysg.servicetemplate.common.general.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String marshal(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static <T> T unmarshal(String jsonString, Class<T> type) throws IOException {
        return OBJECT_MAPPER.readValue(jsonString, type);
    }

    @SneakyThrows
    public static <T> T unmarshal(String value, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.readValue(value, typeReference);
    }

    @SneakyThrows
    public static <T> T silentUnmarshal(String jsonString, Class<T> type) {
        return OBJECT_MAPPER.readValue(jsonString, type);
    }

    public static String silentMarshal(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @SneakyThrows
    public static <T> T silentUnmarshal(String value, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.readValue(value, typeReference);
    }
}

