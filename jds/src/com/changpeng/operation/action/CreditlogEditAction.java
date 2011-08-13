package com.changpeng.operation.action;


import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
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

public class CreditlogEditAction extends AbstractAction {

	private ToprCreditlog creditlog;
	private long creditcardid;
	private List tasklist;
	public long getCreditcardid() {
		return creditcardid;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

	public CreditlogEditAction() {
          rights="opr4,4";
	}

	public String go() throws HibernateException {
                getSession().update(creditlog);
		set("creditlog", creditlog);
                nextpage="creditlogList.action?credittaskid="+creditlog.getToprCredittask().getCredittaskid();
                message="保存成功！";
		return SUCCESS;
	}

	public ToprCreditlog getCreditlog() {
         if (creditlog==null)
            creditlog = (ToprCreditlog) get("creditlog");
          return creditlog;
	}

        public String input() throws Exception {
        	tasklist=getSession().createQuery(" from ToprCredittask where userid="+curuser.getUserid()+" order by creditcardid desc").list();
          return "input";
        }
        public List getTasklist() {
			return tasklist;
		}

}
