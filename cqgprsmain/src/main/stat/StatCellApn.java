/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.util.SelfLog;

/**
 * 
 * 统计每个apn的对应cell的流量 这里的apn,排除掉cmnet和cmwap
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatCellApn {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	private Connection con;
	private String stattime;
	private long start;
	private long end;

	public StatCellApn(Connection con, Date statdate) {
		this.con = con;
		this.start = main.util.MainStatUtil.getDateTime(statdate);
		this.end = main.util.MainStatUtil.getOneDayAfter(start);
		this.stattime = df.format(statdate);
	}


	/**
	 * 总数的统计等从stat_sgsn的表里拿,用户总数从cdr_succ表里面拿
	 */
	public void stat() throws Exception {

		String sql = "insert into stat_cellid_apn(cellid,apnni,stattime,dayflag,usercount,upvolume,downvolume,allvolume) select "
				+ "cellid,apnni,"
				+ stattime
				+ ",1,0,sum(upvolume) as up,sum(downvolume) as down,sum(upvolume+downvolume) as allvolume from stat_cellid_apn where dayflag=0 and (stattime between "
				+ start / 1000 + " and " + end / 1000 + ") group by apnni,cellid";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} finally {
			if (stmt != null)
				stmt.close();
		}

		//这里要更新stat_cellid_apn里的数据情况
		
		String usersql="select apnni,cellid,usercount from msisdn_cellid_apn where stattime between "+start / 1000+" and "+end / 1000;
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(usersql);
		List<String> sqls=new ArrayList<String>();
		while(rs.next()){
			String cellid=rs.getString("cellid");
			String apnni=rs.getString("apnni");
			int usercount=rs.getInt("usercount");
			sqls.add("update stat_cellid_apn set usercount="+usercount+" where cellid="+cellid+" and apnni='"+apnni+"' and stattime="+stattime);
		}
		
		
		rs.close();
		stmt.close();
		
		main.util.MainStatUtil.executeSql(con, sqls);
		
		
	}

	private static SelfLog LOG = SelfLog.getInstance();
}