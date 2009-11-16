/**
 * 
 */
package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import main.readerrors.ReadErrorApns;

import org.apache.commons.logging.Log;

/**
 * @author 华锋 Jul 10, 2009-2:46:02 PM
 * 
 */
public class DBUtils {
//	private static SelfLog LOG = SelfLog.getInstance();
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(DBUtils.class);

	// private static String oracle_url =
	// "jdbc:oracle:thin:@192.168.0.4:1521:orcl";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ora92";
//	private static String url = "jdbc:oracle:thin:@218.201.8.150:1521:ora92";
	private static String user = "jf_gprs";
	private static String pass = "jf_gprs";

	// private static String website_url =
	// "jdbc:mysql://211.154.157.174:3306/lxguangxi?characterEncoding=utf-8";
	// private static String website_username = "root";
	// private static String website_password = "password";
	//
	// private static String oa_url =
	// "jdbc:mysql://211.154.157.174:3306/oa?characterEncoding=utf-8";
	// private static String oa_username = "root";
	// private static String oa_password = "password";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			LOG.error("数据库驱动加载失败::" + e);
		}
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
			con = null;
			LOG.info("成功关闭到数据库的连接");
		} catch (Exception e) {
			LOG.error("关闭连接失败:" + con);
		}
	}

	public static Connection getOracleCon() {
		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			LOG.error("getOracleCon::" + e);
			return null;
		}
	}

	public static void closeResource(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			LOG.error("closeResource::" + e);
		}
	}

	
}