package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
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

public class CreditlogViewAction extends AbstractAction {
	private ToprCreditlog creditlog;
        private long creditlogid;
	public CreditlogViewAction() {
          rights="opr4,1";
	   creditlog = new ToprCreditlog();
	}

	public String go() throws HibernateException {
           nextpage="creditlogList.action?pagenumber="+pagenumber;
           creditlog=(ToprCreditlog)getSession().get(ToprCreditlog.class,creditlogid);
           if(creditlog==null){
             message="未找到此催收日志";
             return "message";
           }
           set("creditlog", creditlog);
           return SUCCESS;
	}
	public ToprCreditlog getCreditlog() {
		return creditlog;
	}
        public void setCreditlogid(long creditlogid) {

          this.creditlogid = creditlogid;
        }
        public long getCreditlogid() {
          return this.creditlogid;
        }

}
