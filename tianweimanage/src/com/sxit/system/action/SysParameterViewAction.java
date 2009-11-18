/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysParameter;

/**
 * 
 * 用户信息修改
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysParameterViewAction extends AbstractAction {

	
	private BasicService service;

	private String paramname;
	private boolean theurl;
	public boolean getTheurl() {
		return theurl;
	}

	/**
	 * @return the paramname
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * @param paramname the paramname to set
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}


	
	
	
	public SysParameterViewAction(){
		service=(BasicService)this.getBean("basicService");
	}
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		SysParameter sysParameter=(SysParameter)service.get(SysParameter.class, paramname);
		this.value=sysParameter.getParamvalue();
		if(paramname.equals("adagent")){
			theurl=true;
		}else if(paramname.equals("onlinehelp")){
			theurl=true;
		}else if(paramname.equals("newmedia")){
			theurl=true;
		}else if(paramname.equals("cooper")){
			theurl=true;
		}else if(paramname.equals("videoshop")){
			theurl=true;
		}else if(paramname.equals("corpinfo")){
			theurl=true;
		}
		
//		this.message="参数信息修改成功,请确认";
//		this.nextPage="sysParameterList.action";
		return SUCCESS;
	}
	
	private String value;
	public String getValue(){
		return this.value;
	}
	


}
