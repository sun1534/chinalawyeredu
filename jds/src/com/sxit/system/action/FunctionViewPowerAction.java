//$Id: FunctionCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * @author zrb
 */
public class FunctionViewPowerAction extends AbstractAction {
	private TsysFunction function;
        private String functionid;
	public FunctionViewPowerAction() {
          rights="sys5,1";
		function = new TsysFunction();
	}

	public String go() throws HibernateException {

           nextpage="functionList.action?pagenumber="+pagenumber;

           function=(TsysFunction)getSession().get(TsysFunction.class,functionid);
           if(function==null){
             message="未找到此用户";
             return "message";
           }
           set("function",function);
//           function.getTsysFunctionRoles();
//           function.getTsysFunctionUsers();
           return SUCCESS;
	}
	public TsysFunction getFunction() {
		return function;
	}
        public void setFunctionid(String functionid) {

          this.functionid = functionid;
        }
        public String getFunctionid() {
          return this.functionid;
        }
}
