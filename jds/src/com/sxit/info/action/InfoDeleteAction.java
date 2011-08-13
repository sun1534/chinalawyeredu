package com.sxit.info.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;




/**
 *
 * <p>功能： 删除信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoDeleteAction extends AbstractAction {

	private TinfInfo info;

	public InfoDeleteAction() {
		rights = "inf3,8";
		nextpage = "infoCreateList.action?pagenumber=" + pagenumber;
	}

	public String go() throws HibernateException {
		info = (TinfInfo) get("info");
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
			System.out.println("path======"
					+ ServletActionContext.getServletContext().getRealPath("")
					+ filepath.replace("/", "\\"));
			String filepath2 = ServletActionContext.getServletContext()
					.getRealPath("")
					+ filepath.replace("/", "\\");
			String dirpath = filepath2
					.substring(0, filepath2.lastIndexOf("\\"));
			File file = new File(filepath2);
			if (file.exists()) {
				file.delete();
			}
			File dir = new File(dirpath);
			if (dir.exists()) {
				dir.delete();
			}

		}
		session.createQuery(hqlDeleteAttach).executeUpdate();

	}
}
