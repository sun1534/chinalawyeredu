/**
 * NumberUtil.java
 */
package com.sxit.query.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 华锋
 * Mar 30, 201011:25:04 PM
 *
 */
public class RauUtil {
	public static String IP_REGIX="([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	
	//2010-09-12 21:12:12的形式
//	public static String DATETIME_REGIX="((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|(1[0-9])|(2[0-3]))\\:(([0-5][0-9])|([0-9]))(((\\s)|(\\:(([0-5][0-9])|([0-9]))))?)))?";
	public static String DATETIME_REGIX="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
	private static Pattern pattern=Pattern.compile(IP_REGIX);
	private static Pattern datepattern=Pattern.compile(DATETIME_REGIX);
	public static String tohex(int i){
	
		String s=Integer.toHexString(i);
		if(s.length()==1)
			return "000"+s;
		if(s.length()==2)
			return "00"+s;
		if(s.length()==3)
			return "0"+s;
		else
			return s;
	}
	
	public static boolean containsDatetime(String datetime){
		  Matcher matcher = datepattern.matcher(datetime);
		  return matcher.find();
	}
	public static boolean isDatetime(String datetime){
		  Matcher matcher = datepattern.matcher(datetime);
		  return matcher.matches();
	}
	
    public static boolean isIp(String ipAddress){
//        String test = "";
//        Pattern pattern = Pattern.compile(test);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
	
	public static void main(String[] args){
//		System.out.println(tohex(1));
		System.out.println(containsDatetime("hdsjj@eqm01s14p2 ANCB ~ # more /logs/er_data_log/tmp/er_data_log.* | grep 13527585633"));
		System.out.println(containsDatetime("2010-03-12 23:12:12 323423333333333333333333"));

//		String str="For my money, the important thing ";
//		String regEx="ab*"; 
//		boolean result=Pattern.compile(regEx).matcher(str).find();
//		System.out.println(result);
	}
}
