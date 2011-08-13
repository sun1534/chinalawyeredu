package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 删除催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawlogDeleteAction extends AbstractAction {
	private TnlwNonlawlog nonlawlog;
	private long nonlawid;

	public TnlwNonlawlog getNonlawlog() {
		if(nonlawlog==null)
			nonlawlog=(TnlwNonlawlog)get("nonlawlog");
		return nonlawlog;
	}
	public long getNonlawid() {
		return nonlawid;
	}
	public void setNonlawlog(TnlwNonlawlog nonlawlog) {
		this.nonlawlog = nonlawlog;
	}
	public void setNonlawid(long nonlawid) {
		this.nonlawid = nonlawid;
	}
	public NonlawlogDeleteAction() {
           rights="nlw3,8";

	}
	public String go() throws HibernateException {
		
		TnlwNonlawlog nonlawlog = (TnlwNonlawlog) get("nonlawlog");
		nextpage="nonlawlogList.action?nonlawtaskid="+nonlawlog.getTnlwNonlawtask().getNonlawtaskid();
                getSession().delete(nonlawlog);
                message="删除成功！";
		return SUCCESS;
	}


}
