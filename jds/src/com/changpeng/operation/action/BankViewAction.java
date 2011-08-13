package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看委托银行</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class BankViewAction extends AbstractAction {
	private ToprBank bank;
        private long bankid;
	public BankViewAction() {
          rights="opr1,1";
	   bank = new ToprBank();
	}

	public String go() throws HibernateException {
           nextpage="bankList.action?pagenumber="+pagenumber;
           bank=(ToprBank)getSession().get(ToprBank.class,bankid);
           if(bank==null){
             message="未找到此委托银行";
             return "message";
           }
           set("bank", bank);
           return SUCCESS;
	}
	public ToprBank getBank() {
		return bank;
	}
        public void setBankid(long bankid) {

          this.bankid = bankid;
        }
        public long getBankid() {
          return this.bankid;
        }

}
