/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysParameter;

/**
 * 
 * 用户信息修改
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysParameterEditAction extends AbstractAction {

	
	private static Log LOG = LogFactory.getLog(SysParameterEditAction.class);
	private static final DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
	
	private BasicService service;
	
	private SysParameter sysParameter;
	private String paramname;
	
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

	public void setUserid(String paramname){
		this.paramname=paramname;
	}
	
	public SysParameter getSysParameter(){
		if(sysParameter==null)
			sysParameter= (SysParameter)get("sysParameter");
		return sysParameter;
		
	}
	
	public SysParameterEditAction(){
		service=(BasicService)this.getBean("basicService");
		this.rightCode="sysParameterEdit";
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		LOG.debug("主键值:::"+sysParameter.getParamname());
		
		service.update(sysParameter);
		synchronized (CommonDatas.SysParameter) {
			CommonDatas.SysParameter.remove(sysParameter.getParamname());
			CommonDatas.SysParameter.put(sysParameter.getParamname(), sysParameter.getParamvalue());
		}
		
		this.message="参数信息修改成功,请确认";
		this.nextPage="sysParameterList.pl";
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		SysParameter sysParameter=(SysParameter)service.get(SysParameter.class, paramname);
		set("sysParameter", sysParameter);
		return INPUT;
	}

}
