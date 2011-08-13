package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import com.sxit.system.model.TsysModule;
import java.util.List;
import org.hibernate.Query;


/**
 *
 * <p>功能： 创建业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessCreateAction extends AbstractAction {

	private TwflBusiness business;
        private List<TsysModule> modulelist;

	public BusinessCreateAction() {
           rights="wfl2,2";
		business = new TwflBusiness();
	}

	public String go() throws HibernateException {
		//business.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		getSession().save(business);
		set("business", business);
                nextpage="businessList.action";
                message="保存成功！";
		return SUCCESS;
	}
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysModule as module order by module.moduleorder";
                Query query = getSession().createQuery(queryName);
                return query;
        }
	public TwflBusiness getBusiness() {
		return business;
	}
        public List getModulelist() {
          return modulelist;
        }
        public String input() throws Exception {
            modulelist=getQuery().list();
            return "input";
    }
}
