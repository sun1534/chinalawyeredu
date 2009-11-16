/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.stat.StatApn.TempApnStat;
import main.util.DBUtils;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatBsc {

	public class TempStat {
		String bscid;
		// String nettype;
		String all = "0";
		String sgsnid="";
		String up = "0";
		String down = "0";
		// String time;
		String usercount = "0";;

	}

	private Connection con;

	public StatBsc(Connection con) {
		this.con = con;
	}

	private void insert(Map<String, TempStat> allsgsns) throws Exception {
		java.util.Iterator<TempStat> stats = allsgsns.values().iterator();

		List sqls = new ArrayList();

		while (stats.hasNext()) {
			TempStat stat = stats.next();
			// stmt.setString(1, stat.bscid);
			// // stmt.setString(2, stat.nettype);
			// stmt.setString(2, stat.up);
			// stmt.setString(3, stat.down);
			// stmt.setString(4, stat.all);
			// stmt.setString(5, stat.usercount);
			// stmt.addBatch();

			String sql = "insert into stat_bsc(bscid,sgsnid,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values('"
					+ stat.bscid + "','"+stat.sgsnid+"',1,to_char(sysdate-1,'yyyyMMdd')," + stat.up + "," + stat.down + "," + stat.all
					+ "," + stat.usercount + ")";
			sqls.add(sql);
			
			/**
			 * 更新总流量
			 */
			String totalsql="update allvolume_bsc set upvolume=upvolume+"+stat.up+",downvolume=downvolume+"+stat.down+",allvolume=allvolume+"+stat.all+" where bscid='"+stat.bscid+"'";
			sqls.add(totalsql);	
			
		}

		main.util.MainStatUtil.executeSql(con, sqls);

		LOG.info("BSC统计数据入库成功");

	}

	private void getStatDatas(Map<String, TempStat> allbscs) throws Exception {
//		String table = MainStatUtil.getCdrTable();
		// String sql = "select bscid,count(distinct(msisdn))as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from "
		// + table + " group by bscid";

		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);

		String sql = "select bscid,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume from stat_bsc where dayflag=0 and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by bscid";

		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			String bscid = rs.getString("bscid");
			// String nettype = rs.getString("nettype");
			String key = bscid;

			if (allbscs.containsKey(key)) {
				// LOG.info("得到的BSC统计数据:"+bscid);
				TempStat stat = allbscs.get(key);
				stat.bscid = rs.getString("bscid");
				// stat.nettype = rs.getString("nettype");
//				stat.usercount = rs.getString("usercount");
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
			}

		}
		LOG.info("得到BSC的统计数据完毕");
		
		// 这里要得到用户数
		String usersql = "select bscid,usercount from msisdn_bsc where stattime>=" + start / 1000
				+ " and stattime<=" + end / 1000 ;
//		+ "  group by bscid";
		LOG.info("usersql:" + usersql);
		stmt = con.createStatement();
		rs = stmt.executeQuery(usersql);
		while (rs.next()) {
			String bscid = rs.getString("bscid");
			int usercount = rs.getInt("usercount");
			TempStat stat = allbscs.get(bscid);
			if(stat!=null)
			stat.usercount = usercount + "";
		}
		rs.close();
		stmt.close();

		LOG.info("得到BSC的用户数据完毕");
		
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() {
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);

		try {
			// 得到所有的sgsn
			Map<String, TempStat> allmap = getALlBscs();
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

	private Map<String, TempStat> getALlBscs() throws Exception {
		String sql = "select bscid,sgsnid from set_bsc where opttype!=2";
		ResultSet rs = null;
		Statement stmt = null;
		Map<String, TempStat> list = new HashMap<String, TempStat>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TempStat stat = new TempStat();
				stat.bscid = rs.getString("bscid");
				stat.sgsnid=rs.getString("sgsnid");
				// stat.nettype = "2";

				list.put(stat.bscid, stat);
				// stat = new TempStat();
				// stat.sgsnid = rs.getString("sgsnid");
				// stat.nettype = "3";
				// list.put(stat.sgsnid + stat.nettype, stat);
			}
			LOG.info("得到所有的BSC个数:" + list.size());
			return list;

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public static void main(String args[]) throws Exception {
		SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
		LOG = SelfLog.getInstance();
		LOG.info("开始统计BSC数据");
		Connection con = DBUtils.getOracleCon();
		LOG.info("建立数据库连接成功");
		StatBsc s = new StatBsc(con);
		s.stat();
		con.close();

	}

	private static SelfLog LOG=SelfLog.getInstance();
}