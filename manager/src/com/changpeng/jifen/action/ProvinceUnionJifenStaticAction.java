/**
 * 
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
import com.changpeng.system.service.SysGroupService;

/**
 * @author 华锋 省的积分统计。一个独立的页面 显示下面的律协名称，律师数，达标分，年审时间。然后提供链接进入具体的律协的页面进行查看
 * 
 * 
 * 
 */
public class ProvinceUnionJifenStaticAction extends AbstractListAction {
	@Override
	protected String go() throws Exception {

		int provinceid = this.getLoginUser().getSysGroup().getGroupid();

		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");

		List list = groupservice.getCityUnion(provinceid);

		if (list != null && list.size() > 0) {
			cityunions = new ArrayList();

			DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class).add(Restrictions.ge("lawyertype", 0));
			dc.setProjection(Projections.projectionList().add(Projections.groupProperty("directunion")).add(
					Projections.count("directunion")));

			List _tongjilist = basicService.findAllByCriteria(dc);
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; _tongjilist != null && i < _tongjilist.size(); i++) {
				Object[] obj = (Object[]) _tongjilist.get(i);
				map.put(Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[1].toString()));
			}

			for (int i = 0; i < list.size(); i++) {
				SysGroup group = (SysGroup) list.get(i);
				if (map.containsKey(group.getGroupid()))
					group.setUsercnts(map.get(group.getGroupid()));

				cityunions.add(group);
			}

		} else {
			this.message = "所在的省律协没有下属律协,无数据查看";
			return "message";
		}
		return SUCCESS;
	}

	private List cityunions;

	public List getCityunions() {
		return this.cityunions;
	}
}
