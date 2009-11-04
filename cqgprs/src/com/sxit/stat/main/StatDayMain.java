package com.sxit.stat.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */

/**
 * 
 * 按天统计的main程序 主要是stat_cellid,stat_apn,stat_sgsn等的入库,都要分2，3g
 * 
 * @author 华锋 Oct 29, 2009-11:02:38 AM
 * 
 */
public class StatDayMain {

	private static String url = "jdbc:oracle:thin:@localhost:1521:ora92";
	private static String user = "jf_gprs";
	private static String pass = "jf_gprs";

	private static Connection con;
	private static String table;

	private static void initCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("连接初始异常"+e);
		}
	}

	private static void closeResource(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initCon();
		if (con == null) {
			System.out.println("获取的数据库连接为空,退出Exit");
			System.exit(1);
		}
		System.out.println("获取到了数据库连接OKOK...");
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		if(weekday==1)//周日
			weekday=weekday-1;
		else
			weekday=weekday-2;
		table = "cdr_succ_0" + weekday;
//		System.out.println("table:::"+ calendar.get(Calendar.DAY_OF_WEEK) );
//		System.out.println("table:::"+table);
		
		long begin = System.currentTimeMillis();
		long now = System.currentTimeMillis();

		System.out.println("开始执行统计程序："+table);
		stat_cellid();
		now = System.currentTimeMillis();
		System.out.println("小区编号统计:" + (now - begin));
		begin = System.currentTimeMillis();
		
		
		stat_apn();
		now = System.currentTimeMillis();
		System.out.println("APN统计:" + (now - begin));
		begin = System.currentTimeMillis();
		
		
		stat_bsc();
		now = System.currentTimeMillis();
		System.out.println("BSC统计:" + (now - begin));
		begin = System.currentTimeMillis();
		
		
		stat_sgsn();
		now = System.currentTimeMillis();
		System.out.println("SGSN统计:" + (now - begin));
		begin = System.currentTimeMillis();
		System.out.println("================================================");

	}

	private static void stat_bsc() {

	}

	private static void stat_apn() {
		try {
			String apn_sql = "insert into stat_apn  select apnni,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from "
					+ table + " group by apnni";
			Statement stmt = con.createStatement();
			stmt.execute(apn_sql);
			closeResource(null, stmt, null);
		} catch (Exception e) {
			System.out.println("stat_apn error"+e);

		}
	}

	private static void stat_cellid() {
		try {
			String cell_sql = "insert into stat_cellid select cellid,null,null,null,nettype,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from "
					+ table + " group by cellid,nettype";
			Statement stmt = con.createStatement();
			stmt.execute(cell_sql);
			closeResource(null, stmt, null);
		} catch (Exception e) {
			System.out.println("stat_cellid error"+e);

		}
	}

	private static void stat_sgsn() {
		try {
			String sgsn_sql = "insert into stat_sgsn select sgsnid,nettype,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from "
					+ table + " group by sgsnid,nettype";
			Statement stmt = con.createStatement();
			stmt.execute(sgsn_sql);
			closeResource(null, stmt, null);
		} catch (Exception e) {
			System.out.println("stat_sgsn error"+e);
		}
	}
}