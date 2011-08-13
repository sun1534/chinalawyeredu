package com.changpeng.operation.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
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

public class CreditlogCreateAction extends AbstractAction {

	private ToprCreditlog creditlog;

	private long credittaskid;
	private List tasklist;

	public long getCredittaskid() {
		return credittaskid;
	}

	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

	public CreditlogCreateAction() {
           rights="opr4,2";
		creditlog = new ToprCreditlog();
	}

	public String go() throws HibernateException {
		creditlog.setUserid(curuser.getUserid());
		creditlog.setCreatetime(new java.util.Date());
		long credittaskid=creditlog.getToprCredittask().getCredittaskid();
		ToprCredittask toprCredittask=(ToprCredittask)getSession().get(ToprCredittask.class, credittaskid);
	
		creditlog.setToprCreditcard(toprCredittask.getToprCreditcard());
		getSession().save(creditlog);
		
		set("creditlog", creditlog);
                nextpage="creditlogList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public ToprCreditlog getCreditlog() {
		return creditlog;
	}
    public String input() throws Exception {
    	creditlog.setLogtime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        	tasklist=getSession().createQuery(" from ToprCredittask where userid="+curuser.getUserid()+" order by creditcardid desc").list();
        
        	return "input";
    }

		public List getTasklist() {
			return tasklist;
		}

}
