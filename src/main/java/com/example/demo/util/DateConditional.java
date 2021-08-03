package com.example.demo.util;

import java.util.Date;

public class DateConditional {
    public  static Date now = new Date();
    public static boolean endDateConditional(Date startedDate, Date endDate) {
        if (endDate.before(now) ||endDate.before(startedDate) ) {
            return false;
        }
        return true;
    }
}
