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

public class WorkexpDeleteAction extends AbstractAction {

	private TmemWorkexp workexp;

	public WorkexpDeleteAction() {
		rights = "mem5,8";
		nextpage = "workexpList.action?pagenumber=" + pagenumber;
	}

	public String go() throws HibernateException {
		TmemWorkexp workexp = (TmemWorkexp)getSession().get(TmemWorkexp.class,workexpid);
		getSession().delete(workexp);
		//commit();
		message = "删除成功！";
		nextpage = "resumeView.action?resumeid=" + resumeid;
		return SUCCESS;
	}

	public TmemWorkexp getWorkexp() {
		if (workexp == null)
			workexp = (TmemWorkexp) get("workexp");
		return workexp;
	}

	private int resumeid;

	public void setResumeid(int resumeid) {
		this.resumeid = resumeid;
	}

	public int getResumeid() {
		return this.resumeid;
	}

	private int workexpid;

	public void setWorkexpid(int workexpid) {

		this.workexpid = workexpid;
	}

	public int getWorkexpid() {
		return this.workexpid;
	}
}
