/**
 * TSysUserAddAction.java
 */
package com.sxit.communicateguard.action;

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
 * 设置tracert时候的ip和名称,和sysparameteraction一样
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class IpdescSetAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(IpdescSetAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private BasicService service;

	private String type;

	private String paramname;

	/**
	 * @return the paramname
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * @param paramname
	 *            the paramname to set
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public void setUserid(String paramname) {
		this.paramname = paramname;
	}

	public IpdescSetAction() {
		service = (BasicService) this.getBean("basicService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		if (type != null && type.equals("delete")) {

			SysParameter s = (SysParameter) service.get(SysParameter.class, paramname);
			service.delete(s);
			this.message = "Tracert测试IP说明删除成功";

			CommonDatas.SysParameter.remove(paramname);
		} else if (type != null && type.equals("modify")) {
			SysParameter sysParameter = (SysParameter) service.get(SysParameter.class, paramname);
			if (sysParameter != null) {
				sysParameter.setParamvalue(pvalue);
				service.update(sysParameter);
				this.message = "Tracert测试IP说明修改成功";
				CommonDatas.SysParameter.remove(paramname);
				CommonDatas.SysParameter.put(paramname, pvalue);
			}
		
		} else if (type != null && type.equals("new")) {
			SysParameter sysParameter = (SysParameter) service.get(SysParameter.class, paramname);
			if (sysParameter != null) {
				this.message = "该IP说明已经存在,不能重复,请返回";
				return SUCCESS;
			}

			SysParameter s = new SysParameter();
			s.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			s.setParamname(paramname);
			s.setParamvalue(pvalue);
			s.setTypeid(1);
			service.save(s);
			this.message = "Tracert测试IP说明新增成功";

			CommonDatas.SysParameter.remove(paramname);
			CommonDatas.SysParameter.put(paramname, pvalue);
		
		} else {
			this.message = "参数不正确，请确认";
			
		}
		this.nextPage="ipdescList.action";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SysParameter sysParameter = null;
		if (paramname != null && !paramname.equals(""))
			sysParameter = (SysParameter) service.get(SysParameter.class, paramname);
		if (sysParameter != null) {
			this.paramname = sysParameter.getParamname();
			this.pvalue = sysParameter.getParamvalue();
		}
		return INPUT;
	}

	private String pvalue;

	/**
	 * @return the pvalue
	 */
	public String getPvalue() {
		return pvalue;
	}

	/**
	 * @param pvalue
	 *            the pvalue to set
	 */
	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
