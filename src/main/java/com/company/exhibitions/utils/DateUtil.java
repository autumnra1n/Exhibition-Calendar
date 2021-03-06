package com.company.exhibitions.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public Date formatDate(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Date(parsed.getTime());
    }

    public Date verifyDate(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    public Date getCurrentDate(){
        return new java.sql.Date(System.currentTimeMillis());
    }
}
