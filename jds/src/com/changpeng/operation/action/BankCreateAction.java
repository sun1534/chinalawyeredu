package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;


/**
 *
 * <p>功能： 创建委托银行</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class BankCreateAction extends AbstractAction {

	private ToprBank bank;


	public BankCreateAction() {
           rights="opr1,2";
		bank = new ToprBank();
	}

	public String go() throws HibernateException {
		//bank.setCreatetime(new java.sql.Date(System.currentTimeMillis()));
		getSession().save(bank);
		set("bank", bank);
                nextpage="bankList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public ToprBank getBank() {
		return bank;
	}
        public String input() throws Exception {
            return "input";
    }
}
