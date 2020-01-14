package com.bigkoo.pickerview.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期时间类型
 */
public enum DateTimeFormat {
    FULL("yyyy-MM-dd HH:mm:ss"),
    YMDHM("yyyy-MM-dd HH:mm"),
    YMD("yyyy-MM-dd"),
    YM("yyyy-MM"),
    HMS("HH:mm:ss"),
    HM("HH:mm");

    private String format;

    DateTimeFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date calendarToData(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);//设置月份，因为月份从0开始，所以用month - 1
        Date date = calendar.getTime();
        return date;
    }

    public static Date parseDateTime(String dateTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            Log.e("DateTimeFormat", "");
        }
        return date;
    }

    public static String parseDateTime(Date date, String format) {
        if (date == null) {
            return null;
        }

        if (TextUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static int daysOfMonth(Date date) {
        return dataToCalendar(date).getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}