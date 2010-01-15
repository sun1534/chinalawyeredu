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
public class StatApn {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	public class TempApnStat {
		String apnni;
		String all = "0";
		String up = "0";
		String down = "0";
		String usercount = "0";
		String allall = "0";

	}

	private Connection con;
	private long start;
	private long end;
	private Date statdate;
	private String statdatestr;

	public StatApn(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.statdate = statdate;
		this.statdatestr = df.format(statdate);
	}

	private void insert(Map<String, TempApnStat> allapns) throws Exception {
		java.util.Iterator<TempApnStat> stats = allapns.values().iterator();

		// String day1 = df.format(main.util.MainStatUtil.getPrevCountDate(1));
		String day1 = statdatestr;
		String day2 = df.format(main.util.MainStatUtil.getPrevCountDate(statdate, 1)); // 上次流量
		String day3 = df.format(main.util.MainStatUtil.getPrevCountDate(statdate, 2)); // 上上次流量

		Statement tempstmt = null;
		List<String> list0 = new ArrayList<String>();
		List<String> sqls = new ArrayList<String>();
		ResultSet rs = null;
		try {

			while (stats.hasNext()) {
				TempApnStat stat = stats.next();
				if (Float.parseFloat(stat.all) > 0) {

					String sql = "insert into stat_apn(apnni,dayflag,stattime,upvolume,downvolume,allvolume,usercount)values('"
							+ stat.apnni
							+ "',1,"
							+ statdatestr
							+ ","
							+ stat.up
							+ ","
							+ stat.down
							+ ","
							+ stat.all
							+ "," + stat.usercount + ")";
					sqls.add(sql);

					/**
					 * 更新总流量
					 */
					String totalsql = "update allvolume_apn set upvolume=upvolume+" + stat.up
							+ ",downvolume=downvolume+" + stat.down + ",allvolume=allvolume+" + stat.all
							+ " where apnni='" + stat.apnni + "'";
					sqls.add(totalsql);
				} else { // 入0流量的apn
					list0.add(stat.apnni);

					String sql0 = "insert into zero_apn(apnni,dayflag,stattime,allvolume,historyvolume,prehistoryvolume)values('"
							+ stat.apnni + "',1," + statdatestr + ",0,0,0)";
					sqls.add(sql0);

				}
			}

			int s = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();

			LOG.info("APN统计数据入库成功：" + s);
			int len = list0.size();
			if (len > 0) {

				tempstmt = con.createStatement();

				// String allsql = "select apnni,sum(allvolume) as allvolume
				// from stat_apn where dayflag=1 and apnni in("
				// + MainStatUtil.list2str(list0) + ") group by apnni";
				// rs = tempstmt.executeQuery(allsql);
				// while (rs.next()) {
				for (int i = 0; i < len; i++) {
					String apnni = list0.get(i).toString();
					// String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_apn set allvolume=" + allapns.get(apnni).allall + " where apnni='"
							+ apnni + "' and stattime=" + day1;
					sqls.add(zero0sql);
				}
				String allsql = "select apnni,sum(allvolume) as allvolume from stat_apn where dayflag=1 and apnni in("
						+ MainStatUtil.list2str(list0) + ") and stattime=" + day2 + " group by apnni";
				// rs.close();
				// rs = null;
				rs = tempstmt.executeQuery(allsql);
				while (rs.next()) {
					String apnni = rs.getString("apnni");
					String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_apn set historyvolume=" + allvolume + " where apnni='" + apnni
							+ "' and stattime=" + day1;
					sqls.add(zero0sql);
				}

				allsql = "select apnni,sum(allvolume) as allvolume from stat_apn where dayflag=1 and apnni in("
						+ MainStatUtil.list2str(list0) + ") and stattime=" + day3 + " group by apnni";
				rs.close();
				rs = null;
				rs = tempstmt.executeQuery(allsql);
				while (rs.next()) {
					String apnni = rs.getString("apnni");
					String allvolume = rs.getString("allvolume");
					String zero0sql = "update zero_apn set prehistoryvolume=" + allvolume + " where apnni='" + apnni
							+ "' and stattime=" + day1;
					sqls.add(zero0sql);
				}
			}

			main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			LOG.info("流量为0APN统计数据入库成功");

			// 这里还要得到流量为0的那些个数据

		} finally {
			if (tempstmt != null)
				tempstmt.close();
			if (rs != null)
				rs.close();
		}
	}

	private void getStatDatas(Map<String, TempApnStat> allapns) throws Exception {
		// String table = MainStatUtil.getCdrTable();
		// String sql = "select apnni,count(distinct(msisdn))as
		// usercount,sum(upvolume) as up,sum(downvolume) as
		// down,sum(upvolume+downvolume) as allvolume from "
		// + table + " group by apnni";

		// long start = MainStatUtil.getYestardayTime();
		// long end = MainStatUtil.getOneDayAfter(start);

		String sql = "select apnni,sum(upvolume) as up,sum(downvolume) as down,sum(allvolume) as allvolume,sum(usercount) as usercount from stat_apn where dayflag=0 and stattime>="
				+ start / 1000 + " and stattime<=" + end / 1000 + " group by apnni";

		LOG.info("sql:" + sql);
		Statement stmt = null;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {

			TempApnStat stat = null;
			String apnni = rs.getString("apnni");
			apnni = apnni.toLowerCase();
			if (allapns.containsKey(apnni)) {
				// LOG.info("得到的APN统计数据:" + apnni);
				stat = allapns.get(apnni);
				stat.apnni = rs.getString("apnni").toLowerCase();

				// int usercount = rs.getInt("usercount");
				// if (usercount < 10)
				// stat.usercount = usercount + "";
				// else
				// stat.usercount = "" + rs.getInt("usercount") / 8;
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
			} else { // 要把这些个apnni插到set_apnni里面
				int orderby = 199;
				if (apnni.indexOf(".cq") != -1) {
					orderby = 200;
				} else if (apnni.indexOf(".") != -1) {
					orderby = 5000;
				} else
					orderby = 50000;
//sbswjp.wapvsmms.softbank.ne.jpkn
		
				String inssql = "insert into set_apn(apnni,apnname,apnconector,updatetime,opttype,isactive,orderby)values('"
						+ apnni
						+ "','"
						+ apnni
						+ "','',"
						+ System.currentTimeMillis()
						/ 1000
						+ ",0,1,"
						+ orderby + ")";

				stat = new TempApnStat();
				stat.apnni = rs.getString("apnni");
				stat.up = rs.getString("up");
				stat.down = rs.getString("down");
				stat.all = rs.getString("allvolume");
				stat.allall = "0";

				LOG.info("把" + apnni + "插入到set_apn中..." + inssql);
				allapns.put(apnni, stat);
				// 插入了1条数据到set_apn中
				if(apnni.indexOf("sbswjp.wapvsmms.softbank.ne")!=-1){
				   LOG.info("该APN不再入库了:"+apnni);
				}else{
					main.util.MainStatUtil.executeSql(con, inssql);
				}
			}
		}
		rs.close();
		stmt.close();
		LOG.info("得到APN的流量数据完毕");
		// 这里要得到用户数
		// String usersql = "select apnni,usercount from msisdn_apn where
		// stattime>=" + start / 1000 + " and stattime<="
		// + end / 1000;
		// // + " group by apnni";
		// LOG.info("usersql:" + usersql);
		// stmt = con.createStatement();
		// rs = stmt.executeQuery(usersql);
		// while (rs.next()) {
		// String apnni = rs.getString("apnni");
		// int usercount = rs.getInt("usercount");
		// TempApnStat stat = allapns.get(apnni);
		// if (stat != null)
		// stat.usercount = usercount + "";
		// }
		// rs.close();
		// stmt.close();
		//
		// LOG.info("得到APN的用户数据完毕");
	}

	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {
		// long start = MainStatUtil.getYestardayTime();
		// long end = MainStatUtil.getOneDayAfter(start);

		// try {
		// 得到所有的sgsn
		Map<String, TempApnStat> allapns = getAllApns();
		// 得到每个sgsn的数据
		getStatDatas(allapns);
		// 插入统计表
		insert(allapns);

	}

	private Map<String, TempApnStat> getAllApns() throws Exception {
		String sql = "select a.apnni,b.allvolume from set_apn a,allvolume_apn b where a.apnni=b.apnni(+) and a.opttype!=2";
		ResultSet rs = null;
		Statement stmt = null;
		Map<String, TempApnStat> list = new HashMap<String, TempApnStat>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TempApnStat stat = new TempApnStat();
				stat.apnni = rs.getString("apnni").toLowerCase();

				if (!(rs.getString("allvolume") == null || rs.getString("allvolume").equals("")))
					stat.allall = rs.getString("allvolume");
				
				list.put(stat.apnni, stat);
			}
			LOG.info("得到所有的APN个数:" + list.size());
			return list;

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	private static SelfLog LOG = SelfLog.getInstance();
}