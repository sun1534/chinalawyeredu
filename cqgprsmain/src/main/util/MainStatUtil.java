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
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00:00");
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(MainStatUtil.class);

	/**
	 * 得到这个日期对应的mobileapn表
	 * 
	 * @param date
	 * @return
	 */
	public static String getMobileApnTable(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String table = "stat_mobile_apn_0" + weekday;
		return table;
	}

	/**
	 * 得到这个日期对应的cdr表
	 * 
	 * @param date
	 * @return
	 */
	public static String getCdrTable(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.DATE));
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String table = "cdr_succ_0" + weekday;
		return table;
	}

	// /**
	// * 昨天是哪个cdr表
	// *
	// * @return
	// */
	// public static String getCdrTable() {
	// return getCdrTable(getPrevDate());
	// }

	// /**
	// * 昨天是哪个mobile_apn表
	// *
	// * @return
	// */
	// public static String getMobileApnTable() {
	// return getMobileApnTable(getPrevDate());
	// }

	/**
	 * list转化成'a','b','c'的形式
	 * 
	 * @param list
	 * @return
	 */
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
	public static int executeSql(Connection con, List<String> sqls) throws Exception {
		Statement stmt = null;
		int result = 0;
		try {
			int len = sqls.size();
			LOG.info("要执行的SQL个数为:" + len);
			long now = System.currentTimeMillis();
			if (len > 0) {
				stmt = con.createStatement();
				int i = 1;
				for (String sql : sqls) {
					stmt.addBatch(sql);
					if ((i++) % 300 == 0) {
						int s[] = stmt.executeBatch();
						stmt.clearBatch();
						result += s.length;
						LOG.info("执行300个的时间为:" + (System.currentTimeMillis() - now));
						now = System.currentTimeMillis();
					}
				}
				int s[] = stmt.executeBatch();
				LOG.info("执行最后" + s.length + "个的时间为:" + (System.currentTimeMillis() - now));
				stmt.clearBatch();
				result += s.length;
			}
		} finally {
			main.util.DBUtils.closeResource(null, stmt, null);
		}
		return result;
	}

	public static int executeSql(Connection con, String sql) {
		Statement stmt = null;
		int result = 0;
		try {

			LOG.info("SQL::" + sql);
			long now = System.currentTimeMillis();

			stmt = con.createStatement();
			result=stmt.executeUpdate(sql);
			stmt.execute(sql);
			LOG.info("处理"+result+"条耗费时间为:" + (System.currentTimeMillis() - now));

		} catch(Exception e){
			LOG.error(sql+"执行失败!",e);
		}finally {
			main.util.DBUtils.closeResource(null, stmt, null);
		}
		return result;
	}

	/**
	 * 得到这个小时的起始时间值
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateHourTime(Date date) {
		String datestr = dfhour.format(date);
		try {
			Date d = dfsec.parse(datestr);
			return d.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 
	 * @param datestr
	 *            必须是2009-11-21 12:00:00的形式
	 * @return
	 */
	public static long getDateHourTime(String datestr) {

		if (!datestr.endsWith(":00:00"))
			return 0;
		try {
			Date d = dfsec.parse(datestr);
			return d.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 得到某天的起始时间
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateTime(Date date) {
		String datestr = df.format(date);
		String hstart = datestr + " 00:00:00";
		try {
			Date d = dfsec.parse(hstart);
			return d.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 得到昨天的起始时间
	 * 
	 * @return
	 */
	public static long getYestardayTime() {
		// try {
		// String datestr = df.format(getPrevDate());
		// String startstr = datestr + " 00:00:00";
		// Date start = dfyyyyMmddHHmmss.parse(startstr);
		// return start.getTime();
		// } catch (Exception e) {
		// e.printStackTrace();
		// return System.currentTimeMillis();
		// }
		return getDateTime(getPrevDate());
	}

	/**
	 * 得到今天的起始时间
	 * 
	 * @return
	 */
	public static long getTodaydayTime() {
		// try {
		// String datestr = df.format(new Date());
		// String startstr = datestr + " 00:00:00";
		// Date start = dfyyyyMmddHHmmss.parse(startstr);
		// return start.getTime();
		// } catch (Exception e) {
		// e.printStackTrace();
		// return System.currentTimeMillis();
		// }
		return getDateTime(new Date());
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

	/**
	 * 得到几天前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}

	public static Date getPrevCountDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}

	/**
	 * 多少小时前
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date getPrevCountHour(Date date, int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, 0 - hours);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}

	public static void main(String[] args) {

	}
}
