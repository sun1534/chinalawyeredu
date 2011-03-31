package com.xjdzjc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * 单例数据库连接
 * 
 * @author Administrator
 * 
 */
public class DbHelper {
	public static Logger log = Logger.getLogger(DbHelper.class);
	private static String user = "root";
//	private static String password = "";
	 private static String password = "JtTechGo3*";
	private static String url = "jdbc:mysql://localhost:3306/base";
	private static DbHelper dbhelper = null;

	/**
	 * 私有的构造方法，保证外部不能实例化，只能由DBManager自己能提供自 己的实例，并且只能有一个。
	 * 根据主机、数据库名称、数据库用户名、数据库用户密码取得连接。
	 * 
	 * @param user
	 *            String
	 * @param password
	 *            String
	 */
	private DbHelper() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			// Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("class not found:" + e.getMessage());
		}
	}

	/**
	 * 
	 * 静态工厂方法，来获得一个DbHelper实例
	 */

	public static DbHelper getInstance() {

		if (dbhelper == null) {
			log.info("取DbHelper实例");
			dbhelper = new DbHelper();
		}
		return dbhelper;

	}

	public static synchronized Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DbHelper.url, DbHelper.user,
					DbHelper.password);
		} catch (SQLException e1) {
			System.err.println("sql exception:" + e1.getMessage());
		}
		return con;
	}


	public void closeCon(Connection con) {
		try {
			if (con != null)
				con.close();
			log.info("----------------数据库关闭连接----------------");

		} catch (Exception e) {
			log.info("----------------数据库关闭失败----------------");
		} finally {
			con = null;
		}
	}
	
	
	public static void closeRsorStmt(ResultSet rs,PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			log.info("----------------数据库关闭结果集失败----------------");
		} finally {
			pstmt=null;
			rs = null;
		}
	}

}
