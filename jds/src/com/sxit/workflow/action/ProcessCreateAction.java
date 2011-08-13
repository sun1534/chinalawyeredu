package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import java.util.List;
import org.hibernate.Query;


/**
 *
 * <p>功能： 创建流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessCreateAction extends AbstractAction {

	private TwflProcess process;
        private List<TwflBusiness> businesslist;

	public ProcessCreateAction() {
           rights="wfl1,2";
	   process = new TwflProcess();
	}

	public String go() throws HibernateException {
                process.setAuthor(curuser);
		process.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
                process.setXposition(200);
                process.setYposition(40);
                process.setNewnodeid(1);
		getSession().save(process);
		set("process", process);
                commit();
                nextpage="processList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TwflProcess getProcess() {
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
