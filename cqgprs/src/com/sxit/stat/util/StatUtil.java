/**
 * 
 */
package com.sxit.stat.util;

import java.util.Date;
import java.util.List;

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
	
}
