package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;




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

	private TnlwRepaylog repaylog;

	public RepaylogDeleteAction() {
           rights="nlw5,8";
           nextpage="repaylogList.action?nonlawid="+repaylog.getTnlwNonlaw().getNonlawid()+"&pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
		
		 long nonlawid=repaylog.getTnlwNonlaw().getNonlawid();
		
                TnlwRepaylog repaylog = (TnlwRepaylog) get("repaylog");
                getSession().delete(repaylog);
                
                getSession().flush();
              //更新还款值
        		Object o = getSession().createSQLQuery("select sum(fee) from Tnlw_Repaylog where substr(repaytime,0,7)=to_char(sysdate,'yyyy-mm') and nonlawid="+nonlawid).list().get(0);
        		getSession().createSQLQuery("update tnlw_nonlaw set refee='"+o+"' where nonlawid='"+nonlawid+"'").executeUpdate();
        		
        		
                message="删除成功！";
		return SUCCESS;
	}

	public TnlwRepaylog getRepaylog() {
         if (repaylog==null)
            repaylog = (TnlwRepaylog) get("repaylog");
          return repaylog;
	}
}
