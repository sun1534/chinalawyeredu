package com.sxit.content.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.SnsFile;

/**
 * 1,家庭 2企业',
 * 
 * 根据姓名，注册的起始终止时间，状态以及身份证号码进行查询
 * 
 * @author 华锋 Jul 9, 2009 11:16:21 PM
 * 
 */

public class VideoListAction extends AbstractListAction {

	public VideoListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
		
		com.sxit.content.util.CommonDatas.getAllContentTypes();
		com.sxit.users.util.CommonDatas.getAllUsers();
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsFile.class);
	
		List uidlist = new ArrayList();
		if (userName != null && !userName.equals("")) {
			String hql = "select id from core_user where user_name like '" + userName + "%'";
			List list = basicService.findBySqlQuery(hql);
			for (int i = 0; list != null && i < list.size(); i++) {
				uidlist.add(Integer.parseInt(list.get(i).toString()));
			}
		}
		// userid
		if (uidlist.size() != 0) {
			detachedCriteria.add(Restrictions.in("userid", uidlist));
		}

		if (start != null && !start.equals("")) {
			java.sql.Timestamp starttime = new java.sql.Timestamp(df.parse(start).getTime());
			detachedCriteria.add(Restrictions.ge("createTime", starttime));
		}
		if (end != null && !end.equals("")) {
			java.sql.Timestamp endtime = new java.sql.Timestamp(df.parse(end).getTime());
			detachedCriteria.add(Restrictions.le("createTime", endtime));
		}

		if (statusid != -1) {
			detachedCriteria.add(Restrictions.eq("statusid", statusid));
		}

		if (dirid != -1) {
		
			detachedCriteria.add(Restrictions.eq("dirid", dirid));
		}
		
		if(typeid!=-1){
			detachedCriteria.add(Restrictions.eq("typeid", typeid));
		}
		

		detachedCriteria.addOrder(Order.desc("id"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	private int typeid=0;
	// 默认显示待审批的
	private short statusid =-1;

	// 上传人
	private String userName;
	// 上传时间
	private String start;
	private String end;

	private int dirid = -1;

	public short getStatusid() {
		return statusid;
	}

	public void setStatusid(short statusid) {
		this.statusid = statusid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDirid() {
		return dirid;
	}

	public void setDirid(int dirid) {
		this.dirid = dirid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}


}
