package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 编辑催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditcardEditAction extends AbstractAction {

	private ToprCreditcard creditcard;

	public CreditcardEditAction() {
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
          return "input";
        }


}
