package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.stat.StatSgsn.TempStat;

import org.apache.commons.logging.Log;

/**
 * 
 */

/**
 * @author 华锋 Dec 2, 2009-12:53:54 AM
 * 
 */
public class StatUserCnt {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(StatUserCnt.class);

	private static long start;
	private static long end;
	private static String stattime = null;
	private static Connection con = null;

	public static void main(String[] args) throws Exception {

		Date date = main.util.MainStatUtil.getPrevDate();
		// Date _date = main.util.MainStatUtil.getPrevCountDate(7);
		start = main.util.MainStatUtil.getDateTime(date);
		end = main.util.MainStatUtil.getOneDayAfter(start);
		stattime = df.format(date);
		// stattime = "20100101";
		con = main.util.DBUtils.getOracleCon();
		daycnt();
		apnusercnt();
		sgsncnt();
		bsccnt();
		cellcnt();
		apncellcnt();

		con.close();

	}

	private static void apnusercnt() {
		String usersql = "select apnni,usercount from msisdn_apn where	 stattime>=" + start / 1000 + " and stattime<="
				+ end / 1000;
		LOG.info(usersql);
		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(usersql);
			while (rs.next()) {
				String apnni = rs.getString("apnni");
				int usercount = rs.getInt("usercount");
				sqls.add("update stat_apn set usercount=" + usercount + " where apnni='" + apnni + "' and stattime="
						+ stattime);
			}

			int len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();

			LOG.info("apn用户统计完毕:" + len);
		} catch (Exception e) {
			LOG.error("apn用户统计:", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}

	private static void bsccnt() {
		String usersql = "select bscid,usercount from msisdn_bsc where stattime>=" + start / 1000 + " and stattime<="
				+ end / 1000;
		LOG.info(usersql);
		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(usersql);
			while (rs.next()) {
				String bscid = rs.getString("bscid");
				int usercount = rs.getInt("usercount");
				sqls.add("update stat_bsc set usercount=" + usercount + " where bscid='" + bscid + "' and stattime="
						+ stattime);
			}

			int len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			LOG.info("bsc用户统计完毕:" + len);
		} catch (Exception e) {
			LOG.error("bsc用户统计:", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}

	private static void cellcnt() {
		String usersql = "select CELLID,lac, usercount from msisdn_CELLID where stattime>=" + start / 1000
				+ " and stattime<=" + end / 1000;
		LOG.info(usersql);
		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(usersql);
			while (rs.next()) {
				int cellid = rs.getInt("CELLID");
				int lac = rs.getInt("lac");
				int usercount = rs.getInt("usercount");
				sqls.add("update stat_cellid_day set usercount=" + usercount + " where cellid='" + cellid
						+ "' and lac='" + lac + "' and stattime=" + stattime);
			}

			int len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			LOG.info("cell用户统计完毕:" + len);
		} catch (Exception e) {
			LOG.error("cell用户统计:", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}

	private static void apncellcnt() {
		String usersql = "select apnni,cellid,lac,usercount from msisdn_cellid_apn where stattime between " + start
				/ 1000 + " and " + end / 1000;
		LOG.info(usersql);
		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(usersql);
			while (rs.next()) {
				int cellid = rs.getInt("cellid");
				String apnni = rs.getString("apnni");
				int lac = rs.getInt("lac");
				int usercount = rs.getInt("usercount");
				// char a = apnni.charAt(0);
				// if(a<='Z'&&a>='A')
				sqls.add("update stat_cellid_apn_day set usercount=" + usercount + " where cellid=" + cellid
						+ " and lac=" + lac + " and apnni='" + apnni + "' and stattime=" + stattime);

			}

			int len = main.util.MainStatUtil.executeSql(con, sqls);
			LOG.info("cell_apn用户统计完毕:" + len);
			sqls.clear();
		} catch (Exception e) {
			LOG.error("cell_apn用户统计:", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}

	private static void sgsncnt() {
		String usersql = "select sgsnid,nettype,usercount,ggsnid,apnni from msisdn_sgsn where stattime>=" + start
				/ 1000 + " and stattime<=" + end / 1000;
		LOG.info(usersql);

		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(usersql);
			Map<String, Integer> map = new HashMap<String, Integer>();
			while (rs.next()) {

				String sgsnid = rs.getString("sgsnid");
				String nettype = rs.getString("nettype");
				String ggsnid = rs.getString("ggsnid");
				String apnni = rs.getString("apnni");
				int usercount = rs.getInt("usercount");

				String ggsnkey = sgsnid + "," + nettype + "," + apnni;
				if (ggsnid.indexOf("GGSN") == -1) {
					int _cnt = 0;
					if (map.containsKey(ggsnkey)) {
						_cnt = map.get(ggsnkey);
						map.remove(ggsnkey);
					}
					_cnt += usercount;
					map.put(ggsnkey, _cnt);
				}
				String sql = "update stat_sgsn set usercount=" + usercount + " where sgsnid='" + sgsnid
						+ "' and nettype=" + nettype + " and ggsnid='" + ggsnid + "' and apnni='" + apnni
						+ "' and stattime=" + stattime;

				System.out.println(sql);

				sqls.add(sql);

			}
//			System.out.println(map);
			int len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();

			LOG.info("sgsn用户统计完毕:" + len);

			java.util.Iterator<String> ggsnotherstats = map.keySet().iterator();
			while (ggsnotherstats.hasNext()) {
				String key = ggsnotherstats.next();
				int usercount = map.get(key);
				// String[] ggsnkey=sgsnid+","+nettype+","+apnni;
				String[] keys = key.split(",");
				String sgsnid = keys[0];
				String nettype = keys[1];
				String apnni = keys[2];

				String sql = "update stat_sgsn set usercount=" + usercount + " where sgsnid='" + sgsnid
						+ "' and nettype=" + nettype + " and ggsnid='GGSN其他' and apnni='" + apnni + "' and stattime="
						+ stattime;

				System.out.println(sql);
				sqls.add(sql);

			}

			len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			// 要统计ggsn其他的用户量
			LOG.info("GGSN其他用户统计完毕:" + len);

		} catch (Exception e) {
			LOG.error("sgsn用户统计", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}

	private static void daycnt() {
		String dayusersql = "select usercount,nettype from msisdn_dayall where stattime>=" + start / 1000
				+ " and stattime<=" + end / 1000;
		LOG.info(dayusersql);

		Statement stmt = null;
		ResultSet rs = null;
		List<String> sqls = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(dayusersql);
			while (rs.next()) {

				String nettype = rs.getString("nettype");
				int usercount = rs.getInt("usercount");
				sqls.add("update stat_allday set usercount=" + usercount + " where nettype=" + nettype
						+ " and stattime=" + stattime);

			}

			int len = main.util.MainStatUtil.executeSql(con, sqls);
			sqls.clear();
			LOG.info("每天用户统计完毕:" + len);
		} catch (Exception e) {
			LOG.error("每天用户统计:", e);

		} finally {

			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}
}
