package br.com.leivas.bancoleivas.util.date;

import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataUtils {

    public static boolean ehOMesmoDia(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    public static Date dataProximoDia(Date date) {
        LocalDateTime proximoDia = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        proximoDia = proximoDia.plusDays(1);
        date = Date.from(proximoDia.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
