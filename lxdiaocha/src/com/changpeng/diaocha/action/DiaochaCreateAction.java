package com.changpeng.diaocha.action;


import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.diaocha.service.DiaochaService;
import com.changpeng.models.*;
import java.util.*;
public class DiaochaCreateAction extends AbstractAction{
	private Diaocha diaocha;
	private List<String> typename;
	public DiaochaCreateAction(){
		this.rightCode="diaocha";
		diaocha=new Diaocha();
	}
	public Diaocha getDiaocha() {
		return diaocha;
	}
	public void setDiaocha(Diaocha diaocha) {
		this.diaocha = diaocha;
	}
	
	public void setTypename(List<String> typename){
		this.typename=typename;
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");		
		diaocha.setCreateuser(getLoginUser().getLoginname());
		diaocha.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		diaocha.setReplycount(0);
		service.save(diaocha);
		
		//debug(typename+".................");
		DiaochaService diaochaService=(DiaochaService)this.getBean("diaochaService");
		diaochaService.saveType(typename, diaocha);
		
		this.nextPage="diaochaList.pl";
		this.message="新增调查成功";
		return SUCCESS;
	}
	public String input(){			
		return INPUT;
	}

}
