package com.changpeng.nonlaw.util;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

import com.changpeng.common.Globals;
import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.operation.model.ToprBank;

public class NonlawUtil {
	
	public static Globals globals=Globals.getInstance();
	
	public static HashMap<String,String> projectnameMap;
	
	static{
		projectnameMap=new HashMap<String,String>();
		projectnameMap.put("1", "消费贷款");
		projectnameMap.put("2", "住房贷款");
		projectnameMap.put("3", "融资贷款");
		projectnameMap.put("4", "汽车贷款");
	}
	/**
	 * 
	 * @return
	 */
	public static HashMap<String,TnlwNonlaw> nonlawMap(){
		HashMap<String,TnlwNonlaw> nonlawMap=new HashMap<String,TnlwNonlaw>();
		List<TnlwNonlaw> listNonlaw=com.changpeng.common.Globals.getInstance().findAll(" from TnlwNonlaw");
		for(TnlwNonlaw nonlaw:listNonlaw){
			nonlawMap.put(nonlaw.getIdcard(), nonlaw);
		}
		return nonlawMap;
	}
	/**
	 * 选择催收记录及人员进行任务分配
	 * @param userid 用户ID
	 * @param check 催收记录
	 * @throws SQLException
	 */
	public static void consignTask(long userid,long[] check) throws SQLException{
		Connection con=null;	
		Statement stmt=null;
		try{
			con=globals.getCon();
			stmt=con.createStatement();
			for(long creditcardid:check){
				//插入用户任务表
				stmt.addBatch("insert into tnlw_nonlawtask (nonlawtaskid,nonlawid,userid,createtime,taskstat) values (tnlwnonlawtaskid.nextval,"+creditcardid+","+userid+",sysdate,0)");
				//更新记录状态
				stmt.addBatch("update tnlw_nonlaw set state=1 where nonlawid="+creditcardid+"");
				//将对应的客户信息记录同步到该用户通讯录
				stmt.addBatch("update tusr_address set userid="+userid+" where oprid="+creditcardid+" and oprflag=2");
			
				//将对应的日志记录更改到新接单人
				stmt.addBatch("update tnlw_nonlawlog set userid="+userid+" where nonlawid="+creditcardid+"");
        		
			}
			stmt.executeBatch();
		}finally{
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}
	}	
	
	/**
	 * 获取指定银行下的非诉记录，且当前还款状态为非全清（不等于2）的 贷款账号及贷款金额作为key
	 * @param bankid
	 * @param session
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String,TnlwNonlaw> nonlawOfBank(long bankid,org.hibernate.Session session){
		HashMap<String,TnlwNonlaw> map=new HashMap<String,TnlwNonlaw>();
		List<TnlwNonlaw> list=session.createQuery(" from TnlwNonlaw where repaystatus<>2 and bankid="+bankid).list();
		for(TnlwNonlaw nonlaw:list){
			map.put(nonlaw.getLendaccount()+","+nonlaw.getLendfee(), nonlaw);
		}
		return map;
	}
	
	public static String taskuser(long nonlawid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String taskuser="";
		try{
			con=globals.getCon();
			pstmt=con.prepareStatement("select a.username from tsys_user a,TNLW_NONLAWTASK b where a.userid=b.userid and b.taskstat=0 and NONLAWID=?");
			pstmt.setLong(1, nonlawid);
			rs=pstmt.executeQuery();
			if(rs.next())
				taskuser=rs.getString(1);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(rs!=null) rs.close();	
				if(pstmt!=null) pstmt.close();	
				if(con!=null) con.close();	
			}catch(Exception e){}
		}
		return taskuser;
	}

}
