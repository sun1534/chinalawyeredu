package com.sxit.info.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;

import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 审批信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoApproveAction extends AbstractAction {
	private TinfInfo info;
    private int infoid=0;
    private List infoAttachList;
	Boolean isedit = false;
    
	public InfoApproveAction() {
          rights="inf2,1";
          info = new TinfInfo();
	}
	
	@Override
	public String go() throws HibernateException {
		if(infoid==0){
			message="没有这个信息"; 
			return "message";
		}
 
		nextpage="infoApproveList.action?pagenumber="+pagenumber;
		TsysUser curuser = (TsysUser)get("curuser");
		info = (TinfInfo)getSession()
				.get(TinfInfo.class, Long.valueOf(infoid));
		if (info == null) {
			message = "未找到此信息";
			return "message";
		}
		if(info.getStatusid()!=2){
			message = "信息不存在";
			return "message";
		}
		infoAttachList = getQueryInfoAttachList().list();
		set("info", info);
		set("infoAttachList", infoAttachList);
		isedit = Power.hasPower(getSession(), curuser, "edit", info.getTinfType().getTypeid());

		return SUCCESS;
	}
	
	private Query getQueryInfoAttachList() throws HibernateException {
		String queryName;
		queryName = "from TinfAttach where tinfInfo.infoid=" + infoid + " ";
		Query query = getSession().createQuery(queryName);
		return query;
	}

	
	public TinfInfo getInfo() {
//		System.out.println("1info========"+info.getInfoid());
		if (info == null)
			info = (TinfInfo) get("info");
		return this.info;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}

//	public int getInfoid() {
//		return this.infoid;
//	}
	
	public Boolean getIsedit(){
		return this.isedit;
	}
	
	public List getInfoAttachList(){
		return this.infoAttachList;
	}

}
