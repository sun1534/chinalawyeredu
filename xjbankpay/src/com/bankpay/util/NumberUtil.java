/**
 * Double2Money.java
 *
 */
package com.bankpay.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @author 华锋 V1.0 2007-12-28 下午01:20:53
 * 
 */
public class NumberUtil {

	private final static DecimalFormat nf = new DecimalFormat("###,##0.00");
	private final static DecimalFormat nff = new DecimalFormat("###,##0.00%");

	public static String toMoney(String s) {
//		System.out.println("string:"+s);
		if (s == null || s.equals("")) {
			return "0.00";
		}
		try {
			return toMoney(Double.parseDouble(s));
		} catch (Exception e) {
			return s;
		}
	}

	public static String toMoney(double d) {
//		System.out.println("double:"+d);
		try {
			if(d==0)
				return "";
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}

	public static String toMoney(BigDecimal d) {
//		System.out.println("BigDecimal:"+d);
		try {
			if (d == null)
				return "";
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}
	
    public static String toPercent(double s){
//    	System.out.println("====="+s);
    	
    	try {
			return nff.format(s);
		} catch (Exception e) {
			return String.valueOf(s);
		}
    }

    public static String toPercent(String s){
//    	System.out.println("=====String::"+s);
    	try {
    		int index=s.indexOf("/");
    		double d1=Double.parseDouble(s.substring(0, index));
    		double d2=Double.parseDouble(s.substring(index+1));
    		
    		
			return nff.format(d1/d2);
		} catch (Exception e) {
			System.out.println(e);
			return String.valueOf(s);
		}
    }
    
    
//    public static void main(String args[]){
//    	System.out.println(toMoney(((double)2/(double)23)));
//    }
}
