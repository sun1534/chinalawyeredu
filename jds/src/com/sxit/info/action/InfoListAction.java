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
 * 功能： 列表信息
 * </p>
 * <p>
 * 作者： 张如兵
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-08-27
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class InfoListAction extends AbstractListAction {
	private List infolist;
	private long type;
	private List typeList;

	public InfoListAction() {
		rights = "inf6,1";
		type = 0;
	}

	public String go() throws HibernateException {
		Criteria criteria = getSession().createCriteria(TinfType.class);
		criteria.addOrder(Order.asc("typeid"));
		typeList = criteria.list();
		if (type == 0 && typeList.size() > 0) {
			type = ((TinfType) typeList.get(0)).getTypeid();
		}

		infolist = getQuery().setMaxResults(maxperpage).setFirstResult(
				maxperpage * pagenumber).setCacheable(true).list();

		return SUCCESS;
	}

	private Query getQuery() throws HibernateException {
		String queryName;
		queryName = "from TinfInfo as info where info.tinfType.typeid=" + type
				+ " order by info.infoid desc";
		Query query = getSession().createQuery(queryName);
		recordsize = query.list().size();
		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
		return query;
	}

	public List getInfolist() {
		return infolist;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getType() {
		return this.type;
	}
}
