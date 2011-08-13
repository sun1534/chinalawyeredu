package com.sxit.member.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看简历录入</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-05-16</p>
 * @版本： V1.0
 * @修改：
 */

public class SkillViewAction extends AbstractAction {
	private TmemSkill skill;
    private int skillid;
	public SkillViewAction() {
          rights="mem3,1";
	   skill = new TmemSkill();
	}

	public String go() throws HibernateException {
           nextpage="skillList.action?pagenumber="+pagenumber;
           skill=(TmemSkill)getSession().get(TmemSkill.class,Integer.valueOf(skillid));
           if(skill==null){
             message="未找到此用户";
             return "message";
           }
           set("skill", skill);
           return SUCCESS;
	}
	public TmemSkill getSkill() {
		return skill;
	}
        public void setSkillid(int skillid) {

          this.skillid = skillid;
        }
        public int getSkillid() {
          return this.skillid;
        }

}
