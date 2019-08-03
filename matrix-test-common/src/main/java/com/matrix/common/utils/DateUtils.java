
package com.matrix.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {

    public final static String MONTH_PATTERN = "yyyy-MM";

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DATE_PATTERN);
    }

    public static Date parseDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 计算两个日期之前相差天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDifferDays(Date startDate, Date endDate) {

        int days = (int)((getStartDay(endDate).getTime() - getStartDay(startDate).getTime()) / (1000 * 3600 * 24));

        return days;
    }

    /**
     * 计算该日期距离当前日期相差的天数
     * 
     * @param startDate
     * @return
     */
    public static int getDifferDays(Date startDate) {
        int days = (int)((getStartDay(new Date()).getTime() - getStartDay(startDate).getTime()) / (1000 * 3600 * 24));

        return days;
    }

    /**
     * 日期转化为cron表达式
     * 
     * @param date
     * @return
     */
    public static String getCron(Date date) {

        String dateFormat = "ss mm HH dd MM ? yyyy";
        return fmtDateToStr(date, dateFormat);
    }

    public static String getCron(Date date, String dateFormat) {
        return fmtDateToStr(date, dateFormat);
    }

    /**
     * cron表达式转为日期
     * 
     * @param cron
     * @return
     * @throws ParseException
     */
    public static Date getCronToDate(String cron) throws ParseException {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return getCronToDate(cron, dateFormat);
    }

    public static Date getCronToDate(String cron, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = sdf.parse(cron);
        return date;
    }

    /**
     * Description:格式化日期,String字符串转化为Date
     * 
     * @param date
     * @param dtFormat 例如:yyyy-MM-dd HH:mm:ss yyyyMMdd
     * @return
     */
    public static String fmtDateToStr(Date date, String dtFormat) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);

        return dateFormat.format(date);
    }

    /**
     * 日期的增减
     * 
     * @param date 日期
     * @param days 正数为增加的天数，负数为减少的天数
     * @return
     */
    public static Date dateAfterDay(Date date, int days) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.add(Calendar.DATE, days);

        return c.getTime();
    }

    /**
     * 获取该日期的凌晨时间点
     * 
     * @param date
     * @return
     */
    public static Date getStartDay(Date date) {

        if (null == date) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取开始时间
     *
     * @param day
     * @return
     */
    public static Date getStartTime(int day) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStartTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        todayStartTime = todayStartTime.plusDays(day);
        ZonedDateTime zdt = todayStartTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取年月：yyyy-MM
     * 
     * @param date
     * @return
     */
    public static String formatMonth(Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(MONTH_PATTERN);
            return df.format(date);
        }
        return null;
    }
    
    public static String formatTimeToString(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DATE_TIME_PATTERN);
        String sDate = f.format(date);
        return sDate;
    }

    public static Date parseLocalDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
        return date;
    }
    
    public static Date LocalDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
    
    /**
     * 获取当前月份的第一天
     * @return
     */
    public static Date getCurrentMonthStartDay(){ 
        Calendar calendar = Calendar.getInstance();    
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    } 
    
    /**
     * 获取当前月份最后一天
     * @param date
     * @param dtFormat
     * @return
     */
    public static Date getCurrentMonthEndDay(){ 
        Calendar calendar = Calendar.getInstance();    
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        calendar.set(Calendar.HOUR_OF_DAY, 23);  
        calendar.set(Calendar.MINUTE, 59);  
        calendar.set(Calendar.SECOND, 59);  
        return calendar.getTime();
    } 
    
    /**
     * 获取当前日期下个月的第一天
     * @return
     */
    public static Date getNextMonthStartDay(Date date){ 
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    } 
    
    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate uDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
    
    
    /**
     * 计算日期{@code startDate}与{@code endDate}的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return 间隔天数
     */
    public static long until(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }
     
    /**
     * 指定增加天数
     * @param days
     * @return
     */
    public static Date gePlusTime0(int days) {
        Date date = getCurrDate0();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        if (days == 0) {
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        } else {
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days - 1);
        }
        return now.getTime();
    }
     
     /**
      * 获取当天时间 0点
      *
      * @return
      */
     public static Date getCurrDate0() {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         calendar.set(Calendar.SECOND, 0);
         Date zero = calendar.getTime();
         return zero;
     }
     
     /**
     * 指定日期加X天
     *
     * @param days 传1返回当天时间
     * @return
     */
    public static Date gePlusTime0Fin(int days) {
        Date date = getCurrDate0();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        if (days == 0) {
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        } else {
            now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        }

        return now.getTime();
    }
    
    public static String parseDateToSring(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
            String dt2 = sdf.format(date);
            //继续转换得到秒数的long型
            return dt2;
        } catch (Exception e) {
            return null;
        }
    }
     
    public static void main(String[] args) {
        Date startDate = DateUtils.parseDate("2018-08-26 23:59:59", "yyyy-M-d HH:mm:ss");
        Date endDate = DateUtils.parseDate("2018-11-23");
        System.out.println(DateUtils.fmtDateToStr(startDate, "M月d日"));
    }
}
