
package com.changpeng.system.action.ajax;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

//import com.changpeng.models.system.*;

/**
 * 验证卡号是否唯一
 */
public class CheckCardnoAction extends AbstractAction {

	private String cardno;
	private boolean isrepeat;

	public boolean getIsrepeat() {
		return isrepeat;
	}

	public void setCardno(String loginname) {
		this.cardno = loginname;
	}

	public String getCardno() {
		return cardno;
	}

	@Override
	protected String go() throws Exception {
		SysUserService service = (SysUserService) this.getBean("sysUserService");
		if (this.cardno != null&&!"".equals(this.cardno)) {
			SysUser sysUser = service.getSysUserByCardNo(cardno);
			isrepeat = false;
			if (sysUser != null)
				isrepeat = true;
		}
		return SUCCESS;
	}
}