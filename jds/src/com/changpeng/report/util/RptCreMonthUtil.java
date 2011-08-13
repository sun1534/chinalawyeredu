package com.changpeng.report.util;
import java.util.*;
import java.sql.SQLException;
import java.text.*;

import org.apache.log4j.Logger;
/**
 * 信用月度回款统计
 * @author sinhoo
 * 2010-1-6
 */
public class RptCreMonthUtil {
	protected static final Logger LOG = Logger.getLogger(RptCreMonthUtil.class);
	
	private String rptmonth; //yyyy-mm 统计月份
	private String lastmonth;  //上月
	private JdbcTemplate jdbc;
	public RptCreMonthUtil(){
		//Date now=new Date();
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		this.rptmonth=sdf.format(c.getTime());
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		this.lastmonth=sdf.format(c.getTime());
		jdbc=new JdbcTemplate();
	}
	public RptCreMonthUtil(String rptmonth){
		this.rptmonth=rptmonth;
		jdbc=new JdbcTemplate();
	}
	
	private void fetchUsers() throws SQLException{
		jdbc.execute("delete from trpt_cremonth where rptmonth='"+rptmonth+"'"); //删除当月已有统计
		
		jdbc.execute("update topr_creditcard set consigntype=1 where cnfee>=3000 and cnfee<5000");
		jdbc.execute("update topr_creditcard set consigntype=4 where cnfee<3000 or cnfee is null");
		jdbc.execute("update topr_creditcard set consigntype=3 where cnfee>=5000 and cnfee<10000");
		jdbc.execute("update topr_creditcard set consigntype=2 where cnfee>=10000");
		
		//roleid:1 信用卡催收员  userid>2 摒除管理员及测试员账号
		String sql="select b.userid from tsys_user_role a,tsys_user b where a.userid=b.userid and a.roleid=1 and b.statusid=1 and b.userid>2";
		List<HashMap<String,Object>> list=jdbc.query(sql);
		List<String> sqlList=new ArrayList<String>();
		for(HashMap<String,Object> map:list){
			Object userid=map.get("userid");
			
			sqlList.add("insert into trpt_cremonth (CREMONTHID,userid,consigntype,rptmonth) values (CREMONTHID.nextval,"+userid+",1,'"+rptmonth+"')");
			sqlList.add("insert into trpt_cremonth (CREMONTHID,userid,consigntype,rptmonth) values (CREMONTHID.nextval,"+userid+",2,'"+rptmonth+"')");
			sqlList.add("insert into trpt_cremonth (CREMONTHID,userid,consigntype,rptmonth) values (CREMONTHID.nextval,"+userid+",3,'"+rptmonth+"')");
			sqlList.add("insert into trpt_cremonth (CREMONTHID,userid,consigntype,rptmonth) values (CREMONTHID.nextval,"+userid+",4,'"+rptmonth+"')");			
		}
		jdbc.executeBatch(sqlList.toArray());
		
	
		/*list=jdbc.query("select cremonthid,userid,consigntype from trpt_cremonth where rptmonth='"+rptmonth+"'");
		for(HashMap<String,Object> map:list){
			Object userid=map.get("userid");
			Object consigntype=map.get("consigntype");
			Object cremonthid=map.get("cremonthid");
			//月存量 部分还款
			//Object _retask=jdbc.queryMap("select sum(cnfee) from topr_creditcard a, topr_credittask b where to_char(b.createtime,'yyyy-mm')<'"+rptmonth+"' and b.userid="+userid+" and a.consigntype="+consigntype+" and a.creditcardid=b.creditcardid and a.REPAYSTATUS=1");			
			
			Object _retask=jdbc.queryMap("select nexttask from trpt_cremonth userid="+userid+" and consigntype="+consigntype+" and rptmonth='"+lastmonth+"'");			
			
			_retask=_retask==null?"0":_retask;
			
			//更新用户当月当类型存量
			jdbc.execute("update trpt_cremonth set retask='"+_retask+"' where cremonthid="+cremonthid);
			
		}*/
	}
	
	private void updateRpt() throws SQLException{
		List<HashMap<String,Object>> list=jdbc.query("select cremonthid,userid,consigntype from trpt_cremonth where rptmonth='"+rptmonth+"'");
		for(HashMap<String,Object> map:list){
			Object userid=map.get("userid");
			Object consigntype=map.get("consigntype");
			Object cremonthid=map.get("cremonthid");
		
			//当月存量
			Object _retask=jdbc.queryOne("select nexttask from trpt_cremonth where userid="+userid+" and consigntype="+consigntype+" and rptmonth='"+lastmonth+"'");			
			
			//当月新增
			Object _newtask=jdbc.queryOne("select sum(cnfee) from topr_creditcard a, topr_credittask b where to_char(b.createtime,'yyyy-mm')='"+rptmonth+"' and b.userid="+userid+" and a.consigntype="+consigntype+" and a.creditcardid=b.creditcardid");
		
			//当月退单 退单日期为当月
			Object _backtask=jdbc.queryOne("select sum(cnfee) from topr_creditcard a, topr_credittask b where to_char(b.createtime,'yyyy-mm')='"+rptmonth+"' and b.userid="+userid+" and a.consigntype="+consigntype+" and a.creditcardid=b.creditcardid and a.tddate='"+rptmonth+"'");
			//当月回款
			Object _endtask=jdbc.queryOne("select sum(fee) from topr_creditcard a, topr_repaylog b where substr(b.repaytime,0,7)='"+rptmonth+"' and b.userid="+userid+" and a.consigntype="+consigntype+" and a.creditcardid=b.creditcardid");
			
			_retask=_retask==null?"0":_retask;
			_newtask=_newtask==null?"0":_newtask;
			_backtask=_backtask==null?"0":_backtask;
			_endtask=_endtask==null?"0":_endtask;
			
			double retask=Double.parseDouble(_retask+"");
			double newtask=Double.parseDouble(_newtask+"");
			double backtask=Double.parseDouble(_backtask+"");
			double endtask=Double.parseDouble(_endtask+"");
			
			double curtask=retask+newtask-backtask; //当月任务=存量+新增-退单
			double nexttask=curtask-endtask;	//次月存量 = 当月任务-回款
			if(nexttask<0d) nexttask=0;
			double ratetask=0;  //回款率
			if(curtask!=0d)
				ratetask=endtask/curtask;
			jdbc.execute("update trpt_cremonth set retask="+retask+",newtask="+newtask+",backtask="+backtask+",endtask="+endtask+",curtask="+curtask+",ratetask="+ratetask+",nexttask="+nexttask+" where cremonthid="+cremonthid);
		}
	}
	
	public void rptmonth(){
		try{
			LOG.info("获取催收员信息");
			fetchUsers();
			LOG.info("更新催收员回款统计");
			updateRpt();
		}catch(SQLException e){
			LOG.error(e);
		}
	}
	
	public static void main(String args[]){
		RptCreMonthUtil r=new RptCreMonthUtil();
		r.rptmonth();
	}
}
