/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.models.Lxnetrecs;

/**
 * <pre>
 * 看视频加分
 * ajax的方式来处理，异步
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午01:21:00
 * 
 */
public class LogVideoLookOutAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(LogVideoLookOutAction.class);
	private int id;

	public LogVideoLookOutAction() {
		this.needsession = false;
	}
	public String go() throws Exception {

		try {
			java.sql.Timestamp outtime = new java.sql.Timestamp(System.currentTimeMillis());
			basicService.execute("update LogVideoLook a set a.outtime=? where a.id=?", new Object[] { outtime, id });
		} catch (Exception e) {

			LOG.error("更新失败:" + e);
		}
		return SUCCESS;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
