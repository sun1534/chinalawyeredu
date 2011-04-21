package main;

import java.util.Calendar;

/**
 * 日期工具类
 */
public final class DateUtil {

	public static int getWeekday() {
		Calendar c = Calendar.getInstance();
		int s= c.get(Calendar.DAY_OF_WEEK);
		if(s==1){
			s=8 ;
		}
		return s-1;
	}

	public static void main(String[] args) {
		System.out.println(getWeekday());
	}
}