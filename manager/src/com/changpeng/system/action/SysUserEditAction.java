/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysRoleService;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 管理员信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserEditAction extends AbstractAction {

//	private static Log _LOG = LogFactory.getLog(SysUserEditAction.class);
//	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private SysUserService service;

	private SysUser sysUser;
	private int userid;
	

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUser getSysUser() {
		if (sysUser == null)
			sysUser = (SysUser) get("sysUser");
		return sysUser;

	}

	public SysUserEditAction() {
		this.datavisible=new DataVisible();
		service = (SysUserService) this.getBean("sysUserService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		
//		BasicService basicService=(BasicService) this.getBean("basicService");
	
		sysUser.setCityid(this.datavisible.getCityid());
		sysUser.setProvinceid(this.datavisible.getProvinceid());
		sysUser.setOfficeid(this.datavisible.getOfficeid());

		SysGroup group = null;
		if (sysUser.getOfficeid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getOfficeid());sysUser.setSysGroup(group);
		} else if (sysUser.getCityid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getCityid());sysUser.setSysGroup(group);
		} else if (sysUser.getProvinceid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getProvinceid());sysUser.setSysGroup(group);
		}
		
		service.updateUser(sysUser);

		this.message = "管理员信息修改成功,请确认";
		this.nextPage = "sysUserList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		this.sysUser = service.getUser(userid);
		set("sysUser", sysUser);
		
		//也显示全国律协的以及系统层级的
		
		
		System.out.println("==================="+sysUser.getCityid());
		this.datavisible.setProvinceid(sysUser.getProvinceid());
		this.datavisible.setCityid(sysUser.getCityid());
		this.datavisible.setOfficeid(sysUser.getOfficeid());
	
		this.datavisible.getVisibleDatas(this.getLoginUser(),true);
	
		SysRole sysrole = this.getLoginUser().getSysRole();
		if (sysrole != null) {
			BasicService bs = (BasicService) this.getBean("basicService");
			DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
			Criterion c1 = null;
			if (sysrole.getCansamegrade())

				c1 = Restrictions.ge("gradeid", sysrole.getGradeid());
			else
				c1 = Restrictions.gt("gradeid", sysrole.getGradeid());
			Criterion c2 = Restrictions.eq("roleid", sysrole.getRoleid());
			dc.add(Restrictions.or(c1, c2));
			allroles = bs.findAllByCriteria(dc);

		} else { // 没角色的话，显示所有的角色
			SysRoleService roleService = (SysRoleService) this.getBean("sysRoleService");
			allroles = roleService.getRoles();
		}
		
		
		return INPUT;
	}

	private List allroles = null;

	public List getAllroles() {
		return this.allroles;
	}
}
