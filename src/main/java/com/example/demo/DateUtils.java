package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    private static DateTimeFormatter dateFormatterddMMMyyyy = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static DateTimeFormatter dateFormatterddMMyyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");   
    private static DateTimeFormatter dateFormatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatddMMMyyyyHHmm = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    private static DateTimeFormatter formatMMMddyyyyHHmm = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
    private static DateTimeFormatter formatddMMyyyyHHmm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static DateTimeFormatter formatMMMddyyyy = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    
    //@Anurag added below for 2022-01-01 to 01-Jan-2022
    private static DateTimeFormatter dateStringToddMMMyyyy = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   
    private DateUtils() {
    }

    public static LocalDate parseStringDDmmYyyyToLocalDate(String date) {
        return LocalDate.parse(date, dateFormatterddMMyyyy);
    }

    public static LocalDateTime atEndOfDay(LocalDate localDate) {
        return (localDate == null ? null : localDate.atTime(23, 59, 59));
    }

    public static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }

    public static LocalDateTime atStartOfDay(LocalDate localDate) {
        return (localDate == null ? null : localDate.atStartOfDay());
    }

    public static String convertMMMddyyyyHHmm(LocalDateTime dateTime) {
        return dateTime.format(formatMMMddyyyyHHmm);
    }

    public static String convertddMMMyyyyHHmm(LocalDateTime dateTime) {
        return dateTime.format(formatddMMMyyyyHHmm);
    }

    public static String convertddMMyyyyHHmm(LocalDateTime dateTime) {
        return dateTime.format(formatddMMyyyyHHmm);
    }

    public static String parseLocalDateDDMMYYYY(LocalDate date) {
        return date.format(dateFormatterddMMyyyy);
    }

    public static String parseLocalDateDDMMMYYYY(LocalDate date) {
        return date.format(dateFormatterddMMMyyyy);
    }

    public static String convertMMMddyyyy(LocalDate localDate) {
        return localDate.format(formatMMMddyyyy);
    }

    public static Long getDifferenceInHours(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return ChronoUnit.HOURS.between(localDateTime1, localDateTime2);
    }


    public static Long getDifferenceInMinutes(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return ChronoUnit.MINUTES.between(localDateTime1, localDateTime2);
    }

    public static Long getDifferenceInSeconds(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return ChronoUnit.SECONDS.between(localDateTime1, localDateTime2);
    }


    public static Long getMilliSeconds(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.of(Constants.IST_TIMEZONE)).toInstant();
        Date date = Date.from(instant);
        return date.getTime();
    }

    public static Long getMilliSecondsFromLocalDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay(ZoneId.of(Constants.IST_TIMEZONE)).toInstant();
        Date date = Date.from(instant);
        return date.getTime();
    }

    public static LocalDateTime getNowIST() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(Constants.IST_TIMEZONE));
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDate getLocalDateIST() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(Constants.IST_TIMEZONE));
        return zonedDateTime.toLocalDate();
    }


    public static LocalDate parseLocalDate(String date) {
        return LocalDate.parse(date, dateFormatterddMMMyyyy);
    }

    public static LocalDate parseLocalDateDDMMYYYY(String date) {
        return LocalDate.parse(date, dateFormatterddMMyyyy);
    }

    public static LocalDate parseLocalDateMM(String date) {
        return LocalDate.parse(date, dateFormatteryyyyMMdd);
    }

    public static LocalDate formatLocalDateDDMMYYYY(LocalDate date) {
        String format = date.format(dateFormatterddMMMyyyy);
        LocalDate parse = LocalDate.parse(format, dateFormatterddMMMyyyy);
        return LocalDate.parse(format, dateFormatterddMMMyyyy);
    }

    public static LocalDateTime parseLocalDateTime(String datetime) {
        return LocalDateTime.parse(datetime, formatddMMMyyyyHHmm);
    }

    public static LocalDateTime parseMilliSeconds(Long milliSeconds) {
        return Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.of(Constants.IST_TIMEZONE)).toLocalDateTime();
    }

    public static LocalDate parseMilliSecondsToLocalData(Long milliSeconds) {
        return Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.of(Constants.IST_TIMEZONE)).toLocalDate();
    }

    public static LocalDate getISTLocalDate() {
        return getNowIST().toLocalDate();
    }

    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public static int toTotalMonths(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return (int) period.toTotalMonths();
    }

    public static LocalDate getFiscalYearStartDate() {
        LocalDate date = LocalDate.now();
        if (date.getMonthValue() < 4) {
            date = LocalDate.of(date.getYear() - 1, 4, 1);
        } else {
            date = LocalDate.of(2019, 4, 1);
        }
        return date;
    }

    public static LocalDate getFiscalYearEndDate() {
        LocalDate date = LocalDate.now();
        if (date.getMonthValue() < 4) {
            date = LocalDate.of(date.getYear(), 3, 31);
        } else {
            date = LocalDate.of(date.getYear() + 1, 3, 31);
        }
        return date;
    }

    public static boolean isDateAfterOrEqual(LocalDate d1, LocalDate d2) {
        boolean result = d1.equals(d2);
        if (!result) {
            return d1.isAfter(d2);
        }
        return result;
    }

    public static boolean isDateBeforeOrEqual(LocalDate d1, LocalDate d2) {
        boolean result = d1.equals(d2);
        if (!result) {
            return d1.isBefore(d2);
        }
        return result;
    }

    public static String getStrMonth(int month) {
        if (month > 9) {
            return String.valueOf(month);
        } else {
            return "0" + month;
        }
    }

    public static String getMMMYYForCurrentMonth() {
        String month = String.valueOf(LocalDate.now().getMonth()).substring(0, 3);
        String year = String.valueOf(LocalDate.now().getYear());
        //year = year.substring(year.length() - 2);
        return month + " " + year;
    }

    public static String getMMMYYYYforDate(LocalDate date) {
        return date.getMonth().toString().substring(0, 3) + " " + date.getYear();
    }
    
    public static String getCamelCase(String str) {

        final StringBuilder ret = new StringBuilder(str.length());
        for (final String word : str.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(Character.toUpperCase(word.charAt(0)));
                ret.append(word.substring(1).toLowerCase());
            }
            if ((ret.length() != str.length())) {
                ret.append(" ");
            }
        }
        return ret.toString();
    }

    public static Calendar parseStringToCalender(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        TimeZone ist = TimeZone.getTimeZone(ZoneId.ofOffset("UTC", ZoneOffset.of("+0530")));
        formatter.setTimeZone(ist);
        Date parse = formatter.parse(date);
        Calendar instance = new GregorianCalendar();
        instance.setTime(parse);
        instance.setTimeZone(ist);
        return instance;
    }
    
    public static String parseStringToddMMMyyyy(String date) {
    	LocalDate strToLocalDate = LocalDate.parse(date, dateStringToddMMMyyyy);
    	return strToLocalDate.format(dateFormatterddMMMyyyy);
    	
    }
}
