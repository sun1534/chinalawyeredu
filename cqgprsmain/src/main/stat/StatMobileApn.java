/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import main.stat.StatCell.TempCellStat;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * 
 * 统计每个apn的对应cell的流量
 * 这里的apn,排除掉cmnet和cmwap
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatMobileApn {

	private Connection con;

	public StatMobileApn(Connection con) {
		this.con = con;
	}


	/**
	 * 
	 */
	public void stat() throws Exception{
		String table = MainStatUtil.getMobileApnTable();
		String sql = "insert into "+table+"(mobile,apnni,stattime,dayflag,upvolume,downvolume,allvolume,periodlen) select "
				+ "mobile,apnni,to_char(sysdate-1,'yyyyMMdd'),1,sum(upvolume),sum(downvolume),sum(allvolume),sum(periodlen) from "
				+ table +" group by mobile,apnni";
		
		LOG.info("StatMobileAPN:"+sql);
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);

		} catch (Exception e) {
			LOG.error("统计MOBILE_APN错误：" + e);
			e.printStackTrace();
		}finally{
			if(stmt!=null)
				stmt.close();
		}

		// String usersql = "select sgsnid,nettype,count(distinct(mobile)) from
		// " +
		// table + " group by sgsnid,nettype";

	}

	

	public static void main(String args[]) throws Exception {

		// SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
		// LOG = SelfLog.getInstance();
		// LOG.info("开始统计CELLID数据");
		// Connection con = DBUtils.getOracleCon();
		// LOG.info("建立数据库连接成功");
		// StatCell s = new StatCell(con);
		// s.stat();
		// con.close();

	}

	private static SelfLog LOG=SelfLog.getInstance();
}