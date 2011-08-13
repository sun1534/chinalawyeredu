package com.changpeng.nonlaw.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-06-18</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawViewAction extends AbstractAction {
	private TnlwNonlaw nonlaw;
        private long nonlawid;
	public NonlawViewAction() {
          rights="nlw1,1";
	   nonlaw = new TnlwNonlaw();
	}

	public String go() throws HibernateException {
           nextpage="nonlawList.action?pagenumber="+pagenumber;
           nonlaw=(TnlwNonlaw)getSession().get(TnlwNonlaw.class,nonlawid);
           if(nonlaw==null){
             message="未找到此催收记录";
             return "message";
           }
           set("nonlaw", nonlaw);
           return SUCCESS;
	}
	public TnlwNonlaw getNonlaw() {
		return nonlaw;
	}
        public void setNonlawid(long nonlawid) {

          this.nonlawid = nonlawid;
        }
        public long getNonlawid() {
          return this.nonlawid;
        }

}
