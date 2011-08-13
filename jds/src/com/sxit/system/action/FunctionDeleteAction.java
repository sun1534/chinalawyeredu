//$Id: FunctionCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;




/**
 *
 * @author zrb
 */
public class FunctionDeleteAction extends AbstractAction {

	private TsysFunction function;

	public FunctionDeleteAction() {
          rights="sys5,4";
	}
	public String go() throws HibernateException {
                TsysFunction function = (TsysFunction) get("function");
                getSession().delete(function);
                message="删除成功！";
                nextpage="functionList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysFunction getFunction() {
         if (function==null)
            function = (TsysFunction) get("function");
          return function;
	}
}
