//$Id: FunctionCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author zrb
 */
public class FunctionCreateAction extends AbstractAction {

	private TsysFunction function;
        private List<TsysModule> modulelist;

	public FunctionCreateAction() {
          rights="sys5,2";
		function = new TsysFunction();
	}

	public String go() throws HibernateException {
		getSession().save(function);
		set("function", function);
                nextpage="functionList.action";
                message="保存成功！";
		return SUCCESS;
	}
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysModule as module order by module.moduleorder";
                Query query = getSession().createQuery(queryName);
                return query;
        }
	public TsysFunction getFunction() {
		return function;
	}
        public List getModulelist() {
          return modulelist;
        }
        //选择框列表
        public String input() throws Exception {
            modulelist=getQuery().list();
            return "input";
    }
}
