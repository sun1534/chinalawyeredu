/**
 * IndexPageAction.java
 */

package com.sxit.index;

import java.text.DateFormat;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRight;
import com.sxit.models.system.SysUser;

/**
 * 
 * 
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 2009-3-6 Tompan 修改加上获取用户信息
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2007;

	public MyWorkspacePageAction() {
		this.rightCode = "shouye";

		
	}

	@Override
	protected String go()throws Exception
	{
		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		
		return SUCCESS;
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