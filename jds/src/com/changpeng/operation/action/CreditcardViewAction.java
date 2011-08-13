package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司：长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditcardViewAction extends AbstractAction {
	private ToprCreditcard creditcard;
        private long creditcardid;
	public CreditcardViewAction() {
          rights="opr2,1";
	   creditcard = new ToprCreditcard();
	}

	public String go() throws HibernateException {
           nextpage="creditcardList.action?pagenumber="+pagenumber;
           creditcard=(ToprCreditcard)getSession().get(ToprCreditcard.class,creditcardid);
           if(creditcard==null){
             message="未找到此催收记录";
             return "message";
           }
           set("creditcard", creditcard);
           return SUCCESS;
	}
	public ToprCreditcard getCreditcard() {
		return creditcard;
	}
        public void setCreditcardid(long creditcardid) {

          this.creditcardid = creditcardid;
        }
        public long getCreditcardid() {
          return this.creditcardid;
        }

}
