/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysRight;
import com.changpeng.models.SysUser;

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


		// LOG.info(sysUser.getUsername() + "进入首页TOP成功......");
		// TODO Auto-generated method stub
		return "top";
	}

	public String left() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		// LOG.info(sysUser.getUsername() + "进入首页LEFT成功......");
		// TODO Auto-generated method stub
		return "left";
	}

	
	public String getTopbarpic(){
		return com.changpeng.common.Constants.TOP_BAR_PIC;
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
