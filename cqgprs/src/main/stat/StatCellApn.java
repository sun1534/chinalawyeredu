/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import main.stat.StatCell.TempCellStat;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * 
 * 统计每个apn的对应cell的流量
 * 这里的apn,排除掉cmnet和cmwap
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatCellApn {

	private Connection con;

	public StatCellApn(Connection con) {
		this.con = con;
	}

	/**
	 * 这里应该还要入流量为0的cell,单位为按天统计
	 * 
	 * @param allsgsns
	 * @throws Exception
	 */
	private void insert(Map<String, TempCellStat> allsgsns) throws Exception {
		java.util.Iterator<TempCellStat> stats = allsgsns.values().iterator();

		String sql = "insert into stat_cellid(cellid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values(?,?,1,to_char(sysdate-1,'yyyyMMdd'),?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			while (stats.hasNext()) {
				TempCellStat stat = stats.next();
				stmt.setString(1, stat.cellid);
				stmt.setString(2, stat.nettype);
				stmt.setString(3, stat.up);
				stmt.setString(4, stat.down);
				stmt.setString(5, stat.all);
				stmt.setString(6, stat.usercount);
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.clearBatch();
			LOG.info("CELLID统计数据入库成功");
		} finally {
			stmt.close();
		}
	}

	private void getStatDatas(Map<String, TempCellStat> allsgsns) throws Exception {
		String table = MainStatUtil.getCdrTable();
		String sql = "select cellid,count(distinct(msisdn))as  usercount,sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from "
				+ table + " group by cellid";
		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			String cellid = rs.getString("cellid");
			String nettype = rs.getString("nettype");
			// String key = cellid + nettype;
			String key = cellid;
			if (allsgsns.containsKey(key)) {
				LOG.info("得到的CELLID统计数据:" + cellid + "," + nettype);
				TempCellStat stat = allsgsns.get(key);
				stat.cellid = rs.getString("cellid");
				// stat.nettype = rs.getString("nettype");
				stat.nettype = "2";
				stat.usercount = rs.getString("usercount");
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
			}
			LOG.info("得到CELLID的统计数据完毕");
		}
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception{
		// long start = StatUtil.getYestardayTime();
		// long end = StatUtil.getOneDayAfter(start);
		// try {
		// // 得到所有的sgsn
		// Map<String, TempCellStat> allmap = getAllCells();
		// // 得到每个sgsn的数据
		// getStatDatas(allmap);
		// // 插入统计表
		// insert(allmap);
		// // String sql="select
		// // sgsnid,nettype,sum(allvolume),sum(downvolume),sum(allvolume) from
		// // stat_sgsn
		// // where dayflag=0 and stattime between "+start+" and "+end+" group
		// // by
		// // sgsnid,nettype";

		// String sql="select cellid,apnni,count(distinct(msisdn))as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from cdr_succ_03 group by
		// apnni,cellid";

		String table = MainStatUtil.getCdrTable();
		String sql = "insert into stat_cellid_apn(cellid,apnni,stattime,dayflag,usercount,upvolume,downvolume,allvolume) select "
				+ "cellid,apnni,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)) as  usercount,sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from "
				+ table + " where apnni not in('cmwap','cmnet','cmmm') group by apnni,cellid";
	
		
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);

		} catch (Exception e) {
			LOG.error("统计CELL_APN错误：" + e);
		}finally{
			if(stmt!=null)
				stmt.close();
		}

		// String usersql = "select sgsnid,nettype,count(distinct(mobile)) from
		// " +
		// table + " group by sgsnid,nettype";

	}

	// private Map<String, TempCellStat> getAllCells() throws Exception {
	// String sql = "select cellid from set_cell";
	// ResultSet rs = null;
	// Statement stmt = null;
	// Map<String, TempCellStat> list = new HashMap<String, TempCellStat>();
	// try {
	// stmt = con.createStatement();
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// TempCellStat stat = new TempCellStat();
	// stat.cellid = rs.getString("cellid");
	// stat.nettype = "2";
	//
	// list.put(stat.cellid, stat);
	// // stat = new TempCellStat();
	// // stat.cellid = rs.getString("cellid");
	// // stat.nettype = "3";
	// // list.put(stat.cellid + stat.nettype, stat);
	// }
	// LOG.info("得到所有的CELLID个数:" + list.size());
	// return list;
	//
	// } finally {
	// if (rs != null)
	// rs.close();
	// if (stmt != null)
	// stmt.close();
	// }
	// }

	public static void main(String args[]) throws Exception {

		// SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
		// LOG = SelfLog.getInstance();
		// LOG.info("开始统计CELLID数据");
		// Connection con = DBUtils.getOracleCon();
		// LOG.info("建立数据库连接成功");
		// StatCell s = new StatCell(con);
		// s.stat();
		// con.close();

	}

	private static SelfLog LOG=SelfLog.getInstance();
}