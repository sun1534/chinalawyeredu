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
public class FunctionEditAction extends AbstractAction {

	private TsysFunction function;
        private List<TsysModule> modulelist;

	public FunctionEditAction() {
          rights="sys5,4";
	}

	public String go() throws HibernateException {
                getSession().update(function);
		set("function", function);
                nextpage="functionList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysFunction getFunction() {
         if (function==null)
            function = (TsysFunction) get("function");
          return function;
	}
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysModule as module order by module.moduleorder";
                Query query = getSession().createQuery(queryName);
                return query;
        }
        public List getModulelist() {
          return modulelist;
        }
        public String input() throws Exception {
          modulelist=getQuery().list();
          return "input";
        }


}
