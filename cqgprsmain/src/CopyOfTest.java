import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private static Connection con = null;
	public static void main(String[] args) throws Exception {
	

		long no1w=1258855447L*1000;
		System.out.println(new java.sql.Timestamp(no1w));
		
		
		// 更新16，15，14，13，12，11，10这几天的stat_**的dayflag=1的数据
		DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	
		int now=20091115;
		for(int i=20091115;i<=20091119;i++){
			Date date=df.parse(i+"");
			int start = main.util.MainStatUtil.getDateTime(date);
			int end = start + 24 * 60 * 60;
			String apnimsisql="insert into stat_imsi_apn_error(imsi,apnni,errcount,dayflag,stattime) select imsi,reqapnni,count(*),1,"+i+" from cdr_mistake where  opentime between "+start+" and "+end+" group by reqapnni,imsi;";
System.out.println(apnimsisql);
			
		}
		


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
			int start = main.util.MainStatUtil.getDateTime(date);
			int end = start + 24 * 60 * 60;
			String stattime = df.format(date);
			sgsn(start, end, stattime);
			bsc(start, end, stattime);
//			cell(start, end, stattime);
		
		
//			apn(start, end, stattime);
//			cellapn(start, end, stattime);
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
			 System.out.println(usql+";");
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		
		now = System.currentTimeMillis();
		try{
		
			main.util.MainStatUtil.executeSql(con, list);
		
		}catch(Exception e){
			System.out.println("ERROR::::"+e);
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
		try{
		
			main.util.MainStatUtil.executeSql(con, list);
		
		}catch(Exception e){
			System.out.println("ERROR::::"+e);
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
		try{
		
			main.util.MainStatUtil.executeSql(con, list);
		
		}catch(Exception e){
			System.out.println("ERROR::::"+e);
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
		try{
		
			main.util.MainStatUtil.executeSql(con, list);
		
		}catch(Exception e){
			System.out.println("ERROR::::"+e);
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
		try{
		
			main.util.MainStatUtil.executeSql(con, list);
		
		}catch(Exception e){
			System.out.println("ERROR::::"+e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

}