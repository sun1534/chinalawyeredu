/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * 
 * 律协列表,包括省级律协和市律协。市律协和事务所列表没这个功能，即使有的话，也提示看不了
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TheUnionListAction extends AbstractListAction {

	private String groupname;

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname
	 *            the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

//	private SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");

	public TheUnionListAction() {
		this.datavisible = new DataVisible();
	}

	private int grouptype;

	/**
	 * @return the grouptype
	 */
	public int getGrouptype() {
		return grouptype;
	}

	/**
	 * @param grouptype
	 *            the grouptype to set
	 */
	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}

	private boolean grouptypeview;

	public boolean getGrouptypeview() {
		return this.grouptypeview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 * 
	 * 根据parentid得到下面一层的所有group;
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		// 也显示全国律协的以及系统层级的
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		SysGroup mygroup = this.getLoginUser().getSysGroup();

		// System.out.println(mygroup);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysGroup.class).add(
				Restrictions.eq("delflag", false));
		if (mygroup != null && (mygroup.getGrouptype() == 1 || mygroup.getGrouptype() == 2)) {
			this.message = "您没有查看律协列表的权利,请返回";
			this.nextPage = "javascript:history.go(-1)";
			return "message";
		}

		else if (mygroup != null && mygroup.getGrouptype() == 3) { // 省级律协,只能看自己下面的律协的

			detachedCriteria.add(Restrictions.eq("grouptype", 2)); // 包括省律协和市律协
		} else {
			grouptypeview = true;
			if (grouptype == 0)

				detachedCriteria.add(Restrictions.in("grouptype", new Object[] { 2, 3 })); // 包括省律协和市律协
			else
				detachedCriteria.add(Restrictions.eq("grouptype", grouptype)); // 包括省律协和市律协

		}

		if (groupname != null && !"".equals(groupname)) {
			detachedCriteria.add(Restrictions.like("groupname", groupname.trim(), MatchMode.ANYWHERE));
		}

		if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("parentid", datavisible.getProvinceid()));
		}

		detachedCriteria.addOrder(Order.desc("groupid"));
		BasicService service = (BasicService) this.getBean("basicService");

		SysUser user=this.getLoginUser();
		if(user.getLoginname().equals("admin")||user.getRightList().contains("sysGroupExcludeRight")){
			hasright=true;
		}
		
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}
	private boolean hasright;
	public boolean getHasright(){
		return this.hasright;
	}
}