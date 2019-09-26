package com.bigkoo.pickerview.utils;

/**
 * 日期时间类型
 */
public enum DateTimeFormat {
    FULL("yyyy-MM-dd HH:mm:ss"),
    YMDHM("yyyy-MM-dd HH:mm"),
    YMD("yyyy-MM-dd"),
    HMS("HH:mm:ss"),
    HM("HH:mm");

    private String format;

    DateTimeFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}