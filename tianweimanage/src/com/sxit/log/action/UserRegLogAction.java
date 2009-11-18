/**
 * SysLoginLogList.java
 */

package com.sxit.log.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.log.SysLoginLog;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.dao.SysRoleDAO;

/**
 * 
 * 显示所有登录的日志,提供根据用户名和登录时间进行查询的功能
 * 
 * 查询的话，根据登录的用户来查询疾
 * 
 * @author 华锋 2008-3-3 上午11:11:29
 * 
 */
public class UserRegLogAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(UserRegLogAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public UserRegLogAction() {
		// this.rightCode = "sysLoginLogList";
	}

	private boolean batchLoginLogBackup=false;//批量导出标志位
	
	
	public boolean isBatchLoginLogBackup() {
		return batchLoginLogBackup;
	}

	public void setBatchLoginLogBackup(boolean batchLoginLogBackup) {
		this.batchLoginLogBackup = batchLoginLogBackup;
	}

	private String loginTime;

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	private short roleid;

	private Integer[] loginids;


	public Integer[] getLoginids() {
		return loginids;
	}

	public void setLoginids(Integer[] loginids) {
		this.loginids = loginids;
	}

	public short getRoleid() {
		return roleid;
	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService bs = (BasicService) getBean("basicService");

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLoginLog.class);
		
		//按管理员角色对登录日志进行分别显示
		if(roleid!=0&&roleid!=-1){
			//System.out.println(roleid);
			SysRole sr = (SysRole) bs.get(SysRole.class, roleid);
			Set<SysUser> userlist = sr.getSysUsers();
			if(null!=userlist&&userlist.size()!=0){
				List<Integer> useridlist = new ArrayList<Integer>();
				for(SysUser sysUser:userlist){
					useridlist.add(sysUser.getUserid());
				}
				if (useridlist != null && useridlist.size() != 0)// 找到了
					detachedCriteria.add(Restrictions.in("userid", useridlist));
				else
					detachedCriteria.add(Restrictions.eq("userid", -1));
			}
		}
		
		if (username != null &&! username.equals("")) {
			String hql = "select userid from com.sxit.models.system.SysUser a where a.username like '%" + username + "%' or a.loginname like '%"
					+ username + "%'";
			List useridlist = bs.find(hql);
			if(useridlist!=null&&useridlist.size()!=0)//找到了
		    	detachedCriteria.add(Restrictions.in("userid", useridlist));
			else//没找到
				detachedCriteria.add(Restrictions.eq("userid", -1));
		}
		if (loginTime != null && !loginTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(loginTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(loginTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("loginTime", begin, end));
		}
		detachedCriteria.addOrder(Order.desc("loginid"));
		SysLoginLogService service = (SysLoginLogService) getBean("sysLoginLogService");
		this.pageSize=10;
		
		if(totype!=null&&totype.equals("excel")){	
			//System.out.println(batchLoginLogBackup);
			if(null!=loginids&&loginids.length!=0&&batchLoginLogBackup)
				detachedCriteria.add(Restrictions.in("loginid", loginids));
			this.page = service.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
			return "excel";
	    	
		}else{
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			return "list";
		}
		
//		return SUCCESS;
	}
	private String totype="list";

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}
}
