package com.sxit.info.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.*;



/**
 *
 * <p>功能： 删除附件</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class AttachDeleteAction extends AbstractAction {

	private TinfAttach attach;
	private Long attachid;

	public AttachDeleteAction() {
		 rights="inf";
	}
	
	public String go() throws HibernateException {
		Session session = getSession();
		TinfAttach attach = (TinfAttach) getSession().get(TinfAttach.class,
				attachid);

		String filepath = attach.getFilepath();
		AttachFile.delete(filepath);


		getSession().delete(attach);
//		nextpage = "infoView.action?infoid=" + attach.getTinfInfo().getInfoid();
		message = "删除成功！";
		return SUCCESS;
	}

	public TinfAttach getAttach() {
         if (attach==null)
            attach = (TinfAttach) get("attach");
          return attach;
	}
	
	public void setAttachid(Long attachid){
		this.attachid = attachid;
	}
	
	public void setNextpage(String nextpage){
		this.nextpage = nextpage;
	}

}
