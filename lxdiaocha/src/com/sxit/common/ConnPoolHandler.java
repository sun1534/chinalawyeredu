/**
 * ConnPoolHandler.java
 */
package com.sxit.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.changpeng.common.CommonDatas;

/**
 * 
 * @author 华锋 Apr 21, 20109:53:42 PM
 * 
 */
public class ConnPoolHandler {
	public ConnPoolHandler() {
	}

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("加载MSSQL数据库驱动成功...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String url = "jdbc:sqlserver://211.154.139.72:1433;DatabaseName=szlawyers";
	private static String username = "szlawyers";
	private static String password = "szlawyers_0755_%@)";

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("链接到sqlserver失败");
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("system error!");
		}
	}
}
