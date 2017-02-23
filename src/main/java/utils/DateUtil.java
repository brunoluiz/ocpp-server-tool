package utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bds on 10/11/2016.
 */
public class DateUtil {
    public static long hours2millis(double hours) {
        return (long) (3600000 * hours);
    }

    public static long mins2millis(double minutes) {
        return (long) (60000 * minutes);
    }

    public static long seconds2millis(double seconds) {
        return (long) (1000 * seconds);
    }

    public static int millis2hours(long millisecondsElapsed) {
        return (int) Math.floor(millisecondsElapsed / (60.0 * 60.0 * 1000.0));
    }

    public static int millis2mins(long millisecondsElapsed) {
        return (int) Math.floor(millisecondsElapsed / (60.0 * 1000.0));
    }

    public static int millis2seconds(long millisecondsElapsed) {
        return (int) Math.floor(millisecondsElapsed / 1000.0);
    }

    public static boolean hasExpired(XMLGregorianCalendar expiryDate) {
        if (expiryDate == null) return false;

        Date now = new Date();
        Date expiry = expiryDate.toGregorianCalendar().getTime();

        if (now.before(expiry))
            return false;

        return true;
    }

    public static XMLGregorianCalendar parseDate(String date) {
        try {
            XMLGregorianCalendar XMLdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
            return XMLdate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static XMLGregorianCalendar parseDate(long milliseconds) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(milliseconds));
        return parseDate(currentDate);
    }

    public static XMLGregorianCalendar getCurrentDate() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date());
        return parseDate(currentDate);
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
