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
import main.util.SelfLog;

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

	private static DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	// private static String table;
	private static String LOGDIR = "c:\\bak";
	private static SelfLog LOG;
	private static Connection con;

	private static void init() {
		InputStream in = null;
		try {
			in = ReadErrorApns.class.getResourceAsStream("/main/stat/stat.properties");
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

	private static void insertAllvolumeSets(Connection con) {
		String cellsql = "insert into allvolume_cellid(cellid,upvolume,downvolume,allvolume) select a.cellid,0,0,0 from set_cell a where not exists(select * from allvolume_cellid b where a.cellid= b.cellid )";
		String sgsngsmsql = "insert into allvolume_sgsn(sgsnid,nettype,upvolume,downvolume,allvolume) select a.sgsnid,'gsm',0,0,0 from set_sgsn a where not exists(select * from allvolume_sgsn b where a.sgsnid= b.sgsnid )";
		String sgsntdsql = "insert into allvolume_sgsn(sgsnid,nettype,upvolume,downvolume,allvolume) select a.sgsnid,'td',0,0,0 from set_sgsn a where not exists(select * from allvolume_sgsn b where a.sgsnid= b.sgsnid )";
		String bscsql = "insert into allvolume_bsc(bscid,upvolume,downvolume,allvolume) select distinct(a.bscid),0,0,0 from set_bsc a where not exists(select * from allvolume_bsc b where a.bscid= b.bscid )";
		String apnsql = "insert into allvolume_apn(apnni,upvolume,downvolume,allvolume) select a.apnni,0,0,0 from set_apn a where not exists(select * from allvolume_apn b where a.apnni= b.apnni )";

		try {
			List<String> sqls = new ArrayList<String>();
			sqls.add(cellsql);
			sqls.add(sgsngsmsql);
			sqls.add(sgsntdsql);
			sqls.add(bscsql);
			sqls.add(apnsql);

			main.util.MainStatUtil.executeSql(con, sqls);
			LOG.info("各配置参数入库成功");
		} catch (Exception e) {
			LOG.error("各配置参数入库失败", e);
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

		insertAllvolumeSets(con);

		stat_cellid();
		
		stat_apn();
//	
		stat_bsc();

		stat_sgsn();

		statapnerror();

		stat_cell_apn();

		stat_mobile_apn();
		con.close();

		LOG.info("==============统计完毕" + df.format(new Date()));

	}

	private static void statapnerror() {
		try {
			StatApnError stat = new StatApnError(con);
			LOG.info("开始统计APN错误情况数据");
			stat.statday();
			LOG.info("统计APN错误情况数据完毕");
		} catch (Exception e) {
			LOG.error("APN错误情况统计异常", e);
			e.printStackTrace();
		}

	}

	private static void stat_bsc() {
		try {
			StatBsc stat = new StatBsc(con);
			LOG.info("开始统计BSC数据");
			stat.stat();
			LOG.info("统计BSC数据完毕");
		} catch (Exception e) {
			LOG.error("BSC数据统计异常", e);
			e.printStackTrace();
		}

	}

	private static void stat_apn() {
		try {
			StatApn stat = new StatApn(con);
			LOG.info("开始统计APN数据");
			stat.stat();
			LOG.info("统计APN数据完毕");
		} catch (Exception e) {
			LOG.error("APN数据统计异常", e);
			e.printStackTrace();
		}
		// try {
		// String apn_sql = "insert into stat_apn select
		// apnni,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume)
		// from "
		// + table + " group by apnni";
		// Statement stmt = con.createStatement();
		// stmt.execute(apn_sql);
		// closeResource(null, stmt, null);
		// } catch (Exception e) {
		// LOG.info("stat_apn error"+e);
		//
		// }
	}

	private static void stat_cellid() {
		try {
			StatCell stat = new StatCell(con);
			LOG.info("开始统计小区数据");
			stat.stat();
			LOG.info("统计小区数据完毕");
		} catch (Exception e) {
			LOG.error("小区数据统计异常", e);
			e.printStackTrace();
		}
		// try {
		// String cell_sql = "insert into stat_cellid select
		// cellid,null,null,null,nettype,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume)
		// from "
		// + table + " group by cellid,nettype";
		// Statement stmt = con.createStatement();
		// stmt.execute(cell_sql);
		// closeResource(null, stmt, null);
		// } catch (Exception e) {
		// LOG.info("stat_cellid error"+e);
		//
		// }
	}

	/**
	 * 这里要处理每天为0的情况
	 */
	private static void stat_sgsn() {
		try {
			StatSgsn stat = new StatSgsn(con);
			LOG.info("开始统计SGSN数据");
			stat.stat();
			LOG.info("统计SGSN数据完毕");
		} catch (Exception e) {
			LOG.error("SGSN数据统计异常", e);
			e.printStackTrace();
		}
		// try {
		// String sgsn_sql = "insert into stat_sgsn select
		// sgsnid,nettype,to_char(sysdate-1,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume)
		// from "
		// + table + " group by sgsnid,nettype";
		// Statement stmt = con.createStatement();
		// stmt.execute(sgsn_sql);
		// closeResource(null, stmt, null);
		// } catch (Exception e) {
		// LOG.info("stat_sgsn error"+e);
		// }
	}

	/**
	 * 这里要处理每天为0的情况
	 */
	private static void stat_cell_apn() {
		try {
			StatCellApn stat = new StatCellApn(con);
			LOG.info("开始统计CELL_APN数据");
			stat.stat();
			LOG.info("统计CELL_APN数据完毕");
		} catch (Exception e) {
			LOG.error("CELL_APN数据统计异常", e);
			e.printStackTrace();
		}
	}

	/**
	 * 这里要处理每天为0的情况
	 */
	private static void stat_mobile_apn() {
		try {
			StatMobileApn stat = new StatMobileApn(con);
			LOG.info("开始统计MOBILE_APN数据");
			stat.stat();
			LOG.info("统计MOBILE_APN数据完毕");
		} catch (Exception e) {
			LOG.error("MOBILE_APN数据统计异常", e);
			e.printStackTrace();
		}
	}
}