package com.canko.common;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nrq on 2017/7/16.
 */
public class CalendarUtil {

    private static final Logger logger = LoggerFactory.getLogger(CalendarUtil.class);

    private static String[] parsePatterns;
    static {
        parsePatterns = new String[] { "yyyy/MM/dd", "yyyy-MM-dd",
                "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm:ss.s", "yyyy-MM"};
    }

    public static Calendar parse(String string) {
        Calendar result = Calendar.getInstance();
        try {
            result.setTime(DateUtils.parseDate(string, parsePatterns));
            return result;
        } catch (Exception e) {
            return  null;
        }
    }

    public static Date parseDate(String string) {
        try {
            return parse(string).getTime();
        } catch (Exception e) {
            return  null;
        }
    }

}
