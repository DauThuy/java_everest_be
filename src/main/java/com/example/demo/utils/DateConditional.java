package com.example.demo.utils;

import java.util.Date;

public class DateConditional {
    public static boolean endDateConditional(Date startedDate, Date endDate) {
        if (endDate.before(startedDate)) {
            return false;
        }
        return true;
    }
}
