package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
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

public class CreditlogDeleteAction extends AbstractAction {
	 private long credittaskid;
	private ToprCreditlog creditlog;

	public CreditlogDeleteAction() {
           rights="opr4,8";

	}
	public String go() throws HibernateException {
		
                ToprCreditlog creditlog = (ToprCreditlog) get("creditlog");
                nextpage="creditlogList.action?credittaskid="+creditlog.getToprCredittask().getCredittaskid();
                getSession().delete(creditlog);
                message="删除成功！";
		return SUCCESS;
	}

	public ToprCreditlog getCreditlog() {
         if (creditlog==null)
            creditlog = (ToprCreditlog) get("creditlog");
          return creditlog;
	}
	public long getCredittaskid() {
		return credittaskid;
	}
	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

}
