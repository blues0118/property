package net.ussoft.property.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static final long ONE_HOUR = 60*60*1000L;
	
	/**
	 * 得到当前时间
	 * @return　1358822733362
	 */
	public static long getCurrentlong(){
		return (new Date()).getTime();
	}
	/**
	 * 得到当前时间
	 * @return　1358822733362
	 */
	public static String getCurrentlongStr(){
		return String.valueOf(getCurrentlong());
	}
	
	/**
	 * Date====>yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return Date====>yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date) {
		java.text.DateFormat df = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String s = df.format(date);
		return s;
	}
	
	/**
	 * Date====>yyyyMMdd
	 * @param date
	 * @param 
	 * @return String====>yyyyMMdd
	 */
	public static String dateToString(Date date, String format) {
		java.text.DateFormat df = new java.text.SimpleDateFormat(format);
		String s = df.format(date);
		return s;
	}
	
	/**
	 * 
	 * @param l
	 * @return 1358822733362===>2013-01-21 14:23:43
	 */
	public static String longToDateString(long l) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(l);
		String sDateTime = null;
		try {
			
			sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sDateTime;
	}
	/**
	 * 
	 * @param l
	 * @return 1358822733362===>14:23:43
	 */
	public static String longToDateStringHMS(long l) {
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
		java.util.Date dt = new Date(l);
		String sDateTime = null;
		try {
			
			sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sDateTime;
	}
	/**
	 * 
	 * @param l
	 * @return 1358822733362===>2013-01-21
	 */
	public static String longToDateStringYMD(long l) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt = new Date(l);
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示
		return sDateTime;
	}
	/**
	 * 
	 * @param strl
	 * @return 1358822733362===>2013-01-21 14:23:43
	 */
	public static String longStrToDateStr(String strl) {
		if(null == strl || "".equals(strl)) {
			return null;
		}
		return longToDateString(Long.parseLong(strl));
	}
	/**
	 * 
	 * @param longTime
	 * @return 1358822733362===>2013-01-21
	 */
	public static String longStrToDateStrYMD(String longTime) {
		if(null == longTime) {
			return "";
		}
		return longToDateStringYMD(Long.parseLong(longTime));
	}
	
	public static String DateToLongString(Date d) {
		if(null == d || "".equals(d)) {
			return null;
		}
		long lSysTime1 = d.getTime();  
		return String.valueOf(lSysTime1); 
	}
	/**
	 * yyyy-MM-dd====>1358822733362
	 * @param str yyyy-MM-dd
	 * @return yyyy-MM-dd====>1358822733362
	 */
	public static String StringToLong(String str) {
		if(null == str || str.equals("")) {
			return null; 
		}
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
			Date dt2 = sdf.parse(str);
			long lTime = dt2.getTime();
			return String.valueOf(lTime);
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * yyyy-MM-dd hh:DD:ss====>1358822733362
	 * @param str yyyy-MM-dd hh:DD:ss
	 * @return yyyy-MM-dd hh:DD:ss====>1358822733362
	 */
	public static String StringToLongss(String str) {
		if(null == str || str.equals("")) {
			return null; 
		}
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd hh:DD:ss");
			Date dt2 = sdf.parse(str);
			long lTime = dt2.getTime();
			return String.valueOf(lTime);
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * yyyy-MM-dd====>1358822733362
	 * @param str
	 * @return yyyy-MM-dd====>1358822733362
	 */
	public static Long dateStringToLong(String str) {
		if(null == str || str.equals("")) {
			return null;
		}
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
			Date dt2 = sdf.parse(str);
			long lTime = dt2.getTime();
			return lTime;
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * 加一天
	 * @param str yy-MM-dd
	 * @return
	 */
	public static String addOneDayString(String str) {
		if(null == str || str.equals("")) {
			return null;
		}
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
			Date dt2 = sdf.parse(str);
			long lTime = dt2.getTime() + ONE_HOUR * 24;
			return String.valueOf(lTime);
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * 
	 * @return
	 */
	public static Date getFirstDateOfThisMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DATE,1);
		return DelHMS(cal.getTime());
	}
	/**
	 * 
	 * @return
	 */
	public static Date getFirstDateOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		cal.set(cal.DATE,1);
		return DelHMS(cal.getTime());
	}
	
	public static Date getLastDateOfThisMonth()  {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		cal.set(cal.DATE,1);
		cal.add(cal.DATE, -1);
		return DelHMS(cal.getTime());
	}
	
	public static Date getFirstDateOfThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DAY_OF_YEAR,1);
		return DelHMS(cal.getTime());
	}
	
	public static Date getFirstDateOfNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, 1);
		cal.set(cal.DAY_OF_YEAR	,1);
		return DelHMS(cal.getTime());
	}
	/**
	 * 
	 * @return Tue Dec 31 00:00:00 CST 2013
	 */
	public static Date getLastDateOfThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, 1);
		cal.set(cal.DAY_OF_YEAR	,1);
		cal.add(cal.DATE, -1);
		return DelHMS(cal.getTime());
	}
	
	
	public static Date DelHMS(Date d) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(d);
		Date d2 = null;
		try {
			d2 = sdf.parse(dateStr);
		} catch (ParseException e) {
			
		}
		return d2;
	}
	
	/**
	 * 某一天到1970年的天数差
	 * @param dateLong
	 * @return
	 */
	public static String getDaysFrom1970(Long dateLong) {
		Calendar c = Calendar.getInstance();
		c.set(1970, 0, 1,0,0,0);
		
		long days = (dateLong - c.getTimeInMillis())/(ONE_HOUR*24);
		return String.valueOf(days);
	}
	public static String getDaysFrom1970(String dateLong) {
		try {
			
			Long date = Long.parseLong(dateLong);
			return getDaysFrom1970(date);
		}catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	public static String getDateFromDaysFromYMD(String date) {
		String result = "0";
		try {
			Calendar c = Calendar.getInstance();
			c.set(1970, 0, 1,0,0,0);
//			System.out.println((Long.parseLong(date)) * ONE_HOUR *24-ONE_HOUR);
			result = longStrToDateStrYMD(String.valueOf(((Long.parseLong(date)) * ONE_HOUR *24 +c.getTimeInMillis())));
			
		}catch(Exception e) {
			
		}
		return result;
	}
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return	得到两个日期的天数之差
	 */
	public static int daysBetween(Date date1,Date date2){  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return 计算两个日期相差的月数
	 */
	public static int monthsBetween( Date date2,Date date1) {
		  Calendar cal1 = new GregorianCalendar();
		  cal1.setTime(date1);
		  Calendar cal2 = new GregorianCalendar();
		  cal2.setTime(date2);
		  int c =(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 
		  		 + cal1.get(Calendar.MONTH)- cal2.get(Calendar.MONTH)
		  		 +(cal1.get(Calendar.DATE)-cal2.get(Calendar.DATE))/30;//计算两个日期之间的月数，如果天数有余数，则计算一个概要的月数
		  return c;
	}
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return 计算两个日期相差的月数
	 */
	public static int yearsBetween( Date date2,Date date1) {
		  Calendar cal1 = new GregorianCalendar();
		  cal1.setTime(date1);
		  Calendar cal2 = new GregorianCalendar();
		  cal2.setTime(date2);
		  int c =cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)
		  		 + (cal1.get(Calendar.MONTH)- cal2.get(Calendar.MONTH))/12
		  		 +(cal1.get(Calendar.DATE)-cal2.get(Calendar.DATE))/365;//计算两个日期之间的年数，如果天数有余数，则计算一个概要的年数
		  return c;
	}
	
}
