package com.changpeng.courseware.action;
import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;
public class CoursetypeCreateAction extends AbstractAction{
	private Coursetype type;
	public CoursetypeCreateAction(){
		this.rightCode="courseware";
		type=new Coursetype();
	}
	public Coursetype getType() {
		return type;
	}
	public void setType(Coursetype type) {
		this.type = type;
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");		
		type.setCreateuser(getLoginUser().getLoginname());
		type.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		type.setWarecount(0);
		service.save(type);
		this.nextPage="coursetypeList.pl";
		this.message="新增课件类别成功";
		return SUCCESS;
	}
	public String input(){			
		return INPUT;
	}

}
