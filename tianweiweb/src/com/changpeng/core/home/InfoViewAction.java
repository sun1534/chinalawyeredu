package com.changpeng.core.home;


import org.apache.log4j.Logger;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.TinfInfo;

public class InfoViewAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(InfoViewAction.class);
	
	private TinfInfo info;
	
	public InfoViewAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {

		
		BasicService service=(BasicService)this.getBean("basicService");
		this.info=(TinfInfo)service.get(TinfInfo.class, infoid);
		
		
		return SUCCESS;

	}
	private int infoid;
	public void setInfoid(int infoid){
		this.infoid=infoid;
	}

	public TinfInfo getInfo() {
		return info;
	}


	


}
