package com.ysg.servicetemplate.common.general.utils;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
public final class BigDecimalUtil {

    // a >= b
    public static boolean ge(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0;
    }

    // a < b
    public static boolean lt(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0;
    }

    public static BigDecimal addAll(BigDecimal... bigDecimals) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal bigDecimal: bigDecimals) {
            if (Objects.nonNull(bigDecimal)) {
                result = result.add(bigDecimal);
            }
        }
        return result;
    }
}
