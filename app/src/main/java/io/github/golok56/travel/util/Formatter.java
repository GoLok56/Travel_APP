package io.github.golok56.travel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Formatter {

    private static final Locale LOCALE = new Locale("in", "ID");

    private static final String DATE_FORMAT_STRING = "dd MMMM yyyy";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING, LOCALE);

    public static String getString(Calendar myCalendar){

        DateFormat dfFrom = new SimpleDateFormat("d M yyyy", LOCALE);

        int month = myCalendar.get(Calendar.MONTH) + 1;

        String dateFrom = myCalendar.get(Calendar.DAY_OF_MONTH) + " " +
                 String.valueOf(month) + " " +
                myCalendar.get(Calendar.YEAR);

        String dateTo = "";
        try {
            dateTo = DATE_FORMAT.format(dfFrom.parse(dateFrom));
        } catch (ParseException ex){
            ex.printStackTrace();
        }

        return dateTo;
    }

    public static String getString(Date date){
        return DATE_FORMAT.format(date);
    }

}
