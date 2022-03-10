package com.gsd.sreenidhi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author Sreenidhi, Gundlupet
 *
 */
public class CalendarUtils {

	/**
	 * Convert Java Date to String
	 * @param date Date
	 * @return string format of Date
	 */
	public static String dateToString(Date date) {
		String dt;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		dt = df.format(date);
		return dt;
	}
	
	public static Calendar addTime(int setHour, int setMin, int addHour, int addMin)  throws ParseException {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, setHour);
		c.set(Calendar.MINUTE, setMin);
		c.set(Calendar.SECOND, 0);
		
		c.add(Calendar.HOUR, addHour);
		c.add(Calendar.MINUTE, addMin);
		
		int hour = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		
		return c;
		
	}
	
	public static String addDays(String date, int duration, String format) throws ParseException {
		Date newDate ;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			calendar.setTime(sdf.parse(date));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		calendar.add(Calendar.DATE, duration);
		newDate = calendar.getTime();
		return sdf.format(newDate);
	}
	
	public static String addWeeks(String date, int duration, String format) throws ParseException {
		Date newDate ;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			calendar.setTime(sdf.parse(date));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		calendar.add(Calendar.DATE, duration*7);
		calendar.add(Calendar.DATE, -1);
		newDate = calendar.getTime();
		return sdf.format(newDate);
	}
	public static String addMonths(String date, int duration, String format) throws ParseException {
		Date newDate ;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			calendar.setTime(sdf.parse(date));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		calendar.add(Calendar.MONTH, duration);
		calendar.add(Calendar.DATE, -1);
		newDate = calendar.getTime();
		return sdf.format(newDate);
	}
	
	public static String addYears(String date, int duration, String format) throws ParseException {
		Date newDate ;
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			calendar.setTime(sdf.parse(date));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		calendar.add(Calendar.YEAR, duration);
		calendar.add(Calendar.DATE, -1);
		newDate = calendar.getTime();
		return sdf.format(newDate);
	}
	public static String dateToStringReadable(Date date) {
		String dt;
		DateFormat df = new SimpleDateFormat("MMM dd yyyy");
		dt = df.format(date);
		return dt;
	}
	
	public static String dateToStringDateTimeReadable(Date date) {
		String dt;
		DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:MM:ss");
		dt = df.format(date);
		return dt;
	}
	
	
	public static String dateToStringDateTimeReadable(Date date, String format) {
		String dt;
		DateFormat df = new SimpleDateFormat(format);
		dt = df.format(date);
		return dt;
	}
	
	public static Date stringToDate(String stringDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date d = sdf.parse(stringDate);
		return d;
	}
	
	public static Date stringToDateOnly(String stringDate, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(stringDate);
		return d;
	}
	
	
	/**
	 * Returns the current time stamp.
	 * @return Current Timestamp
	 */
	public static String getCurrentTimeStamp() {
		//DateFormat dF = DateFormat.getDateTimeInstance();
		Date dte = new Date();
		//return dF.format(dte);
		return dateToStringDateTimeReadable(dte, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getCurrentDateOnly() {
		//DateFormat dF = DateFormat.getDateTimeInstance();
		Date dte = new Date();
		//return dF.format(dte);
		return dateToStringDateTimeReadable(dte, "yyyy-MM-dd");
	}
	
	/**
     * Convert a millisecond duration to a string format
     * 
     * @param millis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public static String getDurationBreakdown(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return sb.toString();
    }
    
    /**
     * @return
     * @throws ParseException
     */
    public static String getDateTimeGMT() throws ParseException {
    	SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    	dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

    	//Local time zone   
    	SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

    	//Time in GMT
    	return dateToString(dateFormatLocal.parse( dateFormatGmt.format(new Date()) ));
    }

	public static int compareDates(String startDate, String endDate) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	      Date d1 = sdformat.parse(startDate);
	      Date d2 = sdformat.parse(endDate);
	      
		return d1.compareTo(d2);
	}
    
    
    
}
