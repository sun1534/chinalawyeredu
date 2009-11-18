package com.sxit.content.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.CorePublish;

/**
 * 1,家庭 2企业',
 * 
 * 根据姓名，注册的起始终止时间，状态以及身份证号码进行查询
 * 
 * @author 华锋 Jul 9, 2009 11:16:21 PM
 * 
 */

public class TvConfirmListAction extends AbstractListAction {

	public TvConfirmListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {

		com.sxit.content.util.CommonDatas.getAllContentTypes();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CorePublish.class);
		// if (userName != null && !userName.equals("")) {
		// detachedCriteria.add(Restrictions.like("userName", userName,
		// MatchMode.START));
		// }
		//		
		detachedCriteria.add(Restrictions.eq("userRole", userRole));
		List uidlist = new ArrayList();
		if (userName != null && !userName.equals("")) {
			String hql = "select id from core_user where user_name = '" + userName + "'";
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
			detachedCriteria.add(Restrictions.ge("createtime", starttime));
		}
		if (end != null && !end.equals("")) {
			java.sql.Timestamp endtime = new java.sql.Timestamp(df.parse(end).getTime());
			detachedCriteria.add(Restrictions.le("createtime", endtime));
		}

		if (statusid != -1) {
			detachedCriteria.add(Restrictions.eq("statusid", statusid));
		}
		if (productid != -1) {
			detachedCriteria.add(Restrictions.eq("productid", productid));
		}
		if (order.equals("desc"))
			detachedCriteria.addOrder(Order.desc(orderfield));
		else
			detachedCriteria.addOrder(Order.asc(orderfield));

		if (totype == null || totype.equals(""))
			totype = "list"+userRole;

		if (totype != null && totype.equals("excel")) {
			this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);

		} else{
			this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			totype = "list"+userRole;
		}
		return totype;
	}

	private String totype;

	private short statusid = -1;
	private int productid = -1;
	private String userName;
	private String start;
	private String end;
	private String orderfield = "createtime";
	private String order = "desc";
	private short userRole;

	public short getUserRole() {
		return userRole;
	}

	public void setUserRole(short userRole) {
		this.userRole = userRole;
	}

	public String getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public short getStatusid() {
		return statusid;
	}

	public void setStatusid(short statusid) {
		this.statusid = statusid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}
private String from;

public String getFrom() {
	return from;
}

public void setFrom(String from) {
	this.from = from;
}

}
