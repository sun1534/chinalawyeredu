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

	private static final DateFormat dfhh = new java.text.SimpleDateFormat("yyyyMMddHH");
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	private Connection con;
	private long start;
	private long end;
	private String stattime;
	private Date statdate;
	public StatApnError(Connection con,Date statdate) {
		this.con = con;
		this.statdate=statdate;
	}
	
	public  void stathour() throws Exception{

		
		this.start=main.util.MainStatUtil.getDateHourTime(statdate);
		this.end=this.start+60*60*1000;
		this.stattime=dfhh.format(statdate);
	
		int prehourstart=(int)(start/1000);
		int prehourend=(int)(end/1000);

		
		String errcodesql="insert into STAT_ERRCODE_ERROR (errcode,errcount,usercount,stattime,dayflag) select errcode,count(errcode),count(distinct(imsi)) ,"+stattime+",0 from cdr_mistake where opentime between "+prehourstart+" and "+prehourend+" group by errcode";
		String errcodeno33sql="insert into stat_imsi_errcode_error(imsi,msisdn,errcode,errcount,dayflag,stattime) select imsi,msisdn,errcode,count(*),0,"+stattime+" from cdr_mistake_no33 where  opentime between "+prehourstart+" and "+prehourend+" group by errcode,imsi,msisdn";
		String apnimsisql="insert into stat_imsi_apn_error(imsi,reqapnni,errcount,dayflag,stattime) select imsi,reqapnni,count(*),0,"+stattime+" from cdr_mistake where  opentime between "+prehourstart+" and "+prehourend+" group by reqapnni,imsi";

		String sql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),"+stattime+",0,errcode from cdr_mistake where reqapnni is not null and (opentime between "+prehourstart+" and "+prehourend+") group by reqapnni,errcode";
		String nullsql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),"+stattime+",0,errcode from cdr_mistake where (reqapnni is null or reqapnni='') and (opentime between "+prehourstart+" and "+prehourend+") group by errcode";
		LOG.info("sql::"+sql);
		LOG.info("nullsql::"+nullsql);
		LOG.info("errcodesql::"+errcodesql);
		LOG.info("errcodeno33sql::"+errcodeno33sql);
		LOG.info("apnimsisql::"+apnimsisql);
		List<String> sqls = new ArrayList<String>();
		sqls.add(errcodesql);
		sqls.add(sql);
		sqls.add(nullsql);
		sqls.add(errcodeno33sql);
		sqls.add(apnimsisql);

			main.util.MainStatUtil.executeSql(con, sqls);


	}

	/**
	 * 
	 */
	public void statday() throws Exception {

		
		this.start=main.util.MainStatUtil.getDateTime(statdate);
		this.end=main.util.MainStatUtil.getOneDayAfter(start);
		this.stattime=df.format(statdate);
		long yetime=start;
		long yeendtime=end;
		
		//明细记录情况
		String sql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),"+stattime+",1,errcode from cdr_mistake where reqapnni is not null and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by reqapnni,errcode";
		String nullsql="insert into stat_apn_error(reqapnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),"+stattime+",1,errcode from cdr_mistake where (reqapnni is null or reqapnni='') and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by errcode";
	
		String errcodesql="insert into STAT_ERRCODE_ERROR (errcode,errcount,usercount,stattime,dayflag) select errcode,count(errcode),count(distinct(imsi)) ,"+stattime+",1 from cdr_mistake where opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode";
		String errcodeno33sql="insert into stat_imsi_errcode_error(imsi,msisdn,errcode,errcount,dayflag,stattime) select imsi,msisdn,errcode,count(*),1,"+stattime+" from cdr_mistake_no33 where  opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode,imsi,msisdn";
		String apnimsisql="insert into stat_imsi_apn_error(imsi,reqapnni,errcount,dayflag,stattime) select imsi,reqapnni,count(*),1,"+stattime+" from cdr_mistake where  opentime between "+yetime/1000+" and "+yeendtime/1000+" group by reqapnni,imsi";

		LOG.info("sql::"+sql);
		LOG.info("nullsql::"+nullsql);
		LOG.info("errcodesql::"+errcodesql);
		LOG.info("errcodeno33sql::"+errcodeno33sql);
		LOG.info("apnimsisql::"+apnimsisql);
		List<String> sqls=new ArrayList<String>();
		sqls.add(sql);
		sqls.add(nullsql);
		sqls.add(errcodesql);
		sqls.add(errcodeno33sql);
		sqls.add(apnimsisql);
		MainStatUtil.executeSql(con, sqls);
	}
	
	public static void main(String args[]) throws Exception {

	}

	private static SelfLog LOG = SelfLog.getInstance();
}