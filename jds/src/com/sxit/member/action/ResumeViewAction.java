package com.sxit.member.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 * 
 * <p>
 * 功能： 查看简历录入
 * </p>
 * <p>
 * 作者： 雷华
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-05-13
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class ResumeViewAction extends AbstractAction {
	private TmemResume resume;

	private int resumeid;

	public ResumeViewAction() {
		rights = "mem2,1";
		resume = new TmemResume();
	}

	public String go() throws HibernateException {
		nextpage = "memberList.action?pagenumber=" + pagenumber;
		resume = (TmemResume) getSession().get(TmemResume.class,resumeid);

		if (resume == null) {
			message = "未找到此用户";
			return "message";
		}
		set("resume", resume);

		skilllist = getQuerySkill().setCacheable(true).list();
		studyexplist = getQueryStudyexp().setCacheable(true).list();
		workexplist = getQueryWorkexp().setCacheable(true).list();
		return SUCCESS;
	}

	public TmemResume getResume() {
		return resume;
	}

	public void setResumeid(int resumeid) {
		this.resumeid = resumeid;
	}

	public int getResumeid() {
		return this.resumeid;
	}

	// skill
	private List skilllist;

	public List getSkilllist() {
		return skilllist;
	}

	private Query getQuerySkill() throws HibernateException {
		String queryName;
		queryName = "from TmemSkill as skill where tmemResume.resumeid="
				+ resumeid + " order by skill.skillid desc";
		Query query = getSession().createQuery(queryName);
		return query;
	}

	// studyexp
	private List studyexplist;

	public List getStudyexplist() {
		return studyexplist;
	}

	private Query getQueryStudyexp() throws HibernateException {
		String queryName;
		queryName = "from TmemStudyexp as studyexp where tmemResume.resumeid="
				+ resumeid + " order by studyexp.studyexpid desc";

		Query query = getSession().createQuery(queryName);
		return query;
	}

	// workexp
	private List workexplist;

	public List getWorkexplist() {
		return workexplist;
	}

	private Query getQueryWorkexp() throws HibernateException {
		String queryName;
		queryName = "from TmemWorkexp as workexp where tmemResume.resumeid="
				+ resumeid + "  order by workexp.workexpid desc";
		Query query = getSession().createQuery(queryName);
		return query;
	}

}
