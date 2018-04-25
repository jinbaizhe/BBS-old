package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
    public static String getCurrentDateTime()
    {
        SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        return f.format(calendar.getTime());
    }

}
