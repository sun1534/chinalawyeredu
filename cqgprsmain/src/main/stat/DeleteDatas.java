/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import main.util.SelfLog;

/**
 * 删除10天前的数据
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class DeleteDatas {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	private Connection con;

	private String daystattime;
	private String hourstattime;
	private String hourstattimelhf;

	private static Map<String, String> DAYTABLELIST = new LinkedHashMap<String, String>();
	private static Map<String, String> HOURTABLELIST = new LinkedHashMap<String, String>();

	public DeleteDatas(Connection con, Date statdat, int days) {
		this.con = con;
		Date daysagodate = main.util.MainStatUtil.getPrevCountDate(statdat, days);

		daystattime = df.format(daysagodate);
		long daystart = main.util.MainStatUtil.getDateTime(daysagodate);
//		long dayend = main.util.MainStatUtil.getOneDayAfter(daystart);
		hourstattime = "" + (daystart / 1000);
		hourstattimelhf = daystattime + "23";
		LOG.info("删除的数据,日统计" + daystattime + ",小时统计" + hourstattime);

		addday();
		addhour();
	}

	private void addday() {
		DAYTABLELIST.put("ALARM_CELLID", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("ALARM_GB", " where alarmtime<=" + hourstattime);

		DAYTABLELIST.put("MSISDN_APN", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("MSISDN_BSC", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("MSISDN_CELLID", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("MSISDN_CELLID_APN", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("MSISDN_SGSN", " where stattime<=" + hourstattime);
		DAYTABLELIST.put("STAT_APN", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_APN_ERROR", " where dayflag=1 and stattime<=" + daystattime);
		// DAYTABLELIST.put("STAT_APN_ZERO", daystattime);
		DAYTABLELIST.put("STAT_BSC", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_CELLID", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_CELLID_APN", " where dayflag=1 and stattime<=" + daystattime);
		// DAYTABLELIST.put("STAT_CELLID_TOP", daystattime);不需要
		// DAYTABLELIST.put("STAT_CELLID_ZERO", daystattime);
		DAYTABLELIST.put("STAT_ERRCODE_ERROR", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_IMSI_APN_ERROR", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_IMSI_ERRCODE_ERROR", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_NSVC", " where stattime<=" + hourstattime);
		// DAYTABLELIST.put("STAT_SGSN", " where dayflag=1 and stattime<=" +
		// daystattime);
		DAYTABLELIST.put("ZERO_APN", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("ZERO_CELLID", " where dayflag=1 and stattime<=" + daystattime);

		DAYTABLELIST.put("STAT_CELLID_DAY", " where dayflag=1 and stattime<=" + daystattime);
		DAYTABLELIST.put("STAT_CELLID_APN_DAY", " where dayflag=1 and stattime<=" + daystattime);

		DAYTABLELIST.put("CDR_MISTAKE_NO33", " where opentime<=" + hourstattime); // 额外的方式

		DAYTABLELIST.put("MSISDN_APN", " where stattime<=" + hourstattime); // 额外的方式
		DAYTABLELIST.put("MSISDN_BSC", " where stattime<=" + hourstattime); // 额外的方式
		DAYTABLELIST.put("MSISDN_CELLID", " where stattime<=" + hourstattime); // 额外的方式
		DAYTABLELIST.put("MSISDN_CELLID_APN", " where stattime<=" + hourstattime); // 额外的方式
		// DAYTABLELIST.put("MSISDN_DAYALL", " where stattime<=" +
		// hourstattime); // 额外的方式
		DAYTABLELIST.put("MSISDN_SGSN", " where stattime<=" + hourstattime); // 额外的方式
		// DAYTABLELIST.put("CDR_MISTAKE", " where opentime<=" + hourstattime);
	}

	private void addhour() {
		HOURTABLELIST.put("STAT_APN", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_APN_ERROR", " where dayflag=0 and stattime<=" + hourstattimelhf);
		HOURTABLELIST.put("STAT_APN_ZERO", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_BSC", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_CELLID", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_CELLID_APN", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_CELLID_ZERO", " where dayflag=0 and stattime<=" + hourstattime);
		HOURTABLELIST.put("STAT_ERRCODE_ERROR", " where dayflag=0 and stattime<=" + hourstattimelhf);
		HOURTABLELIST.put("STAT_IMSI_APN_ERROR", " where dayflag=0 and stattime<=" + hourstattimelhf);
		HOURTABLELIST.put("STAT_IMSI_ERRCODE_ERROR", " where dayflag=0 and stattime<=" + hourstattimelhf);
		// DAYTABLELIST.put("STAT_NSVC", hourstattime);
		HOURTABLELIST.put("STAT_SGSN", " where dayflag=0 and stattime<=" + hourstattime);
		// DAYTABLELIST.put("ZERO_APN", hourstattime);
		// DAYTABLELIST.put("ZERO_CELLID", hourstattime);
	}

	public void deleteDatas() {
		Iterator<String> iteratorday = DAYTABLELIST.keySet().iterator();
		while (iteratorday.hasNext()) {
			String table = iteratorday.next();
			String where = DAYTABLELIST.get(table);
			String sql = "delete from " + table + where;
			long now = System.currentTimeMillis();
			LOG.info(sql );
			main.util.MainStatUtil.executeSql(con, sql);
			LOG.info(sql + "=>" + (System.currentTimeMillis() - now));
		}

		Iterator<String> iteratorhour = HOURTABLELIST.keySet().iterator();
		while (iteratorhour.hasNext()) {
			String table = iteratorhour.next();
			String where = HOURTABLELIST.get(table);
			String sql = "delete from " + table + where;
			long now = System.currentTimeMillis();
			LOG.info(sql );
			main.util.MainStatUtil.executeSql(con, sql);
			LOG.info(sql + "=>" + (System.currentTimeMillis() - now));
		}
		LOG.info("数据删除完毕");

		// try {
		// int opentime = 0;
		// String sql = "select min(opentime) as opentime from cdr_mistake";
		// Statement stmt = con.createStatement();
		// int deletetime= 1258820078;
		// // int deletetime = Integer.parseInt(hourstattime);
		// while (opentime <= deletetime) {
		// ResultSet rs = stmt.executeQuery(sql);
		// rs.next();
		// opentime = rs.getInt("opentime");
		// rs.close();
		// main.util.MainStatUtil.executeSql(con, "delete from cdr_mistake where
		// opentime<=" + (opentime + 3600));
		//
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	private static SelfLog LOG = SelfLog.getInstance();
}