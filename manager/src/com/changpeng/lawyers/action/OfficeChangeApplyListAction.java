/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.lawyers.action;

import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;
import com.changpeng.system.util.CommonDatas;

/**
 * 律师发起转所申请
 * 
 * 插入转所申请表
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class OfficeChangeApplyListAction extends AbstractListAction {

	public OfficeChangeApplyListAction (){
		this.datavisible = new DataVisible();
	}
	
	private String lawyername;

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	private boolean canhandle;
	public boolean getCanhandle(){
		return this.canhandle;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		if(this.getLoginUser().getSysGroup()!=null&&this.getLoginUser().getSysGroup().getGrouptype()==1)
			canhandle=false;
		else
			canhandle=true;
		System.out.println("canhandle======"+canhandle);
		
		CommonDatas.getGroups();
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LawyersOfficeChangeApply.class);
		if (lawyername != null && !"".equals(lawyername))
			detachedCriteria.add(Restrictions.like("lawyername", lawyername, MatchMode.START));
if(status!=-1){
	detachedCriteria.add(Restrictions.eq("status", status));
}
		
		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
				if (v.getThetable().equalsIgnoreCase("lawyers_office_change_apply")) {
					rolevisible = v;
					break;
				}
			}
			// 权限判断了
			if (rolevisible != null) {
				String field = "officeid";
				if (rolevisible.getThefield().equals("oldoffice"))
					field = "officeid";
				else if (rolevisible.getThefield().equals("oldcity"))
					field = "cityid";
				else if (rolevisible.getThefield().equals("oldprovince"))
					field = "provinceid";

				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), field)));
			}
		}

		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("oldoffice", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("oldcity", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("oldprovince", datavisible.getProvinceid()));
		}

		detachedCriteria.addOrder(Order.desc("applyTime"));

		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	private short status=-1;

	/**
	 * @return the status
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(short status) {
		this.status = status;
	}
	
}
