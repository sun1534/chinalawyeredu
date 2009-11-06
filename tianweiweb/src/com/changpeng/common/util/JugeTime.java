package com.changpeng.common.util;

import java.sql.Timestamp;

public class JugeTime {

	private String sub = "";

	public String getSub(Timestamp time) {

		// String subtime;
		long subt;
		Timestamp now = new Timestamp(System.currentTimeMillis());

		subt = (now.getTime() - time.getTime()) / 1000; // 相差的秒数
		if (subt > 0 && subt < 60) {
			sub = subt + "秒前";
		} else if (subt < (60 * 60)) {
			sub = subt / 60 + "分钟前";
		} else if (subt < (60 * 60 * 24)) {
			sub = subt / (60 * 60) + "小时前";
		} else if (subt < (60 * 60 * 24 * 30)) {
			sub = subt / (60 * 60 * 24) + "天前";
		} else if (subt > (60 * 60 * 24 * 30)) {
			sub = subt / (60 * 60 * 24 * 30) + "个月前";
		}

		// System.out.println("--------------------xx ");

		return sub;
	}

}
