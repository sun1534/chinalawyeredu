package com.xjdzjc.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

public class SendSms_Mas {
	public static Logger log = Logger.getLogger(SendSms_Mas.class);

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SendSms_Mas main = new SendSms_Mas();
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		date = main.listen();
		Timer timer = new Timer();
		// 取得当天有时间，如果是星期一，则在11点启动，否则从下星期一开始启动
		if (date == null) {
			// 用户没有输入，按程序流程走，每天8点发送
			date = new Date();
			calendar.setTime(date);
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			if (week == 1) {
				week = 7;
			} else {
				week = week - 1;
			}
			log.info("week==" + week);
			if (week == 1) {
				// 判斷當天是不是星期一，如果是星期一，時間是在11點，則馬上發送
				int hour = calendar.get(calendar.HOUR_OF_DAY);
				if (hour == 11) {
					calendar.set(calendar.MINUTE,	
							calendar.get(calendar.MINUTE) + 1);
				} else {
					calendar.setTime(date);
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					calendar.set(calendar.HOUR_OF_DAY, 11);
					calendar.set(calendar.MINUTE, 0);
					calendar.add(Calendar.DAY_OF_MONTH, 7);
				}
			} else {
				// 如果当天不是星期一 则设置下一个星期一的时间，
				if (week == 7) {
					calendar.setTime(date);
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					calendar.set(calendar.HOUR_OF_DAY, 11);
					calendar.set(calendar.MINUTE, 0);
				} else {
					calendar.setTime(date);
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					calendar.set(calendar.HOUR_OF_DAY, 11);
					calendar.set(calendar.MINUTE, 0);
					calendar.add(Calendar.DAY_OF_MONTH, 7);
				}

			}
		} else {
			log.info("输入日期为：" + date.toLocaleString());
			timer.schedule(new ProessService(2), date);
			date = new Date();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(calendar.HOUR_OF_DAY, 11);
			calendar.set(calendar.MINUTE, 0);
			calendar.add(Calendar.DAY_OF_MONTH, 7);
		}
		// 一天算成毫秒数
		int day = 1000 * 60 * 60 * 24 * 7;
		date = calendar.getTime();
		log.info("发送程序运行中。。。。。" + date.toString());
		// 平常发送程序 每天8点钟发送
		timer.schedule(new ProessService(2), date, day);
	}

	/**
	 * 手工事件监听。 自己输入日期时间启动
	 */
	Date listen() {
		log.info("控制台监听启动......");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		do {
			try {
				String inC = new java.io.BufferedReader(new InputStreamReader(
						System.in)).readLine();
				if (inC.equals("")) {
					return null;
				} else {
					date = sdf.parse(inC);
					if (date.before(new Date())) {
						log.info("输入日期应大于当前日期，请重新 输入！");
					} else {
						break;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				log.info("输入日期错误,请重新输入");
			} catch (IOException e) {
//				log.debug(e);
//				e.printStackTrace();
				log.info("输入日期错误,请重新输入");
			}
		} while (true);
		return date;
	}

}
