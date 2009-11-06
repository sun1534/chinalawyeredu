package com.changpeng.core.progress.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CorePublish;

public class UnOrderAction extends AbstractAction {

	private int id;

	
	public UnOrderAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		String result=SUCCESS;
		
		CorePublish publish=(CorePublish)service.get(CorePublish.class, id);

		if(publish.getUserid()==this.currentUserid&&publish.getStatusid()<3){
			service.delete(publish);
			this.result="ok";
		}
		return SUCCESS;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
