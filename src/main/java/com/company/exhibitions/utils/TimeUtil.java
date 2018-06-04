package com.company.exhibitions.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {

    public Time formatTime(String time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        long ms = 0;
        try {
            ms = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Time(ms);
    }

    public Time verifyTime(String time, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        long ms = format.parse(time).getTime();
        return new java.sql.Time(ms);
    }
}
