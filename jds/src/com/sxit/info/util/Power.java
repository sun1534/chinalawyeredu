package com.sxit.info.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.TinfSet;
import com.sxit.system.model.TsysUser;

public class Power {
	
	/*
	 * 判断是否在某个种类上具有某种权限
	 */
	public static boolean hasPower(Session session,TsysUser curuser,String kind,long typeid){
		String queryName;
		int kindNum = 0;
		 
		kindNum = getKindNum(kind);
    	queryName = 
    		"from TinfSet as setinf where setinf.userid="+curuser.getUserid()+" and tinfType.typeid="+typeid;
    	Query query = session.createQuery(queryName);
    	Iterator it = query.list().iterator();
    	if(it.hasNext()){
	    	TinfSet set = (TinfSet)it.next();
	    	if(kindNum==0){
	    		return false;
	    	}
	    	if((set.getPowerid()&kindNum)>0){
	    		System.out.println("true");
	            return true;
	    	}
    	}
    	System.out.println("false");
    	return false;
	}
	
	/*
	 * 获取用户在某个操作上具有哪种权限
	 */
	public static List whichPower(Session session,TsysUser curuser,String kind){
		String queryName;
		Long[] power;
		int kindNum = 0;
 
		kindNum = getKindNum(kind);
		queryName = 
    		"from TinfSet as setinf where setinf.userid="+curuser.getUserid()+" and  bitand(setinf.powerid,"+kindNum+")>0 order by setinf.tinfType.typeid";
    	Query query = session.createQuery(queryName);
    	List it = query.list();
    	return it;
	}
	
	public static List whoPower(Session session,String kind,Long infotype){
		String queryName;
		int kindNum = 0;
		 
		kindNum = getKindNum(kind);
		queryName = 
    		"from TinfSet as setinf where setinf.tinfType.typeid="+infotype+" and bitand(setinf.powerid,"+kindNum+")>0";
    	Query query = session.createQuery(queryName);
    	List it = query.list();
    	return it;
	}
	
	public static int getKindNum(String kind){
		int kindNum = 0;
		
		if(kind.equals("approve")){		//审批
			kindNum = 1;
		}else if(kind.equals("prom")){	//发布
			kindNum = 2;
		}else if(kind.equals("newinf")){//新建
			kindNum = 4;
		}else if(kind.equals("edit")){	//编辑
			kindNum = 8;
		}else if(kind.equals("del")){	//删除
			kindNum = 16;
		}else if(kind.equals("view")){	//查看
			kindNum = 32;
		}else if(kind.equals("any")){	//任何
			kindNum = 63;
		}
		return kindNum;
	}
	
//    /*
//     * 判断用户是否具有审批的权限
//     */
//    public boolean hasApprove(Session session,TsysUser curuser) {
//    	String queryName;
//    	queryName = 
//    		"from TinfSet as setinf where setinf.userid="+curuser.getUserid();
//    	 Query query = session.createQuery(queryName);
//    	 Iterator it = query.list().iterator();
////    	 if(!it.hasNext()){
////    		 System.out.println("it===============null");
////    	 }
////    	 System.out.println("size=============="+query.list().size());
//    	 if(it.hasNext()){
//	    	 TinfSet set=(TinfSet)it.next();
//	    	 if((set.getPowerid()&1)==1){	//判断是否具有审批权限
//	    		 System.out.println("true");
//	    		 return true;
//	    	 }
//    	 }
//    	 System.out.println("false");
//    	 return false;
//    }
//    
//    /*
//     * 判断用户是否具有发布的权限
//     */
//    public boolean hasProm(Session session,TsysUser curuser){
//    	String queryName;
//    	queryName = 
//    		"from TinfSet as setinf where setinf.userid="+curuser.getUserid();
//    	Query query = session.createQuery(queryName);
//    	Iterator it = query.list().iterator();
//    	if(it.hasNext()){
//	    	TinfSet set = (TinfSet)it.next();
//	    	if((set.getPowerid()&2)==2){
//	    		System.out.println("true");
//	            return true;
//	    	}
//    	}
//    	System.out.println("false");
//    	return false;
//    }
//    
//    /*
//     * 判断用户是否具有新建权限
//     */
//	public boolean hasNewInf(Session session, TsysUser curuser) {
//		// TODO 从tinfset读取power 判断是否具有新建权限 &4 == ture
//		String queryName;
//    	queryName = 
//    		"from TinfSet as setinf where setinf.userid="+curuser.getUserid();
//    	Query query = session.createQuery(queryName);
//    	Iterator it = query.list().iterator();
//    	if(it.hasNext()){
//	    	TinfSet set = (TinfSet)it.next();
//	    	if((set.getPowerid()&4)==4){
//	    		System.out.println("true");
//	            return true;
//	    	}
//    	}
//    	System.out.println("false");
//    	return false;
//	}
//	
//	/*
//	 * 判断用户是否具有编辑权限
//	 */
//	public boolean hasEdit(Session session, TsysUser curuser) {
//		// TODO 从tinfset读取power 判断是否具有编辑权限 &8 == ture
//		return false;
//	}
//	
//	/*
//	 * 判断用户是否具有删除权限
//	 */
//	public boolean hasDel(Session session, TsysUser curuser) {
//		// TODO 从tinfset读取power 判断是否具有删除权限 &16 == ture
//		return false;
//	}

}
