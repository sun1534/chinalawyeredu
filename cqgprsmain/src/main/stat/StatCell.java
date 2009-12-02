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
public class StatCell {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	public class TempCellStat {
		String cellid;
		String nettype = "2";
		String bscid;
		String all = "0";;
		String up = "0";;
		String down = "0";;
		// String time;
		String usercount = "0";;
		String allall = "0";
		String lac = "0";
	}

	private Connection con;
	private long start;
	private long end;
	private String statdatestr;
	private Date statdate;

	public StatCell(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.statdate = statdate;
		this.statdatestr = df.format(statdate);
	}

	/**
	 * 这里应该还要入流量为0的cell,单位为按天统计
	 * 
	 * @param allsgsns
	 * @throws Exception
	 */
	private void insert(Map<String, TempCellStat> allcells) throws Exception {
		java.util.Iterator<TempCellStat> stats = allcells.values().iterator();

		// String day1 = df.format(main.util.MainStatUtil.getPrevCountDate(1));
		// String day2 = df.format(main.util.MainStatUtil.getPrevCountDate(2));
		// String day3 = df.format(main.util.MainStatUtil.getPrevCountDate(3));
		String day1 = statdatestr;
		String day2 = df.format(main.util.MainStatUtil.getPrevCountDate(statdate, 1)); // 上次流量
		String day3 = df.format(main.util.MainStatUtil.getPrevCountDate(statdate, 2)); // 上上次流量

		System.out.println(day1 + ",,," + day2 + ",,," + day3);

		Statement tempstmt = null;
		List<String> list0cellkey = new ArrayList<String>();
		List<String> list0cellid = new ArrayList<String>();
		List<String> sqls = new ArrayList<String>();
		ResultSet rs = null;

		// String sql = "insert into
		// stat_cellid(cellid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values(?,?,1,to_char(sysdate-1,'yyyyMMdd'),?,?,?,?)";
		// PreparedStatement stmt = null;

		try {
			// stmt = con.prepareStatement(sql);

			while (stats.hasNext()) {
				TempCellStat stat = stats.next();
				if (Float.parseFloat(stat.all) > 0) {
					// stmt.setString(1, stat.cellid);
					// stmt.setString(2, stat.nettype);
					// stmt.setString(3, stat.up);
					// stmt.setString(4, stat.down);
					// stmt.setString(5, stat.all);
					// stmt.setString(6, stat.usercount);
					// stmt.addBatch();

					String sql = "insert into stat_cellid_day(cellid,lac,bscid,nettype,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values('"
							+ stat.cellid
							+ "','"
							+ stat.lac
							+ "','"
							+ stat.bscid
							+ "',"
							+ stat.nettype
							+ ",1,"
							+ statdatestr
							+ ","
							+ stat.up
							+ ","
							+ stat.down
							+ ","
							+ stat.all
							+ ","
							+ stat.usercount
							+ ")";
					sqls.add(sql);

					/**
					 * 更新总流量
					 */
					String totalsql = "update allvolume_cellid set upvolume=upvolume+" + stat.up
							+ ",downvolume=downvolume+" + stat.down + ",allvolume=allvolume+" + stat.all
							+ " where cellid='" + stat.cellid + "' and lac='" + stat.lac + "'";
					sqls.add(totalsql);

				} else {

					list0cellkey.add(stat.cellid + "|" + stat.lac);
					list0cellid.add(stat.cellid);
					String sql0 = "insert into zero_cellid(cellid,lac,dayflag,stattime,allvolume,historyvolume,prehistoryvolume)values('"
							+ stat.cellid + "','" + stat.lac + "',1," + statdatestr + ",0,0,0)";
					sqls.add(sql0);

				}
			}
			// stmt.executeBatch();
			// stmt.clearBatch();
			main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();

			LOG.info("CELLID统计数据入库成功");

			int len = list0cellkey.size();
			if (len > 0) {

				tempstmt = con.createStatement();

				// String allsql = "select cellid,sum(allvolume) as allvolume
				// from stat_cellid where dayflag=1 and cellid in("
				// + MainStatUtil.list2str(list0) + ") group by cellid";
				// rs = tempstmt.executeQuery(allsql);
				// while (rs.next()) {
				for (int i = 0; i < len; i++) {
					String str = list0cellkey.get(i).toString();
					int idx = str.indexOf("|");
					String cellid = str.substring(0, idx);
					String lac = str.substring(idx + 1);
					String allvolume = allcells.get(str).allall;
					String zero0sql = "update zero_cellid set allvolume=" + allvolume + " where lac='" + lac
							+ "' and cellid='" + cellid + "' and stattime=" + day1;
					sqls.add(zero0sql);
				}
				int pageSize = 500;
				int totalCount=len;
				int mod = totalCount % pageSize;
				int value = totalCount / pageSize;
				int count = mod == 0 ? value : value + 1;
				
				LOG.info("0流量个数为:"+len+",要分成"+count+"次处理");
				//oracle里面,in的数量不能超过1000个
				for (int i = 0; i < count; i++) {
					int startIndex = i * pageSize;
					List<String> list = new ArrayList<String>();
					
//					System.out.println(ii+"==="+len+"==="+(startIndex+pageSize));
//					System.out.println("ii < len && ii < startIndex + pageSize==="+(ii < len && ii < startIndex + pageSize));
					for ( int ii = startIndex;ii < len && ii < startIndex + pageSize; ii++) {
						list.add(list0cellid.get(ii));
					}
					String allsql = "select cellid,lac,sum(allvolume) as allvolume from stat_cellid_day where dayflag=1 and cellid in("
							+ MainStatUtil.list2str(list) + ") and stattime=" + day2 + " group by cellid,lac";

		    		LOG.info("0流量SQL:"+allsql);
					// rs.close();
					// rs = null;
					rs = tempstmt.executeQuery(allsql);
					while (rs.next()) {
						String cellid = rs.getString("cellid");
						String lac = rs.getString("lac");
						String allvolume = rs.getString("allvolume");
						String zero0sql = "update zero_cellid set historyvolume=" + allvolume + " where lac='" + lac
								+ "' and cellid='" + cellid + "' and stattime=" + day1;
						sqls.add(zero0sql);
					}

					allsql = "select cellid,lac,sum(allvolume) as allvolume from stat_cellid_day where dayflag=1 and cellid in("
							+ MainStatUtil.list2str(list) + ") and stattime=" + day3 + " group by cellid,lac";
					rs.close();
					rs = null;
					rs = tempstmt.executeQuery(allsql);
					while (rs.next()) {
						String cellid = rs.getString("cellid");
						String lac = rs.getString("lac");
						String allvolume = rs.getString("allvolume");
						String zero0sql = "update zero_cellid set prehistoryvolume=" + allvolume + " where cellid='"
								+ cellid + "' and lac='" + lac + "' and stattime=" + day1;
						sqls.add(zero0sql);
					}
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

	private void getStatDatas(Map<String, TempCellStat> allcells) throws Exception {
		//
		// long start = MainStatUtil.getYestardayTime();
		// long end = MainStatUtil.getOneDayAfter(start);

		// String table = MainStatUtil.getCdrTable();
		// String sql = "select cellid,count(distinct(msisdn))as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from "
		// + table + " group by cellid";

		String sql = "select cellid,lac,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume,sum(usercount) as usercount from stat_cellid where dayflag=0 and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by cellid,lac";

		LOG.info(sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int i = 0;
		while (rs.next()) {

			String key = rs.getString("cellid") + "|" + rs.getString("lac");
			// String nettype = rs.getString("nettype");
			// String key = cellid + nettype;

			if (allcells.containsKey(key)) {

				// System.out.println("key在数据中............");

				// LOG.info("得到的CELLID统计数据:" + cellid );
				TempCellStat stat = allcells.get(key);
				stat.cellid = rs.getString("cellid");
				// stat.nettype = rs.getString("nettype");
				stat.nettype = "2";
				int usercount = rs.getInt("usercount");
				if (usercount < 10)
					stat.usercount = usercount + "";
				else
					stat.usercount = "" + rs.getInt("usercount") / 8;

				stat.lac = rs.getString("lac");
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
			} else {
//				System.out.println("哈哈，零流量哦。。。。。。。。。" + (i++));
			}

		}

		LOG.info("得到CELLID的统计数据完毕");

//		// // 这里要得到用户数
//		String usersql = "select CELLID,lac, usercount from msisdn_CELLID where stattime>=" + start / 1000
//				+ " and stattime<=" + end / 1000;
//		// + " group by CELLID";
//		LOG.info("usersql:" + usersql);
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(usersql);
//		while (rs.next()) {
//			String cellid = rs.getString("CELLID");
//			String lac = rs.getString("lac");
//			int usercount = rs.getInt("usercount");
//			TempCellStat stat = allcells.get(cellid + "|" + lac);
//			if (stat != null)
//				stat.usercount = usercount + "";
//		}
//		rs.close();
//		stmt.close();
//
//		LOG.info("得到CELLID的用户数据完毕");
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {

		// try {
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

		// } catch (Exception e) {
		// LOG.error("统计错误：" + e);
		// }

		// String usersql = "select sgsnid,nettype,count(distinct(mobile)) from
		// " +
		// table + " group by sgsnid,nettype";

	}

	private Map<String, TempCellStat> getAllCells() throws Exception {
		// String sql = "select cellid,bscid from set_cell where opttype!=2";
		String sql = "select a.cellid,a.bscid,a.lac,b.allvolume from set_cell a,allvolume_cellid b where a.cellid=b.cellid(+) and a.lac=b.lac(+) and a.opttype!=2";

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
				stat.bscid = rs.getString("bscid");
				stat.allall = rs.getString("allvolume");
				stat.lac = rs.getString("lac");
				list.put(stat.cellid + "|" + stat.lac, stat);
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

	private static SelfLog LOG = SelfLog.getInstance();
}