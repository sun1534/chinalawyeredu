package com.cqmm.common;

import java.io.Serializable;

public class LoginAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -820343264667349072L;
	private String loginName;
	private String pwd;
	private String remberPwd;

	public String tostring() {
		return "[" + loginName + "," + pwd + "," + remberPwd + "]";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getRemberPwd() {
		return remberPwd;
	}



	public void setRemberPwd(String remberPwd) {
		this.remberPwd = remberPwd;
	}


}