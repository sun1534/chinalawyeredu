/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.util.DBUtils;

/**
 * 删除10天前的数据
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class DeleteDatasDay {

	class DelTable {
		public DelTable(String table, int datetype) {
			this.table = table;
			this.datetype = datetype;
		}

		public DelTable(String table, String where, int datetype) {
			this.table = table;
			this.where = where;
			this.datetype = datetype;
		}

		public DelTable(String table, String where, String datefield, int datetype) {
			this.table = table;
			this.where = where;
			this.datefield = datefield;
			this.datetype = datetype;
		}

		public String toString(){
			return table+"=>"+datefield+"=>"+where+"=>"+datetype;
		}
		
		public String table;
		public String where = "";
		public String datefield = "stattime";
		public int datetype = 1;// 1秒2小时3天
	}

	private Connection con;

	private String daystattime;
	private String hourstattime;
	private String hourstattimelhf;

	private static List<DelTable> TABLES = new ArrayList<DelTable>();

	public DeleteDatasDay(Connection con, Date statdat, int days) {
		this.con = con;
		Date daysagodate = main.util.MainStatUtil.getPrevCountDate(statdat, days);

		daystattime = df.format(daysagodate);
		long daystart = main.util.MainStatUtil.getDateTime(daysagodate);
		// long dayend = main.util.MainStatUtil.getOneDayAfter(daystart);
		hourstattime = "" + (daystart / 1000);
		hourstattimelhf = daystattime + "23";
		System.out.println("删除的数据,日统计" + daystattime + ",小时统计" + hourstattime+",,"+hourstattimelhf);

		addday();
	}

	private void addday() {
		DelTable del = new DelTable("ALARM_CELLID", 1);
		TABLES.add(del);
		
		del = new DelTable("ALARM_GB", "", "alarmtime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_APN", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_BSC", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_SGSN", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("STAT_APN", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_APN_ERROR", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_BSC", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_CELLID", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_CELLID_APN", "dayflag=1", "stattime", 3);
		TABLES.add(del);

//		del = new DelTable("STAT_ERRCODE_ERROR", "dayflag=1", "stattime", 3);
//		TABLES.add(del);

		del = new DelTable("STAT_IMSI_APN_ERROR", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_IMSI_ERRCODE_ERROR", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_NSVC", "", "stattime", 2);
		TABLES.add(del);

		del = new DelTable("ZERO_APN", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("ZERO_CELLID", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_CELLID_DAY", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("STAT_CELLID_APN_DAY", "dayflag=1", "stattime", 3);
		TABLES.add(del);

		del = new DelTable("CDR_MISTAKE_NO33", "", "opentime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_APN", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_BSC", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_CELLID_APN", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_SGSN", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("STAT_APN", "dayflag=0", "stattime", 1);
		TABLES.add(del);
		del = new DelTable("STAT_APN_ERROR", "dayflag=0", "stattime", 2);
		TABLES.add(del);
		del = new DelTable("STAT_APN_ZERO", "dayflag=0", "stattime", 1);
		TABLES.add(del);
		del = new DelTable("STAT_BSC", "dayflag=0", "stattime", 1);
		TABLES.add(del);
		del = new DelTable("STAT_CELLID", "dayflag=0", "stattime", 1);
		TABLES.add(del);
		del = new DelTable("STAT_CELLID_APN", "dayflag=0", "stattime", 1);
		TABLES.add(del);
		del = new DelTable("STAT_CELLID_ZERO", "dayflag=0", "stattime", 1);
		TABLES.add(del);
//		del = new DelTable("STAT_ERRCODE_ERROR", "dayflag=0", "stattime", 2);
//		TABLES.add(del);
		del = new DelTable("STAT_IMSI_APN_ERROR", "dayflag=0", "stattime", 2);
		TABLES.add(del);
		del = new DelTable("STAT_IMSI_ERRCODE_ERROR", "dayflag=0", "stattime", 2);
		TABLES.add(del);
		del = new DelTable("STAT_SGSN", "dayflag=0", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_CELLID", "", "stattime", 1);
		TABLES.add(del);

		del = new DelTable("MSISDN_CELLID_APN", "", "stattime", 1);
		TABLES.add(del);
		
		del = new DelTable("hwchr_flowid_stat", "", "statdate", 3);
		TABLES.add(del);
		
		del = new DelTable("hwchr_flowid_error_stat", "", "statdate", 3);
		TABLES.add(del);
	}

	public long getMinStattime(DelTable table) throws Exception {
		long result = 0;
		String sql = "";
		if (table.where != null && !table.where.equals(""))
			sql = "select min(" + table.datefield + ") as thetime from " + table.table + " where " + table.where;
		else
			sql = "select min(" + table.datefield + ") as thetime from " + table.table;
		System.out.println(sql);
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getLong("thetime");
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyyMMddHH");

	private static long getHourtime(String hourdate) {
		try {
			Date date = dfhour.parse(hourdate);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static long getDaytime(String hourdate) {
		try {
			Date date = df.parse(hourdate);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteDatas() {
		System.out.println("开始删除===>"+dftime.format(new Date()));
		try {

			int len = TABLES.size();
			for (int i = 0; i < len; i++) {
				DelTable table = TABLES.get(i);
				long mintime = getMinStattime(table);
				if(mintime==0)
					continue;
				long deletetime = 0;
				if (table.datetype == 1)
					deletetime = Long.parseLong(hourstattime);
				if (table.datetype == 2)
					deletetime = Long.parseLong(hourstattimelhf);
				if (table.datetype == 3)
					deletetime = Long.parseLong(daystattime);
				System.out.println(table.table+"的最小时间:"+mintime+",删除时间:"+deletetime);
				try {

					while (mintime <= deletetime) {

						long now = System.currentTimeMillis();
						long timevalidate = 0;
						if (table.datetype == 1) {
							timevalidate = mintime + 3600;// 加3600秒
						}
						if (table.datetype == 2) {
							long hour = getHourtime(mintime + "");
							timevalidate = Long.parseLong(dfhour.format(new java.sql.Timestamp(hour + 1 * 60 * 60
									* 1000)));
						}
						if (table.datetype == 3) {// 加1天
							long hour = getDaytime(mintime + "");
							timevalidate = Long
									.parseLong(df.format(new java.sql.Timestamp(hour + 24 * 60 * 60 * 1000)));
						}

						String sql = "delete from " + table.table + " where " + table.datefield + "<=" + timevalidate;
						if (table.where != null && !table.where.equals("")) {
							sql = "delete from " + table.table + " where " + table.where + " and " + table.datefield
									+ "<=" + timevalidate;
						}
						System.out.println(sql);
						int ii = main.util.MainStatUtil.executeSql(con, sql);
						System.out
								.println("删除了" + table.table + "的" + ii + "条..." + (System.currentTimeMillis() - now));

						mintime = timevalidate;
					}
					System.out
					.println(table.table+"的数据删除完毕!");

				} catch (Exception e) {
					System.out.println("数据删除失败:"+ table+"=>"+ e);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("数据删除完毕===>"+dftime.format(new Date()));
	}
	private static DateFormat dftime=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		Connection con = DBUtils.getOracleCon();
		Date statdate = main.util.MainStatUtil.getPrevDate();
		DeleteDatasDay dd = new DeleteDatasDay(con, statdate, 3 + 1);
		dd.deleteDatas();
	}
}