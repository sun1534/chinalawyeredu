package com.changpeng.diaocha.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Diaocha;

public class DiaochaDeleteAction extends AbstractAction{
	private int diaochaid;

	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}
	public DiaochaDeleteAction(){
		this.rightCode="diaocha";	
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		service.delete(Diaocha.class, diaochaid);
		this.message="调查删除成功";
		this.nextPage="diaochaList.pl";
		return SUCCESS;
	}
}
