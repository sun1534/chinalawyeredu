
package com.changpeng.index;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IndexPageAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(IndexPageAction.class);
	private List userMenus;
	private SysUser sysUser;

	public IndexPageAction() {
//		rightCode = "shouye";
	}

	protected String go() throws Exception {
		sysUser = getLoginUser();
		userMenus = sysUser.getUserMenus();
		LOG.info(sysUser.getUsername() + "进入首页成功......");
		return "success";
	}

	public String top() throws Exception {
		sysUser = getLoginUser();
		userMenus = sysUser.getUserMenus();
		sysUser = getLoginUser();
		// LOG.info((new StringBuilder(String.valueOf(sysUser.getUsername()))).append("\u8FDB\u5165\u9996\u9875TOP\u6210\u529F......").toString());
		return "top";
	}

	public String left() throws Exception {
		sysUser = getLoginUser();
		userMenus = sysUser.getUserMenus();
		// LOG.info((new StringBuilder(String.valueOf(sysUser.getUsername()))).append("\u8FDB\u5165\u9996\u9875LEFT\u6210\u529F......").toString());
		return "left";
	}

	public List getUserMenus() {
		return userMenus;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

}
