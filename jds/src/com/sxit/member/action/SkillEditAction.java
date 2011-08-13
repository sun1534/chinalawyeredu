package com.sxit.member.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

/**
 * 
 * <p>
 * 功能： 编辑简历录入
 * </p>
 * <p>
 * 作者： 雷华
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-05-16
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class SkillEditAction extends AbstractAction {

	private TmemSkill skill;

	public SkillEditAction() {
		rights = "mem3,4";
		skill = new TmemSkill();
	}

	public String go() throws HibernateException {
		getSession().update(skill);
		set("skill", skill);
		nextpage = "resumeView.action?resumeid=" + resumeid;
		message = "保存成功！";
		return SUCCESS;
	}

	public TmemSkill getSkill() {
		if (skill == null)
			skill = (TmemSkill) get("skill");
		return skill;
	}

	public String input() throws Exception {
		skill = (TmemSkill)getSession().get(TmemSkill.class,skillid);
		return "input";
	}

	private int resumeid;

	public void setResumeid(int resumeid) {
		this.resumeid = resumeid;
	}

	public int getResumeid() {
		return this.resumeid;
	}

	private int skillid;
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}

	public int getSkillid() {
		return this.skillid;
	}	
	
}
