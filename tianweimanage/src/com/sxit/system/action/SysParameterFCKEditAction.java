/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.CommonDatas;
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
public class SysParameterFCKEditAction extends AbstractAction {

	
	private static Log LOG = LogFactory.getLog(SysParameterFCKEditAction.class);
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


	
//	public SysParameter getSysParameter(){
//		if(sysParameter==null)
//			sysParameter= (SysParameter)get("sysParameter");
//		return sysParameter;
//		
//	}
	
	public SysParameterFCKEditAction(){
		service=(BasicService)this.getBean("basicService");
	}
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		LOG.debug("主键值:::"+paramname);
		
		SysParameter sysParameter=(SysParameter)service.get(SysParameter.class, paramname);
		sysParameter.setParamvalue(paramvalue);
		
		service.update(sysParameter);
		synchronized (CommonDatas.SysParameter) {
			CommonDatas.SysParameter.remove(sysParameter.getParamname());
			CommonDatas.SysParameter.put(sysParameter.getParamname(), sysParameter.getParamvalue());
		}
		
		this.message="信息修改成功,请确认";
		this.nextPage="sysParameterView.action?paramname="+paramname;
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		SysParameter sysParameter=(SysParameter)service.get(SysParameter.class, paramname);
//		set("sysParameter", sysParameter);
		this.paramvalue=sysParameter.getParamvalue();
		return INPUT;
	}
	
	private String paramvalue;

	public BasicService getService() {
		return service;
	}

	public void setService(BasicService service) {
		this.service = service;
	}

	public String getParamvalue() {
		return paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

}
