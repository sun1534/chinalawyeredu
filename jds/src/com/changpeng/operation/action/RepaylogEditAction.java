package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 编辑还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogEditAction extends AbstractAction {

	private ToprRepaylog repaylog;

	public RepaylogEditAction() {
          rights="opr5,4";
	}

	public String go() throws HibernateException {
                getSession().update(repaylog);
                long credicardid=repaylog.getToprCreditcard().getCreditcardid();
                
                getSession().flush();
                //更新还款值
          		Object o = getSession().createSQLQuery("select sum(fee) from Topr_Repaylog where creditcardid="+credicardid).list().get(0);
          		if(o==null) o="0";
          		getSession().createSQLQuery("update topr_creditcard set refee='"+o+"' where creditcardid='"+credicardid+"'").executeUpdate();
          		
		set("repaylog", repaylog);
                nextpage="repaylogList.action?creditcardid="+repaylog.getToprCreditcard().getCreditcardid();
                message="保存成功！";
		return SUCCESS;
	}

	public ToprRepaylog getRepaylog() {
         if (repaylog==null)
            repaylog = (ToprRepaylog) get("repaylog");
          return repaylog;
	}

        public String input() throws Exception {
          return "input";
        }


}
