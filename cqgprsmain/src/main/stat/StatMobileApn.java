/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import main.util.SelfLog;

/**
 * 
 * 统计每个apn的对应cell的流量 这里的apn,排除掉cmnet和cmwap
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatMobileApn {

	private Connection con;
	private String table;
	private String stattime;
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	public StatMobileApn(Connection con, Date statdate) {
		this.con = con;
		this.table = main.util.MainStatUtil.getMobileApnTable(statdate);
		this.stattime = df.format(statdate);
	}

	/**
	 * 
	 */
	public void stat() throws Exception {
		// String table = MainStatUtil.getMobileApnTable();

		String sql = "insert into " + table
				+ "(mobile,apnni,stattime,dayflag,upvolume,downvolume,allvolume,periodlen) select " + "mobile,apnni,"
				+ stattime + ",1,sum(upvolume),sum(downvolume),sum(allvolume),sum(periodlen) from " + table
				+ " group by mobile,apnni";

		LOG.info("StatMobileAPN:" + sql);
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);

			// } catch (Exception e) {
			// LOG.error("统计MOBILE_APN错误：" + e);
			// e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	private static SelfLog LOG = SelfLog.getInstance();
}