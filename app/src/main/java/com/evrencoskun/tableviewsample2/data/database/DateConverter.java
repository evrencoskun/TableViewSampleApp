package com.evrencoskun.tableviewsample2.data.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    /**
     * For Date
     */

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
