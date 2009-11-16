/**
 * 
 */
package com.sxit.stat.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 华锋
 * Oct 21, 2009-2:46:19 PM
 *
 */
public class StatUtil {
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
					return (value+1) * tenn(i);
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
	 * @param max
	 * @param min
	 * @param count
	 * @return
	 */	
	 public static double steps(double max,double min,int count){
			
		 double f=max-min;  //差距多大
		 double steps=f/count; //相差的数字多少
			//看是哪个数量级的
//			for (int i = 0; i < 100; i++) {
//
//				long s = tenn(i);
//			    long value=((long)steps)/s;
//			    if(value<10){
//			    	return value
//			    }
//			}
			return steps;
	 }
	 

	 

	private static long tenn(int x) {
		long ten = 1;
		for (int i = 0; i < x; i++) {
			ten = ten * 10;
		}
		return ten;
	}
	
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getYestardayCdrTable() {
//		Calendar calendar = Calendar.getInstance();
//		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
//		if (weekday == 1)// 周日
////			weekday = weekday - 1;
//			weekday=6;
//		else
//			weekday = weekday - 2;
//		String table = "cdr_succ_0" + weekday;
//		return table;
		return getCdrTable(getPrevDate());
	}
	
	public static String getCdrTable(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.DATE));
		int weekday = calendar.get(Calendar.DAY_OF_WEEK)-1;
//		System.out.println(weekday);
//		if (weekday == 1)// 周日
////			weekday = weekday - 1;
//			weekday=0;
//		else
//			weekday = weekday - 1;
		String table = "cdr_succ_0" + weekday;
		return table;
//周 1 2 3 4 5 6 7
//日 0 1 2 3 4 5 6
	}
	
	public static String getMobileApnTable(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK)-1;
//		System.out.println(weekday);
//		if (weekday == 1)// 周日
////			weekday = weekday - 1;
//			weekday=6;
//		else
//			weekday = weekday - 1;
		String table = "stat_mobile_apn_0" + weekday;
		return table;
	}
	
	public static long getYestardayTime(){
		try{
		String datestr=df.format(getPrevDate());
		String startstr=datestr+" 00:00:00";
		Date start=dfyyyyMmddHHmmss.parse(startstr);
		return start.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	public static long getDateTime(Date date){
		try{
		String datestr=df.format(date);
		String startstr=datestr+" 00:00:00";
		Date start=dfyyyyMmddHHmmss.parse(startstr);
		return start.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	public static long getTodaydayTime(){
		try{
		String datestr=df.format(new Date());
		String startstr=datestr+" 00:00:00";
		Date start=dfyyyyMmddHHmmss.parse(startstr);
		return start.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	public static long getOneDayAfter(long start){
		return start+24*60*60*1000;
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
	public static  Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0-days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}
	public static void main(String[] args){
//		System.out.println(getMobileApnTable());
		System.out.println(getYestardayCdrTable());
	}
	
}
