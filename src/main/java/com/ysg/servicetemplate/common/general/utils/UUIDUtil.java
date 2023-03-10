package com.ysg.servicetemplate.common.general.utils;

import java.util.UUID;
import java.util.function.Supplier;

public class UUIDUtil {

    private static final Supplier<String> DEFAULT_GENERATOR = () -> UUID.randomUUID().toString().replace("-", "");

    private static Supplier<String> GENERATOR = DEFAULT_GENERATOR;

    public static String generateUUID() {
        return GENERATOR.get();
    }

    public static void mock(String id) {
        GENERATOR = () -> id;
    }

    public static void reset() {
        GENERATOR = DEFAULT_GENERATOR;
    }
}
