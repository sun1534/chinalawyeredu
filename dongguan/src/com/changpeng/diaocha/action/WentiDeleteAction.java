package com.changpeng.diaocha.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;

import com.changpeng.models.*;
import com.changpeng.models.system.SysUser;

public class WentiDeleteAction extends AbstractAction{
	private int wentiid;

	public void setWentiid(int wentiid) {
		this.wentiid = wentiid;
	}
	public WentiDeleteAction(){
		this.rightCode="diaocha";	
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		
		//service.execute("delete from Diaochaoption where diaochawenti.wentiid="+wentiid+"");
		
		service.delete(Diaochawenti.class, wentiid);
		this.message="删除调查问题成功";
		return SUCCESS;
	}
}
