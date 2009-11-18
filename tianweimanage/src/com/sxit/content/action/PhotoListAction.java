package com.sxit.content.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.SnsPhoto;

/**
 * 相片管理
 * 
 */

public class PhotoListAction extends AbstractListAction {

	public PhotoListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
		
		com.sxit.content.util.CommonDatas.getAllContentTypes();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
	
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

		if (albumid != -1) {
			detachedCriteria.add(Restrictions.eq("albumid", albumid));
		}

		detachedCriteria.addOrder(Order.desc("photoid"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	// 默认显示待审批的
	private short statusid =-1;

	// 上传人
	private String userName;
	// 上传时间
	private String start;
	private String end;

	private int albumid = -1;

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

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

}
