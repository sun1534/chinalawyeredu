/**
 * 
 */
package com.sxit.info.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.TinfInfo;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;

/**
 * @author sg
 * @TODO 
 * @createtime 2008-9-4
 * @version v1.0
 */
public class InfoPromAction extends AbstractAction {
	
	private TinfInfo info;
	private int infoid=0;
	private List infoAttachList;
	Boolean isedit = false;
	String type = "";

	public InfoPromAction() {
        rights="inf4,4";
	}
	@Override
	protected String go() throws JDBCException, HibernateException {
		if(infoid==0){
			message="没有这个信息"; 
			return "message";
		}
 
		nextpage="infoPromList.action?type="+type+"&pagenumber="+pagenumber;
		curuser = (TsysUser)get("curuser");
		info = (TinfInfo)getSession()
				.get(TinfInfo.class, Long.valueOf(infoid));
		if (info == null) {
			message = "未找到此信息";
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
		if (info == null)
			info = (TinfInfo) get("info");
		return info;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public Boolean getIsedit(){
		return this.isedit;
	}
	
	public List getInfoAttachList(){
		return this.infoAttachList;
	}
	
	public void setInfoid(int infoid){
		this.infoid = infoid;
	}

}
