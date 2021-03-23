package com.example.lifedemo01.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: RenHe
 * @Date: 2021/2/25 15:18
 */
public class DateTool {
    /**
     * 获取当前系统时间 返回 12:12:12
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * @param ctm
     *            long 系统时间
     * @param format
     * @return
     */
    public static String getCurrentTimeMillisAsString(long ctm, String format) {
        Date date = new Date(ctm);
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 获取当前系统时间 返回 yyyyMMddHHmmssS
     *
     * @return
     */
    public static String getCurrentTimeMillisAsString() {
        long currTimeM = getCurrentTimeMillis();
        return getCurrentTimeMillisAsString(currTimeM, "yyyyMMddHHmmss");
    }
}
