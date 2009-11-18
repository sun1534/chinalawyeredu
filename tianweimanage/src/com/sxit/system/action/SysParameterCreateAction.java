/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import com.sxit.common.BasicService;
import com.sxit.common.CommonDatas;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysParameter;

/**
 * 
 * 新增参数
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysParameterCreateAction extends AbstractAction {

	private SysParameter sysParameter;

	public SysParameter getSysParameter() {
		return sysParameter;
	}

	public SysParameterCreateAction() {
		this.rightCode = "sysParameterCreate";
		this.sysParameter = new SysParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		BasicService service = (BasicService) this.getBean("basicService");

		// sysParameter.setPassword(password1);
	
		sysParameter.setTypeid(typeid);
		service.save(sysParameter);
		synchronized (CommonDatas.SysParameter) {
			CommonDatas.SysParameter.put(sysParameter.getParamname(), sysParameter.getParamvalue());
		}
		this.nextPage = "sysParameterList.action";
		if(typeid!=0)
			this.nextPage="sysParameterList.action?typeid="+typeid;
		this.message = "系统参数新增成功";
		return SUCCESS;
	}
	private int typeid;

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 */
	@Override
	public String input() throws Exception {
		//
		return INPUT;
	}

//	private String passagain;
//
//	public String getPassagain() {
//		return this.passagain;
//	}
//
//	public void setPassagain(String passagain1) {
//		this.passagain = passagain1;
//	}
	// private String password1;
	// public String getPassword1(){
	// return this.password1;
	// }
	// public void setPassword1(String password1){
	// this.password1=password1;
	// }

}
