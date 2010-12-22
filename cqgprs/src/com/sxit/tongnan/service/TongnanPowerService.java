/**
 * TongnanPowerService.java
 */
package com.sxit.tongnan.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.sxit.common.BasicService;
import com.sxit.models.tongnan.StatTongnanpower;

/**
 * @author 华锋 Nov 23, 20106:36:13 PM
 * 
 */
public class TongnanPowerService extends BasicService {

	@Transactional
	public void save(List<StatTongnanpower> powers) {
		// 新增一批数据
		for (StatTongnanpower power : powers) {
			power.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicDAO.save(power);
		}
	}
	
	public void update(String month,String laccell,int zaiping){
		String sql="update stat_tongnanpower set zaipin="+zaiping+" where month='"+month+"' and lac_cell='"+laccell+"'";
		System.out.println(sql);
		basicDAO.executeSql(sql);
	}
	
	
	public List getMonthPowers(String month,String lacCell){
	
		DetachedCriteria dc=DetachedCriteria.forClass(StatTongnanpower.class);
		dc.add(Restrictions.eq("month",month));
		if(lacCell!=null&&!lacCell.equals(""))
		{
			dc.add(Restrictions.eq("lacCell", lacCell));
		}
		dc.addOrder(Order.asc("id"));
		
		return basicDAO.findAllByCriteria(dc);
	}
	
	
}
