package com.example.qlbhcdio.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormatUtils {

    public static String DateTimeCurrent() {
        Date c = Calendar.getInstance().getTime();
        return new SimpleDateFormat("dd/MM/YYYY").format(c);
    }
    public static  Date FormatDateTime (String date){
        try {
            return new SimpleDateFormat("dd/MM/YYYY").parse(date);
        } catch (ParseException e) {
            Log.e("Error TimeUtils",e.getLocalizedMessage());
        }
        return null;
    }

}
