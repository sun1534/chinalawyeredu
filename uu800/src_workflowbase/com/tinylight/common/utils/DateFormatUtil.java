/**
 * 
 */
package com.tinylight.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java Data类的辅助转换类
 */
public class DateFormatUtil {
	
	
	/**
	 * @param date MM/dd/yyyy格式的字符串日期
	 * @return  yyyy-MM-dd格式的字符串日期
	 */
	public static String parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date beginDate = sdf.parse(date);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(beginDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param date yyyy/MM/dd格式的字符串日期
	 * @return     yyyy/MM/dd格式的Date类
	 */
	public static Date strParseDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try{
			Date d = sdf.parse(date);
			return d;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param date 日期 yyyy-MM-dd格式的date类
	 * @return yyyy-MM-dd型的字符串
	 */
	public static String parseToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
		   String out = sdf.format(date);
		   return out;
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * @param date MM/dd/yyyy格式的字符串日期
	 * @return     yyyy/MM/dd格式的Date类
	 */
	public static Date strParseDate2(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try{
			Date d = sdf.parse(date);
			return d;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
