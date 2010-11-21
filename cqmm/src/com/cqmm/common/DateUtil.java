package com.cqmm.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf1=new SimpleDateFormat("MM-dd");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf_long=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * ·µ»Ø  MM-dd
	 * @return
	 */
	public static String getSimpleDate(Date date){
		return sdf1.format(date);
	}
	
	/**
	 * ·µ»Ø  yyyy-MM-dd
	 * @return
	 */
	public static String getSimpleDate2(Date date){
		
		return sdf2.format(date);
	}
	
	/**
	 * ·µ»Ø  yyyy-MM-dd hh24:mm:ss
	 * @return
	 */
	public static String getSimpleDateTime(Date date){
		
		return sdf_long.format(date);
	}
	
	public static Date getDate(String s){
		try{
			return sdf_long.parse(s);
		}catch(Exception e){
			
		}
		return new Date();
	}
}
