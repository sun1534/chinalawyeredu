package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;




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
	
	private TnlwRepaylog repaylog;
	public RepaylogEditAction() {
          rights="nlw5,4";
	}

	public String go() throws HibernateException {
       getSession().update(repaylog);
       
       long nonlawid=repaylog.getTnlwNonlaw().getNonlawid();
    
       getSession().flush();
   	
   	//更新还款值  当月还款
   	Object o = getSession().createSQLQuery("select sum(fee) from Tnlw_Repaylog where substr(repaytime,0,7)=to_char(sysdate,'yyyy-mm') and nonlawid="+nonlawid).list().get(0);
   	if(o==null) o="0";
   	getSession().createSQLQuery("update tnlw_nonlaw set refee='"+o+"' where nonlawid='"+nonlawid+"'").executeUpdate();
   	
   	
		set("repaylog", repaylog);
                nextpage="repaylogList.action?nonlawid="+repaylog.getTnlwNonlaw().getNonlawid();
                message="保存成功！";
		return SUCCESS;
	}

	public TnlwRepaylog getRepaylog() {
         if (repaylog==null)
            repaylog = (TnlwRepaylog) get("repaylog");
          return repaylog;
	}

        public String input() throws Exception {
          return "input";
        }


}
