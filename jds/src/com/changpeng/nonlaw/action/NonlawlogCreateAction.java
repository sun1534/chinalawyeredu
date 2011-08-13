package com.changpeng.nonlaw.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.nonlaw.model.TnlwNonlawtask;
import com.changpeng.operation.model.*;


/**
 *
 * <p>功能： 创建催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawlogCreateAction extends AbstractAction {

	private TnlwNonlawlog nonlawlog;
	private long nonlawtaskid;
	private List tasklist;


	public long getNonlawtaskid() {
		return nonlawtaskid;
	}


	public void setNonlawtaskid(long nonlawtaskid) {
		this.nonlawtaskid = nonlawtaskid;
	}


	public TnlwNonlawlog getNonlawlog() {
		return nonlawlog;
	}


	public void setNonlawlog(TnlwNonlawlog nonlawlog) {
		this.nonlawlog = nonlawlog;
	}



	public void setTasklist(List tasklist) {
		this.tasklist = tasklist;
	}

	public NonlawlogCreateAction() {
           rights="nlw3,2";
           nonlawlog = new TnlwNonlawlog();
	}

	public String go() throws HibernateException {
		nonlawlog.setUserid(curuser.getUserid());
		nonlawlog.setCreatetime(new java.util.Date());
		
		long nonlawtaskid=nonlawlog.getTnlwNonlawtask().getNonlawtaskid();
		TnlwNonlawtask tnlwNonlawtask=(TnlwNonlawtask)getSession().get(TnlwNonlawtask.class, nonlawtaskid);
		nonlawlog.setTnlwNonlaw(tnlwNonlawtask.getTnlwNonlaw());
		
		getSession().save(nonlawlog);
		set("creditlog", nonlawlog);
                nextpage="nonlawlogList.action?nonlawtaskid="+nonlawlog.getTnlwNonlawtask().getNonlawtaskid();
                message="保存成功！";
		return SUCCESS;
	}

    public String input() throws Exception {
    	nonlawlog.setLogtime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        	tasklist=getSession().createQuery(" from TnlwNonlawtask where userid="+curuser.getUserid()+" order by nonlawid desc").list();
            return "input";
    }

		public List getTasklist() {
			return tasklist;
		}
}
