package com.sxit.info.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.info.model.TinfType;

/**
 * 
 * <p>
 * 功能： 列表权限
 * </p>
 * <p>
 * 作者： 吴桂荣
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-08-28
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class SetListAction extends AbstractListAction {
	private List typelist;
	private Long type=0L;

	public SetListAction() {
		rights = "inf5,1";
	}

	public String go() throws HibernateException {
		Criteria criteria = getSession().createCriteria(TinfType.class);
		criteria.addOrder(Order.asc("typeid"));
		typelist = criteria.list();
		if(type==0){
			LOG.debug("aaa");
		}
		return SUCCESS;
	}

	public List getTypelist() {
		return typelist;
	}
	
	public void setTypelist(List typelist) {
		this.typelist = typelist;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}


}
