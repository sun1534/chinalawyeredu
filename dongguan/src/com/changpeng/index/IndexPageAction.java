/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysRight;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;

/**
 * 
 * userlogin成功后,redirect到这个页面。防止刷新，重复登录
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class IndexPageAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexPageAction.class);

	
	public IndexPageAction(){
		this.rightCode="shouye";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();

		LOG.info(sysUser.getUsername() + "进入首页成功......");
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String top() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		
		
		this.sysUser = getLoginUser();
		
		if (this.sysUser.getLoginname().equals("admin"))
			this.isadminer = true;
		else {
			Iterator<SysRole> roles = sysUser.getSysRoles().iterator();
			short maxroleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				if (maxroleid <= role.getRoleid())
					maxroleid = role.getRoleid();
			}
			if (maxroleid == 3) {// 3的角色id是律协管理员
				isadminer = true;

			}
			else if (maxroleid == 2) {
				isofficeadminer = true;

			}

		}

		//LOG.info(sysUser.getUsername() + "进入首页TOP成功......");
		// TODO Auto-generated method stub
		return "top";
	}

	public String left() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		//LOG.info(sysUser.getUsername() + "进入首页LEFT成功......");
		// TODO Auto-generated method stub
		return "left";
	}

	private boolean isofficeadminer;
	public boolean getIsofficeadminer(){
		return this.isofficeadminer;
	}

	private boolean isadminer;

	public boolean getIsadminer() {
		return this.isadminer;
	}
	
	private List<SysRight> userMenus;

	public List<SysRight> getUserMenus() {
		return this.userMenus;
	}

	private SysUser sysUser;

	public SysUser getSysUser() {

		return this.sysUser;
	}
}
