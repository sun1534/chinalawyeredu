package com.sxit.info.action;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;

import com.sxit.info.model.*;
import com.sxit.common.action.AbstractAction;
import java.util.*;

/**
 * 
 * <p>
 * 功能： 查询信息
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

public class InfoPromSearchAction extends AbstractAction {
	private List infolist;
	private TinfInfo infoExample = new TinfInfo();
	private String departmentname;
	private String typename;

	public InfoPromSearchAction() {
		rights = "inf4,1";
	}

	protected String go() throws HibernateException {
		Criteria criteria = getSession().createCriteria(TinfInfo.class);

		// 状态
		criteria.add(Expression.in("statusid", new Long[]{new Long(0),new Long(-1),new Long(3),new Long(2)}));

		// 标题
		if (!"".equals(infoExample.getInfoname())) {
			criteria.add(Expression.like("infoname", infoExample.getInfoname(),
					MatchMode.ANYWHERE));
		}
		// 编号
		if (!"".equals(infoExample.getFilenumber())) {
			criteria.add(Expression.like("filenumber", infoExample
					.getFilenumber(), MatchMode.ANYWHERE));
		}
		// 类别
		if (!"".equals(typename)) {
			criteria.createCriteria("tinfType").add(
					Expression.like("name", typename, MatchMode.ANYWHERE));
		}
		// 部门
		if (!"".equals(departmentname)) {
			criteria.createCriteria("department").add(
					Expression.like("departmentname", departmentname,
							MatchMode.ANYWHERE));
		}

		infolist = criteria.setMaxResults(50).list();// 一次最多输出maxpaerpage条记录

		// infolist = criteria.list(); //将结果集一次输出
		return SUCCESS;
	}

	public String input() throws Exception {
		return "input";
	}

	public List getInfolist() {
		return infolist;
	}

	public TinfInfo getInfoExample() {
		return infoExample;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return this.flag;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}
