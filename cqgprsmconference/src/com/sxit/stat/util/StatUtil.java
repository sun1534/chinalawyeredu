/**
 * 
 */
package com.sxit.stat.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 华锋 Oct 21, 2009-2:46:19 PM
 * 
 */
public class StatUtil {
	
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00:00");

	
	/**
	 * 得到离f最近的一个整数值,比如43451,最近的整数值为40000 423最近的整数值为400，123的最近整数值为100
	 * 
	 * @param f
	 * @return
	 */
	public static long integerMin(float f) {
		if (f < 0)
			throw new java.lang.IllegalArgumentException("数据为负");
		if (f < 1)
			return 0;
		long lf = (long) f;
		for (int i = 0; i < 100; i++) {

			long s = tenn(i);
			long value = lf / s;
			long mod = lf % s;
			if (value < 10) {
				if (mod != 0)
					return value * tenn(i);
				else {
					if (i != 0)
						return (lf - tenn(i - 1));
					return lf - 1;
				}
			}
		}
		return 0;
	}
/**
 * 得到离f最近的大整数值
 * @param f
 * @return
 */
	public static long integerMax(float f) {
		if (f < 0)
			throw new java.lang.IllegalArgumentException("数据为负");
		if (f < 1)
			return 0;
		long lf = (long) f;
		for (int i = 0; i < 100; i++) {

			long s = tenn(i);
			long value = lf / s;
			long mod = lf % s;
			if (value < 10) {
				if (mod != 0)
					return (value + 1) * tenn(i);
				else {
					if (i != 0)
						return (lf + tenn(i - 1));
					return lf + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * 得到min和max之间相差count个的步长
	 * 
	 * @param max
	 * @param min
	 * @param count
	 * @return
	 */
	public static double steps(double max, double min, int count) {

		double f = max - min; // 差距多大
		double steps = f / count; // 相差的数字多少
		// 看是哪个数量级的
		return Double.parseDouble(com.sxit.system.util.NumberUtil.toMoney(steps));
//		return steps;
	}
/**
 * 返回10的n次方
 * @param x
 * @return
 */
	private static long tenn(int x) {
		long ten = 1;
		for (int i = 0; i < x; i++) {
			ten = ten * 10;
		}
		return ten;
	}


	
//	public static String getYestardayCdrTable() {
//		return getCdrTable(getPrevDate());
//	}
/**
 * 
 * @param date
 * @return
 */
	public static String getCdrTable(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.DATE));
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String table = "cdr_succ_0" + weekday;
		return table;
	}
/**
 * 
 * @param date
 * @return
 */
	public static String getMobileApnTable(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String table = "stat_mobile_apn_0" + weekday;
		return table;
	}

//	public static long getYestardayTime() {
//		return getDateTime(getPrevDate());
//	}
	

	public static String list2str(List list) {
		StringBuffer sb = new StringBuffer();
		int len = list.size();
		int i = 0;
		for (Object obj : list) {
			sb.append("'").append(obj).append("'");
			if (i++ != len - 1)
				sb.append(",");
		}
		return sb.toString();
	}
	
/**
 * 得到那天的启示时间
 * @param date
 * @return
 */
	public static long getDateTime(Date date) {
		try {
			String datestr = df.format(date);
			String startstr = datestr + " 00:00:00";
			Date start = dfyyyyMmddHHmmss.parse(startstr);
			return start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	/**
	 * datestr的形式必须为2009-12-13或者2009-11-01的形式
	 * @param datestr
	 * @return
	 */
	public static long getDateTime(String datestr){
		try {
		
			String startstr = datestr + " 00:00:00";
			Date start = dfyyyyMmddHHmmss.parse(startstr);
			return start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	/**
	 * datestr必须为2009-12-13或者2009-11-01的形式
	 * @param datestr
	 * @return
	 */
	public static Date getDate(String datestr){
		try {
			Date date = df.parse(datestr);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
/**
 * 得到今天的起始时间
 * @return
 */
	public static long getTodaydayTime() {

		return getDateTime(new Date());

	}

	public static long getOneDayAfter(long start) {
		return start + 24 * 60 * 60 * 1000;
	}

	/*
	 * 得到当前日期的前一天
	 */
	public static Date getPrevDate() {
		return getPrevCountDate(1);
	}

	/*
	 * 得到当前日期的前一天
	 */
	public static Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}
	


/**
 * 得到当前日期的前一天
 * @param date
 * @param days
 * @return
 */
	public static Date getPrevCountDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}
	

	/**
	 * 得到这个小时的起始时间值
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateHourTime(Date date) {
		String datestr = dfhour.format(date);
		try {
			Date d = dfyyyyMmddHHmmss.parse(datestr);
			return d.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}
	
	public static int getHourAfterTime(int start) {
	return start + 60 * 60;
}
	public static long getHourAfterTime(long start) {
		return start + 60 * 60*1000;
	}

	/**
	 * 
	 * @param datestr
	 *            必须是2009-11-21 12:00:00的形式
	 * @return
	 */
	public static long getDateHourTime(String datestr) {

//		if (!datestr.endsWith(":00:00"))
//			{
//			throw new java.lang.IllegalArgumentException("必须是2009-01-01 12:00:00的形式");
//			}
		try {
			Date d = dfyyyyMmddHHmmss.parse(datestr);
			return d.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}
	

	public static void main(String[] args) {
		// System.out.println(getMobileApnTable());
		// long yes=getTodaydayTime();
		// System.out.println(yes);
		// System.out.println(getOneDayAfter(yes));
		DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");

		Date date = getPrevCountDate(1);
		System.out.println(dfyyyyMMdd.format(date));
		date = getPrevCountDate(date, 0);
		System.out.println(dfyyyyMMdd.format(date));
		
		System.out.println(dfyyyyMMdd.format(date));

	}

}
