/**
 * SysLoginLogList.java
 */

package com.changpeng.system.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysOperlog;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;

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
//	private static Log _LOG = LogFactory.getLog(SysOperLogListAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public SysOperLogListAction(){
		datavisible=new DataVisible();
	}
	
	
	/**
	 * 哪次登录做的操作
	 */
	private int loginid;
	public void setLoginid(int loginid){
		this.loginid=loginid;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		this.datavisible.getVisibleDatas(this.getLoginUser(),true);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysOperlog.class);
		
		if (loginid != 0) {
			detachedCriteria.add(Restrictions.eq("loginid", loginid));
		}
		if (opTime != null && !opTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(opTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(opTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("opTime", begin, end));
		}
		
		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
				if (v.getThetable().equalsIgnoreCase("sys_operlog")) {
					rolevisible = v;
					break;
				}
			}
			// 权限判断了
			if (rolevisible != null) {
				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), rolevisible.getThefield())));
			}
		}
		
		
		
		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("officeid", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("cityid", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceid", datavisible.getProvinceid()));
		}

		//对rightcode进行处理.模块->功能->具体的rightcode
		detachedCriteria.addOrder(Order.desc("logid"));
		BasicService service=(BasicService)getBean("basicService");
		this.page=service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		

		return SUCCESS;
	}
	

}
