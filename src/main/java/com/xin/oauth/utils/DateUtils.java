package com.xin.oauth.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xinyu.huang02
 * @date 2020-02-27 22:03
 * @class Date相关操作工具类
 */
public class DateUtils {

    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前格式化时间字符串
     *
     * @return
     */
    public static String currentTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        return sdf.format(new Date());
    }


    /**
     * 获取当前时间一天后的时间戳
     *
     * @return
     */
    public static Long currentTimeAfterDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }


    /**
     * 返回当前精确到毫秒的时间戳
     *
     * @return
     */
    public static Long currentTimeInMillis() {
        return System.currentTimeMillis();
    }

}
