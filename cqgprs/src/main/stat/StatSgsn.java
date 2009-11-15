/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.stat.StatBsc.TempStat;
import main.util.DBUtils;
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
		String all="0";
		String up="0";
		String down="0";
		// String time;
		String usercount="0";

	}

	private Connection con;

	public StatSgsn(Connection con) {
		this.con = con;
	}

	private void insert(Map<String, TempStat> allsgsns) throws Exception {
		java.util.Iterator<TempStat> stats = allsgsns.values().iterator();

//		String sql = "insert into stat_sgsn (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT)values(?,?,1,to_char(sysdate-1,'yyyyMMdd'),?,?,?,?)";
//		PreparedStatement stmt = null;
		List sqls = new ArrayList();

		try {
//			stmt = con.prepareStatement(sql);

			while (stats.hasNext()) {
				TempStat stat = stats.next();
//				stmt.setString(1, stat.sgsnid);
//				stmt.setString(2, stat.nettype);
//				stmt.setString(3, stat.up);
//				stmt.setString(4, stat.down);
//				stmt.setString(5, stat.all);
//				stmt.setString(6, stat.usercount);
//				stmt.addBatch();
				String sql = "insert into stat_sgsn (sgsnid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,USERCOUNT)values('"+stat.sgsnid+"',"+stat.nettype+",1,to_char(sysdate-1,'yyyyMMdd'),"+stat.up+","+stat.down+","+stat.down+","+stat.usercount+")";
				
				LOG.info(sql);
				sqls.add(sql);
			}
//			stmt.executeBatch();
//			stmt.clearBatch();
			main.util.MainStatUtil.executeSql(con, sqls);
			LOG.info("SGSN统计数据入库成功");
		} finally {
//			stmt.close();
		}

	}

	private void getStatDatas(Map<String, TempStat> allsgsns) throws Exception {
//		String table = MainStatUtil.getCdrTable();
//		String sql = "select sgsnid,nettype,count(distinct(msisdn))as  usercount,sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from "
//				+ table + " group by sgsnid,nettype";
		
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);

		String sql = "select sgsnid,nettype,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume from stat_sgsn where dayflag=0 and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by sgsnid,nettype";
		
		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			String sgsnid = rs.getString("sgsnid");
			String nettype = rs.getString("nettype");
			String key = sgsnid + nettype;

			if (allsgsns.containsKey(key)) {
				LOG.info("得到的SGSN统计数据:" + sgsnid + "," + nettype);
				TempStat stat = allsgsns.get(key);
				stat.sgsnid = rs.getString("sgsnid");
				stat.nettype = rs.getString("nettype");
//				stat.usercount = rs.getString("usercount");
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
//				allsgsns.remove(key);
//				allsgsns.put(key, stat);
			}
			LOG.info("得到SGSN的统计数据完毕");
		}
		
		
		// 这里要得到用户数
		String usersql = "select sgsnid,nettype,usercount from msisdn_sgsn where stattime>=" + start / 1000
				+ " and stattime<=" + end / 1000;
//				+ "  group by sgsnid,nettype";
		LOG.info("usersql:" + usersql);
		stmt = con.createStatement();
		rs = stmt.executeQuery(usersql);
		while (rs.next()) {
			String sgsnid = rs.getString("sgsnid");
			String nettype=rs.getString("nettype");
			String key = sgsnid + nettype;

			int usercount = rs.getInt("usercount");
			TempStat stat = allsgsns.get(key);
			stat.usercount = usercount + "";
		}
		rs.close();
		stmt.close();
		
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() {
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);

		try {
			// 得到所有的sgsn
			Map<String, TempStat> allmap = getAllSgsns();
			// 得到每个sgsn的数据
			getStatDatas(allmap);
			// 插入统计表
			insert(allmap);
			// String sql="select
			// sgsnid,nettype,sum(allvolume),sum(downvolume),sum(allvolume) from
			// stat_sgsn
			// where dayflag=0 and stattime between "+start+" and "+end+" group
			// by
			// sgsnid,nettype";

		} catch (Exception e) {
			LOG.error("统计错误：" + e);
			e.printStackTrace();
		}

		// String usersql = "select sgsnid,nettype,count(distinct(mobile)) from
		// " +
		// table + " group by sgsnid,nettype";

	}

	private Map<String, TempStat> getAllSgsns() throws Exception {
		String sql = "select sgsnid from set_sgsn";
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
				stat.nettype = "3";
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
//		SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
//		LOG = SelfLog.getInstance();
//		LOG.info("开始统计SGSN数据");
//		Connection con = DBUtils.getOracleCon();
//		LOG.info("建立数据库连接成功");
//		StatSgsn s = new StatSgsn(con);
//		s.stat();
//		con.close();
		
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);
		System.out.println(new java.sql.Timestamp(1258208434L*1000));
		System.out.println(start);
		System.out.println(end);

	}
//	private static SelfLog LOG;
	private static SelfLog LOG=SelfLog.getInstance();
}