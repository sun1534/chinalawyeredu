/**
 * 
 */
package com.changpeng.common.sync;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 Jul 10, 2009-2:46:02 PM
 * 
 */
public class DBUtils {
	private static Log LOG = LogFactory.getLog(DBUtils.class);
//	private static String oracle_url = "jdbc:oracle:thin:@192.168.0.4:1521:orcl";
	private static String oracle_url = "jdbc:oracle:thin:@changpeng.3322.org:1521:orcl";
	private static String oracle_username = "gxlg";
	private static String oracle_password = "gxlg";

	private static String website_url = "jdbc:mysql://211.154.157.174:3306/lxguangxi?characterEncoding=utf-8";
	private static String website_username = "lxguangxi";
	private static String website_password = "lxguangxi";

	private static String oa_url = "jdbc:mysql://211.154.157.174:3306/oa?characterEncoding=utf-8";
	private static String oa_username = "root";
	private static String oa_password = "password";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			LOG.error("数据库驱动加载失败::" + e);
		}
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			LOG.error("关闭连接失败:" + con);
		}
	}

	public static Connection getOracleCon() {
		try {
			Connection con = DriverManager.getConnection(oracle_url, oracle_username, oracle_password);
			return con;
		} catch (Exception e) {
			LOG.error("getOracleCon::" + e);
			return null;
		}
	}

	public static Connection getOACon() {
		try {
			Connection con = DriverManager.getConnection(oa_url, oa_username, oa_password);
			return con;
		} catch (Exception e) {
			LOG.error("getOACon::" + e);
			return null;
		}
	}

	public static Connection getWebsiteCon() {
		try {
			Connection con = DriverManager.getConnection(website_url, website_username, website_password);
			return con;
		} catch (Exception e) {
			LOG.error("getWebsiteCon::" + e);
			return null;
		}
	}
}
