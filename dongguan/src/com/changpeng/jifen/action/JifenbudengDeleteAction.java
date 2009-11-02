/**
 * JifenbudengAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 删除掉补登的积分
 * 
 * @author 华锋 2008-5-4 下午10:46:27
 * 
 */
public class JifenbudengDeleteAction extends AbstractAction {

	@Override
	public String go() throws Exception {
		// 积分的补登，同时应计入积分表里面，考虑事务来处理
		JifenbudengService budengservice = (JifenbudengService) getBean("jifenbudengService");

		budengservice.deleteJifenbudeng(this.budengid);

		this.message = "补登的积分删除成功";
		this.nextPage = "jifenbudengList.pl";
		return SUCCESS;
	}

	private int budengid;

	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	public int getBudengid() {
		return this.budengid;
	}

}
