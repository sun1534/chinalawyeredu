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
public class StatSgsn091225 {

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

	public StatSgsn091225(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.stattime = df.format(statdate);
	}

	private void insert(Map<String, TempStat> allsgsns) throws Exception {
		java.util.Iterator<TempStat> stats = allsgsns.values().iterator();

		// String sql = "insert into stat_sgsn
		// (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT)values(?,?,1,"+stattime+",?,?,?,?)";
		// PreparedStatement stmt = null;
		List<String> sqls = new ArrayList<String>();

		// try {
		// stmt = con.prepareStatement(sql);
		TempStat _2g = new TempStat();
		TempStat _3g = new TempStat();
		while (stats.hasNext()) {
			TempStat stat = stats.next();
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
		// } finally {
		// // stmt.close();
		// }

	}

	private void getStatDatas(Map<String, TempStat> allsgsns) throws Exception {
		// String table = MainStatUtil.getCdrTable();
		// String sql = "select sgsnid,nettype,count(distinct(msisdn))as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from "
		// + table + " group by sgsnid,nettype";

		String sql = "select sgsnid,nettype,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume ,sum(usercount) as usercount,ggsnid,apnni from stat_sgsn where stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by sgsnid,nettype,ggsnid,apnni";

		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			String sgsnid = rs.getString("sgsnid");
			String nettype = rs.getString("nettype");
			String key = sgsnid + nettype;

			if (allsgsns.containsKey(key)) {
				// LOG.info("得到的SGSN统计数据:" + sgsnid + "," + nettype);
				TempStat stat = allsgsns.get(key);
				stat.sgsnid = rs.getString("sgsnid");
				stat.nettype = rs.getString("nettype");
				// stat.usercount = rs.getInt("usercount")/9;
				stat.up = rs.getDouble("up");
				stat.down = rs.getDouble("down");
				stat.all = rs.getDouble("allvolume");
				stat.ggsnid = rs.getString("ggsnid");
				stat.apnni = rs.getString("apnni");
				// allsgsns.remove(key);
				// allsgsns.put(key, stat);
			}

		}
		LOG.info("得到SGSN的统计数据完毕");
		// // 这里要得到用户数
		// String usersql = "select sgsnid,nettype,usercount from msisdn_sgsn
		// where stattime>=" + start / 1000
		// + " and stattime<=" + end / 1000;
		// // + " group by sgsnid,nettype";
		// LOG.info("usersql:" + usersql);
		// stmt = con.createStatement();
		// rs = stmt.executeQuery(usersql);
		// while (rs.next()) {
		// String sgsnid = rs.getString("sgsnid");
		// String nettype = rs.getString("nettype");
		// String key = sgsnid + nettype;
		//
		// int usercount = rs.getInt("usercount");
		// TempStat stat = allsgsns.get(key);
		// stat.usercount = usercount;
		// }
		// rs.close();
		// stmt.close();
		//
		// String dayusersql = "select usercount,nettype from msisdn_dayall
		// where stattime>=" + start / 1000 + " and stattime<="
		// + end / 1000;
		// // + " group by sgsnid,nettype";
		// LOG.info("dayusersql:" + dayusersql);
		// stmt = con.createStatement();
		// rs = stmt.executeQuery(usersql);
		// while (rs.next()) {
		// if(rs.getInt("nettype")==1)
		// dayusercount3g += rs.getInt("usercount");
		// else
		// dayusercount2g += rs.getInt("usercount");
		// }
		//		
		// LOG.info("3G:"+dayusercount3g+",2G:"+dayusercount2g);

		rs.close();
		stmt.close();

	}

	private int dayusercount2g;
	private int dayusercount3g;

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {

		// try {
		// 得到所有的sgsn
		Map<String, TempStat> allmap = getAllSgsns();
		// 得到每个sgsn的数据
		getStatDatas(allmap);
		// 插入统计表
		insert(allmap);

		// } catch (Exception e) {
		// LOG.error("统计错误：" + e);
		// e.printStackTrace();
		// }
	}

	private Map<String, TempStat> getAllSgsns() throws Exception {
		String sql = "select sgsnid from set_sgsn where opttype!=2";
		ResultSet rs = null;
		Statement stmt = null;
		Map<String, TempStat> list = new HashMap<String, TempStat>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TempStat stat = new TempStat();
				stat.sgsnid = rs.getString("sgsnid");
				stat.nettype = "2";

				list.put(stat.sgsnid + stat.nettype, stat);
				stat = new TempStat();
				stat.sgsnid = rs.getString("sgsnid");
				stat.nettype = "1";
				list.put(stat.sgsnid + stat.nettype, stat);
			}
			LOG.info("得到所有的SGSN个数:" + list.size());
			return list;

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
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