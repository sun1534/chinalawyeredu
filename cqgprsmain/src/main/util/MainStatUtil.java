/**
 * 
 */
package main.util;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.readerrors.ReadErrorApns;

import org.apache.commons.logging.Log;

/**
 * @author 华锋 Nov 5, 2009-10:03:13 AM
 * 
 */
public class MainStatUtil {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(MainStatUtil.class);

	public static String getCdrTable() {
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(weekday);
		if (weekday == 1)// 周日
		// weekday = weekday - 1;
			weekday = 6;
		else
			weekday = weekday - 2;
		String table = "cdr_succ_0" + weekday;
		return table;
	}

	public static String getMobileApnTable() {
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(weekday);
		if (weekday == 1)// 周日
		// weekday = weekday - 1;
			weekday = 6;
		else
			weekday = weekday - 2;
		String table = "stat_mobile_apn_0" + weekday;
		return table;
	}

	public static String list2str(List list) {
		StringBuffer sb = new StringBuffer();
		int len = list.size();
		int i = 0;
		for (Object obj : list) {
			sb.append("'").append(obj).append("'");
			if (i++ != len - 1)
				sb.append(",");
		}
		return sb.toString();

	}

	/**
	 * 批量执行sql
	 * 
	 * @param con
	 * @param sqls
	 * @throws Exception
	 */
	public static int executeSql(Connection con, List sqls) throws Exception {
		Statement stmt = null;
		int result = 0;
		try {
			int len = sqls.size();
			// System.out.println("代码个数::"+len);
			LOG.info("要执行的SQL个数为:" + len);
			if (len > 0) {
				stmt = con.createStatement();
				int i = 1;
				for (Object obj : sqls) {
					stmt.addBatch(obj.toString());
					if ((i++) % 500 == 0) {
						int s[] = stmt.executeBatch();
						stmt.clearBatch();
						result += s.length;
					}
				}
				int s[] = stmt.executeBatch();
				stmt.clearBatch();
				result += s.length;
			}
		} finally {
			main.util.DBUtils.closeResource(null, stmt, null);
		}
		return result;
	}

	public static void main(String[] args) {

		System.out.println(getCdrTable());
	}

	public static long getYestardayTime() {
		try {
			String datestr = df.format(getPrevDate());
			String startstr = datestr + " 00:00:00";
			Date start = dfyyyyMmddHHmmss.parse(startstr);
			return start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	public static long getTodaydayTime() {
		try {
			String datestr = df.format(new Date());
			String startstr = datestr + " 00:00:00";
			Date start = dfyyyyMmddHHmmss.parse(startstr);
			return start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	public static long getOneDayAfter(long start) {
		return start + 24 * 60 * 60 * 1000;
	}

	/*
	 * 得到当前日期的前一天
	 */
	public static Date getPrevDate() {
		return getPrevCountDate(1);
	}

	/*
	 * 得到当前日期的前一天
	 */
	public static Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}

}
