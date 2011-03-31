package com.xjdzjc.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

public class Update_City_Prompt {
	public static Logger log = Logger.getLogger(Update_City_Prompt.class);

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Update_City_Prompt main = new Update_City_Prompt();
		Calendar calendar = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		Date date = null;
		date = main.listen();
		Timer timer = new Timer();
		if (date == null) {
			// 用户没有输入，按程序流程走，每天8点发送
			date = new Date();
			calendar.setTime(date);
			int hour = calendar.get(calendar.HOUR_OF_DAY);
			if (hour== 2) {
				calendar
						.set(calendar.MINUTE, calendar.get(calendar.MINUTE) + 1);
			} else {
				calendar.set(calendar.DAY_OF_MONTH, calendar
						.get(calendar.DAY_OF_MONTH) + 1);
				calendar.set(calendar.HOUR_OF_DAY, 2);
				calendar.set(calendar.MINUTE, 0);
			}

		} else {
			log.info("输入日期为=" + date.toLocaleString());
			timer.schedule(new ProssCity_Content(), date);
			date = new Date();
			calendar.setTime(date);
			calendar.set(calendar.DAY_OF_MONTH, calendar
					.get(calendar.DAY_OF_MONTH) + 1);
			calendar.set(calendar.HOUR_OF_DAY, 2);
			calendar.set(calendar.MINUTE, 0);
		}
		// 一天算成毫秒数
		int day = 1000 * 60 * 60 * 24;
		// int day = 1000 * 60 * 3;
		date = calendar.getTime();
		log.info("更新小提示发送程序运行中。。。。。" + date.toString());
		timer.schedule(new ProssCity_Content(), date, day);
		

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
					log.info("接收日期=" + date.toLocaleString());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("输入日期错误,请重新输入");
			} catch (IOException e) {
				log.debug(e);
				e.printStackTrace();
				log.info("输入日期错误,请重新输入");
			}
		} while (true);
		return date;
	}

}
