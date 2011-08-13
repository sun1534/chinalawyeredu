package com.sxit.member.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

/**
 * 
 * <p>
 * 功能： 创建简历录入
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

public class WorkexpCreateAction extends AbstractAction {

	private TmemWorkexp workexp;
	
	private int resumeid;
	public void setResumeid(int resumeid){
		this.resumeid = resumeid;
	}
	public int  getResumeid(){
		return this.resumeid;
	}
	
	public WorkexpCreateAction() {
		rights = "mem5,2";
		workexp = new TmemWorkexp();
	}

	public String go() throws HibernateException {
		TmemResume tr = (TmemResume)getSession().get(TmemResume.class,resumeid);
		workexp.setTmemResume(tr);
		
		getSession().save(workexp);
		set("workexp", workexp);
		nextpage = "resumeView.action?resumeid="+resumeid;
		message = "保存成功！";
		return SUCCESS;
	}

	public TmemWorkexp getWorkexp() {
		return workexp;
	}

	public String input() throws Exception {
		return "input";
	}
}
