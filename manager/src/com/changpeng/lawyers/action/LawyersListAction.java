/**
 * TSysUserAddAction.java
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
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 用户信息列表 1、列表的部分，只能查看自己组以及下面组的人员，对于有权限的人来说 2、对于group部分,也是同样如此 3、根据用户姓名、登录名进行查询
 * 4、
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyersListAction extends AbstractListAction {

	private String loginname;
	private String username;
//	private String groupname;

	

	public void setLoginname(String name) {
		this.loginname = name;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public String getUsername() {
		return this.username;
	}

	public LawyersListAction() {
		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		
		CommonDatas.getGroups();
		
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyers.class);

		if (loginname != null && !"".equals(loginname))
			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.START));
		if (username != null && !"".equals(username))
			detachedCriteria.add(Restrictions.like("lawyername",username, MatchMode.START));

		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
			
				if (v.getThetable().equalsIgnoreCase("lawyers")) {
					rolevisible = v;
					break;
				}
			}
			
			

			// 权限判断了
			if (rolevisible != null) {
			String field="officeid";
				if(rolevisible.getThefield().equals("theoffice"))
					 field="officeid";
				else if(rolevisible.getThefield().equals("directunion"))
					 field="cityid";
				else if(rolevisible.getThefield().equals("provinceunion"))
					 field="provinceid";
				
				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), field)));
			}
		}

		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("theoffice", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("directunion", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceunion", datavisible.getProvinceid()));
		}

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("lawyerid"));
		BasicService service = (BasicService) getBean("basicService");

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		// TODO Auto-generated method stub
		return SUCCESS;
	}
}