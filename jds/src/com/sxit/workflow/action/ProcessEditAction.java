package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;



import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 编辑流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessEditAction extends AbstractAction {

	private TwflProcess process;
        private List<TwflBusiness> businesslist;
	public ProcessEditAction() {
          rights="wfl1,4";
	}

	public String go() throws HibernateException {
                getSession().update(process);
		set("process", process);
                nextpage="processList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TwflProcess getProcess() {
         if (process==null)
            process = (TwflProcess) get("process");
          return process;
	}

        public String input() throws Exception {
          businesslist = getQuery().list();
          return "input";
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TwflBusiness as business order by business.businessid desc";
                Query query = getSession().createQuery(queryName);
                return query;
        }
        public List getBusinesslist() {
          return businesslist;
        }

}
