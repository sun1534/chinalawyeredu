/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.util.DBUtils;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * 
 * 按天统计出错情况
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatApnError {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");


	private Connection con;

	public StatApnError(Connection con) {
		this.con = con;
	}

	

	/**
	 * 
	 */
	public void stat() throws Exception {
		long yetime=MainStatUtil.getYestardayTime();
		long yeendtime=MainStatUtil.getOneDayAfter(yetime);
		String sql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),to_char(sysdate-1,'yyyyMMdd'),1,errcode from cdr_mistake where reqapnni is not null and opentime between "+yetime/1000+" and "+yeendtime/1000+" group by reqapnni,errcode";
		String nullsql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),to_char(sysdate-1,'yyyyMMdd'),1,errcode from cdr_mistake where reqapnni is null or reqapnni='' and opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode";
		
		List<String> sqls=new ArrayList<String>();
		sqls.add(sql);
		sqls.add(nullsql);
		
		MainStatUtil.executeSql(con, sqls);
	}
	public static void main(String args[]) throws Exception {
		
		Date date=MainStatUtil.getPrevDate();
		
		
		System.out.println(date.toLocaleString());
		
		System.out.println(date.getTime()/1000);
		
		SelfLog.LOGDIR = "/export/home/jf/JAVABIN/logs/stat";
		LOG = SelfLog.getInstance();
		LOG.info("开始统计STAT_APN_ERROR数据");
		Connection con = DBUtils.getOracleCon();
		LOG.info("建立数据库连接成功");
		StatApnError s = new StatApnError(con);
		s.stat();
		con.close();
	}

	private static SelfLog LOG = SelfLog.getInstance();
}