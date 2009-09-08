/**
 * ArrangeListAction.java
 */

package com.changpeng.arrange.action;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Arrange;
import com.changpeng.models.Arrangesignup;
import com.changpeng.models.system.SysRole;

/**
 * <pre>
 * 培训的显示，现在分为律协活动和岗位培训，通过arrangetype区分开
 * 
 * 可以对报名情况进行删除以及可以对报名情况进行修改
 * 
 * 
 * </pre>
 * 
 * @author 华锋 2008-5-5 上午01:15:02
 * 
 */
public class ArrangesignupListAction extends AbstractListAction {

	private boolean officenameinput=true;
	public boolean getOfficenameinput(){
		return this.officenameinput;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicService basic = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Arrangesignup.class);

		int groupid = -2;
		Set<SysRole> _roles = this.getLoginUser().getSysRoles();
		if (_roles != null && _roles.size() != 0) {
			Iterator<SysRole> roles = _roles.iterator();
			short temproleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				short roleid = role.getRoleid();
				if (temproleid <= roleid) {
					temproleid = roleid;
				}
			}
			if (temproleid == 2) {// 是事务所管理员，这个人是
				this.officename = this.getLoginUser().getSysGroup().getGroupname();
				this.officenameinput = false;
				groupid = this.getLoginUser().getSysGroup().getGroupid();
			}
		}
		
		
		Arrange arrange = (Arrange) basic.get(Arrange.class, arrangeid);

		detachedCriteria.add(Restrictions.eq("arrange", arrange));
		if (peixunren != null && !"".equals(peixunren)) {
			detachedCriteria.add(Restrictions.like("arrangeperson", peixunren, MatchMode.ANYWHERE));
		}
		if (shixizheng != null && !"".equals(shixizheng)) {
			detachedCriteria.add(Restrictions.like("shixizhenghao", shixizheng, MatchMode.ANYWHERE));
		}
		if (zigezheng != null && !"".equals(zigezheng)) {
			detachedCriteria.add(Restrictions.like("zigezhenghao", zigezheng, MatchMode.ANYWHERE));
		}
		if (lawerno != null && !"".equals(lawerno)) {
			detachedCriteria.add(Restrictions.like("lawerno", lawerno, MatchMode.ANYWHERE));
		}
		if (groupid!=-2) {
			detachedCriteria.add(Restrictions.eq("groupid", groupid));
		}
		else if (officename != null && !"".equals(officename)) {
			detachedCriteria.add(Restrictions.like("groupname", officename, MatchMode.ANYWHERE));
		}
		

		detachedCriteria.addOrder(Order.desc("createtime"));

		// this.arrangelist=basic.findAllByCriteria(detachedCriteria);

		this.page = basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		if (arrange.getArrangetype() == 1)
			return "gangqianpeixun";
		else
			return "lxhuodong";
	}

	// private List arrangelist;
	// public List getArrangelist(){
	// return this.arrangelist;
	// }

	private int arrangeid;

	/**
	 * @return the arrangetype
	 */
	public int getArrangeid() {
		return arrangeid;
	}

	/**
	 * @param arrangetype
	 *            the arrangetype to set
	 */
	public void setArrangeid(int arrangeid) {
		this.arrangeid = arrangeid;
	}

	// 培训人:<s:textfield name="peixunren"/>
	// 实习证号:<s:textfield name="shixizheng"/>
	// 资格证号:<s:textfield name="zigezheng"/>
	//	

	private String peixunren;
	private String shixizheng;
	private String zigezheng;

	/**
	 * @return the peixunren
	 */
	public String getPeixunren() {
		return peixunren;
	}

	/**
	 * @param peixunren
	 *            the peixunren to set
	 */
	public void setPeixunren(String peixunren) {
		this.peixunren = peixunren;
	}

	/**
	 * @return the shixizheng
	 */
	public String getShixizheng() {
		return shixizheng;
	}

	/**
	 * @param shixizheng
	 *            the shixizheng to set
	 */
	public void setShixizheng(String shixizheng) {
		this.shixizheng = shixizheng;
	}

	/**
	 * @return the zigezheng
	 */
	public String getZigezheng() {
		return zigezheng;
	}

	/**
	 * @param zigezheng
	 *            the zigezheng to set
	 */
	public void setZigezheng(String zigezheng) {
		this.zigezheng = zigezheng;
	}
	
	private String officename;

	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}

	/**
	 * @param officename the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}
	
	private String lawerno;
	/**
	 * @return the lawerno
	 */
	public String getLawerno() {
		return lawerno;
	}

	/**
	 * @param lawerno the lawerno to set
	 */
	public void setLawerno(String lawerno) {
		this.lawerno = lawerno;
	}
	

}
