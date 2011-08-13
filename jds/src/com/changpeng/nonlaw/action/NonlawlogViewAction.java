package com.changpeng.nonlaw.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.operation.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawlogViewAction extends AbstractAction {
	private TnlwNonlawlog nonlawlog;
	private long nonlawid;

	public NonlawlogViewAction() {
          rights="nlw3,1";
          nonlawlog = new TnlwNonlawlog();
	}

	public String go() throws HibernateException {
           nextpage="nonlawlogList.action?pagenumber="+pagenumber;
           nonlawlog=(TnlwNonlawlog)getSession().get(TnlwNonlawlog.class,nonlawid);
           if(nonlawlog==null){
             message="未找到此催收日志";
             return "message";
           }
           set("nonlawlog", nonlawlog);
           return SUCCESS;
	}

	public TnlwNonlawlog getNonlawlog() {
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
	

}
