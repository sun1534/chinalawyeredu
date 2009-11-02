package com.changpeng.diaocha.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;
public class SetLogicAction extends AbstractAction{
	private int optionid;
	private int wentiid;
	
	public void setOptionid(int optionid) {
		this.optionid = optionid;
	}

	public void setWentiid(int wentiid) {
		this.wentiid = wentiid;
	}

/*	public int getOptionid() {
		return optionid;
	}

	public int getWentiid() {
		return wentiid;
	}
*/

	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		//debug(optionid+"     "+wentiid);
		Diaochaoption option=(Diaochaoption)service.get(Diaochaoption.class,optionid);
		option.setLogicwenti(wentiid);
		service.update(option);
		return SUCCESS;
	}

}
