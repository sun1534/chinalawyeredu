package com.changpeng.nonlaw.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;




/**
 *
 * <p>功能： 删除催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawDeleteAction extends AbstractAction {

	private TnlwNonlaw nonlaw;

	public NonlawDeleteAction() {
           rights="nlw1,8";
           nextpage="nonlawList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TnlwNonlaw nonlaw = (TnlwNonlaw) get("nonlaw");
                getSession().delete(nonlaw);
                message="删除成功！";
		return SUCCESS;
	}

	public TnlwNonlaw getNonlaw() {
         if (nonlaw==null)
            nonlaw = (TnlwNonlaw) get("nonlaw");
          return nonlaw;
	}
}
