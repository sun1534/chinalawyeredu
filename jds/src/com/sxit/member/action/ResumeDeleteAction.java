package com.sxit.member.action;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

/**
 * 
 * <p>
 * 功能： 删除简历录入
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

public class ResumeDeleteAction extends AbstractAction {

	private TmemResume resume;

	public ResumeDeleteAction() {
		rights = "mem2,8";
		nextpage = "resumeList.action?pagenumber=" + pagenumber;
	}

	public String go() throws HibernateException {
		TmemResume resume = (TmemResume) get("resume");
		if (isDel(resume.getResumeid()) == false) {
			message = "删除失败，请先删除子数据！";
			nextpage = "resumeView.action?resumeid="+resume.getResumeid();
		} else {
			getSession().delete(resume);
			message = "删除成功！";
		}

		return SUCCESS;
	}

	public TmemResume getResume() {
		if (resume == null)
			resume = (TmemResume) get("resume");
		return resume;
	}

	private List skilllist;

	private Query getQuerySkill(long resumeid) throws HibernateException {
		String queryName;
		queryName = "from TmemSkill as skill where tmemResume.resumeid="
				+ resumeid + " order by skill.skillid desc";
		Query query = getSession().createQuery(queryName);
		return query;
	}

	private List studyexplist;

	private Query getQueryStudyexp(long resumeid) throws HibernateException {
		String queryName;
		queryName = "from TmemStudyexp as studyexp where tmemResume.resumeid="
				+ resumeid + " order by studyexp.studyexpid desc";

		Query query = getSession().createQuery(queryName);
		return query;
	}

	private List workexplist;

	private Query getQueryWorkexp(long resumeid) throws HibernateException {
		String queryName;
		queryName = "from TmemWorkexp as workexp where tmemResume.resumeid="
				+ resumeid + "  order by workexp.workexpid desc";
		Query query = getSession().createQuery(queryName);
		return query;
	}

	// 判断是否能删除
	private boolean isDel(long resumeid) throws HibernateException {
		skilllist = getQuerySkill(resumeid).setCacheable(true).list();
		studyexplist = getQueryStudyexp(resumeid).setCacheable(true).list();
		workexplist = getQueryWorkexp(resumeid).setCacheable(true).list();

		if ((skilllist != null && skilllist.size() > 0)
				|| (studyexplist != null && studyexplist.size() > 0)
				|| (workexplist != null && workexplist.size() > 0)) {
			return false;
		} else {
			return true;
		}
	}
}
