package com.changpeng.system.action.ajax;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.system.service.SysGroupService;

//import com.changpeng.models.system.*;
public class GetSubGroupAction extends AbstractAction {

	private Map<Integer,String> groups = new LinkedHashMap<Integer,String>();
	private static Log _LOG = LogFactory.getLog(AbstractAction.class);

	@Override
	protected String go() throws Exception {
		
		System.out.println(parentid);
		
		SysGroupService service = (SysGroupService) this.getBean("sysGroupService");
		try {
			List list = service.getChildGroup(parentid);
			if (list != null && list.size() > 0) {
				for (Object obj : list) {
					SysGroup group = (SysGroup) obj;
					groups.put(group.getGroupid(), group.getGroupname());
				}
			}
			
			System.out.println(groups);
		} catch (Exception e) {
			_LOG.error("获取子部门失误", e);
		}
		return SUCCESS;
	}

	public Map<Integer,String> getGroups() {
		return this.groups;
	}

	private int parentid;

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
