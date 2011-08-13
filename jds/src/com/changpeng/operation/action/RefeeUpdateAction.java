package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;

/**
 * 更新还款金额
 * @author sinhoo
 * Aug 5, 2009
 */

public class RefeeUpdateAction extends AbstractAction {
	private long creditcardid;
	
	private ToprCreditcard creditcard;

	public RefeeUpdateAction() {
          rights="opr2,4";
	}

	public String go() throws HibernateException {
          getSession().update(creditcard);
		set("creditcard", creditcard);
                nextpage="creditcardList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public ToprCreditcard getCreditcard() {
         if (creditcard==null)
            creditcard = (ToprCreditcard) get("creditcard");
          return creditcard;
	}

    public String input() throws Exception {
    	creditcard=(ToprCreditcard)getSession().get(ToprCreditcard.class, creditcardid);
    	set("creditcard",creditcard);
    	return "input";
    }

	public long getCreditcardid() {
		return creditcardid;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

}
