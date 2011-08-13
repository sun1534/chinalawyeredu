package com.sxit.member.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
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
 * 日期： 2008-05-16
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class StudyexpDeleteAction extends AbstractAction {

	private TmemStudyexp studyexp;

	public StudyexpDeleteAction() {
		rights = "mem4,8";
		nextpage = "studyexpList.action?pagenumber=" + pagenumber;
	}

	public String go() throws HibernateException {
		TmemStudyexp studyexp = (TmemStudyexp)getSession().get(TmemStudyexp.class,studyexpid);
		getSession().delete(studyexp);
		message = "删除成功！";
		nextpage = "resumeView.action?resumeid=" + resumeid;
		return SUCCESS;
	}

	public TmemStudyexp getStudyexp() {
		if (studyexp == null)
			studyexp = (TmemStudyexp) get("studyexp");
		return studyexp;
	}

	private int resumeid;

	public void setResumeid(int resumeid) {
		this.resumeid = resumeid;
	}

	public int getResumeid() {
		return this.resumeid;
	}

	private int studyexpid;

	public void setStudyexpid(int studyexpid) {

		this.studyexpid = studyexpid;
	}

	public int getStudyexpid() {
		return this.studyexpid;
	}
}
