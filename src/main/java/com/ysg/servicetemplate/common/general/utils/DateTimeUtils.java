package com.ysg.servicetemplate.common.general.utils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DateTimeUtils {

    private static final int MAX_NANO_SECOND = 999999999;

    /**
     * 获取指定日期所在年的第一天
     */
    public static LocalDateTime getFirstDayOfYear(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0).with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取指定日期所在年的最后一天
     */
    public static LocalDateTime getLastDayOfYear(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 23, 59, 59, MAX_NANO_SECOND)
                .with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 获取指定日期所在月的第一天
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期所在月的最后一天
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 23, 59, 59, MAX_NANO_SECOND)
                .with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定日期所在季度的第一天
     */
    public static LocalDateTime getFirstDayOfQuarter(LocalDateTime dateTime) {
        Month firstMonthOfQuarter = dateTime.getMonth().firstMonthOfQuarter();
        return LocalDateTime.of(dateTime.getYear(), firstMonthOfQuarter.getValue(), 1, 0, 0, 0, 0);
    }

    /**
     * 获取指定日期所在季度的最后一天
     */
    public static LocalDateTime getLastDayOfQuarter(LocalDateTime dateTime) {
        Month firstMonthOfQuarter = dateTime.getMonth().firstMonthOfQuarter();
        return LocalDateTime.of(dateTime.getYear(), firstMonthOfQuarter.getValue() + 2, 1, 23, 59, 59, MAX_NANO_SECOND)
                .with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定天的最初一刻
     */
    public static LocalDateTime getFirstMomentOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * 获取指定天的最后一刻
     */
    public static LocalDateTime getLastMomentOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59, 59, MAX_NANO_SECOND);
    }

    /**
     * 获取最近的时间
     */
    public static LocalDateTime getLatestTime(LocalDateTime... elements) {
        Queue<LocalDateTime> timeQueue = new PriorityQueue<>(new CustomDateTimeComparator());
        Collections.addAll(timeQueue, elements);
        return timeQueue.peek();
    }

    static class CustomDateTimeComparator implements Comparator<LocalDateTime> {
        @Override
        public int compare(LocalDateTime t1, LocalDateTime t2) {
            return t1.isBefore(t2) ? 1 : -1;
        }
    }

    /**
     * 是否是某季度的最后一个月
     */
    public static boolean isQuarterLastMonth(LocalDateTime dateTime) {
        return dateTime.getMonth().getValue() % 3 == 0;
    }

    /**
     * 获取指定日期所在的季度
     */
    public static int getQuarter(LocalDateTime dateTime) {
        return dateTime.get(IsoFields.QUARTER_OF_YEAR);
    }
}
