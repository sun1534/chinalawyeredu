/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysGroup;

/**
 * 系统管理员来查询律师的积分统计情况。默认显示所有的市律协的，雷同Provinceunion的那个
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class GlobalsJifenStaticAction extends AbstractListAction {

	@Override
	protected String go() throws Exception {
		cityunions = new ArrayList();
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class).add(Restrictions.ge("lawyertype", 0));
		dc.setProjection(Projections.projectionList().add(Projections.groupProperty("directunion")).add(
				Projections.count("directunion")));

		List _tongjilist = basicService.findAllByCriteria(dc);

		if (_tongjilist == null || _tongjilist.size() == 0) {
			this.message = "没有律师信息,请返回";
			return "message";
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List groupids = new ArrayList();
		for (int i = 0; _tongjilist != null && i < _tongjilist.size(); i++) {
			Object[] obj = (Object[]) _tongjilist.get(i);
			map.put(Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[1].toString()));
			groupids.add(Integer.parseInt(obj[0].toString()));
		}

		dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("grouptype", 2));
		dc.add(Restrictions.in("groupid", groupids));
		List _list = basicService.findAllByCriteria(dc);

		for (int i = 0; i < _list.size(); i++) {
			SysGroup group = (SysGroup) _list.get(i);
			if (map.containsKey(group.getGroupid()))
				group.setUsercnts(map.get(group.getGroupid()));
			cityunions.add(group);
		}
		return SUCCESS;
	}

	private List cityunions;

	public List getCityunions() {
		return this.cityunions;
	}
}