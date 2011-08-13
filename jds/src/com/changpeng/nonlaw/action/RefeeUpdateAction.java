package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;

/**
 * 更新已还金额
 * @author sinhoo
 * Aug 5, 2009
 */

public class RefeeUpdateAction extends AbstractAction {
	private long nonlawid;
	
	private TnlwNonlaw nonlaw;

	public RefeeUpdateAction() {
          rights="nlw1,4";
	}

	public String go() throws HibernateException {
                getSession().update(nonlaw);
		set("nonlaw", nonlaw);
                nextpage="nonlawList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TnlwNonlaw getNonlaw() {
         if (nonlaw==null)
            nonlaw = (TnlwNonlaw) get("nonlaw");
          return nonlaw;
	}

        public String input() throws Exception {
        	nonlaw=(TnlwNonlaw)getSession().get(TnlwNonlaw.class, nonlawid);
        	set("nonlaw",nonlaw);
        	return "input";
        }

		public long getNonlawid() {
			return nonlawid;
		}

		public void setNonlawid(long nonlawid) {
			this.nonlawid = nonlawid;
		}

}
