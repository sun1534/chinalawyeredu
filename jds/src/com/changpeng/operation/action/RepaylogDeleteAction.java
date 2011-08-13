package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;




/**
 *
 * <p>功能： 删除还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogDeleteAction extends AbstractAction {

	private ToprRepaylog repaylog;

	public RepaylogDeleteAction() {
           rights="opr5,8";
           nextpage="repaylogList.action?creditcardid="+repaylog.getToprCreditcard().getCreditcardid()+"&pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
		
		//getSession().createSQLQuery("update topr_creditcard set refee=refee-'"+repaylog.getFee()+"' where creditcardid='"+repaylog.getToprCreditcard().getCreditcardid()+"'").executeUpdate();
		
		long credicardid=repaylog.getToprCreditcard().getCreditcardid();
		
		ToprRepaylog repaylog = (ToprRepaylog) get("repaylog");
                getSession().delete(repaylog);
                
                getSession().flush();
              //更新还款值
        		Object o = getSession().createSQLQuery("select sum(fee) from Topr_Repaylog where creditcardid="+credicardid).list().get(0);
        		if(o==null) o="0";
        		getSession().createSQLQuery("update topr_creditcard set refee='"+o+"' where creditcardid='"+credicardid+"'").executeUpdate();
        		
        		
                message="删除成功！";
		return SUCCESS;
	}

	public ToprRepaylog getRepaylog() {
         if (repaylog==null)
            repaylog = (ToprRepaylog) get("repaylog");
          return repaylog;
	}
}
