package com.changpeng.nonlaw.action;


import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 编辑催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawlogEditAction extends AbstractAction {

	private TnlwNonlawlog nonlawlog;
	private long nonlawid;

	private List tasklist;


	public NonlawlogEditAction() {
          rights="nlw3,4";
	}

	public String go() throws HibernateException {
                getSession().update(nonlawlog);
		set("nonlawlog", nonlawlog);
                nextpage="nonlawlogList.action?nonlawtaskid="+nonlawlog.getTnlwNonlawtask().getNonlawtaskid();
                message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	tasklist=getSession().createQuery(" from TnlwNonlawtask where userid="+curuser.getUserid()+" order by nonlawid desc").list();
            return "input";
        }
        public List getTasklist() {
			return tasklist;
		}

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

		public void setTasklist(List tasklist) {
			this.tasklist = tasklist;
		}

}
