package com.sxit.info.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.AttachFile;
import com.sxit.system.model.TsysUser;
import com.sxit.wait.util.WaitWork;




/**
 *
 * <p>功能： 删除信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoAdminDeleteAction extends AbstractAction {

	private TinfInfo info;

	public InfoAdminDeleteAction() {
		rights = "inf6,8";
		nextpage = "infoList.action?pagenumber=" + pagenumber;
	}

	public String go() throws HibernateException {
		info = (TinfInfo) get("info");
		
		curuser = (TsysUser) get("curuser");
		int waitid;
		String waitidstring = info.getWaitid();
		if (waitidstring != null && !waitidstring.equals("")) {
			String[] waitids = waitidstring.split(",");
			for (int i = 0; i < waitids.length; i++) {
				waitid = Integer.valueOf(waitids[i]);
				LOG.debug("waitid============"+waitid);
				WaitWork.DelWait(getSession(), waitid, curuser);
			}
		} 
		
		delAttach();
		getSession().delete(info);
		message = "删除成功！";
		return SUCCESS;
	}

	public TinfInfo getInfo() {
		if (info == null)
			info = (TinfInfo) get("info");
		return info;
	}
	
	private void delAttach() {
		Session session = getSession();

		String hqlDeleteAttach = "delete from TinfAttach as attach where attach.tinfInfo.infoid="
				+ info.getInfoid();
		String hqlDeleteFile = "select filepath from TinfAttach as attach where attach.tinfInfo.infoid="
				+ info.getInfoid();
		ArrayList list = (ArrayList) session.createQuery(hqlDeleteFile).list();
		for (int i = 0; i < list.size(); i++) {
			String filepath = (String) list.get(i);
			AttachFile.delete(filepath);

		}
		session.createQuery(hqlDeleteAttach).executeUpdate();

	}
}
