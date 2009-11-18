/**
 * SysLoginLogList.java
 */

package com.sxit.log.action;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.models.log.SysLog;

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
	 * 操作的时间
	 */
	private String opTime;
	private String username;


	public void setOpTime(String loginTime) {
		this.opTime = loginTime;
	}

	public String getOpTime() {
		return this.opTime;
	}
	
	

	// 查询的某个模块

	public SysOperLogListAction() {
		// this.rightCode = "sysOperLogList";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	private String totype="list";

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}
	/**
	 * userid和loginname的匹配关系
	 */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLog.class);
	
		if (opTime != null && !opTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(opTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(opTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("opTime", begin, end));
		}
	

		//按管理员角色对操作日志进行分别显示
		BasicService bs = (BasicService) getBean("basicService");
		if (username != null && !username.equals("")) {
				detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));
        }
		detachedCriteria.addOrder(Order.desc("logid"));
		
		this.pageSize=10;
		


		if(totype!=null&&totype.equals("excel")){

			this.page = bs.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
			return "excel";
	    	
		}else{
			this.page = bs.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			return "list";
		}
	}

	

}
