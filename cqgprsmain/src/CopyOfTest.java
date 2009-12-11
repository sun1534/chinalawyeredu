import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.util.DBUtils;

/** 
 * 
 */

/**
 * @author 华锋 Oct 10, 2009 2:41:59 PM
 * 
 * 
 * 
 */
public class CopyOfTest {
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static byte[] long2bytes(long i) {
		byte mybytes[] = new byte[8];
		mybytes[7] = (byte) (int) ((long) 255 & i);
		mybytes[6] = (byte) (int) (((long) 65280 & i) >> 8);
		mybytes[5] = (byte) (int) (((long) 0xff0000 & i) >> 16);
		mybytes[4] = (byte) (int) (((long) 0xff000000 & i) >> 24);
		int high = (int) (i >> 32);
		mybytes[3] = (byte) (0xff & high);
		mybytes[2] = (byte) ((0xff00 & high) >> 8);
		mybytes[1] = (byte) ((0xff0000 & high) >> 16);
		mybytes[0] = (byte) ((0xff000000 & high) >> 24);
		return mybytes;
	}
	private static Connection con = null;

	public static void main(String[] args) throws Exception {
		long s=3510073023L;
byte[] b=long2bytes(9999999999L);
for(byte bb:b){
	System.out.print(bb+" ");
}
	
//		System.out.println(dfsec.parse("2009-12-01 00:00:00").getTime()/1000);
//		System.out.println(dfsec.parse("2009-12-02 00:00:00").getTime()/1000);
		
		long no1w =1259852400L * 1000;
		System.out.println(new java.sql.Timestamp(no1w));
		
//		 no1w =1259673240L * 1000;
//			System.out.println(new java.sql.Timestamp(no1w));
//		
//		Date daysagodate = main.util.MainStatUtil.getPrevCountDate(8);
//		long daystart = daysagodate.getTime() / 1000;
//		System.out.println("=========" + daystart);
		//		
		// // 更新16，15，14，13，12，11，10这几天的stat_**的dayflag=1的数据
		// DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		//	
		// int now=20091115;
		// for(int i=20091115;i<=20091119;i++){
		// Date date=df.parse(i+"");
		// long start = main.util.MainStatUtil.getDateTime(date)/1000;
		// long end = start + 24 * 60 * 60;
		// String apnimsisql="insert into
		// stat_imsi_apn_error(imsi,apnni,errcount,dayflag,stattime) select
		// imsi,reqapnni,count(*),1,"+i+" from cdr_mistake where opentime
		// between "+start+" and "+end+" group by reqapnni,imsi;";
		// System.out.println(apnimsisql);
		//			
		// }
		//		

		// Date date = new Date();
		//
		// Date yydate = main.util.MainStatUtil.getPrevCountHour(date, 1);
		// System.out.println(dfsec.format(yydate));
//Connection con=null;
//		try {
//			con=DBUtils.getOracleCon();
//			int opentime = 0;
//			String sql = "select min(opentime) as opentime from cdr_mistake";
//			Statement stmt = con.createStatement();
//			int deletetime = 1258820078;
//			// int deletetime = Integer.parseInt(hourstattime);
//			while (opentime <= deletetime) {
//				ResultSet rs = stmt.executeQuery(sql);
//				rs.next();
//				opentime = rs.getInt("opentime");
//				rs.close();
//				main.util.MainStatUtil.executeSql(con, "delete from cdr_mistake where opentime<=" + (opentime + 3600));
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		con.close();
//		System.out.println("妈的，我终于删完了。我尻。。。。。。。。");

	}

	/**
	 * @param args
	 */
	public static void main22(String[] args) throws Exception {
		con = main.util.DBUtils.getOracleCon();

		String sql = "select cellid,lac,sum(allvolume) as allvolume,sum(downvolume) as downvolume,sum(upvolume) as upvolume from stat_cellid where dayflag=1 group by cellid,lac";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List list = new ArrayList();
		long now = System.currentTimeMillis();
		int i = 1;
		while (rs.next()) {
			String sql1 = "update allvolume_cellid set upvolume=" + rs.getString("upvolume") + ",downvolume="
					+ rs.getString("downvolume") + ",allvolume=" + rs.getString("allvolume") + " where cellid='"
					+ rs.getString("cellid") + "' and lac='" + rs.getString("lac") + "'";
			// System.out.println(sql1);
			if (i++ % 300 == 0) {
				System.out.println(i + "::::::::::" + (System.currentTimeMillis() - now));
				now = System.currentTimeMillis();
			}

			list.add(sql1);
		}
		System.out.println((System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		main.util.MainStatUtil.executeSql(con, list);

		System.out.println((System.currentTimeMillis() - now));

	}

	/**
	 * @param args
	 */
	public static void main1(String[] args) throws Exception {
		con = main.util.DBUtils.getOracleCon();

		// 更新16，15，14，13，12，11，10这几天的stat_**的dayflag=1的数据
		DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		for (int i = 2; i <= 15; i++) {

			Date date = main.util.MainStatUtil.getPrevCountDate(i);
			long start = main.util.MainStatUtil.getDateTime(date);
			long end = start + 24 * 60 * 60;
			String stattime = df.format(date);
			// sgsn(start, end, stattime);
			// bsc(start, end, stattime);
			// cell(start, end, stattime);

			// apn(start, end, stattime);
			// cellapn(start, end, stattime);
		}

	}

	private static void apn(int start, int end, String stattime) throws Exception {
		String sql = "select apnni,sum(usercount) as usercount from stat_apn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by apnni";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("apnni");
			String usercount = rs.getString("usercount");

			String usql = "update stat_apn set usercount=" + usercount + " where apnni='" + apnni + "' and stattime="
					+ stattime;
			System.out.println(usql + ";");
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));

		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}

		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void cellapn(int start, int end, String stattime) throws Exception {
		String sql = "select apnni,cellid,sum(usercount) as usercount from stat_cellid_apn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by apnni,cellid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String cellid = rs.getString("cellid");
			String apnni = rs.getString("apnni");
			String usercount = rs.getString("usercount");

			String usql = "update stat_cellid_apn set usercount=" + usercount + " where cellid='" + apnni + "',apnni='"
					+ apnni + "' and stattime=" + stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void cell(int start, int end, String stattime) throws Exception {
		String sql = "select cellid,sum(usercount) as usercount from stat_cellid where dayflag=0 and stattime between "
				+ start + " and " + end + " group by cellid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("cellid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_cellid set usercount=" + usercount + " where cellid='" + apnni
					+ "' and stattime=" + stattime;
			// System.out.println(usql);
			list.add(usql);
		}
		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void bsc(int start, int end, String stattime) throws Exception {
		String sql = "select bscid,sum(usercount) as usercount from stat_bsc where dayflag=0 and stattime between "
				+ start + " and " + end + " group by bscid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("bscid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_bsc set usercount=" + usercount + " where bscid='" + apnni + "' and stattime="
					+ stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void sgsn(int start, int end, String stattime) throws Exception {
		String sql = "select sgsnid,sum(usercount) as usercount from stat_sgsn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by sgsnid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("sgsnid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_sgsn set usercount=" + usercount + " where sgsnid='" + apnni + "' and stattime="
					+ stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

}
