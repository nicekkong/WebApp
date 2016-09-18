package com.je.webapp.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getMonthFirstDay(String div) {
        LocalDate localDate = LocalDate.now();
        return localDate.minusDays(localDate.getDayOfMonth()-1).format(DateTimeFormatter.ofPattern("yyyy"+div+"MM"+div+"dd"));
    }

    public static String getNowDay(String div) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"+div+"MM"+div+"dd"));
    }

    public static String getNowDayHms(String format ) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String getFormatDay(Timestamp timestamp, String div) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy"+div+"MM"+div+"dd"));
    }

    public static String getFormatDay(Date sqlDate, String div) {
        return sqlDate.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy"+div+"MM"+div+"dd"));
    }

    public static String getFormatDay(java.util.Date utilDate, String div) {
        return LocalDateTime.ofInstant(utilDate.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy" + div + "MM" + div + "dd"));
    }

    public static String getFormatTime(Timestamp timestamp, String format) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(format));
    }

    public static Timestamp getString2Timestamp(String startDay, String time) {
        return Timestamp.valueOf(LocalDateTime.parse(startDay+time, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    public static Timestamp getNowTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static String getStartDayHms(String startDay) {
        return startDay + "000000";
    }
    public static String getEndDayHms(String startDay) {
        return startDay + "235959";
    }

}
