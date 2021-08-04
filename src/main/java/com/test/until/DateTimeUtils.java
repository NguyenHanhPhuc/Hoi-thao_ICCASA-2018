package com.test.until;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
    public static String DATE_TIME_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_TIME_FORMAT_YYYY_MM_DD_HHMMSSS = "yyyy-MM-dd HH:mm:ss.S";

    public static String getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return now.truncatedTo(ChronoUnit.SECONDS).format(dtf);
    }

    public static String getCurrentDateTime(String pattern){
        LocalDateTime now = LocalDateTime.now();
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return now.truncatedTo(ChronoUnit.SECONDS).format(dtf);
    }
}

