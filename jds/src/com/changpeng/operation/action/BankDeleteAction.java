package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 删除委托银行</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class BankDeleteAction extends AbstractAction {

	private ToprBank bank;

	public BankDeleteAction() {
           rights="opr1,8";
           nextpage="bankList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                ToprBank bank = (ToprBank) get("bank");
                getSession().delete(bank);
                message="删除成功！";
		return SUCCESS;
	}

	public ToprBank getBank() {
         if (bank==null)
            bank = (ToprBank) get("bank");
          return bank;
	}
}
