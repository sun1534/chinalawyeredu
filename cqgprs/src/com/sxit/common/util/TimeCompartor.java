package com.sxit.common.util;

import java.util.Comparator;
import java.sql.Date;

/**
 * 对TreeMap 进行排序
 * @author Administrator
 *
 */
public class TimeCompartor implements Comparator {

	/***
	 * @param 日期参数  
	 * 进行排序
	 */
	public int compare(Object o1, Object o2) {
		Date d = Date.valueOf(o1.toString());
		Date d2 =Date.valueOf(o2.toString());
		
//		if(o1.toString().toCharArray().length>=4)
//		{
//			d.setYear(new Integer(o1.toString().substring(0,4)));
//			d2.setYear(new Integer(o2.toString().substring(0,4)));
//		}
//		if(o1.toString().toCharArray().length>=6)
//		{
//			d.setMonth(new Integer(o1.toString().substring(5,7)));
//			d2.setMonth(new Integer(o2.toString().substring(5,7)));
//		}
//		if(o1.toString().toCharArray().length>=9)
//		{
//			
//			d.setDate(new Integer(o1.toString().substring(8,10)));
//			d2.setDate(new Integer(o2.toString().substring(8,10)));
//		}
		if(d.equals(d2)&&(o1==o2))
			return 0;
		if(d.before(d2))
			return -1;
		if(d.after(d2))
			return 1;
		
		return 0;
	}
	

}
