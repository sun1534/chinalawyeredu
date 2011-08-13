//$Id: ModuleCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
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
public class ModuleViewAction extends AbstractAction {
	private TsysModule module;
        private String moduleid;
	public ModuleViewAction() {
          rights="sys6,1";
	  module = new TsysModule();
	}

	public String go() throws HibernateException {

           nextpage="moduleList.action?pagenumber="+pagenumber;

           module=(TsysModule)getSession().get(TsysModule.class,moduleid);
           if(module==null){
             message="未找到此用户";
             return "message";
           }
           set("module",module);
           return SUCCESS;
	}
	public TsysModule getModule() {
		return module;
	}
        public void setModuleid(String moduleid) {

          this.moduleid = moduleid;
        }
        public String getModuleid() {
          return this.moduleid;
        }
}
