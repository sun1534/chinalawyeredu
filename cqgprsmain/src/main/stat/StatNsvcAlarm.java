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
 * 每小时统计一次那个gbalarm的情况
 * 如果同一个gbindex,在1个小时之内出现5次，则认为告警
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatNsvcAlarm {

	private static final DateFormat dfhh = new java.text.SimpleDateFormat("yyyyMMddHH");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
private static final int ALARMCOUNT=5;
	private Connection con;
	private long start;
	private long end;
	private String stattime;
	private Date statdate;
	public StatNsvcAlarm(Connection con,Date statdate) {
		this.con = con;
		this.statdate=statdate;
	}
	
	public  void stathour() throws Exception{

		
		this.start=main.util.MainStatUtil.getDateHourTime(statdate);
		this.end=this.start+60*60*1000;
		this.stattime=dfhh.format(statdate);
		
//		String _start=dftime.format(new java.sql.Timestamp(start));
//		String _end=dftime.format(new java.sql.Timestamp(end));
	
		int prehourstart=(int)(start/1000);
		int prehourend=(int)(end/1000);
		
		
		String sql="insert into alarm_nsvc(sgsnid,gbindex,alarmcount,stattime,recordtime) select sgsnid,gbindex,count(*),'"+stattime+"',sysdate from alarm_nsvc_logs where alarmdate between "+prehourstart+" and "+prehourend+" group by sgsnid,gbindex having count(*)>= "+ALARMCOUNT;
		
		LOG.info("sql::"+sql);

	   main.util.MainStatUtil.executeSql(con, sql);

	}

	/**
	 * 
	 */
//	public void statday() throws Exception {
////		long yetime=MainStatUtil.getYestardayTime();
////		long yeendtime=MainStatUtil.getOneDayAfter(yetime);
//		
//		this.start=main.util.MainStatUtil.getDateTime(statdate);
//		this.end=main.util.MainStatUtil.getOneDayAfter(start);
//		this.stattime=df.format(statdate);
//		long yetime=start;
//		long yeendtime=end;
//		
//		//明细记录情况
//		String sql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),"+stattime+",1,errcode from cdr_mistake where reqapnni is not null and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by reqapnni,errcode";
//		String nullsql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),"+stattime+",1,errcode from cdr_mistake where (reqapnni is null or reqapnni='') and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by errcode";
//	
//		String errcodesql="insert into STAT_ERRCODE_ERROR (errcode,errcount,usercount,stattime,dayflag) select errcode,count(errcode),count(distinct(imsi)) ,"+stattime+",1 from cdr_mistake where opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode";
//		String errcodeno33sql="insert into stat_imsi_errcode_error(imsi,msisdn,errcode,errcount,dayflag,stattime) select imsi,msisdn,errcode,count(*),1,"+stattime+" from cdr_mistake_no33 where  opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode,imsi,msisdn";
//		String apnimsisql="insert into stat_imsi_apn_error(imsi,reqapnni,errcount,dayflag,stattime) select imsi,reqapnni,count(*),1,"+stattime+" from cdr_mistake where  opentime between "+yetime/1000+" and "+yeendtime/1000+" group by reqapnni,imsi";
//
//		LOG.info("sql::"+sql);
//		LOG.info("nullsql::"+nullsql);
//		LOG.info("errcodesql::"+errcodesql);
//		LOG.info("errcodeno33sql::"+errcodeno33sql);
//		LOG.info("apnimsisql::"+apnimsisql);
//		List<String> sqls=new ArrayList<String>();
//		sqls.add(sql);
//		sqls.add(nullsql);
//		sqls.add(errcodesql);
//		sqls.add(errcodeno33sql);
//		sqls.add(apnimsisql);
//		MainStatUtil.executeSql(con, sqls);
//	}
//	
	public static void main(String args[]) throws Exception {
//		SelfLog.LOGDIR = "c:/";
//		LOG = SelfLog.getInstance();
//		LOG.info("开始统计STAT_APN_ERROR数据");
//		Connection con = DBUtils.getOracleCon();
//		LOG.info("建立数据库连接成功");
//		StatApnError s = new StatApnError(con);
//		s.stathour();
//		con.close();
	}
//	private static SelfLog LOG =null;
	private static SelfLog LOG = SelfLog.getInstance();
}