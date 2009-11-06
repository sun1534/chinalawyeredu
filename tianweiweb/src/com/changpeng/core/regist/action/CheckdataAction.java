/**
 * 
 */
package com.changpeng.core.regist.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.sysdata.CommonData;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

/**
 */
public class CheckdataAction extends AbstractAction {
	/**
	 * 
	 */
	private static  Logger log = Logger.getLogger(RegistAction.class);
	private static final long serialVersionUID = 1L;
	
	private String username;
	private int type;
	public CheckdataAction() {
		this.rightCode = "PUBLIC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	protected String go() throws Exception {
		String rstr = ERROR;
		UserService uservice=(UserService)this.getBean("userService");
		CoreUser u=uservice.getUserByLoginName(username);
		if(u!=null){
			this.message="<font color='red'>用户名已经被注册</font>";
		}else{
			if(username.trim().length()<6||username.trim().length()>20){
				this.message="<font color='red'>用户名输入有误</font>";
			}else{
				this.message="<font color='#CBCBCB'>ok</font>";
			}
		}
		return "plainmsg";
	}

	@Override
	protected String getin() {
		return INPUT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
