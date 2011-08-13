package com.changpeng.report.util;
import java.util.*;
import java.sql.SQLException;
import java.text.*;

import org.apache.log4j.Logger;
/**
 * 律师费报费
 * @author sinhoo
 * 2010-1-6
 */
public class LawFeeUtil {
	protected static final Logger LOG = Logger.getLogger(LawFeeUtil.class);

	private JdbcTemplate jdbc;
	public LawFeeUtil(){
		jdbc=new JdbcTemplate();
	}
	
	public void fetchTduser() throws SQLException{
		String sql="select * from topr_creditcard where tdflag=1 and creditcard not in (select creditcard from trpt_tduser)";
		List<HashMap<String,Object>> list=jdbc.query(sql);
		if(list!=null&&list.size()>0){
			for(HashMap<String,Object> map:list){
				updateTduser(map);
			}
		}
	}
	
	private void updateTduser(HashMap<String,Object> map) throws SQLException{
		Object creditcardid=map.get("creditcardid");
		Object bankid=map.get("bankid");
		Object consigntype=map.get("consigntype");
		if(consigntype==null) consigntype=1;
		Object consignflag=map.get("consignflag");
		if(consignflag==null) consignflag=1;
		
		Object username=map.get("username");
		Object creditcard=map.get("creditcard");
		Object idcard=map.get("idcard");
		Object consigndate=map.get("consigndate");
		Object cnfee=map.get("cnfee");
		Object usafee=map.get("usafee");
		Object repaystatus=map.get("repaystatus");
		
		if(cnfee==null) cnfee=0;
		if(usafee==null) usafee=0;
		
		Object p_cnfee=0;
		Object p_usafee=0;
		
		String sql="select sum(fee) p_cnfee,sum(usafee) p_usafee from topr_repaylog where creditcardid="+creditcardid;
		HashMap<String,Object> fee=jdbc.queryMap(sql);
		if(fee!=null){
			p_cnfee=fee.get("p_cnfee"); 
			if(p_cnfee==null) p_cnfee=0;
			p_usafee=fee.get("p_usafee");
			if(p_usafee==null) p_usafee=0;
		}
		
		sql="insert into trpt_tduser (tduserid,bankid,consigntype,consignflag,username,creditcard,idcard,consigndate,cnfee" +
				",usafee,repaystatus,p_cnfee,p_usafee,b_cnfee,b_usafee,r_cnfee,r_usafee) values ("+creditcardid+"," +
				"'"+bankid+"','"+consigntype+"','"+consignflag+"','"+username+"','"+creditcard+"','"+idcard+"','"+consigndate+"','"+cnfee+"','"+usafee+"','"+repaystatus+"'," +
						"'"+p_cnfee+"','"+p_usafee+"','"+p_cnfee+"','"+p_usafee+"','"+(Double.parseDouble(cnfee+"")-Double.parseDouble(p_cnfee+""))+"','"+(Double.parseDouble(usafee+"")-Double.parseDouble(p_usafee+""))+"')";
		System.out.println(sql);
		jdbc.execute(sql);
	}
	
	public static void main(String args[]){
		LawFeeUtil fee=new LawFeeUtil();
		try {
			fee.fetchTduser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
