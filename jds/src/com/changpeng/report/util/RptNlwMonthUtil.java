package com.changpeng.report.util;
import java.util.*;
import java.sql.SQLException;
import java.text.*;

import org.apache.log4j.Logger;
/**
 * 非诉月度回款统计
 * @author sinhoo
 * 2010-1-6
 */
public class RptNlwMonthUtil {
	protected static final Logger LOG = Logger.getLogger(RptNlwMonthUtil.class);
	
	private String rptmonth; //yyyy-mm 统计月份
	private String lastmonth;  //上月
	private JdbcTemplate jdbc;
	public RptNlwMonthUtil(){
		//Date now=new Date();
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		this.rptmonth=sdf.format(c.getTime());
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		this.lastmonth=sdf.format(c.getTime());
		jdbc=new JdbcTemplate();
	}
	public RptNlwMonthUtil(String rptmonth){
		this.rptmonth=rptmonth;
		jdbc=new JdbcTemplate();
	}
	
	public void updateUsers() throws SQLException{
		jdbc.execute("delete from trpt_nlwmonth where rptmonth='"+rptmonth+"'"); //删除当月已有统计
		List<HashMap<String,Object>> list=jdbc.query("select b.bankid,b.bankname,a.userid from tnlw_nonlawtask a,tnlw_nonlaw b where a.nonlawid=b.nonlawid group by bankid,bankname,userid order by bankid");
		if(list!=null&&list.size()>0){
			for(HashMap<String,Object> map:list){
				Object bankid=map.get("bankid");
				Object bankname=map.get("bankname");
				Object userid=map.get("userid");
				updateUser(bankid,bankname,userid);
			}
		}
	}
	
	private void updateUser(Object bankid,Object bankname,Object userid) throws SQLException{
		//总
		Object ausers=jdbc.queryOne("select count(*) from tnlw_nonlaw a,tnlw_nonlawtask b where a.nonlawid=b.nonlawid and a.bankid="+bankid+" and a.bankname='"+bankname+"' and b.userid="+userid);
		if(ausers==null) ausers=0;
		//还款户数
		Object pusers=jdbc.queryOne("select count(*) from tnlw_nonlaw a,tnlw_nonlawtask b where a.nonlawid=b.nonlawid and a.bankid="+bankid+" and a.bankname='"+bankname+"' and b.userid="+userid+" and a.refee is not null and a.refee<>0");
		if(pusers==null) pusers=0;
		//结案
		Object eusers=jdbc.queryOne("select count(*) from tnlw_nonlaw a,tnlw_nonlawtask b where a.nonlawid=b.nonlawid and a.bankid="+bankid+" and a.bankname='"+bankname+"' and b.userid="+userid+" and a.repaystatus=2");
		if(eusers==null) eusers=0;
		int rusers=Integer.parseInt(ausers+"")-Integer.parseInt(eusers+"");
		
		
		//本月任务
		//先获取到上月还剩下多少.
		Object refee=jdbc.queryOne("select (allfee-allpfee) refee from trpt_nlwmonth where bankid="+bankid+" and bankname='"+bankname+"' and taskuser="+userid+" and rptmonth='"+lastmonth+"'");
		//当月新增任务
		Object curfee=jdbc.queryOne("select sum(OVERFEE+ACCRUALFEE+CASTFEE) curfee from tnlw_nonlaw a,tnlw_nonlawtask b where a.nonlawid=b.nonlawid and a.bankid="+bankid+" and a.bankname='"+bankname+"' and b.userid="+userid);
		if(refee==null) refee=0;
		if(curfee==null) curfee=0;		
		double allfee=Double.parseDouble(refee+"")+Double.parseDouble(curfee+"");
		//当月回款总额
		Object allpfee=jdbc.queryOne("select sum(refee) from tnlw_nonlaw a,tnlw_nonlawtask b where a.nonlawid=b.nonlawid and a.bankid="+bankid+" and a.bankname='"+bankname+"' and b.userid="+userid);
		if(allpfee==null) allpfee=0;
		
		//回款率
		double rate=0;
		if(allfee!=0)
			rate=Double.parseDouble(allpfee+"")/allfee;
		
		jdbc.execute("insert into trpt_nlwmonth (nlwmonthid,bankid,bankname,taskuser,ausers,pusers,eusers,rusers,allfee,allpfee,ratefee,rptmonth) values (trptnlwmonthid.nextval," +
				""+bankid+",'"+bankname+"',"+userid+","+ausers+","+pusers+","+eusers+","+rusers+","+allfee+","+allpfee+","+rate+",'"+rptmonth+"')");
	}
	
	public static void main(String args[]){
		RptNlwMonthUtil r=new RptNlwMonthUtil();
		try {
			r.updateUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
