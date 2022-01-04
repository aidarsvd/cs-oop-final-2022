package pro.aidar.library.utils;


import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

}
