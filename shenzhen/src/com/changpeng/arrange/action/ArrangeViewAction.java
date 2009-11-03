package com.changpeng.arrange.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Arrange;

public class ArrangeViewAction extends AbstractAction{
	private int arrangeid;

	private Arrange arrange;

	@Override
	protected String go() throws Exception {
		
		BasicService basic = (BasicService) getBean("basicService");
		arrange=(Arrange)basic.get(Arrange.class, arrangeid);
		return SUCCESS;
	}

	public Arrange getArrange() {
		return arrange;
	}

	public void setArrangeid(int arrangeid) {
		this.arrangeid = arrangeid;
	}

}
