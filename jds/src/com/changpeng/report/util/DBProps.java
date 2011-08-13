package com.changpeng.report.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;


/**
 * 获取数据库连接，并设置每次从数据库中读取的最大数据
 * @author sinhoo
 *
 */
public class DBProps {
	private final static Logger log = Logger.getLogger(DBProps.class);

	private String driver;

	private String url;

	private String user;

	private String pass;


	/**
	 * 每次从库里面取出多少条数据
	 */
	/*public int count;*/

	private DBProps() {
		try {
			init();
		} catch (Exception e) {
			log.error("DBProps():" + e);
		}
	}

	private static DBProps instance = null;

	public static synchronized DBProps getInstance() {
		if (instance == null)
			instance = new DBProps();
		return instance;
	}

	private void init() throws Exception {
	
			driver ="oracle.jdbc.driver.OracleDriver";// properties.getProperty("driver").trim();
			url = "jdbc:oracle:thin:@localhost:1521:ora10";// properties.getProperty("dburl").trim();
			user = "jds";// properties.getProperty("user").trim();
			pass = "jds";//  properties.getProperty("pass").trim();
			
			Class.forName(driver);
	}

	public Connection getConnection() {
		Connection con = null;
		try {	
	        con = DriverManager.getConnection(url, user, pass);     
			//log.debug("connect to the database successfully!");
		} catch (Exception e) {
			log.error("connect to the database error:" + e);
		}
		return con;
	}

	public Connection getConnection(String url,String user,String pass) {
		Connection con = null;
		try {	
	        con = DriverManager.getConnection(url, user, pass);     
			//log.debug("connect to the database successfully!");
		} catch (Exception e) {
			//com.sinhoo.sms.MT2DB.smsSubmit("数据库【"+url+"】连接失败。", com.sinhoo.config.SmsConfig.notifyMobiles);
			log.error("connect to the database error:" + e);
		}
		return con;
	}
	
	public static void closeConnection(Connection con) {
		try {
			if (con != null) 
				con.close();
			con=null;
			log.debug("close the database successfully!");
		} catch (Exception e) {
			log.error("closeConnection():" + e);
		}
	}
	
	public static void main(String args[]){
		DBProps.getInstance().getConnection();
	}
}