package com.changpeng.operation.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;

import com.changpeng.operation.model.*;


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
	private long creditcardid;
	private ToprRepaylog repaylog;
	

	public long getCreditcardid() {
		return creditcardid;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

	public RepaylogCreateAction() {
           rights="opr5,2";
		repaylog = new ToprRepaylog();
	}

	public String go() throws HibernateException {
		repaylog.setCreatetime(new java.util.Date());
		//repaylog.setUserid(curuser.getUserid());
		
		ToprCreditcard toprCreditcard=(ToprCreditcard)getSession().get(ToprCreditcard.class, creditcardid);
		repaylog.setToprCreditcard(toprCreditcard);
		
		
		//获取当前催收任务员
		java.util.List list = getSession().createSQLQuery("select userid from topr_credittask where taskstat=0 and creditcardid="+creditcardid).list();
		if(list!=null&&list.size()>0){
			repaylog.setUserid(Long.parseLong(list.get(0)+""));
		}
		
		
		
		getSession().save(repaylog);
		
		getSession().flush();
		
		//更新还款值
		Object o = getSession().createSQLQuery("select sum(fee) from Topr_Repaylog where creditcardid="+creditcardid).list().get(0);
		if(o==null) o="0";
		getSession().createSQLQuery("update topr_creditcard set refee='"+o+"',repaystatus="+repaylog.getRepaystatus()+" where creditcardid='"+creditcardid+"'").executeUpdate();
		
		
		set("repaylog", repaylog);
                nextpage="repaylogList.action?creditcardid="+creditcardid;
                message="保存成功！";
		return SUCCESS;
	}

	public ToprRepaylog getRepaylog() {
		return repaylog;
	}
        public String input() throws Exception {
            return "input";
    }
}
