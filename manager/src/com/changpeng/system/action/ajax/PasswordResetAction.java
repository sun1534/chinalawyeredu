/**
 * LawerChangeCardNoAction.java
 */

package com.changpeng.system.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUser;

/**
 * ajax的方式重置密码
 * 
 * @author 华锋 2008-5-5 下午05:34:22
 * 
 */
public class PasswordResetAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(PasswordResetAction.class);

	public PasswordResetAction() {
		this.rightCode = "passwordReset";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		String newpass = "123456";

		try {
//			SysUserService service = (SysUserService) getBean("sysUserService");
			BasicService basicService = (BasicService) getBean("basicService");

			if (islawyer != null && islawyer.equals("true")) {
				Lawyers lawyers = (Lawyers) basicService.get(Lawyers.class, userid);

				if(lawyers.getCertno()!=null&&!lawyers.getCertno().equals(""))
			    	newpass=lawyers.getCertno();
			//	String md5pass = MD5.md5(newpass);
				lawyers.setPasswd(newpass);
				basicService.update(lawyers);
				this.opResult = lawyers.getLawyername() + "的密码重置为" + newpass;
				
				
				
			} else {
				SysUser sysuser = (SysUser) basicService.get(SysUser.class, userid);
				//String md5pass = MD5.md5(newpass);
				// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
				sysuser.setPassword(newpass);
				basicService.update(sysuser);
				this.opResult = sysuser.getUsername() + "的密码重置为" + newpass;
			}
			changeok = "密码重置为" + newpass + "成功";

		} catch (Exception e) {
			LOG.error("密码重置为" + newpass + "失败:" + e);
			changeok = "密码重置为" + newpass + "失败:" + e;
			this.opResult = "密码重置为" + newpass + "失败:" + e;
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

	private String islawyer;

	/**
	 * @return the islawyer
	 */
	public String getIslawyer() {
		return islawyer;
	}

	/**
	 * @param islawyer
	 *            the islawyer to set
	 */
	public void setIslawyer(String islawyer) {
		this.islawyer = islawyer;
	}
	
	private String now;

	/**
	 * @param now the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}
	

}
