package com.sxit.info.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;

/**
 * 
 * <p>功能： 编辑权限</p>
 * <p>作者： 吴桂荣</p>
 * <p> 公司： 深圳信科</p>
 * <p> 日期： 2008-08-28</p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class SetEditAction extends AbstractAction {

	private TinfSet set;
	private String[] power;
	private List powerlist ;

	public SetEditAction() {
		rights = "inf5,4";
		powerlist = new ArrayList();
		power = new String[] { "approve", "prom", "newinf", "edit", "del",
				"view" };
	}

	public String go() throws HibernateException {
		set = (TinfSet)get("set");
		set.setPowerid(setPower(powerlist));
		getSession().update(set);
		set("set", set);
		nextpage = "setList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public String input() throws Exception {
		set = (TinfSet)get("set");

		TsysUser user = (TsysUser) getSession().get(TsysUser.class,
				set.getUserid());
		for (String p : power) {
			if (Power.hasPower(getSession(), user, p, set.getTinfType()
					.getTypeid())) {
				powerlist.add(Power.getKindNum(p));
			}
		}
		set("set", set);
		return "input";
	}
	
	public Long setPower(List list){
		Long p=(long)0;
		if(list!=null){
			System.out.println("============list not null");
			int i;
			for (i = 0; i < list.size(); i++) {
				if (list.get(i) != null && !list.get(i).equals("")) {
					p=p+Long.valueOf(list.get(i).toString());
					System.out.println("p==============="+p);
				}
			}
		}
		return p;
	}
	
	public TinfSet getSet() {
		if (set == null)
			set = (TinfSet) get("set");
		return set;
	}

	public List getPowerlist() {
		return this.powerlist;
	}
	
	public void setPowerlist(List powerlist){
		this.powerlist = powerlist;
	}

}
