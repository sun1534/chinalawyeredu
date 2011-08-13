package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessViewAction extends AbstractAction {
	private TwflBusiness business;
        private int businessid;
	public BusinessViewAction() {
          rights="wfl2,1";
	   business = new TwflBusiness();
	}

	public String go() throws HibernateException {
           nextpage="businessList.action?pagenumber="+pagenumber;
           business=(TwflBusiness)getSession().get(TwflBusiness.class,Integer.valueOf(businessid));
           if(business==null){
             message="未找到此用户";
             return "message";
           }
           set("business", business);
           return SUCCESS;
	}
	public TwflBusiness getBusiness() {
		return business;
	}
        public void setBusinessid(int businessid) {

          this.businessid = businessid;
        }
        public int getBusinessid() {
          return this.businessid;
        }

}
