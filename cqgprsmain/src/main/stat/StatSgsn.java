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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatSgsn {

	public class TempStat {
		String sgsnid;
		String nettype;
		double all = 0;
		double up = 0;
		double down = 0;
		int usercount = 0;
		String ggsnid = "";
		String apnni = "";

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	private Connection con;
	private long start;
	private long end;
	private String stattime;

	public StatSgsn(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.stattime = df.format(statdate);
		// this.stattime = "20100101";
	}

	private void insert(List<TempStat> stats) throws Exception {
		// java.util.Iterator<TempStat> stats = allsgsns.values().iterator();

		// String sql = "insert into stat_sgsn
		// (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT)values(?,?,1,"+stattime+",?,?,?,?)";
		// PreparedStatement stmt = null;
		List<String> sqls = new ArrayList<String>();

		// try {
		// stmt = con.prepareStatement(sql);
		TempStat _2g = new TempStat();
		TempStat _3g = new TempStat();
		Map<String, TempStat> ggsnother = new HashMap<String, TempStat>();

		for (int i = 0; i < stats.size(); i++) {
			// while (stats.hasNext()) {
			TempStat stat = stats.get(i);
			if (stat.ggsnid.indexOf("GGSN") == -1) {
				String ggsnkey = stat.sgsnid + "," + stat.nettype + "," + stat.apnni;
				TempStat ggsnstat = null;
				if (ggsnother.containsKey(ggsnkey)) {
					ggsnstat = ggsnother.get(ggsnkey);
				} else {
					ggsnstat = new TempStat();
					ggsnother.put(ggsnkey, ggsnstat);
				}
				ggsnstat.sgsnid = stat.sgsnid;
				ggsnstat.nettype = stat.nettype;
				ggsnstat.apnni = stat.apnni;
				ggsnstat.ggsnid = "GGSN其他";
				ggsnstat.all += stat.all;
				ggsnstat.down += stat.down;
				ggsnstat.up += stat.up;
				ggsnstat.usercount += stat.usercount;
			}
			if (stat.nettype.equals("2")) {
				_2g.all += stat.all;
				_2g.up += stat.up;
				_2g.down += stat.down;
				_2g.usercount += stat.usercount;
			} else {
				_3g.all += stat.all;
				_3g.up += stat.up;
				_3g.down += stat.down;
				_3g.usercount += stat.usercount;
			}
			String sql = "insert into stat_sgsn (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT,ggsnid,apnni)values('"
					+ stat.sgsnid
					+ "',"
					+ stat.nettype
					+ ",1,"
					+ stattime
					+ ","
					+ stat.up
					+ ","
					+ stat.down
					+ ","
					+ stat.all + "," + stat.usercount + ",'" + stat.ggsnid + "','" + stat.apnni + "')";

			// System.out.println(sql);
			/**
			 * 更新总流量
			 */
			String totalsql = "update allvolume_sgsn set upvolume=upvolume+" + stat.up + ",downvolume=downvolume+"
					+ stat.down + ",allvolume=allvolume+" + stat.all + " where sgsnid='" + stat.sgsnid
					+ "' and nettype='" + stat.nettype + "'";
			sqls.add(totalsql);
			// LOG.info(sql);
			sqls.add(sql);
		}
		String allsql2g = "insert into STAT_ALLDAY(stattime,nettype,usercount,upvolume,downvolume,allvolume)values("
				+ stattime + ",2," + dayusercount2g + "," + _2g.up + "," + _2g.down + "," + _2g.all + ")";
		String allsql3g = "insert into STAT_ALLDAY(stattime,nettype,usercount,upvolume,downvolume,allvolume)values("
				+ stattime + ",1," + dayusercount3g + "," + _3g.up + "," + _3g.down + "," + _3g.all + ")";
		// stmt.executeBatch();
		// stmt.clearBatch();
		sqls.add(allsql2g);
		sqls.add(allsql3g);
		main.util.MainStatUtil.executeSql(con, sqls);
		LOG.info("SGSN统计数据入库成功");

		java.util.Iterator<TempStat> ggsnotherstats = ggsnother.values().iterator();
		sqls.clear();
		while (ggsnotherstats.hasNext()) {
			TempStat stat = ggsnotherstats.next();
			String sql = "insert into stat_sgsn (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT,ggsnid,apnni)values('"
					+ stat.sgsnid
					+ "',"
					+ stat.nettype
					+ ",1,"
					+ stattime
					+ ","
					+ stat.up
					+ ","
					+ stat.down
					+ ","
					+ stat.all + "," + stat.usercount + ",'" + stat.ggsnid + "','" + stat.apnni + "')";
			sqls.add(sql);
		}
		main.util.MainStatUtil.executeSql(con, sqls);
		LOG.info("GGSN其他的统计数据入库成功");

	}

	private List<TempStat> getStatDatasCqGgns() throws Exception {

		// 这里groupby的顺序不能搞反了，必须是sgsnid,ggsnid,apnni,nettype
		String sql = "select sgsnid,nettype,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume ,sum(usercount) as usercount,ggsnid,apnni from stat_sgsn where apnni is not null and ggsnid is not null and ggsnid like 'GGSN%' and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by sgsnid,ggsnid,apnni,nettype";

		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<TempStat> stats = new ArrayList<TempStat>();
		List apnnis = new ArrayList();
		String temp = "";

		while (rs.next()) {
			String sgsnid = rs.getString("sgsnid");
			String ggsnid = rs.getString("ggsnid") == null ? "" : rs.getString("ggsnid");
			String apnni = rs.getString("apnni") == null ? "fuck" : rs.getString("apnni");
			String nettype = rs.getString("nettype");
			String key = sgsnid + "," + ggsnid;

			if (!key.equals(temp)) {
				if (!temp.equals("")) {
					this.getReminAppni(stats, apnnis, temp);
				}
				apnnis.clear();
				temp = key;
			}
			apnnis.add(apnni);

			TempStat stat = new TempStat();
			stat.sgsnid = sgsnid;
			stat.nettype = nettype;
			stat.up = rs.getDouble("up");
			stat.down = rs.getDouble("down");
			stat.all = rs.getDouble("allvolume");
			stat.ggsnid = ggsnid;
			stat.apnni = apnni;
			stats.add(stat);

		}
		// 这里要判断最后一个是否有cmnet或者cmwap或者other
		this.getReminAppni(stats, apnnis, temp);
		LOG.info("得到SGSN的统计数据完毕::" + stats.size());
		rs.close();
		stmt.close();
		return stats;
	}

	private List<TempStat> getStatDatasNoCqGgns(List<TempStat> stats) throws Exception {

		// 这里groupby的顺序不能搞反了，必须是sgsnid,ggsnid,apnni,nettype
		String sql = "select sgsnid,nettype,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume ,sum(usercount) as usercount,ggsnid,apnni from stat_sgsn where apnni is not null and ggsnid is not null and ggsnid not like 'GGSN%' and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by sgsnid,ggsnid,apnni,nettype";

		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			String sgsnid = rs.getString("sgsnid");
			String ggsnid = rs.getString("ggsnid") == null ? "" : rs.getString("ggsnid");
			String apnni = rs.getString("apnni") == null ? "fuck" : rs.getString("apnni");
			String nettype = rs.getString("nettype");

			TempStat stat = new TempStat();
			stat.sgsnid = sgsnid;
			stat.nettype = nettype;
			stat.up = rs.getDouble("up");
			stat.down = rs.getDouble("down");
			stat.all = rs.getDouble("allvolume");
			stat.ggsnid = ggsnid;
			stat.apnni = apnni;
			stats.add(stat);
		}
		// 这里要判断最后一个是否有cmnet或者cmwap或者other
		LOG.info("得到SGSN的统计数据完毕::" + stats.size());
		rs.close();
		stmt.close();
		return stats;
	}

	private void getReminAppni(List stats, List apnnis, String temp) {
		int idx = temp.indexOf(",");
		String _sgsnid = temp.substring(0, idx);
		String _ggsnid = temp.substring(idx + 1);
		if (!apnnis.contains("cmnet")) {
			TempStat stat1 = new TempStat();
			stat1.sgsnid = _sgsnid;
			stat1.nettype = "1";
			stat1.ggsnid = _ggsnid;
			stat1.apnni = "cmnet";

			TempStat stat2 = new TempStat();
			stat2.sgsnid = _sgsnid;
			stat2.nettype = "2";
			stat2.ggsnid = _ggsnid;
			stat2.apnni = "cmnet";

			stats.add(stat1);
			stats.add(stat2);
		}
		if (!apnnis.contains("cmwap")) {
			TempStat stat1 = new TempStat();
			stat1.sgsnid = _sgsnid;
			stat1.nettype = "1";
			stat1.ggsnid = _ggsnid;
			stat1.apnni = "cmwap";

			TempStat stat2 = new TempStat();
			stat2.sgsnid = _sgsnid;
			stat2.nettype = "2";
			stat2.ggsnid = _ggsnid;
			stat2.apnni = "cmwap";

			stats.add(stat1);
			stats.add(stat2);
		}
		if (!apnnis.contains("other")) {
			TempStat stat1 = new TempStat();
			stat1.sgsnid = _sgsnid;
			stat1.nettype = "1";
			stat1.ggsnid = _ggsnid;
			stat1.apnni = "other";

			TempStat stat2 = new TempStat();
			stat2.sgsnid = _sgsnid;
			stat2.nettype = "2";
			stat2.ggsnid = _ggsnid;
			stat2.apnni = "other";

			stats.add(stat1);
			stats.add(stat2);
		}
		// System.out.println("apnnis:" + apnnis);
	}

	private int dayusercount2g;
	private int dayusercount3g;

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {

		List<TempStat> stats = getStatDatasCqGgns();
		getStatDatasNoCqGgns(stats);
		// 插入统计表
		insert(stats);

	}

	public static void main(String args[]) throws Exception {
		// SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
		// LOG = SelfLog.getInstance();
		// LOG.info("开始统计SGSN数据");
		// Connection con = DBUtils.getOracleCon();
		// LOG.info("建立数据库连接成功");
		// StatSgsn s = new StatSgsn(con);
		// s.stat();
		// con.close();

		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);
		System.out.println(new java.sql.Timestamp(1258208434L * 1000));
		System.out.println(start);
		System.out.println(end);

	}

	// private static SelfLog LOG;
	private static SelfLog LOG = SelfLog.getInstance();
}