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
public class StatCellApn {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	private Connection con;
	private String stattime;
	private long start;
	private long end;

	public StatCellApn(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.stattime = df.format(statdate);
	}


	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {

		// String sql = "insert into
		// stat_cellid_apn(cellid,apnni,stattime,dayflag,usercount,upvolume,downvolume,allvolume)
		// select "
		// + "cellid,apnni,"+stattime+",1,count(distinct(msisdn)) as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from "
		// + table + " where apnni not in('cmwap','cmnet','cmmm') group by
		// apnni,cellid";

		String sql = "insert into stat_cellid_apn(cellid,apnni,stattime,dayflag,usercount,upvolume,downvolume,allvolume) select "
				+ "cellid,apnni,"
				+ stattime
				+ ",1,sum(usercount),sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from stat_cellid_apn where dayflag=0 and (stattime between "
				+ start / 1000 + " and " + end / 1000 + ") group by apnni,cellid";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);

//		} catch (Exception e) {
//			LOG.error("统计CELL_APN错误：" + e);
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	private static SelfLog LOG = SelfLog.getInstance();
}