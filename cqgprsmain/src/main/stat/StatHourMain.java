package main.stat;

import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import main.readerrors.ReadErrorApns;
import main.util.DBUtils;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * 
 */

/**
 * 
 * 按小时统计，主要是入那个
 * 
 * @author 华锋 Oct 29, 2009-11:02:38 AM
 * 
 */
public class StatHourMain {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHH");

	// private static String table;
	private static String LOGDIR = "c:\\bak";
	private static SelfLog LOG;
	private static Connection con;

	private static void init() {
		InputStream in = null;
		try {
			in = ReadErrorApns.class.getResourceAsStream("/main/stat/stathour.properties");
			Properties prop = new Properties();
			prop.load(in);
			LOGDIR = prop.getProperty("LOGDIR");
		} catch (Exception e) {
			try {
				if (in != null)
					in.close();
			} catch (Exception ee) {
				e.printStackTrace();
			}
		}
	}
	




	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		init();
		SelfLog.LOGDIR = LOGDIR;
		LOG = SelfLog.getInstance();

		LOG.info("==============开始统计" + df.format(new Date()) + "的数据");

		con = DBUtils.getOracleCon();
		LOG.info("获取到数据库连接OK1111");

		StatApnError sae=new StatApnError(con);
		sae.stathour();

		con.close();

		LOG.info("==============统计完毕" + df.format(new Date()));

	}	
}