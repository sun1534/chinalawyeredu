/**
 * LawerChangeCardNoAction.java
 */

package com.sxit.system.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.common.util.MD5;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

/**
 * ajax的方式重置密码
 * 
 * @author 华锋 2008-5-5 下午05:34:22
 * 
 */
public class PasswordResetAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(PasswordResetAction.class);

	public PasswordResetAction(){
		this.rightCode="passwordReset";
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		String newpass="123456";
		try {
			SysUserService service = (SysUserService) getBean("sysUserService");
			BasicService basicService = (BasicService) getBean("basicService");
			SysUser lawer = (SysUser) basicService.get(SysUser.class, userid);
			
		
			String md5pass = MD5.md5(newpass);
			// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
			lawer.setPassword(md5pass);
			service.updateUser(lawer);
			changeok = "密码重置为"+newpass+"成功";
			this.opResult = lawer.getUsername() + "的密码重置为"+newpass;

		}
		catch (Exception e) {
			LOG.error("密码重置为"+newpass+"失败:" + e);
			changeok = "密码重置为"+newpass+"失败:" + e;
			this.opResult="密码重置为"+newpass+"失败:" + e;
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private String changeok;

	public String getChangeok() {
		return this.changeok;
	}

	private int userid;

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

}
