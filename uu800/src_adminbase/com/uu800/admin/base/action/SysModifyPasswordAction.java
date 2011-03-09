package com.uu800.admin.base.action;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;


import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysUser;
import com.uu800.admin.base.service.ModifyPasswordService;

public class SysModifyPasswordAction extends AbstractAdminAction {
	
	
	private static Log LOG = LogFactory.getLog(AbstractAdminAction.class);

	private TsysUser tsysUser;
	private String password1;
	private String password2;
	private String flag;
	private String inputoldpassword;
	private String oldpassword;
	private String userinfoname;

	private ModifyPasswordService service;

	public SysModifyPasswordAction() {
		service = (ModifyPasswordService) this.getBean("modifyPasswordService");
	}

	@Override
	public String execute() {
		tsysUser = (TsysUser) service.get(TsysUser.class, getUserinfo().getId());

		try {
			oldpassword = getUserinfo().getPassword();
			String _inputoldpassword = com.uu800.webbase.util.Md5.MD5(inputoldpassword);
			if (_inputoldpassword.equals(oldpassword)) {
				tsysUser.setPassword(com.uu800.webbase.util.Md5.MD5(password1));

				set("tsysUser", tsysUser);
				service.update(tsysUser);
				// 成功转向页面
				nextPage = "modifyPassword!input.action";
				message = "密码修改成功！";
				return SUCCESS;
			} else {
				// 转向页面
				nextPage = "modifyPassword!input.action";
				message = "旧密码输入错误";
				return "sysmsg";
			}
		} catch (Exception e) {
			message = e.getMessage();
			return "sysmsg";
		}
	}

	public String input() throws Exception {
		userinfoname = getUserinfo().getUserName();
		return "input";
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getCurusername() {
		return userinfoname;
	}

	public void setCurusername(String userinfoname) {
		this.userinfoname = userinfoname;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getInputoldpassword() {
		return inputoldpassword;
	}

	public void setInputoldpassword(String inputoldpassword) {
		this.inputoldpassword = inputoldpassword;
	}

	public TsysUser getTsysUser() {
		return tsysUser;
	}

	public void setTsysUser(TsysUser tsysUser) {
		this.tsysUser = tsysUser;
	}

}
