package com.qiaobei.hmp.support;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yanbin on 11/13/15.
 */
public class DateFilter {
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date startDate = new Date();
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date endDate = new Date();
    private boolean plusEndDay;

    public DateFilter() {
    }

    public DateFilter(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.plusEndDay = true;
    }

    public DateFilter(Date startDate, Date endDate, boolean plusEndDay) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.plusEndDay = plusEndDay;
    }

    public static DateFilter withStartPlusDays(int days) {
        LocalDate now = LocalDate.now();
        return new DateFilter(now.plusDays(days).toDate(), now.toDate());
    }

    public static DateFilter withStartPlusWeeks(int weeks) {
        DateFilter filter = new DateFilter();
        LocalDate now = LocalDate.now();
        return new DateFilter(now.plusWeeks(weeks).toDate(), now.toDate());
    }

    public static DateFilter withStartPlusMonths(int months) {
        LocalDate now = LocalDate.now();
        return new DateFilter(now.plusMonths(months).toDate(), now.toDate());
    }

    public static DateFilter withPlusMonths(Date day, int months) {
        DateFilter filter = new DateFilter();
        LocalDate start = new LocalDate(day);
        if (months < 0) {
            filter.setEndDate(day);
            filter.setStartDate(start.plusMonths(months).toDate());
        } else {
            filter.setStartDate(day);
            filter.setEndDate(start.plusMonths(months).toDate());
        }
        return filter;
    }

    public static DateFilter withStartPlusYears(int years) {
        DateFilter filter = new DateFilter();
        LocalDate now = LocalDate.now();
        filter.setEndDate(now.toDate());
        filter.setStartDate(now.plusYears(years).toDate());
        return filter;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayAfter(Date dateVal, Long daySend) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        c.setTime(dateVal);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, (int) (day + daySend));
        return c.getTime();
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

    public boolean isValid() {
        return null != startDate && null != endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRealEndDate() {
        if (plusEndDay) {
            return LocalDate.fromDateFields(endDate).plusDays(1).toDate();
        } else {
            return endDate;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateFilter filter = (DateFilter) o;

        if (plusEndDay != filter.plusEndDay) return false;
        if (!startDate.equals(filter.startDate)) return false;
        return endDate.equals(filter.endDate);

    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (plusEndDay ? 1 : 0);
        return result;
    }

    public String toString() {
        return DateFormatUtils.format(startDate, "yyyy/MM/dd") + "-" +
                DateFormatUtils.format(endDate, "yyyy/MM/dd");

    }

}
