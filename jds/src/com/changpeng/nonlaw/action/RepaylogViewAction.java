package com.changpeng.nonlaw.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogViewAction extends AbstractAction {
	private TnlwRepaylog repaylog;
        private long repaylogid;
	public RepaylogViewAction() {
          rights="nlw5,1";
	   repaylog = new TnlwRepaylog();
	}

	public String go() throws HibernateException {
           nextpage="repaylogList.action?pagenumber="+pagenumber;
           repaylog=(TnlwRepaylog)getSession().get(TnlwRepaylog.class,repaylogid);
           if(repaylog==null){
             message="未找到此还款记录";
             return "message";
           }
           set("repaylog", repaylog);
           return SUCCESS;
	}
	public TnlwRepaylog getRepaylog() {
		return repaylog;
	}
        public void setRepaylogid(long repaylogid) {

          this.repaylogid = repaylogid;
        }
        public long getRepaylogid() {
          return this.repaylogid;
        }

}
