package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;


/**
 *
 * <p>功能： 创建还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogCreateAction extends AbstractAction {
	private long nonlawid;
	private TnlwRepaylog repaylog;

	
	public long getNonlawid() {
		return nonlawid;
	}

	public void setNonlawid(long nonlawid) {
		this.nonlawid = nonlawid;
	}

	public RepaylogCreateAction() {
           rights="nlw5,2";
		repaylog = new TnlwRepaylog();
	}

	public String go() throws HibernateException {
		repaylog.setCreatetime(new java.util.Date());
		//repaylog.setUserid(curuser.getUserid());
		
		TnlwNonlaw tnlwNonlaw=(TnlwNonlaw)getSession().get(TnlwNonlaw.class, nonlawid);
		repaylog.setTnlwNonlaw(tnlwNonlaw);
		

		//获取当前催收任务员
		java.util.List list = getSession().createSQLQuery("select userid from tnlw_nonlawtask where taskstat=0 and nonlawid="+nonlawid).list();
		if(list!=null&&list.size()>0){
			repaylog.setUserid(Long.parseLong(list.get(0)+""));
		}
		
		getSession().save(repaylog);
		
		getSession().flush();
		
		//更新还款值
		Object o = getSession().createSQLQuery("select sum(fee) from Tnlw_Repaylog where substr(repaytime,0,7)=to_char(sysdate,'yyyy-mm') and nonlawid="+nonlawid).list().get(0);
		if(o==null) o="0";
		getSession().createSQLQuery("update tnlw_nonlaw set refee='"+o+"',repaystatus="+repaylog.getRepaystatus()+" where nonlawid='"+nonlawid+"'").executeUpdate();
		
		if(repaylog.getRepaystatus()==2) //全清记录中的逾期金额应该为零
			getSession().createSQLQuery("update tnlw_nonlaw set curoverfee=0,curaccrualfee=0 where nonlawid='"+nonlawid+"'").executeUpdate();
		
		set("repaylog", repaylog);
                nextpage="repaylogList.action?nonlawid="+nonlawid;
                message="保存成功！";
		return SUCCESS;
	}

	public TnlwRepaylog getRepaylog() {
		return repaylog;
	}
        public String input() throws Exception {
            return "input";
    }
}
