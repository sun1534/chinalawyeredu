package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;




/**
 *
 * <p>功能： 删除业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessDeleteAction extends AbstractAction {

	private TwflBusiness business;

	public BusinessDeleteAction() {
           rights="wfl2,8";
	}
	public String go() throws HibernateException {
                TwflBusiness business = (TwflBusiness) get("business");
                getSession().delete(business);
                message="删除成功！";
                nextpage="businessList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TwflBusiness getBusiness() {
         if (business==null)
            business = (TwflBusiness) get("business");
          return business;
	}
}
