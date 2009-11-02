/**
 * ElearningInterfaceRequests.java
 */

package com.changpeng.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.context.Globals;
import com.changpeng.models.system.SysParameter;
import com.changpeng.system.service.SysRightService;
import com.changpeng.system.util.RightTree;

/**
 * @author 华锋 2008-6-9 下午05:06:41
 * 
 */
public class ElearningInterfaceRequests extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(ElearningInterfaceRequests.class);
	private String dotype;

	public void setDotype(String dotype) {
		this.dotype = dotype;
		this.needsession=false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		try {
			if (this.dotype != null && !this.dotype.equals("") && this.dotype.equals("add")) {
				if (RightTree.rightMap.size() == 0) {
					SysRightService rightService = (SysRightService) getBean("sysRightService");
					BasicService basicService = (BasicService) getBean("basicService");
					List list = basicService.findAll(SysParameter.class);
					int length = list == null ? 0 : list.size();
					for (int i = 0; i < length; i++) {
						SysParameter param = (SysParameter) list.get(i);
						CommonDatas.SysParameter.put(param.getParamname(), param.getParamvalue());
					}
					RightTree.setRightList(rightService.findAll());
				}
				msg = "新增成功";
			}
			else {
				CommonDatas.SysParameter.clear();
				RightTree.rightMap.clear();
				RightTree.rightNameMap.clear();
				msg = "清除成功";
			}
			
		}
		catch (Exception e) {
			_LOG.error("错误:" + e);
			msg = "失败:" + e;
		}
		return SUCCESS;
	}

	private String msg;

	public String getMsg() {
		return this.msg;
	}
}
