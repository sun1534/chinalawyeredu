/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 新增用户信息,新增成功后,转到信息提示页面吧
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupCreateEditAction extends AbstractAction {
	private BasicService bservice = null;
	private SysGroup sysGroup;

	public SysGroup getSysGroup() {
		if (sysGroup == null)
			sysGroup = (SysGroup) get("sysGroup");
		return sysGroup;
	}

	public SysGroupCreateEditAction() {

		bservice = (BasicService) this.getBean("basicService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		sysGroup.setGrouptype(4);
		if (sysGroup.getGroupenname() == null || sysGroup.getGroupenname().equals(""))
			sysGroup.setGroupenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(sysGroup.getGroupname()));

		SysGroup parent = (SysGroup) bservice.get(SysGroup.class, sysGroup.getParentid());
		int grouplevel = 1;
		if (parent != null) {
			grouplevel = parent.getGrouplevel() + 1;
		}
		sysGroup.setGrouplevel(grouplevel);
		sysGroup.setDirectgroup(sysGroup.getParentid());
		if (!isedit) {
			sysGroup.setCreateuser(getLoginUser().getUsername());
			sysGroup.setCreatetype(1);
			sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysGroup.setDelflag(false);
			sysGroup.setSystemno(System.currentTimeMillis() / 1000 + "");

			bservice.save(sysGroup);
			this.message = "部门信息新增成功";

		} else {

			bservice.update(sysGroup);
			this.message = "部门信息修改成功";
		}

		this.nextPage = "sysGroupList.pl";

		CommonDatas.getGroups();
		// System.out.println("刷新下group的信息");
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService service = (BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysGroup.class);
		Criterion c4 = Restrictions.eq("grouptype", 4);
		Criterion c5 = Restrictions.eq("grouptype", 5);
		detachedCriteria.add(Restrictions.or(c4, c5));
		detachedCriteria.addOrder(Order.desc("groupid"));
		this.parentList = service.findAllByCriteria(detachedCriteria);

		this.sysGroup = (SysGroup) service.get(SysGroup.class, groupid);
		if (sysGroup == null) {
			sysGroup = new SysGroup();
			isedit = false;
		} else {
			isedit = true;
			//这里除了排除自己,还要排除我现在的那些个下属
			for (int i = 0; parentList != null && i < parentList.size(); i++) {
				SysGroup group = (SysGroup) parentList.get(i);
				if (group.getGroupid() == groupid) {
					parentList.remove(group);
					break;
				}
			}
		}
		set("sysGroup", sysGroup);
		return INPUT;
	}

	private List parentList;

	public List getParentList() {
		return this.parentList;
	}

	private boolean isedit;

	/**
	 * @return the isedit
	 */
	public boolean isIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	private int groupid;

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
}