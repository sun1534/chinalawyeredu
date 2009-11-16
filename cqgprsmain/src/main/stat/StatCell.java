/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatCell {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	public class TempCellStat {
		String cellid;
		String nettype="2";
		String bscid;
		String all="0";;
		String up="0";;
		String down="0";;
		// String time;
		String usercount="0";;

	}

	private Connection con;

	public StatCell(Connection con) {
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
		
		String day1 = df.format(main.util.MainStatUtil.getPrevCountDate(1));
		String day2 = df.format(main.util.MainStatUtil.getPrevCountDate(2));
		String day3 = df.format(main.util.MainStatUtil.getPrevCountDate(3));

		Statement tempstmt = null;
		List list0 = new ArrayList();
		List sqls = new ArrayList();
		ResultSet rs = null;

//		String sql = "insert into stat_cellid(cellid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values(?,?,1,to_char(sysdate-1,'yyyyMMdd'),?,?,?,?)";
//		PreparedStatement stmt = null;
	
		try {
//			stmt = con.prepareStatement(sql);

			while (stats.hasNext()) {
				TempCellStat stat = stats.next();
				if(Float.parseFloat(stat.all)>0){
//				stmt.setString(1, stat.cellid);
//				stmt.setString(2, stat.nettype);
//				stmt.setString(3, stat.up);
//				stmt.setString(4, stat.down);
//				stmt.setString(5, stat.all);
//				stmt.setString(6, stat.usercount);
//				stmt.addBatch();
					
					String sql = "insert into stat_cellid(cellid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values('"+stat.cellid+"',"+stat.nettype+",1,to_char(sysdate-1,'yyyyMMdd'),"+stat.up+","+stat.down+","+stat.all+","+stat.usercount+")";
                    sqls.add(sql);
					
				}else{
					
					 list0.add(stat.cellid);
						String sql0 = "insert into zero_cellid(cellid,dayflag,stattime,allvolume,historyvolume,prehistoryvolume)values("
							+ stat.cellid + ",1,to_char(sysdate-1,'yyyyMMdd'),0,0,0)";
					sqls.add(sql0);
					
				}
			}
//			stmt.executeBatch();
//			stmt.clearBatch();
			main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();

			LOG.info("CELLID统计数据入库成功");
			
			
			int len = list0.size();
			if (len > 0) {

				tempstmt = con.createStatement();

				String allsql = "select cellid,sum(allvolume) as allvolume from stat_cellid where dayflag=1 and cellid in("
						+ MainStatUtil.list2str(list0) + ") group by cellid";
				rs = tempstmt.executeQuery(allsql);
				while (rs.next()) {
					String cellid = rs.getString("cellid");
					String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_cellid set allvolume=" + allvolume + " where cellid='" + cellid
							+ "' and stattime=" + day1;
					sqls.add(zero0sql);
				}
				allsql = "select cellid,sum(allvolume) as allvolume from stat_cellid where dayflag=1 and cellid in("
						+ MainStatUtil.list2str(list0) + ") and stattime=" + day2 + " group by cellid";
				rs.close();
				rs = null;
				rs = tempstmt.executeQuery(allsql);
				while (rs.next()) {
					String cellid = rs.getString("cellid");
					String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_cellid set historyvolume=" + allvolume + " where cellid='" + cellid
							+ "' and stattime=" + day1;
					sqls.add(zero0sql);
				}

				allsql = "select cellid,sum(allvolume) as allvolume from stat_cellid where dayflag=1 and cellid in("
						+ MainStatUtil.list2str(list0) + ") and stattime=" + day3 + " group by cellid";
				rs.close();
				rs = null;
				rs = tempstmt.executeQuery(allsql);
				while (rs.next()) {
					String cellid = rs.getString("cellid");
					String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_cellid set prehistoryvolume=" + allvolume + " where cellid='" + cellid
							+ "' and stattime=" + day1;
					sqls.add(zero0sql);
				}

			}
			
			main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			LOG.info("流量为0的CELL统计数据入库成功");

			// 这里还要得到流量为0的那些个数据

		} finally {
			if (tempstmt != null)
				tempstmt.close();
			if (rs != null)
				rs.close();
		}
	}

	private void getStatDatas(Map<String, TempCellStat> allsgsns) throws Exception {
		
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);
		
		String table = MainStatUtil.getCdrTable();
//		String sql = "select cellid,count(distinct(msisdn))as  usercount,sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from "
//				+ table + " group by cellid";
		
		String sql = "select cellid,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume from stat_cellid where dayflag=0 and stattime>="
			+ start / 1000 + " and stattime<=" + end / 1000 + " group by cellid";
		
		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			String cellid = rs.getString("cellid");
//			String nettype = rs.getString("nettype");
			// String key = cellid + nettype;
			String key = cellid;
			if (allsgsns.containsKey(key)) {
//				LOG.info("得到的CELLID统计数据:" + cellid );
				TempCellStat stat = allsgsns.get(key);
				stat.cellid = rs.getString("cellid");
				// stat.nettype = rs.getString("nettype");
				stat.nettype = "2";
//				stat.usercount = rs.getString("usercount");
				
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
			}
			
		}
		
		LOG.info("得到CELLID的统计数据完毕");
		
		// 这里要得到用户数
		String usersql = "select CELLID, usercount from msisdn_CELLID where stattime>=" + start / 1000
				+ " and stattime<=" + end / 1000 ;
//				+ "  group by CELLID";
		LOG.info("usersql:" + usersql);
		stmt = con.createStatement();
		rs = stmt.executeQuery(usersql);
		while (rs.next()) {
			String cellid = rs.getString("CELLID");
			int usercount = rs.getInt("usercount");
			TempCellStat stat = allsgsns.get(cellid);
			if(stat!=null)
			stat.usercount = usercount + "";
		}
		rs.close();
		stmt.close();

		LOG.info("得到CELLID的用户数据完毕");
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception{
		long start = MainStatUtil.getYestardayTime();
		long end = MainStatUtil.getOneDayAfter(start);
//		try {
			// 得到所有的sgsn
			Map<String, TempCellStat> allmap = getAllCells();
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

//		} catch (Exception e) {
//			LOG.error("统计错误：" + e);
//		}

		// String usersql = "select sgsnid,nettype,count(distinct(mobile)) from
		// " +
		// table + " group by sgsnid,nettype";

	}

	private Map<String, TempCellStat> getAllCells() throws Exception {
		String sql = "select cellid from set_cell";
		ResultSet rs = null;
		Statement stmt = null;
		Map<String, TempCellStat> list = new HashMap<String, TempCellStat>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TempCellStat stat = new TempCellStat();
				stat.cellid = rs.getString("cellid");
				stat.nettype = "2";

				list.put(stat.cellid, stat);
				// stat = new TempCellStat();
				// stat.cellid = rs.getString("cellid");
				// stat.nettype = "3";
				// list.put(stat.cellid + stat.nettype, stat);
			}
			LOG.info("得到所有的CELLID个数:" + list.size());
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
		// LOG.info("开始统计CELLID数据");
		// Connection con = DBUtils.getOracleCon();
		// LOG.info("建立数据库连接成功");
		// StatCell s = new StatCell(con);
		// s.stat();
		// con.close();

	}

	private static SelfLog LOG=SelfLog.getInstance();
}