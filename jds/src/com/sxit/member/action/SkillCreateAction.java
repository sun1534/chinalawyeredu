package com.sxit.member.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;


/**
 *
 * <p>功能： 创建简历录入</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-05-16</p>
 * @版本： V1.0
 * @修改：
 */

public class SkillCreateAction extends AbstractAction {

	private TmemSkill skill;
	
	private int resumeid;
	public void setResumeid(int resumeid){
		this.resumeid = resumeid;
	}
	public int  getResumeid(){
		return this.resumeid;
	}

	public SkillCreateAction() {
        rights="mem3,2";
		skill = new TmemSkill();
	}

	public String go() throws HibernateException {
		TmemResume tr = (TmemResume)getSession().get(TmemResume.class,resumeid);
		skill.setTmemResume(tr);
		
		getSession().save(skill);
		set("skill", skill);
        nextpage="resumeView.action?resumeid="+resumeid;
        message="保存成功！";
		return SUCCESS;
	}

	public TmemSkill getSkill() {
		return skill;
	}
	
	public String input() throws Exception {
            return "input";
    }
}
