/**
 * SysLoginLogList.java
 */

package com.changpeng.system.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysLog;
import com.changpeng.models.system.SysRight;
import com.changpeng.system.service.SysLogService;
import com.changpeng.system.util.CommonDatas;
import com.changpeng.system.util.RightTree;

/**
 * 
 * 显示所有的操作日志，提供根据操作模块，操作人名称，操作时间查询的功能
 * 
 * 
 * 
 * @author 华锋 2008-3-3 上午11:11:29
 * 
 */
public class SysOperLogListAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(SysOperLogListAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 哪次登录做的操作
	 */
	private int loginid;
	public void setLoginid(int loginid){
		this.loginid=loginid;
	}
	/**
	 * 谁做的操作
	 */
	private int userid;
	public void setUserid(int userid){
		this.userid=userid;
	}
	public int getUserid() {
		return this.userid;
	}
/**
 * 操作的时间
 */	
	private String opTime;
	public void setOpTime(String loginTime) {
		this.opTime = loginTime;
	}
	public String getOpTime() {
		return this.opTime;
	}
	//查询的某个模块
	
    public SysOperLogListAction() {
		this.rightCode = "sysOperLogList";
	}
	/**
	 * userid和loginname的匹配关系
	 */
	private Map allUsers;
	public Map getAllUsers(){
//	   allUsers=CommonDatas.getUsers();
		allUsers=new HashMap();
	//   _LOG.debug(allUsers);
	   return this.allUsers;
	}
	
	private String grade1="0";
	private String grade2="0";
	private String grade3="0";
	
	public void setGrade1(String grade1){
		_LOG.debug("__Grade1:::"+grade1);
		this.grade1=grade1;
	}
	public String getGrade1(){
		return this.grade1;
	}
	public void setGrade2(String grade2){
		_LOG.debug("__Grade2:::"+grade2);
		this.grade2=grade2;
	}
	public String getGrade2(){
		return this.grade2;
	}
	public void setGrade3(String grade3){
		_LOG.debug("__Grade3:::"+grade3);
		this.grade3=grade3;
	}
	public String getGrade3(){
		return this.grade3;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLog.class);
		if (userid != 0) {
			detachedCriteria.add(Restrictions.eq("userid", userid));
		}
		if (loginid != 0) {
			detachedCriteria.add(Restrictions.eq("loginid", loginid));
		}
		if (opTime != null && !opTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(opTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(opTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("opTime", begin, end));
		}
		if(!grade3.equals("0")){//最小的菜单
			_LOG.debug("...最小的菜单:::"+grade3);
			detachedCriteria.add(Restrictions.eq("rightCode", grade3));
		}else if(!grade2.equals("0")){ //顶层的菜单
			_LOG.debug("...菜单grade2:::"+grade2);
			
			List list=new ArrayList();
			List<SysRight> rights=RightTree.getChildRights(grade2);
			for(int i=0;i<rights.size();i++){
				SysRight right=rights.get(i);
				list.add(right.getRightcode());
			}
			detachedCriteria.add(Restrictions.in("rightCode", list));
		}else if(!grade1.equals("0")){//最顶层的菜单
			_LOG.debug("...菜单grade1:::"+grade1);
			List list=new ArrayList();
			List<SysRight> rights=RightTree.getChildRights(grade1);
			for(int i=0;i<rights.size();i++){
				SysRight right=rights.get(i);
				list.add(right.getRightcode());
			}
			detachedCriteria.add(Restrictions.in("rightCode", list));
		}
		//对rightcode进行处理.模块->功能->具体的rightcode
		detachedCriteria.addOrder(Order.desc("logid"));
		SysLogService service=(SysLogService)getBean("sysLogService");
		this.page=service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		
		if(!grade1.equals("0"))
		{
			List<SysRight> rights=RightTree.getChildRights(grade1);
			for(int i=0;i<rights.size();i++){
				SysRight right=rights.get(i);
				if(right.getGrade()==2)
					grade2Map.put(right.getRightcode(), right.getRightname());
			}
		}
		if(!grade2.equals("0"))
		{
    		List<SysRight> rights=RightTree.getChildRights(grade2);
			for(int i=0;i<rights.size();i++){
				SysRight right=rights.get(i);
				if(right.getGrade()==3)
					grade3Map.put(right.getRightcode(), right.getRightname());
			}
		}
		
		return SUCCESS;
	}
	
	private Map grade1Map=new HashMap();
	private Map grade2Map=new HashMap();
	private Map grade3Map=new HashMap();
	public Map getGrade1Map(){

		List<SysRight> rights=RightTree.getChildRights("0");
		for(int i=0;i<rights.size();i++){
			SysRight right=rights.get(i);
			if(right.getGrade()==1)
				grade1Map.put(right.getRightcode(), right.getRightname());
		}
		return grade1Map;
	}
	public Map getGrade2Map(){
		return this.grade2Map;
	}
	public Map getGrade3Map(){
		return this.grade3Map;
	}
}
