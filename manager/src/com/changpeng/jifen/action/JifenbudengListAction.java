/**
 * JifenbudengListAction.java
 */

package com.changpeng.jifen.action;

import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;

/**
 * @author 华锋 2008-5-4 下午11:28:45
 * 
 */
public class JifenbudengListAction extends AbstractListAction {
	public JifenbudengListAction() {
		// this.rightCode="jifenbudeng";
		this.datavisible = new DataVisible();

	}
	private boolean gongzheng;
	public boolean getgongzheng(){
		return gongzheng;
	}
	/**
	 * 判断这个人的角色,是不是管理员，如果使系统管理员，显示所有补登的情况 <br/> 事务所管理员没这个功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String go() throws Exception {

		
		
		// SysUser user = (SysUser) this.getLoginUser();
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Jifenbudeng.class);

		SysRole role = this.getLoginUser().getSysRole();
		if(role!=null&&(role.getRoleid()==11||role.getRoleid()==12)){
			gongzheng=true;
		}
		
		if (role != null) {

			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
				if (v.getThetable().equalsIgnoreCase("jifenbudeng")) {
					rolevisible = v;
					break;
				}
			}

			// 权限判断了
			if (rolevisible != null) {
				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), rolevisible.getThefield())));
			}
		}
		if (lawyername != null && !"".equals(lawyername)) {
			detachedCriteria.add(Restrictions.like("lawyername", lawyername, MatchMode.ANYWHERE));
		}
		if (lawyerno != null && !"".equals(lawyerno)) {
			detachedCriteria.add(Restrictions.like("lawyerno", lawyerno, MatchMode.ANYWHERE));
		}

		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("officeid", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("cityid", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceid", datavisible.getProvinceid()));
		}

		detachedCriteria.addOrder(Order.desc("createtime"));
		BasicService basic = (BasicService) getBean("basicService");
		this.page = basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	// 根据律师名字、律师执业资格证号、事务所名称来查询补登情况
	private String lawyerno;
	private String lawyername;

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the lawername
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawername
	 *            the lawername to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

}
