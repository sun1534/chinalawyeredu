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
 * 按天统计的main程序
 * 主要是stat_cellid,stat_apn,stat_sgsn等的入库,都要分2，3g
 * 
 * @author 华锋
 * Oct 29, 2009-11:02:38 AM
 *
 */
public class StatDayMain {

	private static Log _LOG = LogFactory.getLog(StatDayMain.class);
	private static String url="jdbc:oracle:thin:@localhost:1521:ora92";
	private static String user="jf_gprs";
	private static String pass="jf_gprs";
	
	private static Connection initCon(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection(url,user,pass);
		return con;
		}catch(Exception e){
			_LOG.error("连接初始异常",e);
			return null;
		}
	}
	private static void closeResource(ResultSet rs,Statement stmt,Connection con){
		try{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		if(con!=null)con.close();
		}catch(Exception e){
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 Connection con= initCon();
//		 if(con==null){
//			 _LOG.error("获取的数据库连接为空,退出");
//			 System.exit(1);
//		 }
		 Calendar calendar=Calendar.getInstance();
		 int weekday=calendar.get(Calendar.DAY_OF_WEEK)-1;
		 String cdrtable="cdr_succ_0"+weekday;
		 
		 
	
		 

	}
	
	
	private static void stat_sgsn(Connection con,String table){
	
		String sgsn_sql="insert into stat_sgsn select sgsnid,nettype,to_char(sysdate,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from cdr_succ_04 group by sgsnid,nettype";
		String cell_sql="insert into stat_cellid select cellid,null,null,null,nettype,to_char(sysdate,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from cdr_succ_04 group by cellid,nettype";
		String apn_sql="insert into stat_apn  select apnni,to_char(sysdate,'yyyyMMdd'),1,count(distinct(msisdn)),sum(upvolume),sum(downvolume),sum(upvolume+downvolume) from cdr_succ_04 group by apnni";
	}
}