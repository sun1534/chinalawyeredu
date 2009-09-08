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
 * 新增管理员信息,新增成功后,转到信息提示页面吧
 * 
 * 默认所在的locationid为其所在的id
 * 
 * @author 华锋 2008-2-25 上午11:12:05 2009-3-11 Tompan 新增分配角色部分,默认为这个人所对应的角色
 * 
 */
public class SysUserCreateAction extends AbstractAction {

	private SysUser sysUser;

	public SysUser getSysUser() {
		return sysUser;
	}

	public SysUserCreateAction() {
		this.sysUser = new SysUser();
		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 这里还涉及到了所在地域的问题
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		SysUserService service = (SysUserService) this.getBean("sysUserService");
		if (service.getSysUserByLoginname(sysUser.getLoginname()) != null) {
			this.message = "对不起，您输入的帐号【" + sysUser.getLoginname() + "】已经被他人使用。";
			return "message";
		}
		if (!sysUser.getPassword().equals(passagain)) {
			this.message = "两次密码输入不同,请重新输入!";
			return "message";
		}

		sysUser.setCreateuser(super.getLoginUser().getUsername());
		sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysUser.setCreateuserid(this.getLoginUser().getUserid());
		sysUser.setCityid(this.datavisible.getCityid());
		sysUser.setProvinceid(this.datavisible.getProvinceid());
		sysUser.setOfficeid(this.datavisible.getOfficeid());

		SysGroup group = null;
		if (sysUser.getOfficeid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getOfficeid());
		} else if (sysUser.getCityid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getCityid());
		} else if (sysUser.getProvinceid() != 0) {
			group = new SysGroup();
			group.setGroupid(sysUser.getProvinceid());
		}
		
		sysUser.setSysGroup(group);
		service.addUser(sysUser);

		this.nextPage = "sysUserList.pl";
		this.opResult = "管理员" + super.getLoginUser().getUsername() + "新增了管理员" + sysUser.getUsername();
		this.message = "管理员新增成功";
		return SUCCESS;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 * 
	 * 这里得到省一级的地市信息数据
	 */
	@Override
	public String input() throws Exception {

		// 也显示全国律协的以及系统层级的
		this.datavisible.getVisibleDatas(this.getLoginUser(), true);
		this.datavisible.setCityid(this.getLoginUser().getCityid());
		this.datavisible.setOfficeid(this.getLoginUser().getOfficeid());
		this.datavisible.setProvinceid(this.getLoginUser().getProvinceid());

		SysRoleService roleService = (SysRoleService) this.getBean("sysRoleService");
		// if (!this.getLoginUser().getLoginname().equals("admin")) {
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
			allroles = roleService.getRoles();
		}

		return INPUT;
	}

	private String passagain;

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	private List allroles = null;

	public List getAllroles() {
		return this.allroles;
	}

	/**
	 * @return the passagain
	 */
	public String getPassagain() {
		return passagain;
	}
}