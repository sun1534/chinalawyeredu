package com.changpeng.customer.util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.changpeng.customer.model.TusrCustomerNew;
@SuppressWarnings("unchecked")
public class NewCustomerUtil {
	

	
	/**
	 * 
	 * @return
	 */
	public static HashMap<String,Integer> allCustomers(){
		HashMap<String,Integer> creditcardMap=new HashMap<String,Integer>();
		List<TusrCustomerNew> listCreditcard=com.changpeng.common.Globals.getInstance().findAll(" from TusrCustomerNew");
		for(TusrCustomerNew card:listCreditcard){
			creditcardMap.put(card.getUsername()+","+card.getIdcard(), card.getCustomerid()); //账号唯一
		}
		return creditcardMap;
	}
	
	/**
	 * 判断这个user/pass是否存在
	 * @param session
	 * @param name
	 * @param idcard
	 * @return
	 */
	public static TusrCustomerNew getCustomer(Session session,String name,String idcard){
		String sql=" from TusrCustomerNew where username='"+name+"' and idcard='"+idcard+"'";
		List list=session.createQuery(sql).list();
		if(list==null||list.size()==0)
			return null;
		return (TusrCustomerNew)list.get(0);
	}

	/**
	 * 
	 * @param session
	 * @param serviceid
	 * @param servicetype
	 * @return
	 */
	public static int getCustomerByService(Session session,int serviceid,int servicetype){
		String sql=" select customerid from Tusr_Customer_service where servicetype="+servicetype+" and serviceid="+serviceid;
		List list=session.createSQLQuery(sql).list();
		if(list==null||list.size()==0)
			return 0;
		java.math.BigDecimal b=(java.math.BigDecimal)list.get(0);
		return b.intValue();
	}
	
	public static int getCustomerByService(Connection con,int serviceid,int servicetype)throws SQLException{
		String sql=" select customerid from Tusr_Customer_service where servicetype="+servicetype+" and serviceid="+serviceid;
		java.sql.Statement stmt=null;
		java.sql.ResultSet rs=null;
		try{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next())
				return rs.getInt("customerid");
			return 0;
				
		}finally{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
		
	}
}
