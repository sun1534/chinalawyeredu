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
 * 日期： 2008-05-13
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class ResumeEditAction extends AbstractAction {

	private TmemResume resume;

	public ResumeEditAction() {
		rights = "mem2,4";
	}

	public String go() throws HibernateException {
		getSession().update(resume);
		set("resume", resume);

		nextpage = "resumeView.action?resumeid=" + resume.getResumeid();
		message = "保存成功！";
		return SUCCESS;
	}

	public TmemResume getResume() {
		if (resume == null)
			resume = (TmemResume) get("resume");
		return resume;
	}

	public String input() throws Exception {
		return "input";
	}

}
