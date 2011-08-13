/**
 * 
 */
package com.changpeng.lawcase.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawOperlog;
import com.changpeng.lawcase.model.TlawStageDate;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.system.model.TsysUser;
import com.sxit.workflow.model.TwflAction;

/**
 * @author 华锋 Oct 26, 2009-9:52:37 PM
 * 
 */
public class LawcaseLogicService {

	/**
	 * 得到这个案件的历史处理记录
	 * 
	 * @param session
	 * @param caseid
	 * @return
	 */
	public static List getLawhistories(Session session, long caseid) {

		return session.createQuery("from TlawHistory a where a.caseid=" + caseid).list();
	}

	/**
	 * 
	 * @param session
	 * @param caseid
	 * @param nodeid
	 * @param logcontent
	 * @param opertype
	 *            1新增2修改4删除
	 */
	public static void saveOperlog(Session session, long caseid, int actionid, String logcontent, int opertype) {
		TsysUser sysuser = (TsysUser) ActionContext.getContext().getSession().get("curuser");
		com.changpeng.lawcase.model.TlawOperlog operlog = new TlawOperlog();
		operlog.setCaseid(caseid);
		operlog.setLogcontent(logcontent);
		operlog.setLogtime(new java.sql.Timestamp(System.currentTimeMillis()));
		// operlog.setNodeid(nodeid);
		operlog.setActionid(actionid);
		operlog.setOpertype(opertype);
		operlog.setUserid(sysuser.getUserid());
		operlog.setUsername(sysuser.getUsername());
		session.save(operlog);
	}
	private static String list2str(List list){
		String s="(";
		for(int i=0;list!=null&&i<list.size();i++){
			s+=list.get(i)+",";
		}
		s+="-1)";
		return s;
	}

	/**
	 * 判断这个节点所有必须的action是否都已经处理完了,这里返回没有处理完毕的actionid
	 * 
	 * @param session
	 * @param caseid
	 * @param nodeid
	 * @param actionid
	 * @return
	 */
	
	public static List getNodeActions(Session session, long caseid, int nodeid) {
		String hql = "";
		Iterator<TwflAction> actions = com.changpeng.lawcase.util.CommanDatas.ALLACTIONS.values().iterator();
		List alllist = new ArrayList();
		while (actions.hasNext()) {
			TwflAction action = actions.next();
			if (action.getIsbixu().equalsIgnoreCase("Y")&&action.getNodeid().equals(nodeid+""))
				alllist.add(action.getActionid());
		}
		
		List list = session.createQuery(
				"select distinct(actionid) from tlaw_operlog where actionid in "+list2str(alllist)+" and caseid=" + caseid).list();
		System.out.println(alllist);
		System.out.println(list);
		alllist.removeAll(list);
		System.out.println("后面的list:" + alllist);
		return alllist;
	}
	/**
	 * 判断这个case的这个action是否已经处理完毕了
	 * @param session
	 * @param caseid
	 * @param actionid
	 * @return
	 */
	public static boolean actionIsHandled(Session session,long caseid,int actionid){
		String sql="select count(*) as cnt from tlaw_operlog where actionid="+actionid+" and caseid="+caseid;
		List list=session.createSQLQuery(sql).list();
		int cnt=Integer.parseInt(list.get(0).toString());
		return cnt>0?true:false;
	}
	
	/**
	 * 得到这个caseid各个阶段的时间信息
	 * @param session
	 */
	
	@SuppressWarnings("unchecked")
	public static TlawStageDate getStagetime(Session session,long caseid)throws Exception{
		String hql="from TlawOperlog log where log.caseid="+caseid+" order by actionid,logtime";
		
		Class stageclass=Class.forName("com.changpeng.lawcase.model.TlawStagetime");
		Object obj=stageclass.newInstance();
		Class partypes[] = new Class[]{java.sql.Timestamp.class};
		List list=session.createQuery(hql).list();
		int len=list==null?0:list.size();
		int _actionid=0;
		for(int i=0;i<len;i++){
			TlawOperlog operlog=new TlawOperlog();
			int actionid=operlog.getActionid();
			String stagetime=com.changpeng.lawcase.util.CommanDatas.ALLACTIONS.get(actionid).getStagetime();
			if(_actionid!=actionid){
			
				//要将stagetime的第一个字母大写
				String first=stagetime.substring(0,1);
				String last=stagetime.substring(1);
				String field=first.toUpperCase()+last;
				
				Method method=stageclass.getDeclaredMethod("set"+field, partypes);
				Object arglist[] = new Object[]{operlog.getLogtime()};
				method.invoke(obj,arglist);
				
				_actionid=actionid;
			}
			
		}
		
		
		TlawStageDate lawstagetime=(TlawStageDate)obj;
		TlawLawcase lawcase=(TlawLawcase)session.get(TlawLawcase.class, caseid);
		lawstagetime.setThedate(lawcase.getThedate());
		lawstagetime.setCaseid(caseid);
		return lawstagetime;
		
	}
}
