/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;
import com.changpeng.models.SysUser;

/**
 * 
 * 管理员信息列表 1、列表的部分，只能查看自己组以及下面组的人员，对于有权限的人来说 2、对于group部分,也是同样如此 3、根据管理员姓名、登录名进行查询
 * 4、
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserListAction extends AbstractListAction {

	private String loginname;
	private String username;
	private String groupname;

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname
	 *            the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

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

	public SysUserListAction() {
		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		//也显示全国律协的以及系统层级的
		this.datavisible.getVisibleDatas(this.getLoginUser(),true);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);

		if (loginname != null && !"".equals(loginname))
			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.ANYWHERE));
		if (username != null && !"".equals(username))
			detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		// 必须是没有删除的啊
		detachedCriteria.add(Restrictions.eq("delflag", false));
		// 取消掉自己的显示
		detachedCriteria.add(Restrictions.ne("userid", getLoginUser().getUserid()));
		// 取消掉admin的显示
		detachedCriteria.add(Restrictions.ne("loginname", "admin"));

		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
				if (v.getThetable().equalsIgnoreCase("sys_user")) {
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
		
		
		// 如果没有所属的group,则能查看所有的数据信息，否则只能看本部门和下级部门的数据
		if (groupname != null && !"".equals(groupname)) {
			detachedCriteria.createAlias("sysGroup", "sysGroup").add(
					Restrictions.like("sysGroup.groupname", groupname, MatchMode.ANYWHERE));
		}

		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("officeid", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("cityid", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceid", datavisible.getProvinceid()));
		}

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("userid"));
		BasicService service = (BasicService) getBean("basicService");

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		// TODO Auto-generated method stub
		return SUCCESS;
	}
}